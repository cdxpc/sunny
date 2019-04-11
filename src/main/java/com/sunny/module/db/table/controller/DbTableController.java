package com.sunny.module.db.table.controller;

import com.sunny.core.ControllerHelper;
import com.sunny.core.ResponseJson;
import com.sunny.core.SpringContextHolder;
import com.sunny.core.base.controller.AbstractInternalController;
import com.sunny.core.constant.RestApiConstants;
import com.sunny.module.db.DbRestApiConstants;
import com.sunny.module.db.table.domain.DbTableDto;
import com.sunny.module.db.table.entity.DbTable;
import com.sunny.module.db.table.service.DbTableService;
import com.sunny.plugin.gen.GenRestApiConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(DbRestApiConstants.REST_API_TABLE)
public class DbTableController extends AbstractInternalController<DbTable, DbTableDto> {

	public static final String TABLE = "table/";
	public static final String COLUMN = "column/";

	private DbTableService dbTableService = SpringContextHolder.getBean(DbTableService.class);

	@Override
	protected ControllerHelper<DbTable, DbTableDto> helper() {
		return ControllerHelper.all(dbTableService, DbTable.of(), new DbTableDto(), DbRestApiConstants.REST_API_TABLE_LIST);
	}
	
	public String list() {
		return toView();
	}
	
	@PostMapping(TABLE + RestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(DbTableDto dto) { 	// 数据列表
		if(dto != null) {
			dto.setIsColumn("2");
		}
		return findListWithPage(dto);
	}
	
	@GetMapping(COLUMN + RestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(String dbId, String tableName) { 	// 数据列表
		DbTableDto dto = helper().getDto(); 
		if(StringUtils.isNotEmpty(dbId) && StringUtils.isNotEmpty(tableName)) {
			dto.setDbId(dbId);
			dto.setTableName(tableName);
			dto.setIsColumn("1");
			return findListNoPage(dto);
		}
		return ResponseJson.noData();
	}

	@GetMapping(TABLE + RestApiConstants.EDIT + "/{id}")
	public String edit(@PathVariable String id, Model model) {
		if(StringUtils.isNotEmpty(id)) {
			ResponseJson json = findById(id);
			model.addAttribute("dbTable", json.getRows());
		}
		return GenRestApiConstants.REST_API_GEN_FORM;
	}

}
