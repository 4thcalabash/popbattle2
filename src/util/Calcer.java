package util;

import java.util.ArrayList;

public class Calcer implements CalcMethod{
	private ArrayList <Integer> ansList = new ArrayList <Integer>();
	public Calcer(int ans1){
		ansList.add(ans1);
	}
	public Calcer(int ans1,int ans2){
		ansList.add(ans1);
		ansList.add(ans2);
	}
	public Calcer(int ans1,int ans2,int ans3){
		ansList.add(ans1);
		ansList.add(ans2);
		ansList.add(ans3);
	}
	@Override
	public int calc(Calcable calced) {
		// TODO Auto-generated method stub
		int level = calced.getLevel();
		return ansList.get(level-1);
	}
	@Override
	public int calc(int level) {
		// TODO Auto-generated method stub
		return ansList.get(level-1);
	}
	
}
