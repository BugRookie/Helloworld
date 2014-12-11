package com.jiuqi.dna.training.mahan.service;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.InsertStatementDeclarator;

public final class InsertDept extends InsertStatementDeclarator {


		public final ArgumentDefine arg_recid;
		public final ArgumentDefine arg_deptname;
		public final ArgumentDefine arg_deptcreatedate;
		public final ArgumentDefine arg_parentid;
		public final ArgumentDefine arg_deptremark;


		public InsertDept() {
			this.arg_recid = this.statement.getArguments().get(0);
			this.arg_deptname = this.statement.getArguments().get(1);
			this.arg_deptcreatedate = this.statement.getArguments().get(2);
			this.arg_parentid = this.statement.getArguments().get(3);
			this.arg_deptremark = this.statement.getArguments().get(4);
		}
	}
