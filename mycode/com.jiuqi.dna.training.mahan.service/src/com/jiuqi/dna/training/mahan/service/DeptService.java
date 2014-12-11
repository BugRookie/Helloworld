package com.jiuqi.dna.training.mahan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.da.RecordSetField;
import com.jiuqi.dna.core.def.query.StatementDeclare;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.training.mahan.Impl.DeptImpl;
import com.jiuqi.dna.training.mahan.Impl.StuffImpl;
import com.jiuqi.dna.training.mahan.facade.IDeptFacade;
import com.jiuqi.dna.training.mahan.task.DeptTask;
import com.jiuqi.dna.training.mahan.task.DeptTask.DeptMethod;

public class DeptService extends Service {
	
	OrmDept ormDept;
	UpdateDept updateDept;
	DeleteDept deleteDept;
	InsertDept insertDept;
	QueryDept queryDept;
	OrmQueryByParentId ormQueryByParentId;
	OrmQueryByDeptName ormQueryByDeptName;
	DeleteStuffByDeptId deleteStuffByDeptId;
	QueryDeptByParentId queryDeptByParentId;
	QueryDeptByName queryDeptByName;
	QueryDeptOrder queryDeptOrder;
	QueryDeptAll queryDeptAll;

	//原数据引用
	protected DeptService(OrmDept ormDept,InsertDept insertDept,DeleteDept deleteDept,
			UpdateDept updateDept,QueryDept queryDept,OrmQueryByParentId ormQueryByParentId,
			OrmQueryByDeptName ormQueryByDeptName,DeleteStuffByDeptId deleteStuffByDeptId,
			QueryDeptByParentId queryDeptByParentId,QueryDeptByName queryDeptByName,
			QueryDeptOrder queryDeptOrder,QueryDeptAll queryDeptAll) {
		super("start service");
		this.updateDept = updateDept;
		this.insertDept = insertDept;
		this.deleteDept = deleteDept;
		this.queryDept = queryDept;
		this.ormDept = ormDept;
		this.ormQueryByParentId = ormQueryByParentId;
		this.ormQueryByDeptName = ormQueryByDeptName;
		this.deleteStuffByDeptId = deleteStuffByDeptId;
		this.queryDeptByParentId = queryDeptByParentId;
		this.queryDeptByName = queryDeptByName;
		this.queryDeptOrder = queryDeptOrder;
		this.queryDeptAll = queryDeptAll;
		System.out.println("==================== start DeptService");
	}

	//插入
	@Publish
	protected class DeptTaskMethodInsert extends TaskMethodHandler<DeptTask, DeptMethod>{

		protected DeptTaskMethodInsert() {
			super(DeptMethod.Add);
		}

		@Override
		protected void handle(Context context, DeptTask deptTask) throws Throwable {
			System.out.println("ADD");
			int deptOrder = 0;
			RecordSet rs = context.openQuery(queryDeptOrder);
			if(rs != null && rs.getRecordCount()>0){
				rs.next();
				RecordSetField field = rs.getFields().get(0);
				deptOrder = field.getInt() + 1;
			}
			deptTask.setDeptOrder(deptOrder);
			/*
			System.out.println("******" + deptTask.getDeptName());
			System.out.println(deptTask.getDeptCreateDate());
			System.out.println(deptTask.getDeptParentId());
			*/
			
			/*
			ORMAccessor<DeptImpl> orm = context.newORMAccessor(ormDept);
			DeptImpl deptImpl = new DeptImpl();
			deptImpl.setDeptName(deptTask.getDeptName());
			deptImpl.setDeptRemark(deptTask.getDeptRemark());
			deptImpl.setRecid(context.newRECID());
			deptImpl.setDeptCreateDate(deptTask.getDeptCreateDate());
			//deptImpl.setDeptParentId(deptTask.getDeptParentId());
			orm.insert(deptImpl);
			*/
			
			/*
			String dnaSQL = "define insert insertDept(@recid guid,@deptname string,@deptcreatedate date,@deptremark string)" +
					" begin" +
					" insert into DeptStorage(RECID,DEPTNAME,DEPTCREATEDATE,DEPTREMARK)" +
					" values(@recid,@deptname,@deptcreatedate,@deptremark)" +
					" end";
			DBCommand dbc = context.prepareStatement(dnaSQL);
			dbc.setArgumentValues(context.newRECID(),deptTask.getDeptName(),deptTask.getDeptCreateDate(),deptTask.getDeptRemark());
			dbc.executeUpdate();
			dbc.unuse();//释放
			*/
			
			int i = context.executeUpdate(insertDept, context.newRECID(),deptTask.getDeptName(),deptTask.getDeptCreateDate(),deptTask.getDeptParentId(), deptTask.getDeptRemark());
			System.out.println(i);
		}
	}

	//更新
	@Publish
	protected class DeptTaskMethodUpadte extends TaskMethodHandler<DeptTask, DeptMethod>{

		protected DeptTaskMethodUpadte() {
			super(DeptMethod.Update);
		}
		
		@Override
		protected void handle(Context context, DeptTask deptTask) throws Throwable {
			System.out.println("Update");
			int i = context.executeUpdate(updateDept,deptTask.getRecid(), deptTask.getDeptName(),deptTask.getDeptCreateDate(),deptTask.getDeptParentId(), deptTask.getDeptRemark(),deptTask.getDeptOrder());
			System.out.println(i);
		}
	}
	//删除部门
	@Publish
	protected class DeptTaskMethodDelete extends TaskMethodHandler<DeptTask, DeptMethod>{
		protected DeptTaskMethodDelete() {
			super(DeptMethod.Delete);
		}
		@Override
		protected void handle(Context context, DeptTask deptTask) throws Throwable {
			context.executeUpdate(deleteStuffByDeptId, deptTask.getRecid());
			context.executeUpdate(deleteDept, deptTask.getRecid());
		}
		
	}

	@Publish
	protected class DeptResultProvider extends ResultProvider<IDeptFacade>{
		@Override
		protected IDeptFacade provide(Context context) throws Throwable {
			DeptImpl deptImpl = new DeptImpl();
			return deptImpl;
		}

	}
	
	//查询所有部门
	@Publish
	protected class DeptResultListProvider extends ResultListProvider<IDeptFacade>{

		@Override
		protected void provide(Context context, List<IDeptFacade> list)	throws Throwable {
			System.out.println("DeptResultListProvider");
//			ORMAccessor<DeptImpl> orm = context.newORMAccessor(ormDept);
//			List<DeptImpl> deptList = orm.fetch();
//			list.addAll(deptList);
			RecordSet rs = context.openQuery(queryDeptAll);
			if(rs!=null && rs.getRecordCount()>0){
				List<IDeptFacade> rList= assembleDeptFacade(rs);
				list.addAll(rList);
			}
		}
	}
	
	//通过部门名字查询
	@Publish
	protected class DeptListTwoKeyResultProvider extends TwoKeyResultListProvider<IDeptFacade,String,Integer>{

		@Override
		protected void provide(Context context, String name, Integer flag,List<IDeptFacade> deptList) throws Throwable {
//			ORMAccessor<DeptImpl> orm = context.newORMAccessor(ormQueryByDeptName);
//			List<DeptImpl> rList = orm.fetch(name);
//			deptList.addAll(rList);
			RecordSet rs = context.openQuery(queryDeptByName, name);
			if(rs!=null && rs.getRecordCount()>0){
				List<IDeptFacade> rList= assembleDeptFacade(rs);
				deptList.addAll(rList);
			}
		}
	}
	
	//通过部门ID查询
	@Publish
	protected class DeptByKeyTwoKeyResultProvider extends TwoKeyResultListProvider<IDeptFacade,GUID,Integer>{
		@Override
		protected void provide(Context context, GUID id, Integer flag,List<IDeptFacade> deptList) throws Throwable {
			RecordSet rs = context.openQuery(queryDept, id);
			if(rs!=null && rs.getRecordCount()>0){
				List<IDeptFacade> rList= assembleDeptFacade(rs);
				deptList.addAll(rList);
			}
		}
	}
	
	
	//通过父部门ID查询
	@Publish
	protected class DeptListTest extends OneKeyResultListProvider<IDeptFacade,GUID>{

		@Override
		protected void provide(Context context, GUID id, List<IDeptFacade> list) throws Throwable {
//			ORMAccessor<DeptImpl> orm = context.newORMAccessor(ormQueryByParentId);
//			List<DeptImpl> rList = orm.fetch(id);
//			list.addAll(rList);
			RecordSet rs = context.openQuery(queryDeptByParentId, id);
			if(rs!=null && rs.getRecordCount()>0){
				List<IDeptFacade> deptList= assembleDeptFacade(rs);
				list.addAll(deptList);
			}
		}
	}
	
	private List<IDeptFacade> assembleDeptFacade(RecordSet rs){
		ArrayList<IDeptFacade> resultList = new ArrayList<IDeptFacade>();
		DeptImpl deptImpl;
		while(rs.next()){
			RecordSetField recid = rs.getFields().get(0);
			RecordSetField deptName = rs.getFields().get(1);
			RecordSetField deptCreateDate = rs.getFields().get(2);
			RecordSetField deptParentId = rs.getFields().get(3);
			RecordSetField deptRemark = rs.getFields().get(4);
			RecordSetField deptOrder = rs.getFields().get(5);
			
			deptImpl = new DeptImpl();
			deptImpl.setRecid(recid.getGUID());
			deptImpl.setDeptName(deptName.getString());
			deptImpl.setDeptCreateDate(new Date(deptCreateDate.getDate()));
			deptImpl.setDeptParentId(deptParentId.getGUID());
			deptImpl.setDeptRemark(deptRemark.getString());
			deptImpl.setDeptOrder(deptOrder.getInt());
			
			resultList.add(deptImpl);
		}
		return resultList;
	}
	
}
