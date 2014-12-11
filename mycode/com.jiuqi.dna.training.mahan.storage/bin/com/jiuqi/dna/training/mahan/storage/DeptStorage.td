<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="DeptStorage" ID="7B92AC1DED04C5BB4A07EC7237362A94" name-db="DeptStorage" category="" title="部门表" author="" description="" packagename="com.jiuqi.dna.training.mahan.storage" name="DeptStorage" isneedrebuild="false">
	<fields>
		<field dbtable="DeptStorage" name="DEPTNAME" name-db="DEPTNAME" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="名称" description="" ID="D7D250F0DE0A170D18EC35EFEA30CC5B"/>
		<field dbtable="DeptStorage" name="DEPTCREATEDATE" name-db="DEPTCREATEDATE" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="0FBC3C29228D65EAF05D542455196BBF"/>
		<field dbtable="DeptStorage" name="DEPTPARENTID" name-db="DEPTPARENTID" type="guid" primary-key="false" keep-valid="false" author="" title="上级部门" description="" ID="CC79F6E1A4B1F0E2A1CB034DB0C45B2D"/>
		<field dbtable="DeptStorage" name="DEPTREMARK" name-db="DEPTREMARK" type="nvarchar(400)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="409B5881181208DF7BFCF43D17D3EB38"/>
		<field dbtable="DeptStorage" name="DEPTMANAGER" name-db="DEPTMANAGER" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="部门经理" description="" ID="07B86C6CD3C152A80755961C9877526D"/>
		<field dbtable="DeptStorage" name="DEPTNUM" name-db="DEPTNUM" type="numeric(10,0)" primary-key="false" keep-valid="false" author="" title="人数" description="" ID="9BFD9FDF5886B91009526EAE1D162AF9"/>
		<field dbtable="DeptStorage" name="DEPTORDER" name-db="DEPTORDER" type="int" primary-key="false" keep-valid="false" author="" title="序号" description="" ID="1E15C6088419466D74F75C6AE5CAA708"/>
	</fields>
	<indexs/>
	<hierarchies/>
	<dbtables>
		<dbtable author="" name="DeptStorage" name-db="DeptStorage" title="" description=""/>
	</dbtables>
	<relations/>
</tabledefine>
