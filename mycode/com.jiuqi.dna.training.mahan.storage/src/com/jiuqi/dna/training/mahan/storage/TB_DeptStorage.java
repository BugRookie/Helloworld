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

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_DeptStorage() {
		super(TABLE_NAME);
		this.table.setTitle("���ű�");
		TableFieldDeclare field;
		this.f_DEPTNAME = field = this.table.newField(FN_DEPTNAME, TypeFactory.NVARCHAR(10));
		field.setTitle("����");
		this.f_DEPTCREATEDATE = field = this.table.newField(FN_DEPTCREATEDATE, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_DEPTPARENTID = field = this.table.newField(FN_DEPTPARENTID, TypeFactory.GUID);
		field.setTitle("�ϼ�����");
		this.f_DEPTREMARK = field = this.table.newField(FN_DEPTREMARK, TypeFactory.NVARCHAR(400));
		field.setTitle("��ע");
		this.f_DEPTMANAGER = field = this.table.newField(FN_DEPTMANAGER, TypeFactory.NVARCHAR(20));
		field.setTitle("���ž���");
		this.f_DEPTNUM = field = this.table.newField(FN_DEPTNUM, TypeFactory.NUMERIC(10,0));
		field.setTitle("����");
		this.f_DEPTORDER = field = this.table.newField(FN_DEPTORDER, TypeFactory.INT);
		field.setTitle("���");
	}

}
