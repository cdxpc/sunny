/**
 * 字典相关的辅助js
 * @author cdxpc
 */
(function($) {
	$.extend({
		_treeTable : {},
		treeTable : {
			_option : {},
			init : function(options) {
				_tableId = options.tableId === undefined ? "bootstrap-treeTable" : options.tableId;
				$.treeTable._option = options;
				$.treeTable._option.tableId = _tableId;
                $._treeTable = $("#" + _tableId).bootstrapTreeTable({
					code : options.id,
					parentCode : options.parentId,
					type : "post",
					url : options.url,
					ajaxParams : {},
					expandColumn : "0",
					striped : false,
					bordered : true,
					expandAll : $.common.visible(options.expandAll),
					columns : options.columns
				});
			},
			// 查询
			search : function(formId) {
				var params = $("#" + formId).serializeJSON();
				$.treeTable.refresh(params);
			},
			// 重置
			reset : function(formId) {
				$("#" + formId +"  input").val("");
			  	$("#" + formId +"  select").val("");
			  	$.treeTable.refresh();
			},
			// 刷新
			refresh: function(params) {
				$._treeTable.bootstrapTreeTable("refresh", params);
			}
		}
	});
})(jQuery);