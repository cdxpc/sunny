package com.sunny.plugin.zk.domain;

import com.sunny.core.persistence.DtoEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 功能描述：
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/6/15 22:02
 * @since 1.0.0v
 */
@Getter
@Setter
public class ZkModelDto extends DtoEntity {

    // 上级
    private ZkModelDto parent;
    // 当前自己
    private String current;
    // 子级集合
    private List<ZkModelDto> children;

}
