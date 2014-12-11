package com.jiuqi.dna.training.mahan.storage;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_Stuff extends TableDeclarator {

	public static final String TABLE_NAME ="Stuff";

	public final TableFieldDefine f_STUFFNAME;
	public final TableFieldDefine f_STUFFAGE;
	public final TableFieldDefine f_STUFFSEX;
	public final TableFieldDefine f_STUFFDATE;
	public final TableFieldDefine f_STUFFREMARK;
	public final TableFieldDefine f_DEPTID;
	public final TableFieldDefine f_STUFFORDER;

	public static final String FN_STUFFNAME ="STUFFNAME";
	public static final String FN_STUFFAGE ="STUFFAGE";
	public static final String FN_STUFFSEX ="STUFFSEX";
	public static final String FN_STUFFDATE ="STUFFDATE";
	public static final String FN_STUFFREMARK ="STUFFREMARK";
	public static final String FN_DEPTID ="DEPTID";
	public static final String FN_STUFFORDER ="STUFFORDER";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_Stuff() {
		super(TABLE_NAME);
		this.table.setTitle("Ա��");
		TableFieldDeclare field;
		this.f_STUFFNAME = field = this.table.newField(FN_STUFFNAME, TypeFactory.NVARCHAR(10));
		field.setTitle("����");
		this.f_STUFFAGE = field = this.table.newField(FN_STUFFAGE, TypeFactory.INT);
		field.setTitle("����");
		this.f_STUFFSEX = field = this.table.newField(FN_STUFFSEX, TypeFactory.NVARCHAR(2));
		field.setTitle("�Ա�");
		this.f_STUFFDATE = field = this.table.newField(FN_STUFFDATE, TypeFactory.DATE);
		field.setTitle("��ְ����");
		this.f_STUFFREMARK = field = this.table.newField(FN_STUFFREMARK, TypeFactory.NVARCHAR(400));
		field.setTitle("��ע");
		this.f_DEPTID = field = this.table.newField(FN_DEPTID, TypeFactory.GUID);
		field.setTitle("��������");
		this.f_STUFFORDER = field = this.table.newField(FN_STUFFORDER, TypeFactory.INT);
		field.setTitle("�����");
	}

}
