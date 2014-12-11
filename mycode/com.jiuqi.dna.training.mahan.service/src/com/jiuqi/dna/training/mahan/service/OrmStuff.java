package com.jiuqi.dna.training.mahan.service;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class OrmStuff extends ORMDeclarator<com.jiuqi.dna.training.mahan.Impl.StuffImpl> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_deptId;
	public final QueryColumnDefine c_recid;
	public final QueryColumnDefine c_stuffName;
	public final QueryColumnDefine c_stuffRemark;
	public final QueryColumnDefine c_stuffSex;

	public OrmStuff() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_deptId = this.orm.getColumns().get(0);
		this.c_recid = this.orm.getColumns().get(1);
		this.c_stuffName = this.orm.getColumns().get(2);
		this.c_stuffRemark = this.orm.getColumns().get(3);
		this.c_stuffSex = this.orm.getColumns().get(4);
	}
}
