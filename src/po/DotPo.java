package po;

public class DotPo {
	//F
	private int x,y;
	//��¼��һ���������ɫ
	private int color;
	//һ�������Ƿ���һ����Ч
	private int bonus;


	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public DotPo(int x,int y,int color,int bonus){
		this.x=x;
		this.y=y;
		this.color=color;
		this.bonus=bonus;
	}
	public DotPo(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int getColor() {
		return color;
	}
}
