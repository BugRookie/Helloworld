package com.jiuqi.dna.training.mahan.facade;

import java.util.Date;

import com.jiuqi.dna.core.type.GUID;

public interface IDeptFacade {
	public GUID getRecid();
	public String getDeptName();
	public String getDeptManager();
	public Date getDeptCreateDate();
	public GUID getDeptParentId();
	public String getDeptRemark();
	public Integer getDeptOrder();
}
