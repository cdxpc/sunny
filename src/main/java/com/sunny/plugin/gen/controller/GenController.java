package com.sunny.plugin.gen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunny.core.ControllerHelper;
import com.sunny.core.ResponseJson;
import com.sunny.core.SpringContextHolder;
import com.sunny.core.base.controller.AbstractInternalController;
import com.sunny.core.constant.RestApiConstants;
import com.sunny.core.util.StringUtils;
import com.sunny.module.db.database.entity.DataBase;
import com.sunny.module.db.database.service.DataBaseService;
import com.sunny.module.db.table.domain.DbTableDto;
import com.sunny.module.db.table.entity.DbTable;
import com.sunny.module.db.table.service.DbTableService;
import com.sunny.plugin.gen.GenRestApiConstants;

@Controller
@RequestMapping(GenRestApiConstants.REST_API_GEN)
public class GenController extends AbstractInternalController<DbTable, DbTableDto> {
	
	public static final String TABLE = "table/";
	public static final String COLUMN = "column/";
	
	private DbTableService dbTableService = SpringContextHolder.getBean(DbTableService.class);
	private DataBaseService dataBaseService = SpringContextHolder.getBean(DataBaseService.class);

	@Override
	protected ControllerHelper<DbTable, DbTableDto> helper() {
		return ControllerHelper.all(dbTableService, DbTable.of(), new DbTableDto(), GenRestApiConstants.REST_API_GEN_LIST);
	}

	public String list() {
		return toView();
	} // 列表页

	@PostMapping(TABLE + RestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(DbTableDto dto) { 	// 数据列表
		DataBase db = DataBase.of();
		db.setIsDefault("1");
		DataBase dataBase = dataBaseService.findOne(db);
		if(dto != null && dataBase != null) {
			dto.setIsColumn("2");
			dto.setDbId(dataBase.getDbId());
		}
		return findListWithPage(dto);
	}

	@GetMapping(COLUMN + RestApiConstants.DATA)
	@ResponseBody
	public ResponseJson data(String dbId, String tableName) { 	// 数据列表
		DbTableDto dto = helper().getDto();
		if(StringUtils.isNotEmpty(dbId) && StringUtils.isNotEmpty(tableName)) {
			dto.setDbId(dbId);
			dto.setTableName(tableName);
			dto.setIsColumn("1");
			return findListNoPage(dto);
		}
		return ResponseJson.noData();
	}

	@GetMapping(TABLE + RestApiConstants.EDIT + "/{tableId}")
	public String edit(@PathVariable String tableId, Model model) {
		if(StringUtils.isNotEmpty(tableId)) {
			ResponseJson json = findById(tableId);
			model.addAttribute("dbTable", json.getRows());
		}
		return GenRestApiConstants.REST_API_GEN_FORM;
	}

}
