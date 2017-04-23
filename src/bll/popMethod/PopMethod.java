package bll.popMethod;
import po.DotPo;
import po.PopPo;
public interface PopMethod {
	//给后端platform用的接口
	public boolean hasLegalMove();
	public void remake();
	public boolean popCheck(DotPo dot1,DotPo dot2);
	public PopPo pop(DotPo dot1,DotPo dot2);//第一步消除，需要根据移动的格子来判定特效生成信息等
	public PopPo pop();//触发联销
}
