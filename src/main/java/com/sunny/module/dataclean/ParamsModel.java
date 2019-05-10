package com.sunny.module.dataclean;

import lombok.Getter;
import lombok.Setter;

/**
 * 功能描述：
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/5/10 16:45
 * @since 1.0.0v
 */
@Getter
@Setter
public class ParamsModel {

    private String[] sources; // ["",""] 第一个为来源库、第二个为目标库

    private TableAndColumn tableAndColumn;

    private int pages = 10;
}
