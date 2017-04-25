package test;

import bll.support.Mission;
import dal.FileHelper;
import vo.MissionVo;
import vo.PlayerVo;

public class FileHelperTestDrive {
	public static void main(String[] args) {
		FileHelper fh = new FileHelper();
		PlayerVo read = fh.loadData(100);
		fh.saveData(read, 1);
		
		for (int o = 0; o < 3; o++) {
			MissionVo missionVo = fh.loadMission(o);
			Mission mission = new Mission(missionVo);
			System.out.println("ID:" + mission.getID());
			System.out.println("Introduction:" + mission.getIntroduction());
			System.out.println("AInum:" + mission.getAIID().size());
			System.out.print("AllAI:");
			for (Integer i : mission.getAIID()) {
				System.out.print(i + " ");
			}
			System.out.println();
			System.out.println("----------------");
		}
	}
}
