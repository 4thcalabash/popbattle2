package dal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

import bll.individual.PaperPlayer;
import bll.individual.Player;
import bll.matrix.Matrix;
import bll.support.Skill;
import datahelper.DataOperator;
import vo.MissionVo;
import vo.PlayerVo;
import vo.ShopVo;
public class FileHelper implements DataOperator{

	@Override
	public PlayerVo loadData(int index) {
		// TODO Auto-generated method stub
		//´æÈëÄÄ¸öµµ°¸index
		PlayerVo playerVo = new PlayerVo();
		playerVo.setShopVo(new ShopVo());
		try{
			BufferedReader reader = new BufferedReader (new InputStreamReader(
					FileHelper.class.getClassLoader().getResourceAsStream("Data/Save"+index+".data")));
			String line = null;
			line = reader.readLine();
			if (line==null){
				return null;
			}
			System.out.println(line);
			playerVo.setPro(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setLevel(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setNowExp(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setPotentialPoint(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setGold(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setSkillPointNum(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setUpGradeStoneNum(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setEvolveStoneNum(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setBasichp(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setBasicad(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setBasicap(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setBasicDR(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setBasicMR(Integer.parseInt(line));
			playerVo.setSkillList(new int [Skill.TOTALNUMOFGENERATESKILL+Skill.TOTALNUMOFSPECIALSKILL]);
			for (int i=0;i<Skill.TOTALNUMOFGENERATESKILL+Skill.TOTALNUMOFSPECIALSKILL;i++){
				line = reader.readLine();
				if (line==null){
					return null;
				}
				playerVo.getSkillList()[i]=Integer.parseInt(line);
			}
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setHeadWearingID(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setHeadWearingLevel(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setWeaponID(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setWeaponLevel(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setWearingID(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setWearingLevel(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setWingsID(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.setWingsLevel(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.getShopVo().setPPPrice(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.getShopVo().setExpPrice(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.getShopVo().setSkillPointPrice(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.getShopVo().setUpGradeStonePrice(Integer.parseInt(line));
			line = reader.readLine();
			if (line==null){
				return null;
			}
			playerVo.getShopVo().setEvolveStonePrice(Integer.parseInt(line));
			reader.close();
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
//			BufferedWriter writer = new BufferedWriter(new FileWriter("/Data/Save"+index+".data",false));

			writer.write(Integer.toString(playerVo.getPro()));
			writer.newLine();
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
			writer.write(Integer.toString(playerVo.getShopVo().getExpPrice()));
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
	public MissionVo loadNormal(int index){
		MissionVo missionVo = new MissionVo ();
		try{
			BufferedReader reader = new BufferedReader (new InputStreamReader (FileHelper.class.getClassLoader().getResourceAsStream("MissionData/Normal/"+index+".data")));
			missionVo.setID(index);
			String line = null;
//			line = reader.readLine();
//			missionVo.setAvailTime(Integer.parseInt(line));
//			line = reader.readLine();
//			missionVo.setAvailOperateTimes(Integer.parseInt(line));
			int [] targetElementNum = new int [Matrix.KIND] ;
			
			for (int i=0;i<Matrix.KIND;i++){
				line = reader.readLine();
				targetElementNum[i]=Integer.parseInt(line);
			}
			missionVo.setTargetElementNum(targetElementNum);
		}catch(Exception e){
			e.printStackTrace();
		}
		return missionVo;
	}
	@Override
	public MissionVo loadMission(int index) {
		// TODO Auto-generated method stub
		MissionVo missionVo = new MissionVo ();
		try{
			BufferedReader reader = new BufferedReader (new InputStreamReader (FileHelper.class.getClassLoader().getResourceAsStream("MissionData/Mission/"+index+".data")));
			missionVo.setID(index);
			String line = null;
			line = reader.readLine();
			missionVo.setIntroduction(line);
			line = reader.readLine();
			int AInum = Integer.parseInt(line);
			ArrayList <Integer> AIID = new ArrayList<Integer> ();
			for (int i=0;i<AInum;i++){
				line = reader.readLine();
				AIID.add(Integer.parseInt(line));
			}
			missionVo.setAIID(AIID);
			reader.close();
//			filereader.close();
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
//			String filePath = FileHelper.class.getClassLoader().getResource("AIData/AI"+ID+".data").getPath();
//			File save = new File (filePath);
//			FileReader filereader = new FileReader(save);
//			BufferedReader reader = new BufferedReader (filereader);
			BufferedReader reader = new BufferedReader(new InputStreamReader(FileHelper.class.getClassLoader().getResourceAsStream("AIData/AI"+ID+".data")));
			String line = null;
			line = reader.readLine();
			AIBasic.setPro(Integer.parseInt(line));
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
			int [] temp = new int [3]; 
			for (int i=0;i<3;i++){
				line = reader.readLine();
				temp[i]=Integer.parseInt(line);
//				AIBasic.getSkillList()[i]=Integer.parseInt(line);
			}

			AI = AIBasic.createPaper();
			line = reader.readLine();
			System.out.println("Skill1"+Integer.parseInt(line));
			AI.getAllSkills()[0]=Skill.getSkillByID(Integer.parseInt(line));
			AI.getPlayer().getSkillList()[Integer.parseInt(line)%100]=temp[0];
			line = reader.readLine();
			System.out.println("Skill1"+Integer.parseInt(line));
			AI.getAllSkills()[1]=Skill.getSkillByID(Integer.parseInt(line));
			AI.getPlayer().getSkillList()[Integer.parseInt(line)%100]=temp[1];
			line = reader.readLine();
			System.out.println("Skill1"+Integer.parseInt(line));
			AI.getAllSkills()[2]=Skill.getSkillByID(Integer.parseInt(line));

			AI.getPlayer().getSkillList()[Integer.parseInt(line)%100]=temp[2];
			line = reader.readLine();
			AIBasic.setAILevel(Integer.parseInt(line));
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("AI Level ="+AI.getPlayer().getLevel());
		return AI;
	}
	
}
