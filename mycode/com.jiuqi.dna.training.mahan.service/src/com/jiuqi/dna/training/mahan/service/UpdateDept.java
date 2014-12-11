package com.jiuqi.dna.training.mahan.service;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.UpdateStatementDeclarator;

public final class UpdateDept extends UpdateStatementDeclarator {


		public final ArgumentDefine arg_recid;
		public final ArgumentDefine arg_deptname;
		public final ArgumentDefine arg_deptcreatedate;
		public final ArgumentDefine arg_deptparentid;
		public final ArgumentDefine arg_deptremark;
		public final ArgumentDefine arg_deptorder;


		public UpdateDept() {
			this.arg_recid = this.statement.getArguments().get(0);
			this.arg_deptname = this.statement.getArguments().get(1);
			this.arg_deptcreatedate = this.statement.getArguments().get(2);
			this.arg_deptparentid = this.statement.getArguments().get(3);
			this.arg_deptremark = this.statement.getArguments().get(4);
			this.arg_deptorder = this.statement.getArguments().get(5);
		}
	}
