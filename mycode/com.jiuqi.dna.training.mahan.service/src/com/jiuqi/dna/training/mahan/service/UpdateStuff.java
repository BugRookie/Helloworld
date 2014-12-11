package com.jiuqi.dna.training.mahan.service;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.UpdateStatementDeclarator;

public final class UpdateStuff extends UpdateStatementDeclarator {


		public final ArgumentDefine arg_recid;
		public final ArgumentDefine arg_name;
		public final ArgumentDefine arg_age;
		public final ArgumentDefine arg_sex;
		public final ArgumentDefine arg_stuffdate;
		public final ArgumentDefine arg_stuffremark;
		public final ArgumentDefine arg_deptid;
		public final ArgumentDefine arg_stufforder;


		public UpdateStuff() {
			this.arg_recid = this.statement.getArguments().get(0);
			this.arg_name = this.statement.getArguments().get(1);
			this.arg_age = this.statement.getArguments().get(2);
			this.arg_sex = this.statement.getArguments().get(3);
			this.arg_stuffdate = this.statement.getArguments().get(4);
			this.arg_stuffremark = this.statement.getArguments().get(5);
			this.arg_deptid = this.statement.getArguments().get(6);
			this.arg_stufforder = this.statement.getArguments().get(7);
		}
	}
