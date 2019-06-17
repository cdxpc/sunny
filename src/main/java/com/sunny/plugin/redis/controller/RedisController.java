package com.sunny.plugin.redis.controller;

import com.sunny.core.ControllerHelper;
import com.sunny.core.ResponseJson;
import com.sunny.core.base.controller.AbstractInternalController;
import com.sunny.core.constant.RestApiConstants;
import com.sunny.core.persistence.ObjectEntity;
import com.sunny.plugin.redis.RedisRestApiConstants;
import com.sunny.plugin.redis.client.redisson.RedissonClientHelper;
import com.sunny.plugin.redis.domain.RedisModelDto;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 功能描述：
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/6/15 22:02
 * @since 1.0.0v
 */
@Controller
@RequestMapping(RedisRestApiConstants.REST_API_REDIS)
public class RedisController extends AbstractInternalController<ObjectEntity, RedisModelDto> {

    @Override
    protected ControllerHelper<ObjectEntity, RedisModelDto> helper() {
        return ControllerHelper.all(null, ObjectEntity.of(), new RedisModelDto(), RedisRestApiConstants.REST_API_REDIS_LIST);
    }

    public String list() {
        return toView();
    } // 列表页

    @PostMapping(RestApiConstants.DATA)
    @ResponseBody
    public ResponseJson data(RedisModelDto dto) { 	// 数据列表
        // 访问的数据库编号
        int dbIndex = 0;
        if(dto.getDbIndex() != null) {
            dbIndex = dto.getIndex();
        }

        RedissonClient client = RedissonClientHelper.client(dbIndex);

        long toatal = client.getKeys().count(); // keys 的总个数

        List<RedisModelDto> show = show(toatal, dto.getPageNum(), dto.getPageSize(), client.getKeys());




        return null;
    }

    private List<RedisModelDto> show(long toatal, Integer pageNum, Integer pageSize, RKeys rKeys) {
        List<RedisModelDto> list = new ArrayList<>();

        Iterable<String> keys = rKeys.getKeys();

        if(keys != null) {
            Iterator<String> it = keys.iterator();
            int count = 0;
            while(it.hasNext()) {
                count++;
                String key = it.next();
                if((pageNum - 1) * pageSize > count) {
                    break;
                }
                if(false) {

                }
                RedisModelDto rmd = new RedisModelDto();

                rmd.setDbIndex(0);
                rmd.setKey(key);
                rmd.setValType(rKeys.getType(key).name());
                rmd.setTtl(rKeys.remainTimeToLive(key));

                list.add(rmd);
            }
        }

        return list;
    }
}
