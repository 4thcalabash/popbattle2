package po;
import java.util.ArrayList;
public class AIPo {
	//F
	//��ǰ�˴���AI��Ԥ����������
	private ArrayList <ActionPo> AIActionList;
	//AI������ָ�����ж�ִ����ɺ�ѭ��ִ�е�ָ������  ps�����̫��������AI������ֻ����������
	private ArrayList <ActionPo> AIRepeatAction;
	//AI������Ϣ
	private FigurePo AIFigurePo;
	public ArrayList<ActionPo> getAIActionList() {
		return AIActionList;
	}
	public void setAIActionList(ArrayList<ActionPo> aIActionList) {
		AIActionList = aIActionList;
	}
	public ArrayList<ActionPo> getAIRepeatAction() {
		return AIRepeatAction;
	}
	public void setAIRepeatAction(ArrayList<ActionPo> aIRepeatAction) {
		AIRepeatAction = aIRepeatAction;
	}
	public FigurePo getAIFigurePo() {
		return AIFigurePo;
	}
	public void setAIFigurePo(FigurePo aIFigurePo) {
		AIFigurePo = aIFigurePo;
	}
	
}
