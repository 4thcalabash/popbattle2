package bllservice;

import bll.individual.Player;

public interface Supportable {
	public Player getPlayer1();
	public void saveData(int index);
	public void loadData(int index);
}
