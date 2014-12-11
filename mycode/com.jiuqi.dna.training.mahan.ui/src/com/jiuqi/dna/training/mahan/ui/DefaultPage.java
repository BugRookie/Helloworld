package com.jiuqi.dna.training.mahan.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.training.mahan.Impl.DeptImpl;
import com.jiuqi.dna.training.mahan.common.DeptTreeCompare;
import com.jiuqi.dna.training.mahan.common.DeptTreeContentProvider;
import com.jiuqi.dna.training.mahan.facade.IDeptFacade;
import com.jiuqi.dna.training.mahan.facade.StuffFacade;
import com.jiuqi.dna.training.mahan.msg.MsgClass;
import com.jiuqi.dna.training.mahan.task.DeptTask;
import com.jiuqi.dna.training.mahan.task.DeptTask.DeptMethod;
import com.jiuqi.dna.training.mahan.task.StuffTask.StuffMethod;
import com.jiuqi.dna.training.mahan.task.StuffTask;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.template.launch.TemplateLauncher;
import com.jiuqi.dna.ui.template.launch.TemplateWindow;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.Rectangle;
import com.jiuqi.dna.ui.wt.grid2.Grid2;
import com.jiuqi.dna.ui.wt.grid2.GridCell;
import com.jiuqi.dna.ui.wt.grid2.GridEnums;
import com.jiuqi.dna.ui.wt.grid2.GridModel;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.viewers.TreeViewer;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.ComboPanel;
import com.jiuqi.dna.ui.wt.widgets.Form;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Menu;
import com.jiuqi.dna.ui.wt.widgets.MenuItem;
import com.jiuqi.dna.ui.wt.widgets.MessageDialog;
import com.jiuqi.dna.ui.wt.widgets.Tree;
import com.jiuqi.dna.bap.common.constants.BapImages;
import com.jiuqi.dna.bap.common.control.SearchBar;
import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.DocumentEvent;
import com.jiuqi.dna.ui.wt.events.DocumentListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.bap.common.paginate.Paginate;
import com.jiuqi.dna.bap.common.paginate.PaginateBar;
import com.jiuqi.dna.bap.common.paginate.ResourceFinder;
import com.jiuqi.dna.bap.common.paginate.render.PaginateRender;

public class DefaultPage<TControls extends DefaultPageControls> extends Form<DefaultPageControls> {
	
	private Grid2 grid2;
	private TreeViewer tr;
	private MessageDialog confirm;
	private int pageNum = 3;
	private PaginateBar paginateBar;
	private Paginate<StuffFacade> paginate;
	private SearchBar searchBar;
	
	
	public DefaultPage(Composite parent) {
		super(parent);
		initListener();//�Խ���������Ϣ����
		//��ʼ��TrreViewer�ؼ���
		initTree();
		//��ʼ��ToolBarͼ��
		initToolBarImage();
		//��ʼ��searchBar
		initSearchBar();
		
		paginate = new Paginate<StuffFacade>(StuffFacade.class, getContext(), pageNum);
		paginateBar = new PaginateBar(controls.cmp_paginate, paginate);
		paginateRender();
		
	}

	//��ʼ��searchBar
	private void initSearchBar() {
		searchBar = new SearchBar(controls.cmp_search);
		searchBar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				paginateFinder();
				paginateBar.init();
			}
		});
		//�ı��ı������
		searchBar.getControlText().addDocumentListener(new DocumentListener() {
			@Override
			public void documentUpdate(DocumentEvent docEvent) {
				paginateFinder();
				paginateBar.init();
			}
		});
	}

	private void paginateRender() {
		System.out.println("paginateRender");
		paginate.addRender(new PaginateRender<StuffFacade>(){
			@Override
			public void render(List<StuffFacade> records) {
				//��records��ֳɶ�ҳ��ͨ����ʼ��Ŀ����ֹ��Ŀ
				ArrayList<StuffFacade> listShow = new ArrayList<StuffFacade>();
				for(StuffFacade record : records){
					listShow.add(record);
				}
				//��ʼ��grid2������
				initGrid2Data(listShow);
			}
		});
		paginateBar.init();
	}
	
	private void paginateTreeFinder(){
		System.out.println("paginateTreeFinder");
		paginate.setResourceFinder(new ResourceFinder() {
			@Override
			public List<StuffFacade> find(){
				IDeptFacade mfc = (IDeptFacade) tr.getSelection();
				List<GUID> deptIdList = checekSelectTree(mfc);
				List<StuffFacade> stuffList = getContext().getList(StuffFacade.class,deptIdList);
				return stuffList;
			}
		});
	}
	
	//����������ѯʱʹ��
	private void paginateFinder(){
		paginate.setResourceFinder(new ResourceFinder() {
			@Override
			public List<StuffFacade> find() {
				String stuffName = searchBar.getText();
				Object obj = tr.getSelection();
				if(stuffName!=null && !stuffName.equals("")){
					if(obj!=null && obj instanceof IDeptFacade){
						IDeptFacade idf = (IDeptFacade) obj;
						List<GUID> deptIdList = checekSelectTree(idf);
						return getContext().getList(StuffFacade.class,stuffName,deptIdList);
					}else{
						return getContext().getList(StuffFacade.class,stuffName);
					}
				}
				if(obj!=null && obj instanceof IDeptFacade){
					IDeptFacade idf = (IDeptFacade) obj;
					List<GUID> deptIdList = checekSelectTree(idf);
					return getContext().getList(StuffFacade.class,deptIdList);
				}
				return getContext().getList(StuffFacade.class);
			}
		});
	}
/*
	private void initSearchBar(){
		searchBar = new SearchBar(controls.cmp_searchbar);
		searchBar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paginateFinder();
				//���³�ʼ����ҳ�ؼ�������ҳ��
				paginateBar.init();
			}
		});
	}
	*/

	//��ʼ���������
	protected void initGrid2Data(ArrayList<StuffFacade> list) {
		controls.cmp_table.clear();

		grid2 = new Grid2(controls.cmp_table);
		GridModel gridModel = grid2.getModel();
		gridModel.setEditMode(GridEnums.EditMode.READONLY);
		
		if(list!=null && list.size()!=0){
			gridModel.setRowCount(list.size()+1);
		}else{
			gridModel.setRowCount(2);
		}
		gridModel.setColumnCount(8);
		
		GridCell cellOrder = gridModel.getGridCell(0, 0);
		cellOrder.setShowText("���");
		GridCell cellNameHeader = gridModel.getGridCell(2, 0);
		cellNameHeader.setShowText("����");
		GridCell cellAgeHeader = gridModel.getGridCell(3, 0);
		cellAgeHeader.setShowText("����");
		GridCell cellSexHeader = gridModel.getGridCell(4, 0);
		cellSexHeader.setShowText("�Ա�");
		GridCell cellDateHeader = gridModel.getGridCell(5, 0);
		cellDateHeader.setShowText("��ְ����");
		GridCell cellDeptHeader = gridModel.getGridCell(6, 0);
		cellDeptHeader.setShowText("��������");
		GridCell cellRemarkHeader = gridModel.getGridCell(7, 0);
		cellRemarkHeader.setShowText("��ע");
		
		GridCell cellName, cellButton, cellAge, cellSex, cellDate, cellDept, cellRemark;
		int i=1;
		for(StuffFacade stuff:list){
			
			cellButton = gridModel.getGridCell(1, i);
			Button b = new Button(grid2,JWT.CHECK);
			cellButton.setControl(b);
			cellButton.setData(stuff);
			
			cellName = gridModel.getGridCell(2, i);
			cellName.setShowText(stuff.getStuffName());
			cellAge = gridModel.getGridCell(3, i);
			cellAge.setShowText(stuff.getStuffAge().toString());
			cellSex = gridModel.getGridCell(4, i);
			cellSex.setShowText(stuff.getStuffSex());
			cellDate = gridModel.getGridCell(5, i);
			
			Date date = stuff.getStuffDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] strDate = sdf.format(date).split("-");
			cellDate.setShowText(strDate[0] + "��" + strDate[1] + "��" + strDate[2] + "��");
			
			cellDept = gridModel.getGridCell(6, i);
			cellDept.setShowText(stuff.getDeptName());
			cellRemark = gridModel.getGridCell(7, i);
			cellRemark.setShowText(stuff.getStuffRemark());
			
			i++;
		}
		controls.cmp_table.layout();
	}

	//��ʼ��������
	private void initTree() {
		Composite cp = controls.cmp_3;
		cp.clear();
		tr = new TreeViewer(cp,JWT.SINGLE|JWT.V_SCROLL|JWT.H_SCROLL);
		//ʵ����һ�������ݺͱ�ǩ�ṩ����
		DeptTreeContentProvider deptTreeContentProvider = new DeptTreeContentProvider(getContext());
		//ʵ����һ����Ԫ�رȽ�����
		DeptTreeCompare deptTreeCompare = new DeptTreeCompare();
		//����TreeViewer
		tr.setContentProvider(deptTreeContentProvider);
		tr.setLabelProvider(deptTreeContentProvider);
		tr.setComparer(deptTreeCompare);
		Object[] elements = deptTreeContentProvider.getElements("�������");
//		List<IDeptFacade> list = getContext().getList(IDeptFacade.class,"�������",1);
//		List<IDeptFacade> list = getContext().getList(IDeptFacade.class,"��������",1);
		if (elements != null && elements.length > 0){
			//��ȡ���ڵ�DeptFacade����Ԫ�أ�Ĭ�����ñ��е�һ��
			IDeptFacade mf = (IDeptFacade) elements[0];
			//����TrreViewer�ڵ�
			//ֻ��Ҫ������ڵ�Ԫ�أ����������ڵ������ͼ���ɿ�����
			tr.setInput(mf);
		}
		
		tr.getTree().addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent event){
				if (tr.getSelection()!=null && tr.getSelection() instanceof IDeptFacade){
//					paginateTreeFinder();
					paginateFinder();
					paginateBar.init();
				}
			}
		});
		cp.layout();
	}

	//��ʼ������ѡ��ͼ��
	private void initToolBarImage() {
		ImageDescriptor addImage = getContext().find(ImageDescriptor.class, BapImages.ico_add);
		controls.tbi_Add.setImage(addImage);
		
		Menu menu = new Menu(controls.tbr_1,JWT.PUSH);
		MenuItem menuItem1 = new MenuItem(menu,JWT.PUSH);
		menuItem1.setText("��������");
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("title", "�������");
				TemplateWindow tw = TemplateLauncher.openTemplateWindow(controls.cmp_2, "EditPage",map, JWT.MODAL|JWT.CLOSE|JWT.RESIZE|JWT.MAXIMUM, JWT.NO);
				tw.setTitle("����������Ϣ");
				ImageDescriptor addImage = getContext().find(ImageDescriptor.class,BapImages.ico_add);
				tw.setIcon(addImage);
			}
		});
		MenuItem menuItem2 = new MenuItem(menu,JWT.PUSH);
		menuItem2.setText("����Ա��");
		menuItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, Object> map = new HashMap<String, Object>(); 
				TemplateWindow tw = TemplateLauncher.openTemplateWindow(controls.cmp_2, "AddStuffPage",map, JWT.MODAL|JWT.CLOSE|JWT.RESIZE|JWT.MAXIMUM,JWT.NO);
				tw.setTitle("����Ա��Ϣ");
				ImageDescriptor addImage = getContext().find(ImageDescriptor.class,BapImages.ico_add);
				tw.setIcon(addImage);
			}
		});
		controls.tbi_Add.setMenu(menu);
		
		ImageDescriptor updateImage = getContext().find(ImageDescriptor.class, BapImages.ico_modify_img);
		controls.tbi_Update.setImage(updateImage);
		ImageDescriptor deleteImage = getContext().find(ImageDescriptor.class, BapImages.ico_delete);
		controls.tbi_Delete.setImage(deleteImage);
		ImageDescriptor upImage = getContext().find(ImageDescriptor.class, BapImages.ico_moveup);
		controls.tbi_Up.setImage(upImage);
		ImageDescriptor downImage = getContext().find(ImageDescriptor.class, BapImages.ico_movedown);
		controls.tbi_Down.setImage(downImage);
		ImageDescriptor closeImage = getContext().find(ImageDescriptor.class, BapImages.ico_close_window);
		controls.tbi_Close.setImage(closeImage);
	}
	
	
	private void initListener(){
		getContext().regMessageListener(MsgClass.class, new MessageListener<MsgClass>() {
			@Override
			public void onMessage(Situation context, MsgClass message,
					MessageTransmitter<MsgClass> transmitter) {
				//����Ϣ����ֹͣ
//				transmitter.getRegHandle().setEnabled(false);
				//���巢����Ϣ���������ȼ�������(�˴��������ѭ��)
//				getContext().bubbleMessage(message);
				//��������Ϣ��������
//				transmitter.getRegHandle().setEnabled(true);
				if(message == null || message.getName() ==null)
					return;
				String msg = message.getName();
				if(msg.equals("PaginateRender")){
					paginateRender();
				}
				if(msg.equals("initTree")){
					initTree();
				}
			}
		});
	}
	
	//�޸� ���ź���Ա
	protected void on_tbi_Update_Action(ActionEvent actionEvent) {
		//�޸���Ա
		ArrayList<StuffFacade> stuffSelect = checkSelectGrid2();
		if (stuffSelect!=null && stuffSelect.size()>0){
			StuffFacade stuffFacade = stuffSelect.get(0);
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("modifyStuff", stuffFacade);
			TemplateWindow tw = TemplateLauncher.openTemplateWindow(this, "AddStuffPage", map, JWT.MODAL|JWT.CLOSE|JWT.RESIZE|JWT.MAXIMUM, JWT.NO);
			tw.setTitle("�޸���Ա��Ϣ");
			ImageDescriptor addImage = getContext().find(ImageDescriptor.class,BapImages.ico_modify);
			tw.setIcon(addImage);
//			paginateRender();
			return;
		}
		//�޸Ĳ���
		Object obj = tr.getSelection();
		if (obj != null){
			DeptTreeContentProvider treeProvider = new DeptTreeContentProvider(getContext());
			if(treeProvider.getParent(obj) == null)
				return;
			IDeptFacade deptDeptFacade = (IDeptFacade)obj;
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("modifyDept", deptDeptFacade);
			TemplateWindow tw = TemplateLauncher.openTemplateWindow(this, "EditPage", map, JWT.MODAL|JWT.CLOSE|JWT.RESIZE|JWT.MAXIMUM, JWT.NO);
			tw.setTitle("�޸Ĳ�����Ϣ");
			ImageDescriptor addImage = getContext().find(ImageDescriptor.class,BapImages.ico_modify);
			tw.setIcon(addImage);
			return;
		}
	}
	
	//ɾ�����š�ɾ����Ա
	@SuppressWarnings("deprecation")
	protected void on_tbi_Delete_Action(ActionEvent actionEvent) {
		//ɾ����Ա
		ArrayList<StuffFacade> stuffSelect = checkSelectGrid2();
		if (stuffSelect!=null && stuffSelect.size()>0){
			confirm = MessageDialog.confirm("ɾ��ȷ��", "�Ƿ�Ҫɾ����");
			confirm.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent event) {
					if(confirm.getReturnCode() == JWT.OK){
						StuffTask stuffTask = new StuffTask();
						for (StuffFacade sf:checkSelectGrid2()){
							stuffTask.setRecid(sf.getRecid());
							getContext().handle(stuffTask,StuffMethod.Delete);
						}
						paginateFinder();
						paginateBar.init();
					}
				}
			});
			return;
		}
		//ɾ�����ż���������Ա
		Object obj = tr.getSelection();
		if (obj != null){
			DeptTreeContentProvider treeProvider = new DeptTreeContentProvider(getContext());
			if(treeProvider.getParent(obj) == null)
				return;
			confirm = MessageDialog.confirm("ɾ��ȷ��", "�Ƿ�Ҫɾ�����ż���������Ա��");
			confirm.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					if(confirm.getReturnCode() == JWT.OK){
						IDeptFacade dept = (IDeptFacade) tr.getSelection();
						ArrayList<GUID> deptIdList = checekSelectTree(dept);
						DeptTask deptTask;
						for (GUID deptId :deptIdList){
							deptTask = new DeptTask();
							deptTask.setRecid(deptId);
							getContext().handle(deptTask,DeptMethod.Delete);
						}
						paginateFinder();
						paginateBar.init();
					}
				}
			});
		}
	}

	//����ѡ�е����ڵ���ӽڵ�ID
	private ArrayList<GUID> checekSelectTree(IDeptFacade dept){
		ArrayList<GUID> deptIdList = new ArrayList<GUID>();
		if (dept == null)
			return null;
		DeptTreeContentProvider treeProvider = new DeptTreeContentProvider(getContext());
		iteratorTree(treeProvider, dept, deptIdList);
		return deptIdList;
	}
	//������
	private void iteratorTree(DeptTreeContentProvider treeProvider,IDeptFacade dept,List<GUID> idList){
		idList.add(dept.getRecid());
		if (dept==null || !treeProvider.hasChildren(dept))
			return;
		Object[] child = treeProvider.getChildren(dept);
		for (int i=0; i<child.length; i++){
			iteratorTree(treeProvider, (IDeptFacade)child[i], idList);
		}
	}
	
	//����Grid2�б�ѡ��
	private ArrayList<StuffFacade> checkSelectGrid2(){
		ArrayList<StuffFacade> rList = new ArrayList<StuffFacade>();
		int rowCount = grid2.getModel().getRowCount();
		for(int i=1; i<rowCount; i++){
			Button b = (Button) grid2.getModel().getGridCell(1, i).getControl();
			if(b!=null && b.getSelection()){
				StuffFacade stuff = (StuffFacade) grid2.getModel().getGridCell(1, i).getData();
				rList.add(stuff);
			}
		}
		return rList;
	}

	protected void on_tbi_Up_Action(ActionEvent actionEvent) {
		
		ArrayList<StuffFacade> rList = checkSelectGrid2();
		if(rList!=null && rList.size()==1){
			changeStuffOrder(1);	
		}else{
			changeDeptOrder(1);
		}
	}

	protected void on_tbi_Down_Action(ActionEvent actionEvent) {
		ArrayList<StuffFacade> rList = checkSelectGrid2();
		if(rList!=null && rList.size()==1){
			changeStuffOrder(-1);
		}else{
			changeDeptOrder(-1);
		}
	}
	//Ա��������λ
	private void changeStuffOrder(int flag){
		//flag=1���� flag=-1����
		ArrayList<StuffFacade> rList = checkSelectGrid2();
		int rowCount = grid2.getModel().getRowCount();
		if (rList==null || rList.size()!=1 || rowCount==2)
			return;
		
		int rowNum = 0;
		int changeRow = 0;
		for(int i=1; i<rowCount; i++){
			Button b = (Button) grid2.getModel().getGridCell(1, i).getControl();
			if(b.getSelection()){
				rowNum = i;
				break;
			}
		}
		if((flag==1 & rowNum == 1) || (flag==-1 & rowNum==rowCount-1))
			return;
		if(flag==1){
			changeRow = rowNum-1;
		}else{
			changeRow = rowNum+1;
		}
		Button b = (Button) grid2.getModel().getGridCell(1, rowNum).getControl();
		b.setSelection(false);
		b = (Button) grid2.getModel().getGridCell(1, changeRow).getControl();
		b.setSelection(true);
		
		GridCell c1,c2;
		String s = "";
		int columnCount = grid2.getModel().getColumnCount();
		for (int i=1; i<columnCount; i++){
			c1 = grid2.getModel().getGridCell(i, rowNum);
			c2 = grid2.getModel().getGridCell(i, changeRow);
			s = c1.getShowText();
			c1.setShowText(c2.getShowText());
			c2.setShowText(s);
		}
		c1 = grid2.getModel().getGridCell(1, rowNum);
		c2 = grid2.getModel().getGridCell(1, changeRow);
		
		StuffFacade stuff1 = (StuffFacade)c1.getData();
		StuffFacade stuff2 = (StuffFacade)c2.getData();
		c1.setData(stuff2);
		c2.setData(stuff1);
		
		StuffTask st1 = ensembleStuffTask(stuff1);
		st1.setSutffOrder(stuff2.getStuffOrder());
		StuffTask st2 = ensembleStuffTask(stuff2);
		st2.setSutffOrder(stuff1.getStuffOrder());
		
		getContext().handle(st1,StuffMethod.Update);
		getContext().handle(st2,StuffMethod.Update);
	}
	//��StuffFcase���󣬷�װStuffTask����
	private StuffTask ensembleStuffTask(StuffFacade sf){
		StuffTask stuffTask = new StuffTask();
		stuffTask.setRecid(sf.getRecid());
		stuffTask.setStuffName(sf.getStuffName());
		stuffTask.setStuffAge(sf.getStuffAge());
		stuffTask.setStuffSex(sf.getStuffSex());
		stuffTask.setStuffDate(sf.getStuffDate());
		stuffTask.setDeptId(sf.getDeptId());
		stuffTask.setStuffRemark(sf.getStuffRemark());
		return stuffTask;
	}
	
	
	//����������
	private void changeDeptOrder(int flag){
		Object obj = tr.getSelection();
		DeptTreeContentProvider treeProvider = new DeptTreeContentProvider(getContext());
		if(obj==null || treeProvider.getParent(obj)==null)
			return;
		IDeptFacade deptFacade = (IDeptFacade)obj;
		List<IDeptFacade> deptList = getContext().getList(IDeptFacade.class,deptFacade.getDeptParentId());
		if(deptList==null || deptList.size()<=1)
			return;
		int index=0;
		int toIndex=0;
		for(int i=0; i<deptList.size();i++){
			if(deptList.get(i).getRecid().equals(deptFacade.getRecid())){
				index = i;
				break;
			}
		}
		
		if((flag==1&&index==0) || (flag==-1 && index==deptList.size()-1))
			return;
		
		if(flag==1)
			toIndex = index-1;
		if(flag==-1)
			toIndex = index+1;
		
		Integer order;
		order = deptFacade.getDeptOrder();
		DeptTask deptTask1 = assembleDept(deptFacade);
		DeptTask deptTask2 = assembleDept(deptList.get(toIndex));
		
		deptTask1.setDeptOrder(deptTask2.getDeptOrder());
		deptTask2.setDeptOrder(order);
		
		getContext().handle(deptTask1,DeptMethod.Update);
		getContext().handle(deptTask2,DeptMethod.Update);
		initTree();
	}
	
	//��װdept
	private DeptTask assembleDept(IDeptFacade deptFacade){
		DeptTask deptTask = new DeptTask();
		deptTask.setRecid(deptFacade.getRecid());
		deptTask.setDeptName(deptFacade.getDeptName());
		deptTask.setDeptParentId(deptFacade.getDeptParentId());
		deptTask.setDeptRemark(deptFacade.getDeptRemark());
		deptTask.setDeptCreateDate(deptFacade.getDeptCreateDate());
		deptTask.setDeptOrder(deptFacade.getDeptOrder());
		return deptTask;
	}
	
	
	
	
	
	protected void on_tbi_Close_Action(ActionEvent actionEvent) {
//		paginateRender();
//		canelCheck();
//		getContext().handle(new StuffTask(),StuffMethod.Add);
//		paginateFinder();
		
		
	}
	
}