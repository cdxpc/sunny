package com.sunny.module.dataclean.task.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 这里写类的描述信息
 *
 * @author keivn.chen <crsfyc-9@163.com>
 * @date 2019-05-08 14:15
 * @since 1.0.0v
 */
@Getter
@Setter
public class TaskParams {

    private String[] sources; // ["",""] 第一个为来源库、第二个为目标库

    private TableAndColumn tableAndColumn;

    private int pages = 1;

}

@Getter
@Setter
class TableAndColumn {

    private String tableName;

    private List<WhereColumn> WhereColumns;

    private String andOr;
}

@Getter
@Setter
class WhereColumn {

    private String columnName;

    private String columnValue;

    private String connector;
}

class Test {
    // public static void main(String[] args){
    //     WhereColumn wc1 = new WhereColumn();
    //     wc1.setColumnName("age");
    //     wc1.setColumnValue("23");
    //     wc1.setConnector(">=");
    //
    //     WhereColumn wc2 = new WhereColumn();
    //     wc2.setColumnName("sex");
    //     wc2.setColumnValue("man");
    //     wc2.setConnector("=");
    //
    //     List<WhereColumn> wcs = new ArrayList<>();
    //     wcs.add(wc1);
    //     wcs.add(wc2);
    //
    //     TableAndColumn tac = new TableAndColumn();
    //     tac.setAndOr("and");
    //     tac.setTableName("t_text_001");
    //     tac.setWhereColumns(wcs);
    //
    //     TaskParams tp = new TaskParams();
    //     tp.setPages(100);
    //     tp.setSources(new String[]{"aaaaa", "bbbbb"});
    //     tp.setTableAndColumn(tac);
    //
    //     String json = JacksonUtils.getInstance(true).toJson(tp);
    //     System.out.println(json);
    //
    //     TaskParams bean = JacksonUtils.getInstance(true).toBean(json, TaskParams.class);
    //     System.out.println(bean.getTableAndColumn().getTableName());
    //     System.out.println(bean.getTableAndColumn().getAndOr());
    //     System.out.println(bean.getTableAndColumn().getWhereColumns().get(1).getColumnName());
    //     System.out.println(bean.getTableAndColumn().getWhereColumns().get(0).getConnector());
    // }
}