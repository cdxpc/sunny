package com.sunny.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.sunny.core.base.BaseMapper;
import com.sunny.core.persistence.Changer;
import com.sunny.core.persistence.DbEntity;

/**
 * 功能描述： 数据交换（上移下移）操作辅助类
 * 
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 *		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月21日
 * @since 1.0.0v
 */
public class DataChangeHelper<E> {
	
	private BaseMapper<E> mapper;
	
	private DataChangeHelper(BaseMapper<E> mapper) {
		this.mapper = mapper;
	}
	
	public static <E> DataChangeHelper<E> getInstance(BaseMapper<E> mapper) {
		return new DataChangeHelper<>(mapper);
	}
	
	public List<DbEntity> changeSort(E entity, Changer changer) {
		int index = changer.getIndex();
		// 确定是上移还是下移操作
		boolean up = changer.isUp();
		// 需要用到分页
		if(changer.isNeedPage()) {
			// 获取上移或下移操作中是否有跨页面的情况： prePage 跨上一页； nextPage 跨下一页；无  不跨页
			String action = changer.getAction();
			// 当前页
			int pageNum = changer.getPageNum();
			// 每页记录数
			int pageSize = changer.getPageSize();
			if(action != null && !"none".equals(action)) {
				// 需要跨其他页面交换数据
				return changeWithOtherPage(entity, action, index, pageNum, pageSize);
			} else {
				// 无需跨页交换数据
				return changeCurrentPage(entity, up, index, pageNum, pageSize);
			}
		} else {
			// 无需分页
			return changeCurrentPage(entity, up, index, null, null);
		}
	}
	
	private List<DbEntity> changeWithOtherPage(E entity, String action, int index, int pageNum, int pageSize) {
		int changeIndex = 0;
		int queryNum = 0;
		if("prePage".equals(action)) {
			// 涉及到和上一页最后一条数据间的交互
			changeIndex = pageSize - 1;
			queryNum = pageNum - 1;
		} else if("nextPage".equals(action)) {
			// 涉及到和下一页的第一条数据间的交互
			queryNum = pageNum + 1;
		}
		
		// 当前的数据
		List<E> list1 = mapper.selectByRowBounds(entity, new RowBounds((pageNum -1) * pageSize, pageSize));
		// 需要交换的数据
		List<E> list2 = mapper.selectByRowBounds(entity, new RowBounds((queryNum -1) * pageSize, pageSize));
		return doChange(index, changeIndex, list1, list2);
	}
	
	private List<DbEntity> changeCurrentPage(E entity, boolean up, int index, Integer pageNum, Integer pageSize) {
		int changeIndex;
		if(up) {
			changeIndex = index - 1;
		} else {
			changeIndex = index + 1;
		}
		List<E> list;
		if(pageNum == null) {
			list = mapper.select(entity);
		} else {
			list = mapper.selectByRowBounds(entity, new RowBounds((pageNum -1) * pageSize, pageSize));
		}
		return doChange(index, changeIndex, list, list);
	}

	private List<DbEntity> doChange(int index, Integer changeIndex, List<E> currents, List<E> changers) {
		List<DbEntity> datas = new ArrayList<>();
		DbEntity current = (DbEntity) currents.get(index);
		Integer indexSort = current.getSort();
		if(changers != null && changers.size() > 1) {
			DbEntity changer = (DbEntity) changers.get(changeIndex);
			Integer changedSort = changer.getSort();
			Date date = new Date();
			String updateBy = "";
			
			current.setSort(changedSort);
			current.setLastUpdateTime(date);
			current.setLastUpdateBy(updateBy);
			
			changer.setSort(indexSort);
			changer.setLastUpdateTime(date);
			changer.setLastUpdateBy(updateBy);
			
			datas.add(current);
			datas.add(changer);
		}
		return datas;
	}
	
}
