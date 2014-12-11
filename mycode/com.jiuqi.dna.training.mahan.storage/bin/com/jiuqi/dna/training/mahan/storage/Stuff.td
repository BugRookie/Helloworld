<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="Stuff" ID="957C3DA541AFE4537711187D47F41E8F" name-db="Stuff" category="" title="员工" author="" description="" packagename="com.jiuqi.dna.training.mahan.storage" name="Stuff" isneedrebuild="false">
	<fields>
		<field dbtable="Stuff" name="STUFFNAME" name-db="STUFFNAME" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="姓名" description="" ID="520D551EB563459AC6CCDFBD360DA19E"/>
		<field dbtable="Stuff" name="STUFFAGE" name-db="STUFFAGE" type="int" primary-key="false" keep-valid="false" author="" title="年龄" description="" ID="FE33A8BA99162037E84CA801001738F5"/>
		<field dbtable="Stuff" name="STUFFSEX" name-db="STUFFSEX" type="nvarchar(2)" primary-key="false" keep-valid="false" author="" title="性别" description="" ID="658D4F31E1866F2289F24971696AB119"/>
		<field dbtable="Stuff" name="STUFFDATE" name-db="STUFFDATE" type="date" primary-key="false" keep-valid="false" author="" title="入职日期" description="" ID="8F291BB2A5B6BCF21DD8BAB09CECE860"/>
		<field dbtable="Stuff" name="STUFFREMARK" name-db="STUFFREMARK" type="nvarchar(400)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="3CF5720D965E3E87C04E972668011DBD"/>
		<field dbtable="Stuff" name="DEPTID" name-db="DEPTID" type="guid" primary-key="false" keep-valid="false" author="" title="所属部门" description="" ID="88E7EA6FC71BE5E34141F95534647CC8"/>
		<field dbtable="Stuff" name="STUFFORDER" name-db="STUFFORDER" type="int" primary-key="false" keep-valid="false" author="" title="排序号" description="" ID="7CA14F3811C75527514622A82BB6C99B"/>
	</fields>
	<indexs/>
	<hierarchies/>
	<dbtables>
		<dbtable author="" name="Stuff" name-db="Stuff" title="" description=""/>
	</dbtables>
	<relations/>
</tabledefine>
