package ui.sceneInterface;
import javafx.scene.Parent;
import ui.abstractStage.SupportParent;
import util.MissionInfo;
public interface BasicScene {
	public void createNewBattle(MissionInfo missionInfo,SupportParent father);
	public void setStage(Parent root);
	public void returnStatic();
	public void exitGame();
}
