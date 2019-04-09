/** 设置全局ajax超时处理 */
$.ajaxSetup({
    complete: function(XMLHttpRequest, textStatus) {
        if (textStatus === "parsererror") {
        	$.modal.confirm("登陆超时！请重新登陆！", function() {
        		window.location.href = ctx + "login";
        	})
        }
    }
});/** iframe处理 */
$(function() {
	// 复选框事件绑定
	if ($.fn.select2 !== undefined) {
		$("select.form-control:not(.noselect2)").each(function () {
			$(this).select2().on("change", function () {
				$(this).valid();
			})
		})
	}
	if ($(".i-checks").length > 0) {
	    $(".i-checks").iCheck({
	        checkboxClass: "icheckbox_square-green",
	        radioClass: "iradio_square-green",
	    })
	}
    //通过遍历给菜单项加上data-index属性
    $(".menuItem").each(function(index) {
        if (!$(this).attr('data-index')) {
            $(this).attr('data-index', index);
        }
    });
    // 全屏
    $('#fullScreen').on('click', fullScreen);
    // 添加选项卡
    $('.menuItem').on('click', addTab);
    // 关闭选项卡菜单
    $('.menuTabs').on('click', '.menuTab i', closeTab);
    // 滚动到已激活的选项卡
    $('.tabShowActive').on('click', showActiveTab);
    // 点击选项卡菜单
    $('.menuTabs').on('click', '.menuTab', activeTab);
    // 刷新主页按钮
    $('.tabReload').on('click', refreshTab);
    // 双击tab页，刷新tab页的内容 
    $('.menuTabs').on('dblclick', '.menuTab', refreshTab);
    // 左移按扭
    $('.tabLeft').on('click', scrollTabLeft);
    // 右移按扭
    $('.tabRight').on('click', scrollTabRight);
    // 关闭当前激活选项卡
    $('.tabCloseCurrent').on('click', closeActiveTabs);
    // 关闭其他选项卡
    $('.tabCloseOther').on('click', closeOtherTabs);
    // 关闭全部
    $('.tabCloseAll').on('click', closeAllTabs);
});
// 计算元素集合的总宽度
function calSumWidth(elements) {
    var width = 0;
    $(elements).each(function() {
        width += $(this).outerWidth(true);
    });
    return width;
}
// 全屏
function fullScreen() {
    $('#wrapper').fullScreen();
}
// 添加选项卡
function addTab() {
	$.modal.loading("数据加载中，请稍后...");
    // 获取标识数据
    var dataUrl = $(this).attr('href'),
    dataIndex = $(this).data('index'),
    menuName = $.trim($(this).text()),
    flag = true;
    if (dataUrl === undefined || $.trim(dataUrl).length === 0) return false;
    // 选项卡菜单已存在
    $('.menuTab').each(function() {
        if ($(this).data('id') === dataUrl) {
            if (!$(this).hasClass('active')) {
                $(this).addClass('active').siblings('.menuTab').removeClass('active');
                scrollToTab(this);
                // 显示tab对应的内容区
                $('.mainContent .cxc_iframe').each(function() {
                    if ($(this).data('id') === dataUrl) {
                        $(this).show().siblings('.cxc_iframe').hide();
                        return false;
                    }
                });
            }
            flag = false;
            return false;
        }
    });
    // 选项卡菜单不存在
    if (flag) {
        var str = '<a href="javascript:;" class="active menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
        $('.menuTab').removeClass('active');
        // 添加选项卡对应的iframe
        var str1 = '<iframe class="cxc_iframe" name="cxc_iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
        $('.mainContent').find('iframe.cxc_iframe').hide().parents('.mainContent').append(str1);
        // 添加选项卡
        $('.menuTabs .page-tabs-content').append(str);
        scrollToTab($('.menuTab.active'));
    }
    $.modal.closeLoading();
    return false;
}
// 滚动到指定选项卡
function scrollToTab(element) {
    var marginLeftVal = calSumWidth($(element).prevAll()),
    marginRightVal = calSumWidth($(element).nextAll());
    // 可视区域非tab宽度
    var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
    //可视区域tab宽度
    var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
    //实际滚动宽度
    var scrollVal = 0;
    if ($(".page-tabs-content").outerWidth() < visibleWidth) {
        scrollVal = 0;
    } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
        if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
            scrollVal = marginLeftVal;
            var tabElement = element;
            while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                scrollVal -= $(tabElement).prev().outerWidth();
                tabElement = $(tabElement).prev();
            }
        }
    } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
        scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
    }
    $('.page-tabs-content').animate({
        marginLeft: 0 - scrollVal + 'px'
    }, "fast");
}
// 关闭选项卡菜单
function closeTab() {
    var closeTabId = $(this).parents('.menuTab').data('id');
    var currentWidth = $(this).parents('.menuTab').width();
    // 当前元素处于活动状态
    if ($(this).parents('.menuTab').hasClass('active')) {
        // 当前元素后面有同辈元素，使后面的一个元素处于活动状态
        if ($(this).parents('.menuTab').next('.menuTab').size()) {
            var activeId = $(this).parents('.menuTab').next('.menuTab:eq(0)').data('id');
            $(this).parents('.menuTab').next('.menuTab:eq(0)').addClass('active');
            $('.mainContent .cxc_iframe').each(function() {
                if ($(this).data('id') == activeId) {
                    $(this).show().siblings('.cxc_iframe').hide();
                    return false;
                }
            });
            var marginLeftVal = parseInt($('.page-tabs-content').css('margin-left'));
            if (marginLeftVal < 0) {
                $('.page-tabs-content').animate({
                    marginLeft: (marginLeftVal + currentWidth) + 'px'
                },"fast");
            }
            //  移除当前选项卡
            $(this).parents('.menuTab').remove();
            // 移除tab对应的内容区
            $('.mainContent .cxc_iframe').each(function() {
                if ($(this).data('id') === closeTabId) {
                    $(this).remove();
                    return false;
                }
            });
        }
        // 当前元素后面没有同辈元素，使当前元素的上一个元素处于活动状态
        if ($(this).parents('.menuTab').prev('.menuTab').size()) {
            var activeId = $(this).parents('.menuTab').prev('.menuTab:last').data('id');
            $(this).parents('.menuTab').prev('.menuTab:last').addClass('active');
            $('.mainContent .cxc_iframe').each(function() {
                if ($(this).data('id') === activeId) {
                    $(this).show().siblings('.cxc_iframe').hide();
                    return false;
                }
            });
            //  移除当前选项卡
            $(this).parents('.menuTab').remove();
            // 移除tab对应的内容区
            $('.mainContent .cxc_iframe').each(function() {
                if ($(this).data('id') === closeTabId) {
                    $(this).remove();
                    return false;
                }
            });
        }
    }
    // 当前元素不处于活动状态
    else {
        //  移除当前选项卡
        $(this).parents('.menuTab').remove();
        // 移除相应tab对应的内容区
        $('.mainContent .cxc_iframe').each(function() {
            if ($(this).data('id') === closeTabId) {
                $(this).remove();
                return false;
            }
        });
        scrollToTab($('.menuTab.active'));
    }
    return false;
}
// 滚动到已激活的选项卡
function showActiveTab() {
    scrollToTab($('.menuTab.active'));}

// 点击选项卡菜单
function activeTab() {
    if (!$(this).hasClass('active')) {
        var currentId = $(this).data('id');
        // 显示tab对应的内容区
        $('.mainContent .cxc_iframe').each(function() {
            if ($(this).data('id') === currentId) {
                $(this).show().siblings('.cxc_iframe').hide();
                return false;
            }
        });
        $(this).addClass('active').siblings('.menuTab').removeClass('active');
        scrollToTab(this);
    }
}
// 刷新iframe
function refreshTab() {
	var currentId = $('.page-tabs-content').find('.active').attr('data-id');
	var target = $('.cxc_iframe[data-id="' + currentId + '"]');
    var url = target.attr('src');
    target.attr('src', url).ready();
}
// 查看左侧隐藏的选项卡
function scrollTabLeft() {
    var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
    // 可视区域非tab宽度
    var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
    //可视区域tab宽度
    var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
    //实际滚动宽度
    var scrollVal = 0;
    if ($(".page-tabs-content").width() < visibleWidth) {
        return false;
    } else {
        var tabElement = $(".menuTab:first");
        var offsetVal = 0;
        while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) { //找到离当前tab最近的元素
            offsetVal += $(tabElement).outerWidth(true);
            tabElement = $(tabElement).next();
        }
        offsetVal = 0;
        if (calSumWidth($(tabElement).prevAll()) > visibleWidth) {
            while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).prev();
            }
            scrollVal = calSumWidth($(tabElement).prevAll());
        }
    }
    $('.page-tabs-content').animate({
        marginLeft: 0 - scrollVal + 'px'
    }, "fast");
}
// 查看右侧隐藏的选项卡
function scrollTabRight() {
    var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
    // 可视区域非tab宽度
    var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
    //可视区域tab宽度
    var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
    //实际滚动宽度
    var scrollVal = 0;
    if ($(".page-tabs-content").width() < visibleWidth) {
        return false;
    } else {
        var tabElement = $(".menuTab:first");
        var offsetVal = 0;
        while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) { //找到离当前tab最近的元素
            offsetVal += $(tabElement).outerWidth(true);
            tabElement = $(tabElement).next();
        }
        offsetVal = 0;
        while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
            offsetVal += $(tabElement).outerWidth(true);
            tabElement = $(tabElement).next();
        }
        scrollVal = calSumWidth($(tabElement).prevAll());
        if (scrollVal > 0) {
            $('.page-tabs-content').animate({
                marginLeft: 0 - scrollVal + 'px'
            }, "fast");
        }
    }
}
// 关闭当前激活选项卡
function closeActiveTabs() {
	$('.page-tabs-content').find('.active i').trigger("click");
}
// 关闭其他选项卡
function closeOtherTabs() {
    $('.page-tabs-content').children("[data-id]").not(":first").not(".active").each(function() {
        $('.cxc_iframe[data-id="' + $(this).data('id') + '"]').remove();
        $(this).remove();
    });
    $('.page-tabs-content').css("margin-left", "0");
}
//关闭全部选项卡
function closeAllTabs() {
    $('.page-tabs-content').children("[data-id]").not(":first").each(function() {
        $('.cxc_iframe[data-id="' + $(this).data('id') + '"]').remove();
        $(this).remove();
    });
    $('.page-tabs-content').children("[data-id]:first").each(function() {
        $('.cxc_iframe[data-id="' + $(this).data('id') + '"]').show();
        $(this).addClass("active");
    });
    $('.page-tabs-content').css("margin-left", "0");
}
/** 创建新的选项卡 */
/*function createNewTab(dataId, param, menuName) {
    dataIndex = $.common.random(1,100),
    flag = true;
    var dataUrl = dataId + param;
    if (dataUrl == undefined || $.trim(dataUrl).length == 0) return false;
    var topWindow = $(window.parent.document);
    // 选项卡菜单已存在
    $('.menuTab', topWindow).each(function() {
        if ($(this).data('id') == dataId) {
            if (!$(this).hasClass('active')) {
                $(this).addClass('active').siblings('.menuTab').removeClass('active');
                $('.page-tabs-content').animate({ marginLeft: ""}, "fast");
                // 显示tab对应的内容区
                $('.mainContent .cxc_iframe', topWindow).each(function() {
                    if ($(this).data('id') == dataId) {
                        $(this).show().siblings('.cxc_iframe').hide();
                        return false;
                    }
                });
            }
            flag = false;
            return false;
        }
    });
    // 选项卡菜单不存在
    if (flag) {
        var str = '<a href="javascript:;" class="active menuTab" data-id="' + dataId + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
        $('.menuTab', topWindow).removeClass('active');
        // 添加选项卡对应的iframe
        var str1 = '<iframe class="cxc_iframe" name="cxc_iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataId + '" seamless></iframe>';
        $('.mainContent', topWindow).find('iframe.cxc_iframe').hide().parents('.mainContent').append(str1);
        // 添加选项卡
        $('.menuTabs .page-tabs-content', topWindow).append(str);
    }
    return false;
}*/
