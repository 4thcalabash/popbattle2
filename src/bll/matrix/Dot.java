package bll.matrix;

public class Dot {
	private int color;
	private int bonus;
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public Dot(int color,int bonus){
		this.color=color;
		this.bonus=bonus;
	}
}
