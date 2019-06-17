package com.sunny.plugin.redis.domain;

import com.sunny.core.persistence.DtoEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 功能描述：
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/6/15 22:01
 * @since 1.0.0v
 */
@Getter
@Setter
public class RedisModelDto extends DtoEntity {

    // 数据库编号
    private Integer dbIndex;
    // 键 key
    private String key;
    // 键类型
    private String valType;
    // 存活时长(time to live)
    private long ttl;

    // value
    private String value;

    // hash field
    private String hfield;
    // hash value
    private String hvalue;
}
