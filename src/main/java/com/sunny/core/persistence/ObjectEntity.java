package com.sunny.core.persistence;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 功能描述：
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/6/15 22:15
 * @since 1.0.0v
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(staticName = "of")
public class ObjectEntity extends DbEntity {
}
