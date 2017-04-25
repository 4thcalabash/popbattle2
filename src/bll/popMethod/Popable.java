package bll.popMethod;
import bll.matrix.*;
import po.DotPo;
public interface Popable {
	//每个具体的method的接口
	public boolean hasLegalMove(Matrix chessboard);
	public boolean popChcek(Matrix chessboard,DotPo dot1,DotPo dot2);
	public void pop(Matrix chessboard,DotPo dot1,DotPo dot2);
	public void pop(Matrix chessboard);
}
