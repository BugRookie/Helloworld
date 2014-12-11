package com.jiuqi.dna.training.mahan.common;

import java.util.List;

import com.jiuqi.dna.bap.common.constants.BapImages;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.training.mahan.Impl.DeptImpl;
import com.jiuqi.dna.training.mahan.facade.IDeptFacade;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.provider.LabelProvider;
import com.jiuqi.dna.ui.wt.provider.TreeContentProvider;

public class DeptTreeContentProvider implements TreeContentProvider,
		LabelProvider {

	private Context context;

	public DeptTreeContentProvider(Context context) {
		this.context = context;
	}

	/*
	 * 此方法为TreeViewer控件的入口方法。 当程序开始构建树的时候，首先调用getElements返回一个对象的数组，
	 * 此数组对象表示了当前树的根节点。 inputElement参数为TreeViewer的输入， 即页面中调用setInput方法传入的参数。
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		Object obj[] = new Object[1];
		if( inputElement instanceof String){
			List<IDeptFacade> list = context.getList(IDeptFacade.class,inputElement,1);
			if(list!=null && list.size()>0){
				obj[0] = list.get(0);
				return obj;
			}
		}else{
			obj[0] = inputElement;
			return obj;
		}
		return obj;
	}

	/*
	 * 当用户第一次选择节点打开子节点时，会调用getChildren函数获取其下一级子节点。 element参数为选择点击的子节点。
	 */
	@Override
	public Object[] getChildren(Object element) {
		// 如果参数不为null，则根据该参数获取子节点列表
		if (element != null) {
			// 判断是否是部门接口类型
			if (element instanceof IDeptFacade) {
				// 转换成部门接口类型
				IDeptFacade deptFacade = (IDeptFacade) element;
				// 实例化一个部门参数实体
				/*
				DeptImpl deptImpl = new DeptImpl();
				// 将该部门的父部门ID写入参数实体
				deptImpl.setDeptParentId(deptFacade.getRecid());
				// 通过传过来的上下文环境context获取父部门ID是上面设置的父部门ID的所有部门列表
				*/
				List<IDeptFacade> list = context.getList(IDeptFacade.class,deptFacade.getRecid());
				// 如果查询到的部门列表不是空的
				if (list != null && list.size() > 0) {
					return list.toArray();
				}
			}
		} else {
			// 如果参数为null，则获取数据库中全部的部门DeptFacade对象数组
			// 获取所有的部门列表
			List<IDeptFacade> list = context.getList(IDeptFacade.class);
			// 如果查询到的部门列表不是空的
			if (list != null && list.size() > 0) {
				// 返回所有的部门数组
				return list.toArray();
			}
		}
		return null;
	}

	/*
	 * 当TreeViewer显示一个节点的时候， 会调用hasChildren函数来判断当前节点是否有子节点， 如果有子节点则显示“+”。
	 * element参数为判断是否有子节点的节点。
	 */
	@Override
	public boolean hasChildren(Object element) {
		// 判断参数是否为null
		if (element != null) {
			// 判断是不是部门接口类型
			if (element instanceof IDeptFacade) {
				// 转换成部门接口类型
				IDeptFacade myFacade = (IDeptFacade) element;
				// 判断是不是存在子节点
				if (getChildren(myFacade) != null && getChildren(myFacade).length > 0) {
					// 存在则返回true
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * 通过此方法返回element的父节点。
	 */
	@Override
	public Object getParent(Object element) {
		// 如果参数不为null，则根据该子节点获取的父部门ID来查询部门信息
		if (element != null) {
			// 判断是否是部门接口类型
			if (element instanceof IDeptFacade) {
				// 转换成部门接口类型
				IDeptFacade deptFacade = (IDeptFacade) element;
				// 如果该部门的信息中存在父部门ID
				if (deptFacade.getDeptParentId() != null) {
					/*
					// 实例化一个部门参数实体
					DeptImpl dp = new DeptImpl();
					// 设置这个部门参数实体的ID是上面获取的父部门ID
					dp.setRecid(deptFacade.getDeptParentId());
					// 根据参数实体获取部门列表
					 */
					List<IDeptFacade> list = context.getList(IDeptFacade.class,deptFacade.getDeptParentId());
					// 如果该列表不为空
					if (list != null && list.size() > 0) {
						// 返回一个部门接口，此接口即为父部门
						return (IDeptFacade) list.get(0);
					}
				}
			}
		}
		// 如果参数为null，则获取数据库中全部的部门DeptFacade对象数组
		else {
			// 获取所有的部门列表
			List<IDeptFacade> list = context.getList(IDeptFacade.class);
			// 如果该列表不为空
			if (list != null && list.size() > 0) {
				// 默认返回第一个部门
				return (IDeptFacade) list.get(0);
			}
		}
		return null;
	}

	/*
	 * 如果节点存在子节点则返回树节点功能图标，否则返回树节点文件图标
	 */
	@Override
	public ImageDescriptor getImage(Object element) {
		// 如果参数不为null并且是部门接口类型
		if (element != null && element instanceof IDeptFacade) {
			// 如果该节点存在子节点
			if (getChildren(element) != null && getChildren(element).length > 0) {
				// 返回树节点功能图标
				return context.find(ImageDescriptor.class,BapImages.ico_treenode_function);
			} else {
				// 返回树节点文件图标
				return context.find(ImageDescriptor.class,BapImages.ico_treenode_file);
			}
		}
		return null;

	}

	/*
	 * 返回树节点的文本信息。
	 */
	@Override
	public String getText(Object element) {
		// 如果参数不为null并且是部门接口类型
		if (element != null && element instanceof IDeptFacade) {
			// 返回部门名称
			return ((IDeptFacade) element).getDeptName();
		}
		return null;
	}

}
