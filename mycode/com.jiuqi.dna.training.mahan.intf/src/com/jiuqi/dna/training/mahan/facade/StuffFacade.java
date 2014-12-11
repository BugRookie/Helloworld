package com.jiuqi.dna.training.mahan.facade;

import java.util.Date;

import com.jiuqi.dna.core.type.GUID;

public interface StuffFacade {

	public GUID getRecid();

	public String getStuffName();

	public String getStuffRemark();

	public GUID getDeptId();

	public String getStuffSex();

	public Integer getStuffAge();
	
	public Date getStuffDate();
	
	public Integer getStuffOrder();
	
	public String getDeptName();
}
