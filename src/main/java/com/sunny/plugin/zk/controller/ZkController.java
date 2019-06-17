package com.sunny.plugin.zk.controller;

import com.sunny.core.ControllerHelper;
import com.sunny.core.ResponseJson;
import com.sunny.core.base.controller.AbstractInternalController;
import com.sunny.core.constant.RestApiConstants;
import com.sunny.core.persistence.ObjectEntity;
import com.sunny.module.db.table.domain.DbTableDto;
import com.sunny.plugin.zk.ZkRestApiConstants;
import com.sunny.plugin.zk.domain.ZkModelDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述：
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/6/15 22:02
 * @since 1.0.0v
 */
@Controller
@RequestMapping(ZkRestApiConstants.REST_API_ZK)
public class ZkController extends AbstractInternalController<ObjectEntity, ZkModelDto> {

    @Override
    protected ControllerHelper<ObjectEntity, ZkModelDto> helper() {
        return ControllerHelper.all(null, ObjectEntity.of(), new ZkModelDto(), ZkRestApiConstants.REST_API_ZK_LIST);
    }

    public String list() {
        return toView();
    } // 列表页

    @PostMapping(RestApiConstants.DATA)
    @ResponseBody
    public ResponseJson data(DbTableDto dto) {    // 数据列表
        return null;
    }

}
