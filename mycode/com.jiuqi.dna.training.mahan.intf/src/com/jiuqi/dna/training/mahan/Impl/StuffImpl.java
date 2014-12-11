package com.jiuqi.dna.training.mahan.Impl;

import java.util.Date;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.training.mahan.facade.StuffFacade;

public class StuffImpl implements StuffFacade{
	
	private GUID recid;
	private String stuffName;
	private String stuffRemark;
	private GUID deptId;
	private String stuffSex;
	private int stuffAge;
	private Integer stuffOrder;
	private Date stuffDate;
	
	private String deptName;
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Date getStuffDate() {
		return stuffDate;
	}
	public void setStuffDate(Date stuffDate) {
		this.stuffDate = stuffDate;
	}
	public GUID getRecid() {
		return recid;
	}
	public String getStuffName() {
		return stuffName;
	}
	public String getStuffRemark() {
		return stuffRemark;
	}
	public GUID getDeptId() {
		return deptId;
	}
	public String getStuffSex() {
		return stuffSex;
	}
	public Integer getStuffAge() {
		return stuffAge;
	}
	
	public Integer getStuffOrder() {
		return stuffOrder;
	}
	public void setStuffOrder(Integer stuffOrder) {
		this.stuffOrder = stuffOrder;
	}
	public void setStuffAge(int stuffAge) {
		this.stuffAge = stuffAge;
	}
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	public void setStuffName(String stuffName) {
		this.stuffName = stuffName;
	}
	public void setStuffRemark(String stuffRemark) {
		this.stuffRemark = stuffRemark;
	}
	public void setDeptId(GUID deptId) {
		this.deptId = deptId;
	}
	public void setStuffSex(String stuffSex) {
		this.stuffSex = stuffSex;
	}
	public void setStuffAge(Integer stuffAge) {
		this.stuffAge = stuffAge;
	}
	
	
	
}
