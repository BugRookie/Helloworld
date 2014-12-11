package com.jiuqi.dna.training.mahan.common;

import com.jiuqi.dna.training.mahan.facade.IDeptFacade;
import com.jiuqi.dna.ui.wt.viewers.IElementComparer;

public class DeptTreeCompare implements IElementComparer {

	/*
	 * �������ڱȽ������ڵ�����Ƿ���ȣ��ڸ���һ��Ԫ�ؽڵ��ʱ�򣬻�����Ԫ�رȽ�������ȡԪ�ؽڵ��Ӧ�����ڵ���и��¡�
	 */
	@Override
	public boolean equals(Object a, Object b) {
		// ��֤�����ڵ�����Ƿ��ǲ��Žӿ�����
		if (a != null && b != null && a instanceof IDeptFacade
				&& b instanceof IDeptFacade) {
			// ת���ɲ��Žӿ�����
			IDeptFacade d1 = (IDeptFacade) a;
			IDeptFacade d2 = (IDeptFacade) b;
			// �ж��Ƿ���ȣ�����򷵻�true
			if (d1.getRecid().equals(d2.getRecid())) {
				return true;
			}
		}
		return false;

	}

	/*
	 * ��ȡ����Ψһ��ʶ�ڵ�Ԫ�ص�hashCode��
	 */
	@Override
	public int hashCode(Object element) {
		// ��֤�����ڵ�����Ƿ��ǲ��Žӿ�����
		if (element != null && element instanceof IDeptFacade) {
			// ת���ɲ��Žӿ�����
			IDeptFacade d = (IDeptFacade) element;
			// ����Ψһ��ʶ����Ԫ�ص�Recid��hashCode
			return d.getRecid().hashCode();
		}
		return 0;

	}

}
