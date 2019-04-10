/**
 * 字典相关的辅助js
 * @author cdxpc
 */
(function($) {
	$.extend({
		dictHelper: {
			/*post: function(map, dictTypeKey) {
				$.ajax({
					url : ctx + "sys/dict/value/key",
					type : 'post',
					dataType : 'json',
					async: false,
					data: {dictTypeKey: dictTypeKey},
					success : function(data) {
						if (data.result.code === 200) {
							var rows = data.rows;
							for(var i = 0; i < rows.length; i++) {
								map.set(rows[i]['dictValue'], rows[i]['dictLabel']);
						    }
						}
					}
				});
				return map;
			},*/
			
			show: function(dictValues, value) {
				var val = "";
				$.each(dictValues, function(index, dv){
					if(dv.dictValue === value) {
						if(dv.cssClass) {
							val = "<span class='" + dv.cssClass + "'>" + dv.dictLabel + "</span>";
						} else {
							val = dv.dictLabel;
						}
						return;
					}
				});
				return val;
			}
		}
	});
})(jQuery);