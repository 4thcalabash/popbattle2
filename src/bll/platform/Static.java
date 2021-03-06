package bll.platform;

import bll.individual.PaperPlayer;
import bll.individual.Player;
import bll.support.Skill;
import bllservice.Achievementable;
import bllservice.BasicPlatform;
import bllservice.BattlePlatform;
import bllservice.Chooseable;
import bllservice.Equipable;
import bllservice.Playerable;
import bllservice.Shopable;
import bllservice.Skillable;
import dal.FileHelper;
import vo.PlayerVo;
import vo.ShopVo;

public class Static implements BasicPlatform, Shopable, Achievementable, Equipable, Playerable, Skillable, Chooseable{
	private Player player;
	private FileHelper helper;
	public Static (int index){
		//index������
		helper = new FileHelper ();
		player = new Player(helper.loadData(index));
	}
	@Override
	public Player getPlayer1() {
		// TODO Auto-generated method stub
		return player;
	}
	@Override
	public BattlePlatform createBattle(int missionID,int [] allSkills) {
		// TODO Auto-generated method stub
		PaperPlayer paperPlayer1 = player.createPaper();
		for (int i=0;i<3;i++){
			paperPlayer1.getAllSkills()[i]=Skill.getSkillByID(allSkills[i]);
		}
		Battle battle = new Battle (missionID,paperPlayer1);
		
		
		return (BattlePlatform)battle;
	}
	@Override
	public int totalNormalNum() {
		// TODO Auto-generated method stub
		return 10;
	}
	@Override
	public int nowNormalNum() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int totalPVENum() {
		// TODO Auto-generated method stub
		return 10;
	}
	@Override
	public int nowPVENum() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void saveData(int index){
		PlayerVo temp = new PlayerVo();
		temp.setBasicad(player.getBasicad());
		temp.setBasicap(player.getBasicap());
		temp.setBasicDR(player.getBasicDR());
		temp.setBasichp(player.getBasichp());
		temp.setBasicMR(player.getBasicMR());
		temp.setEvolveStoneNum(player.getEvolveStoneNum());
		temp.setGold(player.getGold());
		temp.setHeadWearingID(player.getHeadWearingID());
		temp.setHeadWearingLevel(player.getHeadWearingLevel());
		temp.setWearingID(player.getWeaponID());
		temp.setWearingLevel(player.getWearingLevel());
		temp.setWeaponID(player.getWeaponID());
		temp.setWeaponLevel(player.getWeaponLevel());
		temp.setWingsID(player.getWingsID());
		temp.setWingsLevel(player.getWingsLevel());
		temp.setLevel(player.getLevel());
		temp.setNowExp(player.getNowExp());
		temp.setPotentialPoint(player.getPotentialPoint());
		temp.setPro(player.getPro());
		ShopVo shopVo = new ShopVo();
		shopVo.setEvolveStonePrice(player.getShop().getEvolveStonePrice());
		shopVo.setExpNum(player.getShop().getExpNum());
		shopVo.setExpPrice(player.getShop().getExpPrice());
		shopVo.setPPNum(player.getShop().getPPNum());
		shopVo.setPPPrice(player.getShop().getPPPrice());
		shopVo.setSkillPointPrice(player.getShop().getSkillPointPrice());
		shopVo.setUpGradeStonePrice(player.getShop().getUpGradeStonePrice());
		temp.setShopVo(shopVo);
		temp.setSkillChoosed(new int [3]);
		temp.setSkillList(player.getSkillList().clone());
		temp.setSkillPointNum(player.getSkillPointNum());
		temp.setUpGradeStoneNum(player.getUpGradeStoneNum());
		new FileHelper().saveData(temp, index);
	}
	@Override
	public void loadData(int index){
		this.player=new Player(new FileHelper().loadData(index));
	}
}
