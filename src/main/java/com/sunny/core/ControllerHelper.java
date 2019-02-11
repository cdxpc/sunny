package com.sunny.core;

import com.sunny.core.base.BaseService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "all")
public class ControllerHelper<E, D> {
	
	private BaseService<E, D> service;
	private E entity;
	private D dto;
	
	private String viewUrl;

}
