package dal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import bll.individual.PaperPlayer;
import bll.individual.Player;
import bll.support.Equip;
import bll.support.Skill;
import datahelper.*;
import vo.*;
public class FileHelper implements DataOperator{

	@Override
	public PlayerVo loadData(int index) {
		// TODO Auto-generated method stub
		//�����ĸ�����index
		PlayerVo playerVo = new PlayerVo();
		playerVo.setShopVo(new ShopVo());
		try{
			String filePath = FileHelper.class.getClassLoader().getResource("Data/Save"+index+".data").getPath();
			File save = new File (filePath);
			FileReader filereader = new FileReader(save);
			BufferedReader reader = new BufferedReader (filereader);
			String line = null;
			
			line = reader.readLine();
			playerVo.setLevel(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setNowExp(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setPotentialPoint(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setGold(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setSkillPointNum(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setUpGradeStoneNum(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setEvolveStoneNum(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setBasichp(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setBasicad(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setBasicap(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setBasicDR(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setBasicMR(Integer.parseInt(line));
			playerVo.setSkillList(new int [Skill.TOTALNUMOFGENERATESKILL+Skill.TOTALNUMOFSPECIALSKILL]);
			for (int i=0;i<Skill.TOTALNUMOFGENERATESKILL+Skill.TOTALNUMOFSPECIALSKILL;i++){
				line = reader.readLine();
				playerVo.getSkillList()[i]=Integer.parseInt(line);
			}
			playerVo.setSkillChoosed(new int [3]);
			for (int i=0;i<3;i++){
				line = reader.readLine();
				playerVo.getSkillChoosed()[i]=Integer.parseInt(line);
			}
			line = reader.readLine();
			playerVo.setHeadWearingID(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setHeadWearingLevel(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setWeaponID(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setWeaponLevel(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setWearingID(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setWearingLevel(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setWingsID(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.setWingsLevel(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.getShopVo().setPPPrice(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.getShopVo().setPPNum(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.getShopVo().setExpPrice(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.getShopVo().setExpNum(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.getShopVo().setSkillPointPrice(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.getShopVo().setUpGradeStonePrice(Integer.parseInt(line));
			line = reader.readLine();
			playerVo.getShopVo().setEvolveStonePrice(Integer.parseInt(line));
			//return null;
			reader.close();
			filereader.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return playerVo;
	}

	@Override
	public void saveData(PlayerVo playerVo, int index) {
		// TODO Auto-generated method stub
		try{
			String filePath = FileHelper.class.getClassLoader().getResource("").getPath()+"Data/Save"+index+".data";
			File save = new File (filePath);
			FileWriter filewriter = new FileWriter (save,false);
			BufferedWriter writer = new BufferedWriter (filewriter);
			writer.write(Integer.toString(playerVo.getLevel()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getNowExp()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getPotentialPoint()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getGold()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getSkillPointNum()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getUpGradeStoneNum()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getEvolveStoneNum()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getBasichp()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getBasicad()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getBasicap()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getBasicDR()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getBasicMR()));
			writer.newLine();
			for (int i=0;i<Skill.TOTALNUMOFGENERATESKILL+Skill.TOTALNUMOFSPECIALSKILL;i++){
				writer.write(Integer.toString(playerVo.getSkillList()[i]));
				writer.newLine();
			}
			for (int i=0;i<3;i++){
				writer.write(Integer.toString(playerVo.getSkillChoosed()[i]));
				writer.newLine();
			}
			writer.write(Integer.toString(playerVo.getHeadWearingID()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getHeadWearingLevel()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getWeaponID()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getWeaponLevel()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getWearingID()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getWearingLevel()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getWingsID()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getWingsLevel()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getShopVo().getPPPrice()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getShopVo().getPPNum()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getShopVo().getExpPrice()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getShopVo().getExpNum()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getShopVo().getSkillPointPrice()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getShopVo().getUpGradeStonePrice()));
			writer.newLine();
			writer.write(Integer.toString(playerVo.getShopVo().getEvolveStonePrice()));
			writer.flush();
			writer.close();
			filewriter.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public MissionVo loadMission(int index) {
		// TODO Auto-generated method stub
		MissionVo missionVo = new MissionVo ();
		String filePath = FileHelper.class.getClassLoader().getResource("MissionData/"+index+".data").getPath();
		try{
			File save = new File (filePath);
			FileReader filereader = new FileReader (save);
			BufferedReader reader = new BufferedReader (filereader);
			missionVo.setID(index);
			String line = null;
			line = reader.readLine();
			missionVo.setIntroduction(line);
			line = reader.readLine();
			int AInum = Integer.parseInt(line);
			ArrayList <Integer> AIID = new ArrayList ();
			for (int i=0;i<AInum;i++){
				line = reader.readLine();
				AIID.add(Integer.parseInt(line));
			}
			missionVo.setAIID(AIID);
			reader.close();
			filereader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return missionVo;
	}

	@Override
	public PaperPlayer loadAI(int ID) {
		// TODO Auto-generated method stub
		PlayerVo basic = this.loadData(0);
		Player AIBasic = new Player (basic);
		PaperPlayer AI = null;
		try{
			String filePath = FileHelper.class.getClassLoader().getResource("AIData/AI"+ID+".data").getPath();
			File save = new File (filePath);
			FileReader filereader = new FileReader(save);
			BufferedReader reader = new BufferedReader (filereader);
			String line = null;
			line = reader.readLine();
			AIBasic.setLevel(Integer.parseInt(line));
			line = reader.readLine();
			AIBasic.setHp(Integer.parseInt(line));
			line = reader.readLine();
			AIBasic.setAd(Integer.parseInt(line));
			line = reader.readLine();
			AIBasic.setAp(Integer.parseInt(line));
			line = reader.readLine();
			AIBasic.setDR(Integer.parseInt(line));
			line = reader.readLine();
			AIBasic.setMR(Integer.parseInt(line));
			line = reader.readLine();
			AIBasic.setDT(Integer.parseInt(line));
			line = reader.readLine();
			AIBasic.setMT(Integer.parseInt(line));
			for (int i=0;i<Skill.TOTALNUMOFGENERATESKILL+Skill.TOTALNUMOFSPECIALSKILL;i++){
				line = reader.readLine();
				AIBasic.getSkillList()[i]=Integer.parseInt(line);
			}
			AI = AIBasic.createPaper();
			line = reader.readLine();
			AI.getAllSkills()[0]=Skill.getSkillByID(Integer.parseInt(line));
			line = reader.readLine();
			AI.getAllSkills()[1]=Skill.getSkillByID(Integer.parseInt(line));
			line = reader.readLine();
			AI.getAllSkills()[2]=Skill.getSkillByID(Integer.parseInt(line));
		}catch(Exception e){
			e.printStackTrace();
		}
		return AI;
	}
	
}
