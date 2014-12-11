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
	 * �˷���ΪTreeViewer�ؼ�����ڷ����� ������ʼ��������ʱ�����ȵ���getElements����һ����������飬
	 * ����������ʾ�˵�ǰ���ĸ��ڵ㡣 inputElement����ΪTreeViewer�����룬 ��ҳ���е���setInput��������Ĳ�����
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
	 * ���û���һ��ѡ��ڵ���ӽڵ�ʱ�������getChildren������ȡ����һ���ӽڵ㡣 element����Ϊѡ�������ӽڵ㡣
	 */
	@Override
	public Object[] getChildren(Object element) {
		// ���������Ϊnull������ݸò�����ȡ�ӽڵ��б�
		if (element != null) {
			// �ж��Ƿ��ǲ��Žӿ�����
			if (element instanceof IDeptFacade) {
				// ת���ɲ��Žӿ�����
				IDeptFacade deptFacade = (IDeptFacade) element;
				// ʵ����һ�����Ų���ʵ��
				/*
				DeptImpl deptImpl = new DeptImpl();
				// ���ò��ŵĸ�����IDд�����ʵ��
				deptImpl.setDeptParentId(deptFacade.getRecid());
				// ͨ���������������Ļ���context��ȡ������ID���������õĸ�����ID�����в����б�
				*/
				List<IDeptFacade> list = context.getList(IDeptFacade.class,deptFacade.getRecid());
				// �����ѯ���Ĳ����б��ǿյ�
				if (list != null && list.size() > 0) {
					return list.toArray();
				}
			}
		} else {
			// �������Ϊnull�����ȡ���ݿ���ȫ���Ĳ���DeptFacade��������
			// ��ȡ���еĲ����б�
			List<IDeptFacade> list = context.getList(IDeptFacade.class);
			// �����ѯ���Ĳ����б��ǿյ�
			if (list != null && list.size() > 0) {
				// �������еĲ�������
				return list.toArray();
			}
		}
		return null;
	}

	/*
	 * ��TreeViewer��ʾһ���ڵ��ʱ�� �����hasChildren�������жϵ�ǰ�ڵ��Ƿ����ӽڵ㣬 ������ӽڵ�����ʾ��+����
	 * element����Ϊ�ж��Ƿ����ӽڵ�Ľڵ㡣
	 */
	@Override
	public boolean hasChildren(Object element) {
		// �жϲ����Ƿ�Ϊnull
		if (element != null) {
			// �ж��ǲ��ǲ��Žӿ�����
			if (element instanceof IDeptFacade) {
				// ת���ɲ��Žӿ�����
				IDeptFacade myFacade = (IDeptFacade) element;
				// �ж��ǲ��Ǵ����ӽڵ�
				if (getChildren(myFacade) != null && getChildren(myFacade).length > 0) {
					// �����򷵻�true
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * ͨ���˷�������element�ĸ��ڵ㡣
	 */
	@Override
	public Object getParent(Object element) {
		// ���������Ϊnull������ݸ��ӽڵ��ȡ�ĸ�����ID����ѯ������Ϣ
		if (element != null) {
			// �ж��Ƿ��ǲ��Žӿ�����
			if (element instanceof IDeptFacade) {
				// ת���ɲ��Žӿ�����
				IDeptFacade deptFacade = (IDeptFacade) element;
				// ����ò��ŵ���Ϣ�д��ڸ�����ID
				if (deptFacade.getDeptParentId() != null) {
					/*
					// ʵ����һ�����Ų���ʵ��
					DeptImpl dp = new DeptImpl();
					// ����������Ų���ʵ���ID�������ȡ�ĸ�����ID
					dp.setRecid(deptFacade.getDeptParentId());
					// ���ݲ���ʵ���ȡ�����б�
					 */
					List<IDeptFacade> list = context.getList(IDeptFacade.class,deptFacade.getDeptParentId());
					// ������б�Ϊ��
					if (list != null && list.size() > 0) {
						// ����һ�����Žӿڣ��˽ӿڼ�Ϊ������
						return (IDeptFacade) list.get(0);
					}
				}
			}
		}
		// �������Ϊnull�����ȡ���ݿ���ȫ���Ĳ���DeptFacade��������
		else {
			// ��ȡ���еĲ����б�
			List<IDeptFacade> list = context.getList(IDeptFacade.class);
			// ������б�Ϊ��
			if (list != null && list.size() > 0) {
				// Ĭ�Ϸ��ص�һ������
				return (IDeptFacade) list.get(0);
			}
		}
		return null;
	}

	/*
	 * ����ڵ�����ӽڵ��򷵻����ڵ㹦��ͼ�꣬���򷵻����ڵ��ļ�ͼ��
	 */
	@Override
	public ImageDescriptor getImage(Object element) {
		// ���������Ϊnull�����ǲ��Žӿ�����
		if (element != null && element instanceof IDeptFacade) {
			// ����ýڵ�����ӽڵ�
			if (getChildren(element) != null && getChildren(element).length > 0) {
				// �������ڵ㹦��ͼ��
				return context.find(ImageDescriptor.class,BapImages.ico_treenode_function);
			} else {
				// �������ڵ��ļ�ͼ��
				return context.find(ImageDescriptor.class,BapImages.ico_treenode_file);
			}
		}
		return null;

	}

	/*
	 * �������ڵ���ı���Ϣ��
	 */
	@Override
	public String getText(Object element) {
		// ���������Ϊnull�����ǲ��Žӿ�����
		if (element != null && element instanceof IDeptFacade) {
			// ���ز�������
			return ((IDeptFacade) element).getDeptName();
		}
		return null;
	}

}
