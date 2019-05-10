package com.sunny.module.dataclean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 功能描述：
 *
 * @author cdxpc <cdxpc2018@163.com>
 * @date 2019/5/10 16:45
 * @since 1.0.0v
 */
@Getter
@Setter
public class TableAndColumn {

    private String tableName;

    private List<WhereColumn> WhereColumns;

}
