package bll.support;

public class Achievement {
	public final int ITEMID;//�ɾ�ͳ�Ƶ���Ŀ�������Ƿ��飬�ȼ������ж��������ݳ�����ƥ��
	public final int TARGETNUM;//Ŀ������
	private int finishedNum;//���������
	private boolean isFinished;//�Ƿ��Ѿ����
	public boolean isFinished() {
		return isFinished;
	}
	public Achievement(int ITEMID,int TARGETNUM){
		this.ITEMID=ITEMID;
		this.TARGETNUM=TARGETNUM;
	}
	public void renewprogress(int nowFinishedNum){
		this.finishedNum=nowFinishedNum;
		if (this.finishedNum>=TARGETNUM){
			isFinished=true;
		}
	}
	//aaaaa
	public int getFinishedNum() {
		return finishedNum;
	}
	public void setFinishedNum(int finishedNum) {
		this.finishedNum = finishedNum;
	}
	public int getITEMID() {
		return ITEMID;
	}
	public int getTARGETNUM() {
		return TARGETNUM;
	}
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
}
