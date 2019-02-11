package com.sunny.core.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sunny.boot.SunnyConfig;
import com.sunny.core.SpringContextHolder;
import com.sunny.core.auth.Principal;
import com.sunny.core.auth.ShiroHelper;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true, value = { "handler" })
public class DtoEntity extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Length(min = 1, max = 10)
	protected transient Integer sort; 
	
	@JsonIgnore
	@Length(min = 1, max = 1)
	protected transient String delFlag;
	
	@Length(min = 1, max = 1)
	protected transient String status; 
	
	@JsonIgnore
	@XmlTransient
	protected transient String currentUser;
	
	@JsonIgnore
	@XmlTransient
	protected transient DataScope dataScope;
	
	public void pre(DataScope dataScope) {
		Principal p = ShiroHelper.getPrincipal(); // 获取当前登录的用户信息
		Date date = new Date();
		// 根据数据域来获取相应的配置项
		if(dataScope != null) {
			String pref = dataScope.name();
			switch (dataScope) {
			case C:
				this.createBy = p.getLoginName() + ":" + p.getRealName();
				this.createTime = date;
			case U:
				this.lastUpdateBy = p.getLoginName() + ":" + p.getRealName();
				this.lastUpdateTime = date;
			default:
				this.delFlag = SpringContextHolder.getBean(SunnyConfig.class).getPros().getOrDefault(pref + ".delFlag", "N"); // 默认是未删除的数据
				this.status = SpringContextHolder.getBean(SunnyConfig.class).getPros().get(pref + ".status"); // 默认是有效的数据
				this.dataScope = dataScope;
				break;
			}
		}
	}

}
