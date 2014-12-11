package com.jiuqi.dna.training.mahan.codepage;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.UIEntry;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Shell;

public class CalcPage implements UIEntry{

	@Override
	public void createUI(String[] args, Shell shell) {
		InitComponents(shell);
	}

	private void InitComponents(Shell shell) {
		
		shell.setLayout(new GridLayout(1));
		shell.setBackground(Color.COLOR_BLACK);
		/***************************∂•≤„«¯”Ú*************************/
		Composite comp_Main = new Composite(shell);
		comp_Main.setBackground(Color.COLOR_BURLYWOOD);
		GridLayout comp_Main_Layout = new GridLayout(1);
		comp_Main.setLayout(comp_Main_Layout);
		GridData comp_Main_Data = new GridData();
		comp_Main_Data.horizontalAlignment = JWT.FILL;
		comp_Main_Data.grabExcessVerticalSpace = true;
		comp_Main_Data.heightHint = 25;
		
		comp_Main.setLayoutData(comp_Main_Data);
		
		
	}

}
