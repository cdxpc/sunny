// 响应状态
response_status = {
	SUCCESS : 0,
	FAIL : 500
};

// 模块状态
modal_status = {
	SUCCESS : "success",
	FAIL : "error",
	WARNING : "warning"
};

(function($) {
	$.extend({
		table : {
			_option : {},
			_params : {},
			init : function(options) {
				_tableId = options.tableId == undefined ? "bootstrap-table" : options.tableId;
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
					columns : options.columns
				})
				return $.table;
			},
			// 查询参数
			queryParams : function(params) {
				return {
					pageSize : params.limit,
					pageNum : params.offset / params.limit + 1,
					searchValue : params.search,
					orderByColumn : params.sort,
					isAsc : params.order
				}
			},
			// 查询
			search : function(form) {
				var params = $("#" + $.table._option.tableId).bootstrapTable("getOptions");
				params.queryParams = function(params) {
					var search = {};
					$.each($("#" + form).serializeArray(), function(i, field) {
						if(field.value) {
							search[field.name] = field.value
						}
					});
					search.pageSize = params.limit;
					search.pageNum = params.offset / params.limit + 1;
					search.searchValue = params.search;
					search.orderByColumn = params.sort;
					search.isAsc = params.order;
					return search
				};
				$("#" + $.table._option.tableId).bootstrapTable("refresh", params)
			},
			// 重置
			reset : function(form) {
				$("#" + form +"  input").val("");
			  	$("#" + form +"  select").val("");
			  	$.table.refresh();
			},
			// 表格导出
			/*exportExcel : function(form) {
				$.post($.table._option.exportUrl, $("#" + form)
						.serializeArray(), function(result) {
					if (result.code == response_status.SUCCESS) {
						window.location.href = ctx + "common/download?fileName=" + result.msg + "&delete=" + true
					} else {
						$.modal.alertError(result.msg)
					}
				})
			},*/
			// 刷新
			refresh : function() {
				$("#" + $.table._option.tableId).bootstrapTable("refresh", {
					url : $.table._option.url,
					silent : true
				})
			},
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
		},
		_treeTable : {},
		treeTable : {
			_option : {},
			init : function(options) {
				_tableId = options.tableId === undefined ? "bootstrap-treeTable" : options.tableId;
				$.table._option = options;
				$.table._option.tableId = _tableId;
				var tt = $("#" + _tableId).bootstrapTreeTable({
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
				$._treeTable = tt;
			},
			refresh: function(params) {
				$._treeTable.bootstrapTreeTable("refresh", params);
			}
		},
		form : {
			selectCheckeds : function(name) {
				var checkeds = "";
				$('input:checkbox[name="' + name + '"]:checked').each(
						function(i) {
							if (0 == i) {
								checkeds = $(this).val()
							} else {
								checkeds += ("," + $(this).val())
							}
						});
				return checkeds
			},
			selectSelects : function(name) {
				var selects = "";
				$("#" + name + " option:selected").each(function(i) {
					if (0 == i) {
						selects = $(this).val()
					} else {
						selects += ("," + $(this).val())
					}
				});
				return selects
			}
		},
		modal : {
			icon : function(type) {
				var icon = "";
				if (type == modal_status.WARNING) {
					icon = 0
				} else {
					if (type == modal_status.SUCCESS) {
						icon = 1
					} else {
						if (type == modal_status.FAIL) {
							icon = 2
						} else {
							icon = 3
						}
					}
				}
				return icon
			},
			msg : function(content, type) {
				if (type != undefined) {
					layer.msg(content, {
						icon : $.modal.icon(type),
						time : 500,
						shift : 5,
						shade : 0.1
					})
				} else {
					layer.msg(content)
				}
			},
			msgError : function(content) {
				$.modal.msg(content, modal_status.FAIL)
			},
			msgSuccess : function(content) {
				$.modal.msg(content, modal_status.SUCCESS)
			},
			msgWarning : function(content) {
				$.modal.msg(content, modal_status.WARNING)
			},
			alert : function(content, type) {
				layer.alert(content, {
					icon : $.modal.icon(type),
					title : "系统提示",
					btn : [ "确认" ],
					btnclass : [ "btn btn-primary" ],
				})
			},
			msgReload : function(msg, type) {
				layer.msg(msg, {
					icon : $.modal.icon(type),
					time : 500,
					shade : [ 0.1, "#8F8F8F" ]
				}, function() {
					$.modal.reload()
				})
			},
			alertError : function(content) {
				$.modal.alert(content, modal_status.FAIL)
			},
			alertSuccess : function(content) {
				$.modal.alert(content, modal_status.SUCCESS)
			},
			alertWarning : function(content) {
				$.modal.alert(content, modal_status.WARNING)
			},
			close : function(index) {
				if($.common.isEmpty(index)) {
					index = parent.layer.getFrameIndex(window.name);
				}
				parent.layer.close(index);
			},
			confirm : function(content, callBack) {
				layer.confirm(content, {
					icon : 3,
					title : "系统温馨提示！",
					btn : [ "确认", "取消" ],
					btnclass : [ "btn btn-primary", "btn btn-danger" ],
				}, function(index) {
					layer.close(index);
					callBack(true)
				})
			},
			loading : function(message) {
				$.blockUI({message : '<div class="loaderbox"><div class="loading-activity"></div> ' + message + "</div>"})
			},
			closeLoading : function() {
				setTimeout(function() {
					$.unblockUI()
				}, 50)
			},
			reload : function() {
				parent.location.reload()
			},
			open : function(title, url, width, height, btns, $table) {
				$.modal.openDialog(false, title, url, width, height, btns, $table);
			},
			openFull : function(title, url, width, height, btns, $table) {
				$.modal.openDialog(true, title, url, width, height, btns, $table);
			},
			// 打开弹框（查看、新增、编辑）,带按钮的
			openDialog: function(isFull, title, url, width, height, btns, $table) {
				if ($.common.isEmpty(title)) {
					title = '信息'
				}
				if ($.common.isEmpty(url)) {
					url = "404.html"
				}
				if ($.common.isEmpty(width)) {
					width = 800 + "px"
				}
				if ($.common.isEmpty(height)) {
					height = ($(window).height() - 50) + "px"
				}
				var auto = true;
				// 是否使用响应式，使用百分比时，应设置为false
				if(width.indexOf("%") >= 0 || height.indexOf("%") >= 0){
					auto = false;
				}
				if($.common.isEmpty(btns)) {
					// btns = ['确定', '取消'];
					btns = ['谨遵谕旨', '臣妾做不到'];
				}
				var index = layer.open({
					auto : auto,
					title : ">> " + title,    	// 标题
					type : 2,		  			// 弹出层类型
					area : [width, height], 	// 宽高
					fix : false,				// 是否固定
					maxmin : true,				// 是否允许缩小放大
					shade : [0.3, '#393D49'],	// 遮罩层，0.3:表示透明度为0.3的黑色背景，可配置为：[0.5, #393D49]
					content : url, 				// 访问url
					anim : 0,					// 弹出是动画效果， 取值范围： -1~6
					closeBtn: 1,				// 关闭按钮，默认值为：1（0:无关闭按钮，1：无样式，2：带样式）
					btnAlign: 'c',				// 按钮排列位置， 默认:'r',右对齐
					btn : btns,					// 按钮数组，默认第一个按钮回调yes，其他的依次回调btn2：function(){}
					yes : function(index, layero) { // 第一个按钮的回调方法
						// 得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
						var iframe = layero.find('iframe');
						if($.common.isEmpty($table)) {
							iframe[0].contentWindow.doSubmit($.table);
						} else {
							iframe[0].contentWindow.doSubmit($table);
						}
					},
//					btn2: function(index, layero) {
//						// btn2... 依次
//					},
					cancel : function(index){ 	// 弹出层中的右上角的关闭按钮回调方法
					}
				});
				if(isFull) {
					layer.full(index);
				}
			},
			openView : function(title, url, width, height) {
				$.modal.openDialogView(false, title, url, width, height);
			},
			openFullView : function(title, url, width, height) {
				$.modal.openDialogView(true, title, url, width, height);
			},
			// 打开弹框，不带按钮的（查看）
			openDialogView: function(isFull, title, url, width, height) {
				if ($.common.isEmpty(title)) {
					title = '信息'
				}
				if ($.common.isEmpty(url)) {
					url = "404.html"
				}
				if ($.common.isEmpty(width)) {
					width = 800 + "px"
				}
				if ($.common.isEmpty(height)) {
					height = ($(window).height() - 50) + "px"
				}
				var auto = true;
				// 是否使用响应式，使用百分比时，应设置为false
				if(width.indexOf("%") >= 0 || height.indexOf("%") >= 0){
					auto = false;
				}
				var index = layer.open({
					auto : auto,
					title : ">> " + title,    	// 标题
					type : 2,		  			// 弹出层类型
					area : [width, height], 	// 宽高
					fix : false,				// 是否固定
					maxmin : true,				// 是否允许缩小放大
					shade : 0.3,				// 遮罩层，0.3:表示透明度为0.3的黑色背景，可配置为：[0.5, #393D49]
					content : url, 				// 访问url
					anim : 0,					// 弹出是动画效果， 取值范围： -1~6
					closeBtn: 1,				// 关闭按钮，默认值为：1（0:无关闭按钮，1：无样式，2：带样式）
					cancel : function(index){ 	// 弹出层中的右上角的关闭按钮回调方法
					}
				});
				if(isFull) {
					layer.full(index);
				}
			}
		},
		operate : { // 相关操作
			// ajax 请求
			post: function(url, data) {
				$.modal.loading("正在处理中，请稍后...");
				var config = {
					url : url,
					type : 'post',
					dataType : 'json',
					data : data,
					success : function(data) {
						if (data.result.code === 200) {
							$.modal.msgSuccess(data.result.message);
							$.table.refresh()
						} else {
							$.modal.alertError(data.result.message)
						}
						$.modal.closeLoading()
					}
				};
				$.ajax(config)
			},
			// 单个删除
			remove : function(id) {
				$.modal.confirm("确定删除该条" + $.table._option.modalName + "信息吗？",
						function() {
							var url = $.common.isEmpty(id) ? $.table._option.removeUrl : $.table._option.removeUrl.replace("{id}", id);
							var data = {
								"ids" : "'" + id + "'"
							};
							$.operate.post(url, data);
						})
			},
			// 批量删除
			batRemove : function() {
				var rowIds = $.common.isEmpty($.table._option.id) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._option.id);
				if (rowIds.length == 0) {
					$.modal.alertWarning("请至少选择一条记录");
					return
				}
				$.modal.confirm("确认要删除选中的" + rowIds.length + "条数据吗?",
						function() {
							var url = $.table._option.removeUrl;
							var data = {
								"ids" : rowIds.join()
							};
							$.operate.post(url, data);
						})
			},
			// 新增按钮
			add : function(id, btns, width, height) {
				var url = $.common.isEmpty(id) ? $.table._option.createUrl : $.table._option.createUrl.replace("{id}", id);
				$.modal.open("添加 [" + $.table._option.modalName + "]", url, width, height, btns);
			},
			// 更新按钮
			edit : function(id, btns, width, height) {
				var url = $.table._option.updateUrl.replace("{id}", id);
				$.modal.open("修改 [" + $.table._option.modalName + "]", url, width, height, btns);
			},
			// 查看按钮
			details : function(id, width, height) {
				var url = $.table._option.updateUrl.replace("{id}", id);
				$.modal.openView("查看 [" + $.table._option.modalName + "]", url);
			},
			addFull : function(id, btns, width, height) {
				var url = $.common.isEmpty(id) ? $.table._option.createUrl : $.table._option.createUrl.replace("{id}", id);
				$.modal.openFull("添加 [" + $.table._option.modalName + "]", url, width, height, btns);
			},
			editFull : function(id, btns, width, height) {
				var url = $.table._option.updateUrl.replace("{id}", id);
				$.modal.openFull("修改 [" + $.table._option.modalName + "]", url, width, height, btns);
			},
			detailsFull : function(id, width, height) {
				var url = $.table._option.updateUrl.replace("{id}", id);
				$.modal.openFullView("查看 [" + $.table._option.modalName + "]", url, width, height);
			}
		},
		common : {
			// 为空
			isEmpty : function(value) {
				if (value == null || this.trim(value) == "") {
					return true
				}
				return false
			},
			// 非空
			visible : function(value) {
				if ($.common.isEmpty(value) || value == true) {
					return true
				}
				return false
			},
			// 去除tab符、换号符
			trim : function(value) {
				if (value == null) {
					return ""
				}
				return value.toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "")
			},
			// 随机数
			random : function(min, max) {
				return Math.floor((Math.random() * max) + min)
			}
		}
	});
})(jQuery);