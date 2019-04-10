/**
 * 字典相关的辅助js
 * @author cdxpc
 */
(function($) {
	$.extend({
		table : {
			_option : {},
			_params : {},
			init : function(options) {
				_tableId = options.tableId === undefined ? "bootstrap-table" : options.tableId;
				$.table._option = options;
				$.table._option.tableId = _tableId;
				$.table._params = $.common.isEmpty(options.queryParams) ? $.table.queryParams : options.queryParams;
				_sortOrder = $.common.isEmpty(options.sortOrder) ? "asc" : options.sortOrder;
				_sortName = $.common.isEmpty(options.sortName) ? "" : options.sortName;
				$("#" + _tableId).bootstrapTable({
					url : options.url,
					contentType : "application/x-www-form-urlencoded",
					method : "post",
					cache : false,
					//是否显示行间隔色
				    striped: true,
					sortable : false,
					sortName : _sortName,
					sortOrder : _sortOrder,
					sortStable : true,
					pagination : true,
					pageNumber : 1,
					pageSize : 10,
					pageList : [ 10, 25, 50, 100],
					iconSize : "outline",
					toolbar : "#toolbar",
					sidePagination : "server",
					search : $.common.visible(options.search),
					showRefresh : $.common.visible(options.showRefresh),
					showColumns : $.common.visible(options.showColumns),
					showToggle : $.common.visible(options.showToggle),
					showExport : $.common.visible(options.showExport),
					queryParams : $.table._params,
					onDblClickRow : options.dblClickRow, // 双击行，进行数据编辑操作
					//onLoadSuccess : options.loadSuccess,
					columns : options.columns
				});
				return $.table;
			},
			// 查询参数
			queryParams : function(params) {
				return {
					pageNum : params.offset / params.limit + 1,
					pageSize : params.limit,
					searchValue : params.search,
					orderByColumn : params.sort,
					isAsc : params.order
				}
			},
			// 查询
			search : function(formId) {
				var params = $("#" + $.table._option.tableId).bootstrapTable("getOptions");
				params.queryParams = function(params) {
					var search = {};
					$.each($("#" + formId).serializeArray(), function(i, field) {
						if(field.value) {
							search[field.name] = field.value
						}
					});
					search.pageSize = params.limit;
					search.pageNum = params.offset / params.limit + 1;
					search.searchValue = params.search;
					search.orderByColumn = params.sort;
					search.isAsc = params.order;
					return search;
				};
				$.table.refresh(params);
			},
			// 重置
			reset : function(formId) {
				$("#" + formId +"  input").val("");
			  	$("#" + formId +"  select").val("");
			  	$.table.refresh();
			},
			// 刷新
			refresh : function(params) {
				$("#" + $.table._option.tableId).bootstrapTable("refresh", params);
			},
			// 表格导出
			/*exportExcel : function(formId) {
				$.post($.table._option.exportUrl, $("#" + formId).serializeArray(), function(result) {
					if (result.code == response_status.SUCCESS) {
						window.location.href = ctx + "common/download?fileName=" + result.msg + "&delete=" + true
					} else {
						$.modal.alertError(result.msg)
					}
				})
			},*/
			selectColumns : function(column) {
				return $.map($("#" + $.table._option.tableId).bootstrapTable("getSelections"), function(row) {
					return "'" + row[column] + "'"
				})
			},
			selectFirstColumns : function() {
				return $.map($("#" + $.table._option.tableId).bootstrapTable("getSelections"), function(row) {
					return "'" + row[$.table._option.columns[1].field] + "'"
				})
			}
		}
	});
})(jQuery);