package com.jiuqi.dna.training.mahan.service;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class OrmQueryByDeptName extends ORMDeclarator<com.jiuqi.dna.training.mahan.Impl.DeptImpl> {

	public final ArgumentDefine arg_name;

	public final QueryColumnDefine c_deptManager;
	public final QueryColumnDefine c_deptName;
	public final QueryColumnDefine c_deptParentId;
	public final QueryColumnDefine c_deptRemark;
	public final QueryColumnDefine c_recid;

	public OrmQueryByDeptName() {
		this.arg_name = this.orm.getArguments().get(0);
		this.c_deptManager = this.orm.getColumns().get(0);
		this.c_deptName = this.orm.getColumns().get(1);
		this.c_deptParentId = this.orm.getColumns().get(2);
		this.c_deptRemark = this.orm.getColumns().get(3);
		this.c_recid = this.orm.getColumns().get(4);
	}
}
