package com.jiuqi.dna.training.mahan.service;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QueryStuffByDeptId extends QueryStatementDeclarator {


		public final ArgumentDefine arg_deptid;

		public final QueryColumnDefine c_recid;
		public final QueryColumnDefine c_stuffname;
		public final QueryColumnDefine c_stuffage;
		public final QueryColumnDefine c_stuffsex;
		public final QueryColumnDefine c_stuffdate;
		public final QueryColumnDefine c_deptid;
		public final QueryColumnDefine c_remark;
		public final QueryColumnDefine c_stufforder;

		public QueryStuffByDeptId() {
			this.arg_deptid = this.query.getArguments().get(0);
			this.c_recid = this.query.getColumns().get(0);
			this.c_stuffname = this.query.getColumns().get(1);
			this.c_stuffage = this.query.getColumns().get(2);
			this.c_stuffsex = this.query.getColumns().get(3);
			this.c_stuffdate = this.query.getColumns().get(4);
			this.c_deptid = this.query.getColumns().get(5);
			this.c_remark = this.query.getColumns().get(6);
			this.c_stufforder = this.query.getColumns().get(7);
		}
	}
