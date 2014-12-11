package com.jiuqi.dna.training.mahan.service;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QueryDeptOrder extends QueryStatementDeclarator {


		public final QueryColumnDefine c_maxorder;

		public QueryDeptOrder() {
			this.c_maxorder = this.query.getColumns().get(0);
		}
	}
