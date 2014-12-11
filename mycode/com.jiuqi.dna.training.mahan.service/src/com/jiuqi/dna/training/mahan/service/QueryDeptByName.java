package com.jiuqi.dna.training.mahan.service;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QueryDeptByName extends QueryStatementDeclarator {


		public final ArgumentDefine arg_name;

		public final QueryColumnDefine c_recid;
		public final QueryColumnDefine c_deptname;
		public final QueryColumnDefine c_deptcreatedate;
		public final QueryColumnDefine c_deptparentid;
		public final QueryColumnDefine c_deptremark;
		public final QueryColumnDefine c_deptorder;

		public QueryDeptByName() {
			this.arg_name = this.query.getArguments().get(0);
			this.c_recid = this.query.getColumns().get(0);
			this.c_deptname = this.query.getColumns().get(1);
			this.c_deptcreatedate = this.query.getColumns().get(2);
			this.c_deptparentid = this.query.getColumns().get(3);
			this.c_deptremark = this.query.getColumns().get(4);
			this.c_deptorder = this.query.getColumns().get(5);
		}
	}
