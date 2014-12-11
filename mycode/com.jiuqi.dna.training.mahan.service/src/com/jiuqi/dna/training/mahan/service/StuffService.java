package com.jiuqi.dna.training.mahan.service;

import java.security.Guard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.da.RecordSetField;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.training.mahan.Impl.StuffImpl;
import com.jiuqi.dna.training.mahan.facade.IDeptFacade;
import com.jiuqi.dna.training.mahan.facade.StuffFacade;
import com.jiuqi.dna.training.mahan.task.StuffTask;
import com.jiuqi.dna.training.mahan.task.StuffTask.StuffMethod;

public class StuffService extends Service{

	private InsertStuff insertStuff;
	private DeleteStuff deleteStuff;
	private UpdateStuff updateStuff;
	private QueryStuff queryStuff;
	private QueryStuffOrder queryStuffOrder;
	private QueryStuffByDeptId queryStuffByDeptId;
	private QueryStuffByName queryStuffByName;
	private QueryStuffByNameAndDeptId queryStuffByNameAndDeptId;
	
	protected StuffService(InsertStuff insertStuff,DeleteStuff deleteStuff,UpdateStuff updateStuff,
			QueryStuff queryStuff,QueryStuffOrder queryStuffOrder,QueryStuffByDeptId queryStuffByDeptId,
			QueryStuffByName queryStuffByName,QueryStuffByNameAndDeptId queryStuffByNameAndDeptId) {
		super("StuffService");
		this.insertStuff = insertStuff;
		this.deleteStuff = deleteStuff;
		this.updateStuff = updateStuff;
		this.queryStuff = queryStuff;
		this.queryStuffOrder = queryStuffOrder;
		this.queryStuffByDeptId = queryStuffByDeptId;
		this.queryStuffByName = queryStuffByName;
		this.queryStuffByNameAndDeptId = queryStuffByNameAndDeptId;
		System.out.println("===============start StuffService");
	}
	
	
	//插入
	@Publish
	protected class StuffTaskMethodInsert extends TaskMethodHandler<StuffTask,StuffMethod>{
		protected StuffTaskMethodInsert() {
			super(StuffMethod.Add);
		}
		@Override
		protected void handle(Context context, StuffTask stuffTask) throws Throwable {
			System.out.println("Stuff ADD");
			int sutffOrder = 0;
			RecordSet rs = context.openQuery(queryStuffOrder);
			if(rs != null && rs.getRecordCount()>0){
				rs.next();
				RecordSetField field = rs.getFields().get(0);
				sutffOrder = field.getInt() + 1;
			}
			stuffTask.setSutffOrder(sutffOrder);
			context.executeUpdate(insertStuff, context.newRECID(),stuffTask.getStuffName(),stuffTask.getStuffAge(),
					stuffTask.getStuffSex(),stuffTask.getStuffDate(),stuffTask.getStuffRemark(),stuffTask.getDeptId(),stuffTask.getSutffOrder());
		}
	}
	
	//修改
	@Publish
	protected class StuffTaskMethodUpdate extends TaskMethodHandler<StuffTask, StuffMethod>{
		protected StuffTaskMethodUpdate() {
			super(StuffMethod.Update);
		}
		@Override
		protected void handle(Context context, StuffTask stuffTask) throws Throwable {
			context.executeUpdate(updateStuff, stuffTask.getRecid(),stuffTask.getStuffName(),stuffTask.getStuffAge(),
					stuffTask.getStuffSex(),stuffTask.getStuffDate(),stuffTask.getStuffRemark(),stuffTask.getDeptId(),stuffTask.getSutffOrder());
		}
	}
	
	//删除
	@Publish
	protected class StuffTaskMethodDelete extends TaskMethodHandler<StuffTask, StuffMethod>{
		protected StuffTaskMethodDelete() {
			super(StuffMethod.Delete);
		}
		@Override
		protected void handle(Context context, StuffTask stuffTask) throws Throwable {
			context.executeUpdate(deleteStuff, stuffTask.getRecid());
		}
	}
	
	//获取全部员工信息
	@Publish
	protected class StuffResultListProvider extends ResultListProvider<StuffFacade>{

		@Override
		protected void provide(Context context, List<StuffFacade> list)throws Throwable {
			System.out.println("StuffResultListProvider ");
			RecordSet rs = context.openQuery(queryStuff);
			List<StuffFacade> stuffList = assembleStuffFacade(rs,context);
			list.addAll(stuffList);
		}
	}
	
	//查询指定部门ID下的员工
	@Publish
	protected class StuffByDeptIdListProvider extends OneKeyResultListProvider<StuffFacade, ArrayList<GUID>>{
		@Override
		protected void provide(Context context, ArrayList<GUID> deptIdList, List<StuffFacade> list)
				throws Throwable {
			System.out.println("StuffByDeptIdListProvider ");
			if (deptIdList!=null && deptIdList.size()>0){
				for(GUID deptId : deptIdList){
					RecordSet rs = context.openQuery(queryStuffByDeptId, deptId);
					if(rs!=null && rs.getRecordCount()>0){
						List<StuffFacade> stuffList = assembleStuffFacade(rs,context);
						list.addAll(stuffList);
					}
				}
				Collections.sort(list, new ComparatorStuff());
			}
		}
	}
	//根据员工姓名查询
	@Publish
	protected class StuffByName extends OneKeyResultListProvider<StuffFacade, String>{
		@Override
		protected void provide(Context context, String name, List<StuffFacade> list)
				throws Throwable {
			if(name!=null){
				RecordSet rs = context.openQuery(queryStuffByName, name);
				if(rs!=null && rs.getRecordCount()>0){
					List<StuffFacade> stuffList = assembleStuffFacade(rs,context);
					list.addAll(stuffList);
				}
			}
		}
	}
	
	//根据员工姓名和部门id查询
	@Publish
	protected class StuffByNameAndDeptId extends TwoKeyResultListProvider<StuffFacade, String, ArrayList<GUID>>{
		@Override
		protected void provide(Context context, String name, ArrayList<GUID> deptIdList,
				List<StuffFacade> list) throws Throwable {
			if (name!=null && deptIdList!=null && deptIdList.size()>0){
				for(GUID deptId : deptIdList){
					RecordSet rs = context.openQuery(queryStuffByNameAndDeptId, name,deptId);
					if(rs!=null && rs.getRecordCount()>0){
						List<StuffFacade> stuffList = assembleStuffFacade(rs,context);
						list.addAll(stuffList);
					}
				}
			}
			Collections.sort(list, new ComparatorStuff());
		}
		
	}
	
	
	//将查询得到的RecordSet 转换成StuffFacade
	private List<StuffFacade> assembleStuffFacade(RecordSet rs,Context context){
		ArrayList<StuffFacade> stuffList = new ArrayList<StuffFacade>();
		StuffImpl stuff;
		while (rs.next()){
			RecordSetField recid = rs.getFields().get(0);
			RecordSetField stuffName = rs.getFields().get(1);
			RecordSetField stuffAge = rs.getFields().get(2);
			RecordSetField stuffSex = rs.getFields().get(3);
			RecordSetField stuffDate = rs.getFields().get(4);
			RecordSetField deptId = rs.getFields().get(5);
			RecordSetField stuffRemark = rs.getFields().get(6);
			RecordSetField stuffOrder = rs.getFields().get(7);
			
			List<IDeptFacade> deptList = context.getList(IDeptFacade.class,deptId.getGUID(),1);
			String deptName="";
			if(deptList!=null && deptList.size()>0){
				 IDeptFacade deptFacade = deptList.get(0);
				 deptName = deptFacade.getDeptName();
			}
			stuff = new StuffImpl();
			stuff.setRecid(recid.getGUID());
			stuff.setStuffName(stuffName.getString());
			stuff.setStuffAge(stuffAge.getInt());
			stuff.setStuffSex(stuffSex.getString());
			stuff.setStuffDate(new Date(stuffDate.getDate()));
			stuff.setDeptId(deptId.getGUID());
			stuff.setStuffRemark(stuffRemark.getString());
			stuff.setStuffOrder(stuffOrder.getInt());
			stuff.setDeptId(deptId.getGUID());
			stuff.setDeptName(deptName);
			
			stuffList.add(stuff);
		}
		return stuffList;
	}
	
	private class ComparatorStuff implements Comparator{
		@Override
		public int compare(Object o1, Object o2) {
			StuffFacade s1 = (StuffFacade)o1;
			StuffFacade s2 = (StuffFacade)o2;
			return s2.getStuffOrder().compareTo(s1.getStuffOrder());
		}
		
	}

}
