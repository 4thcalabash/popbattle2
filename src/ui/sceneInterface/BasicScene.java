package ui.sceneInterface;
import javafx.scene.Parent;
import javafx.stage.Stage;
import po.*;
import util.*;
public interface BasicScene {
	public void createNewBattle(MissionInfo missionInfo);
	public void setStage(Parent root);
	public void returnStatic();
	public void exitGame();
}
