package com.jiuqi.dna.training.mahan.storage;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_DeptStorage extends TableDeclarator {

	public static final String TABLE_NAME ="DeptStorage";

	public final TableFieldDefine f_DEPTNAME;
	public final TableFieldDefine f_DEPTCREATEDATE;
	public final TableFieldDefine f_DEPTPARENTID;
	public final TableFieldDefine f_DEPTREMARK;
	public final TableFieldDefine f_DEPTMANAGER;
	public final TableFieldDefine f_DEPTNUM;
	public final TableFieldDefine f_DEPTORDER;

	public static final String FN_DEPTNAME ="DEPTNAME";
	public static final String FN_DEPTCREATEDATE ="DEPTCREATEDATE";
	public static final String FN_DEPTPARENTID ="DEPTPARENTID";
	public static final String FN_DEPTREMARK ="DEPTREMARK";
	public static final String FN_DEPTMANAGER ="DEPTMANAGER";
	public static final String FN_DEPTNUM ="DEPTNUM";
	public static final String FN_DEPTORDER ="DEPTORDER";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_DeptStorage() {
		super(TABLE_NAME);
		this.table.setTitle("部门表");
		TableFieldDeclare field;
		this.f_DEPTNAME = field = this.table.newField(FN_DEPTNAME, TypeFactory.NVARCHAR(10));
		field.setTitle("名称");
		this.f_DEPTCREATEDATE = field = this.table.newField(FN_DEPTCREATEDATE, TypeFactory.DATE);
		field.setTitle("创建日期");
		this.f_DEPTPARENTID = field = this.table.newField(FN_DEPTPARENTID, TypeFactory.GUID);
		field.setTitle("上级部门");
		this.f_DEPTREMARK = field = this.table.newField(FN_DEPTREMARK, TypeFactory.NVARCHAR(400));
		field.setTitle("备注");
		this.f_DEPTMANAGER = field = this.table.newField(FN_DEPTMANAGER, TypeFactory.NVARCHAR(20));
		field.setTitle("部门经理");
		this.f_DEPTNUM = field = this.table.newField(FN_DEPTNUM, TypeFactory.NUMERIC(10,0));
		field.setTitle("人数");
		this.f_DEPTORDER = field = this.table.newField(FN_DEPTORDER, TypeFactory.INT);
		field.setTitle("序号");
	}

}
