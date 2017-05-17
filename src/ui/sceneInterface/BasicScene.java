package ui.sceneInterface;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import util.MissionInfo;
public interface BasicScene {
	public void createNewBattle(MissionInfo missionInfo,AnchorPane father);
	public void setStage(Parent root);
	public void returnStatic();
	public void exitGame();
}
