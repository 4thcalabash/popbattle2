package po;
import java.util.ArrayList;
public class AIPo {
	//F
	//向前端传递AI的预设命令序列
	private ArrayList <ActionPo> AIActionList;
	//AI在上述指令序列都执行完成后，循环执行的指令序列  ps：玩家太蠢，打不死AI。。。只好这样子了
	private ArrayList <ActionPo> AIRepeatAction;
	//AI基本信息
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
