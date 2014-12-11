package com.jiuqi.dna.training.mahan.common;

import com.jiuqi.dna.training.mahan.facade.IDeptFacade;
import com.jiuqi.dna.ui.wt.viewers.IElementComparer;

public class DeptTreeCompare implements IElementComparer {

	/*
	 * 此类用于比较两个节点对象是否相等，在更新一个元素节点的时候，会利用元素比较器来获取元素节点对应的树节点进行更新。
	 */
	@Override
	public boolean equals(Object a, Object b) {
		// 验证两个节点对象是否是部门接口类型
		if (a != null && b != null && a instanceof IDeptFacade
				&& b instanceof IDeptFacade) {
			// 转换成部门接口类型
			IDeptFacade d1 = (IDeptFacade) a;
			IDeptFacade d2 = (IDeptFacade) b;
			// 判断是否相等，相等则返回true
			if (d1.getRecid().equals(d2.getRecid())) {
				return true;
			}
		}
		return false;

	}

	/*
	 * 获取用于唯一标识节点元素的hashCode。
	 */
	@Override
	public int hashCode(Object element) {
		// 验证两个节点对象是否是部门接口类型
		if (element != null && element instanceof IDeptFacade) {
			// 转换成部门接口类型
			IDeptFacade d = (IDeptFacade) element;
			// 返回唯一标识部门元素的Recid的hashCode
			return d.getRecid().hashCode();
		}
		return 0;

	}

}
