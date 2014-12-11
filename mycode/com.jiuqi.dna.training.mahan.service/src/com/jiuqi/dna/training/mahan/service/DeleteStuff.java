package com.jiuqi.dna.training.mahan.service;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.DeleteStatementDeclarator;

public final class DeleteStuff extends DeleteStatementDeclarator {


		public final ArgumentDefine arg_recid;


		public DeleteStuff() {
			this.arg_recid = this.statement.getArguments().get(0);
		}
	}
