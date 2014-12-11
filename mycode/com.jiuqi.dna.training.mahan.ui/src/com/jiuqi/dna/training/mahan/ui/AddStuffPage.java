package com.jiuqi.dna.training.mahan.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.training.mahan.facade.IDeptFacade;
import com.jiuqi.dna.training.mahan.facade.StuffFacade;
import com.jiuqi.dna.training.mahan.msg.MsgClass;
import com.jiuqi.dna.training.mahan.task.StuffTask;
import com.jiuqi.dna.training.mahan.task.StuffTask.StuffMethod;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.template.launch.TemplateWindow;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.ComboPanel;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Form;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.ListItem;
import com.jiuqi.dna.ui.wt.widgets.Spinner;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.dna.ui.wt.events.MouseEvent;

public class AddStuffPage<TControls extends AddStuffPageControls> extends Form<AddStuffPageControls> {

	private com.jiuqi.dna.ui.wt.widgets.List list;
	private ComboPanel cpDept = controls.cop_dept;
	private ComboList clSex = controls.cbl_sex;
	private DatePicker datePicker = controls.dap_date;
	private Text textName = controls.txt_name;
	private Text textRemark = controls.txt_remark;
	private Spinner spinnerAge = controls.spr_age;
	private GUID deptParentId;
	private Boolean UPDATE_FLAG = false;
	
	public AddStuffPage(Composite parent,Map<String,StuffFacade> map) {
		super(parent);
		
		initData();
		
		if(map!=null && map.containsKey("modifyStuff")){
			UPDATE_FLAG=true;
			controls.btn_addSave.setVisible(false);
		
			StuffFacade stuffFacade = (StuffFacade) map.get("modifyStuff");
			textName.setText(stuffFacade.getStuffName());
			textName.setData(stuffFacade.getRecid());
			datePicker.setDate(stuffFacade.getStuffDate());
			spinnerAge.setSelection(stuffFacade.getStuffAge());
			if (stuffFacade.getStuffSex()!=null && stuffFacade.getStuffSex().equals("男")){
				clSex.setSelection(0);
			}else{
				clSex.setSelection(1);
			}
//			Composite composite = cpDept.getComposite();
//			Control[] child = composite.getChildren();
			ListItem[] listItems = list.getItems();
			ListItem item;
			IDeptFacade dept;
			int indexSelected = 0;
			for (int i=0; i<listItems.length; i++){
				item = (ListItem)listItems[i];
				dept = (IDeptFacade) item.getData();
				if(dept.getRecid().equals(stuffFacade.getDeptId())){
					indexSelected = i;
					break;
				}
			}
			list.select(indexSelected);
		}
		
	}

	private void initData() {
		//初始化日期
		Date now = new Date();
		datePicker.setDate(now);
		
		//初始化性别
		clSex.addItem("男");
		clSex.addItem("女");
		clSex.setSelection(0);
		
		//初始化年龄
		spinnerAge.setIncrement(1);
		
		//初始化部门列表
		cpDept.setEditable(false);
		Composite c = cpDept.getComposite();
		GridLayout gridLayout = new GridLayout(1);
		c.setLayout(gridLayout);
		
		list = new com.jiuqi.dna.ui.wt.widgets.List(c,JWT.SINGLE);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = JWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		list.setLayoutData(gridData);
		list.setBorder(new CBorder(0, JWT.BORDER_STYLE_DOTTED, 0));
		
		List<IDeptFacade> deptList=getContext().getList(IDeptFacade.class);
		ListItem listItem;
		if (deptList!=null && deptList.size()>0){
			int i=0;
			for (IDeptFacade dept:deptList){
				listItem = new ListItem(list);
				listItem.setData(dept);
				listItem.setText(dept.getDeptName());
				if(dept.getDeptParentId()==null){
					list.setSelection(i);
					cpDept.setText(dept.getDeptName());
					deptParentId = dept.getRecid();
				}
				i++;
			}
		}
		list.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int index = list.getSelectionIndex();
				ListItem item = list.getItem(index);
				IDeptFacade dept = (IDeptFacade) item.getData();
				cpDept.setText(dept.getDeptName());
				deptParentId = dept.getRecid();
				cpDept.setPanelVisible(false);
			}
		});
	}

	//新增或修改
	protected void on_btn_add_Click(MouseEvent mouseEvent) throws Throwable {
		StuffTask stuffTask = collectInfo();
		if(UPDATE_FLAG == false){
			getContext().handle(stuffTask, StuffMethod.Add);
		}else{
			getContext().handle(stuffTask, StuffMethod.Update);
		}
		MsgClass msg = new MsgClass();
		msg.setName("PaginateRender");
		getContext().bubbleMessage(msg);
		getContext().bubbleMessage(new TemplateWindow.CloseMessage());
	}

	protected void on_btn_cancel_Click(MouseEvent mouseEvent) {
		String stuffDate = datePicker.getText();
		System.out.println("··"+stuffDate);
//		MsgClass msg = new MsgClass();
//		msg.setName("PaginateRender");
//		getContext().bubbleMessage(msg);
		getContext().bubbleMessage(new TemplateWindow.CloseMessage());
		
	}

	protected void on_btn_addSave_Click(MouseEvent mouseEvent) throws Throwable {
		StuffTask stuffTask = collectInfo();
		getContext().handle(stuffTask, StuffMethod.Add);
		textRemark.setText("");
		spinnerAge.setSelection(0);
		clSex.setSelection(0);
		textName.setText("");
	}
	
	private StuffTask collectInfo() throws Throwable{
		String stuffName = textName.getText();
		String stuffDate = datePicker.getText();
		String stuffRemark = textRemark.getText();
		String sex = clSex.getText();
		Integer age = new Integer(spinnerAge.getText());
		
		
		StuffTask stuffTask = new StuffTask();
		stuffTask.setStuffName(stuffName);
		stuffTask.setStuffAge(age);
		stuffTask.setStuffSex(sex);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		stuffTask.setStuffDate(sdf.parse(stuffDate));
		stuffTask.setDeptId(deptParentId);
		stuffTask.setStuffRemark(stuffRemark);

		Object idData = textName.getData();
		if(idData!=null){
			stuffTask.setRecid((GUID)idData);
		}
		System.out.println(stuffName+"|"+stuffDate+"|"+stuffRemark+"|"+sex+"|"+age);
		return stuffTask;
	}
}