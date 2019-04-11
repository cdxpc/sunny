/**
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
		form : {
			selectCheckeds : function(name) {
				var checkeds = "";
				$('input:checkbox[name="' + name + '"]:checked').each(
						function(i) {
							if (0 === i) {
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
					if (0 === i) {
						selects = $(this).val()
					} else {
						selects += ("," + $(this).val())
					}
				});
				return selects
			},
		},
		modal : {
			icon : function(type) {
				var icon = "";
				if (type === modal_status.WARNING) {
					icon = 0
				} else {
					if (type === modal_status.SUCCESS) {
						icon = 1
					} else {
						if (type === modal_status.FAIL) {
							icon = 2
						} else {
							icon = 3
						}
					}
				}
				return icon
			},
			msg : function(content, type) {
				if (type !== undefined) {
					layer.msg(content, {
						icon : $.modal.icon(type),
						time : 300,
						shift : 10,
						shade : 0.3
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
					btnclass : [ "btn btn-primary" ]
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
					btnclass : [ "btn btn-primary", "btn btn-danger" ]
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
			open : function($table, title, url, width, height, btns) {
				$.modal.openDialog(false, $table, title, url, width, height, btns);
			},
			openFull : function($table, title, url, width, height, btns) {
				$.modal.openDialog(true, $table, title, url, width, height, btns);
			},
			// 打开弹框（查看、新增、编辑）,带按钮的
			openDialog: function(isFull, $table, title, url, width, height, btns) {
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
					btns = ['<i class="fa fa-check"></i> 谨遵谕旨', '<i class="fa fa-close"></i> 臣妾做不到'];
				}
				var index = layer.open({
					auto : auto,
					title : ">> " + title,    	// 标题
					type : 2,		  			// 弹出层类型
					area : [width, height], 	// 宽高
					fix : false,				// 是否固定
					maxmin : true,				// 是否允许缩小放大
					shade : [0.3, '#393D49'],	// 遮罩层，0.3:表示透明度为0.3的黑色背景，可配置为：[0.5,
												// #393D49]
					content : url, 				// 访问url
					anim : 0,					// 弹出是动画效果， 取值范围： -1~6
					closeBtn: 1,				// 关闭按钮，默认值为：1（0:无关闭按钮，1：无样式，2：带样式）
					btnAlign: 'c',				// 按钮排列位置， 默认:'r',右对齐
					btn : btns,					// 按钮数组，默认第一个按钮回调yes，其他的依次回调btn2：function(){}
					yes : function(index, layero) { // 第一个按钮的回调方法
						// 得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
						var iframe = layero.find('iframe');
						iframe[0].contentWindow.doSubmit($table);
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
			},
		},
		operate : { // 相关操作
			// ajax 请求
			post: function($table, url, data, t) {
				//$.modal.loading("正在处理中，请稍后...");
				var config = {
					url : url,
					type : 'post',
					dataType : 'json',
					data : data,
					success : function(data) {
						if (data.result.code === 200) {
							$.modal.msgSuccess(data.result.message);
								} else {
						} else {
							$.modal.alertError(data.result.message)
						}
						$.modal.closeLoading()
					}
				};
				$.ajax(config)
			},
			// 启用
			inuse : function($table, data) {
				data.status = '1';
				var url = $table._option.modifyStatusUrl;
				$.operate.post($table, url, data);
			},
			// 禁用
			unuse : function($table, data) {
				data.status = '2';
				var url = $table._option.modifyStatusUrl;
				$.operate.post($table, url, data);
			},
			// 单个删除
			remove : function($table, id) {
				$.modal.confirm("确定删除该条" + $table._option.modalName + "信息吗？",
						function() {
							var url = $.common.isEmpty(id) ? $table._option.removeUrl : $table._option.removeUrl.replace("{id}", id);
							var data = {
								"id" : id
							};
							$.operate.post($table, url, data);
						})
			},
			// 批量删除
			batRemove : function($table) {
				var rowIds = $.common.isEmpty($table._option.id) ? $table.selectFirstColumns() : $table.selectColumns($table._option.id);
				if (rowIds.length === 0) {
					$.modal.alertWarning("请至少选择一条记录");
					return;
				}
				$.modal.confirm("确认要删除选中的" + rowIds.length + "条数据吗?",
						function() {
							var url = $table._option.removeUrl + "Batch";
							var data = {
								"ids" : rowIds.join()
							};
							$.operate.post($table, url, data);
						})
			},
			// 新增按钮
			add : function($table, id, btns, width, height) {
				var url = $.common.isEmpty(id) ? $table._option.createUrl : $table._option.createUrl.replace("{id}", id);
				$.modal.open($table, "添加 [" + $table._option.modalName + "]", url, width, height, btns);
			},
			// 新增子数据按钮
			addSub : function($table, id, btns, width, height) {
				var url = $.common.isEmpty(id) ? $table._option.createSubUrl : $table._option.createSubUrl.replace("{id}", id);
				$.modal.open($table, "添加 [子" + $table._option.modalName + "]", url, width, height, btns);
			},
			// 更新按钮
			edit : function($table, id, btns, width, height) {
				var url = $table._option.updateUrl.replace("{id}", id);
				$.modal.open($table, "修改 [" + $table._option.modalName + "]", url, width, height, btns);
			},
			// 查看按钮
			details : function($table, id, width, height) {
				var url = $table._option.updateUrl.replace("{id}", id);
				$.modal.openView("查看 [" + $table._option.modalName + "]", url);
			},
			addFull : function($table, id, btns, width, height) {
				var url = $.common.isEmpty(id) ? $table._option.createUrl : $table._option.createUrl.replace("{id}", id);
				$.modal.openFull($table, "添加 [" + $table._option.modalName + "]", url, width, height, btns);
			},
			editFull : function($table, id, btns, width, height) {
				var url = $table._option.updateUrl.replace("{id}", id);
				$.modal.openFull($table, "修改 [" + $table._option.modalName + "]", url, width, height, btns);
			},
			detailsFull : function($table, id, width, height) {
				var url = $table._option.updateUrl.replace("{id}", id);
				$.modal.openFullView("查看 [" + $table._option.modalName + "]", url, width, height);
			}
		},
		common : {
			// 为空
			isEmpty : function(value) {
				return (value == null || this.trim(value) === "");
			},
			// 是否显示数据，为空或true时显示，默认为空
			visible : function(value) {
				return ($.common.isEmpty(value) || value === true);
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