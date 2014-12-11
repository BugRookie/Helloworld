package com.jiuqi.dna.training.mahan.task;

import java.util.Date;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

public class StuffTask extends Task<StuffTask.StuffMethod>{
	private GUID recid;
	private String stuffName;
	private String stuffRemark;
	private GUID deptId;
	private String stuffSex;
	private Integer stuffAge;
	private Integer sutffOrder;
	private Date stuffDate;
	
	
	public Date getStuffDate() {
		return stuffDate;
	}
	public void setStuffDate(Date stuffDate) {
		this.stuffDate = stuffDate;
	}
	public GUID getRecid() {
		return recid;
	}
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	public String getStuffName() {
		return stuffName;
	}
	public void setStuffName(String stuffName) {
		this.stuffName = stuffName;
	}
	public String getStuffRemark() {
		return stuffRemark;
	}
	public void setStuffRemark(String stuffRemark) {
		this.stuffRemark = stuffRemark;
	}
	public GUID getDeptId() {
		return deptId;
	}
	public void setDeptId(GUID deptId) {
		this.deptId = deptId;
	}
	public String getStuffSex() {
		return stuffSex;
	}
	public void setStuffSex(String stuffSex) {
		this.stuffSex = stuffSex;
	}
	public Integer getStuffAge() {
		return stuffAge;
	}
	public void setStuffAge(Integer stuffAge) {
		this.stuffAge = stuffAge;
	}
	public Integer getSutffOrder() {
		return sutffOrder;
	}
	public void setSutffOrder(Integer sutffOrder) {
		this.sutffOrder = sutffOrder;
	}
	
	public enum StuffMethod{
		Add,
		Update,
		Delete
	}
}
