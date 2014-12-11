package com.jiuqi.dna.training.mahan.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.training.mahan.facade.IDeptFacade;
import com.jiuqi.dna.training.mahan.msg.MsgClass;
import com.jiuqi.dna.training.mahan.task.DeptTask;
import com.jiuqi.dna.training.mahan.task.DeptTask.DeptMethod;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.template.launch.TemplateWindow;
import com.jiuqi.dna.ui.wt.widgets.ComboPanel;
import com.jiuqi.dna.ui.wt.widgets.Form;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.ListItem;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;

public class EditPage<TControls extends EditPageControls> extends Form<EditPageControls> {

	private com.jiuqi.dna.ui.wt.widgets.List list;
	private ComboPanel cp = controls.cop_UpDept;
	private DatePicker datePicker = controls.dap_deptDate;
	private Text deptNameText = controls.txt_DeptName;
	private Text deptRemark = controls.txt_Remark;
	private GUID deptParentId;
	private Boolean UPDATE_FLAG = false;
	private GUID deptIdModify;//待修改的部门ID
	
	public EditPage(Composite parent,Map<String,Object> map) {
		super(parent);
		
//		deptNameText.setText(map.get("title").toString());
		initComboPanel();
		initDate();
		InitListener();
		
		if(map!=null && map.containsKey("modifyDept")){
			UPDATE_FLAG=true;
			
			IDeptFacade deptFacade = (IDeptFacade)map.get("modifyDept");
			deptIdModify = deptFacade.getRecid();
			deptNameText.setText(deptFacade.getDeptName());
			datePicker.setDate(deptFacade.getDeptCreateDate());
			deptRemark.setText(deptFacade.getDeptRemark());
			
			ListItem[] listItems = list.getItems();
			ListItem item;
			IDeptFacade deptTemp;
			int indexSelected = 0;
			for (int i=0; i<listItems.length; i++){
				item = (ListItem)listItems[i];
				deptTemp = (IDeptFacade) item.getData();
				if(deptTemp.getRecid().equals(deptFacade.getDeptParentId())){
					indexSelected = i;
					break;
				}
			}
			list.select(indexSelected);
		}
	}

	private void initDate() {
		Date now = new Date();
		datePicker.setDate(now);
	}

	private void initComboPanel() {
		
		cp.setEditable(false);
		Composite c = cp.getComposite();
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
					cp.setText(dept.getDeptName());
					deptParentId = dept.getRecid();
				}
				i++;
			}
		}
//		list.setToolTipText(dept.getDeptName());
		list.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int index = list.getSelectionIndex();
				ListItem item = list.getItem(index);
				IDeptFacade dept = (IDeptFacade) item.getData();
				cp.setText(dept.getDeptName());
				deptParentId = dept.getRecid();
				cp.setPanelVisible(false);
			}
		});
	}
	
	private void InitListener() {
		getContext().regMessageListener(MsgClass.class, new MessageListener<MsgClass>(){
			@Override
			public void onMessage(Situation context, MsgClass message,
					MessageTransmitter<MsgClass> transmitter) {
//				transmitter.getRegHandle().setEnabled(false);
			}
		});
	}

	//保存
	protected void on_btn_Save_Action(ActionEvent actionEvent) throws Throwable {
		
		String deptName = deptNameText.getText();
		String strDate = datePicker.getText();
		String deptRemark = controls.txt_Remark.getText();
		
		DeptTask deptTask = new DeptTask();
		deptTask.setDeptName(deptName);
		deptTask.setDeptParentId(deptParentId);
		deptTask.setDeptRemark(deptRemark);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		deptTask.setDeptCreateDate(sdf.parse(strDate));
		
		if(UPDATE_FLAG == false){
			getContext().handle(deptTask, DeptMethod.Add);
		}else{
			deptTask.setRecid(deptIdModify);
			getContext().handle(deptTask, DeptMethod.Update);
		}
		MsgClass msg = new MsgClass();
		msg.setName("initTree");
		getContext().bubbleMessage(msg);
		getContext().bubbleMessage(new TemplateWindow.CloseMessage());
		
	}

	protected void on_btn_Cancel_Click(MouseEvent mouseEvent) {
		getContext().bubbleMessage(new TemplateWindow.CloseMessage());
	}
	
	
}