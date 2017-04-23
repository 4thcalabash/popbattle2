package po;
import bll.matrix.*;
public class MatrixPo {
	private Dot [] [] matrix = new Dot [Matrix.TOTALLINE*2] [Matrix.TOTALROW];

	public Dot[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Dot[][] matrix) {
		this.matrix = matrix;
	}
	public MatrixPo(){
		
	}
	public MatrixPo(Dot [] [] matrix){
		this.matrix=matrix;
	}
	
}
