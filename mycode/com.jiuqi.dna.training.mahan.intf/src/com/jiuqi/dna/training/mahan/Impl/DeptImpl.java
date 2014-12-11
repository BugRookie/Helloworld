package com.jiuqi.dna.training.mahan.Impl;

import java.util.Date;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.training.mahan.facade.IDeptFacade;

public class DeptImpl implements IDeptFacade{
	private GUID recid;
	private String deptName;
	private String deptManager;
	private Date deptCreateDate;
	private GUID deptParentId;
	private String deptRemark;
	private Integer deptOrder;
	
	public Integer getDeptOrder() {
		return deptOrder;
	}

	public void setDeptOrder(Integer deptOrder) {
		this.deptOrder = deptOrder;
	}

	public GUID getRecid() {
		return recid;
	}

	public void setRecid(GUID recid) {
		this.recid = recid;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptManager() {
		return deptManager;
	}

	public void setDeptManager(String deptManager) {
		this.deptManager = deptManager;
	}

	public Date getDeptCreateDate() {
		return deptCreateDate;
	}

	public void setDeptCreateDate(Date deptCreateDate) {
		this.deptCreateDate = deptCreateDate;
	}

	public GUID getDeptParentId() {
		return deptParentId;
	}

	public void setDeptParentId(GUID deptParentId) {
		this.deptParentId = deptParentId;
	}

	public String getDeptRemark() {
		return deptRemark;
	}

	public void setDeptRemark(String deptRemark) {
		this.deptRemark = deptRemark;
	}
}
