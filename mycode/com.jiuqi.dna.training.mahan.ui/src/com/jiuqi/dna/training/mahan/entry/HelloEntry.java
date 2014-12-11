package com.jiuqi.dna.training.mahan.entry;

import com.jiuqi.dna.ui.wt.UIEntry;
import com.jiuqi.dna.ui.wt.widgets.Shell;

public class HelloEntry implements UIEntry{

	@Override
	public void createUI(String[] args, Shell shell) {
		//shell.showPage("HelloDNA");
		shell.showPage("DefaultPage");
		//shell.showPage("EditPage");
	}

}
