package bll.popMethod;
import po.DotPo;
import po.PopPo;
public interface PopMethod {
	//�����platform�õĽӿ�
	public boolean hasLegalMove();
	public void remake();
	public boolean popCheck(DotPo dot1,DotPo dot2);
	public PopPo pop(DotPo dot1,DotPo dot2);//��һ����������Ҫ�����ƶ��ĸ������ж���Ч������Ϣ��
	public PopPo pop();//��������
}
