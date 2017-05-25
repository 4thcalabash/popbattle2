package ui.awt.ImageButton;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import util.Audio;
public class ImageButton extends ImageView{
	/**
	 * staticGraohics->按钮平时状态
	 * pressedGraphics->按钮被按下状态
	 * myWorker->按钮被点击一次的动作执行类
	 */
	private ButtonWorker myWorker,enteredWorker;
	private Image staticGraphics;
	private Image pressedGraphics;
	private Image enteredGraphics;
	private boolean entered=false;
	private boolean pressed=false;
	private ImageButton myself = this;
	private boolean playAudio=true;
	private final AudioClip enteredAudio = Audio.entered;
	private final AudioClip illegalAudio = Audio.illegal;
	private final AudioClip pressedAudio = Audio.pressed;
	public ImageButton(Image staticGraphics,Image enteredGraphics,Image pressedGraphics,ButtonWorker buttonWorker){
		
		this.staticGraphics=staticGraphics;
		this.pressedGraphics=pressedGraphics;
		this.enteredGraphics=enteredGraphics;
		this.myWorker=buttonWorker;
		this.setImage(staticGraphics);
		this.setOnMouseEntered(new EventHandler <MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				entered=true;
				System.out.println("is in!");
				if (pressed==false){
					setImage(myself.enteredGraphics);
					if (playAudio)
					enteredAudio.play();
				}
//				entered=true;
			}
			
		});
		this.setOnMousePressed(new EventHandler <MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				pressed=true;
				if (!playAudio){
					illegalAudio.play();
				}else{
					pressedAudio.play();
				}
				setImage(myself.pressedGraphics);
			}
			
		});
		this.setOnMouseExited(new EventHandler <MouseEvent>(){

//			@SuppressWarnings("deprecation")
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				entered=false;
				//move.stop();
				if (pressed==false){
					setImage(myself.staticGraphics);
				}
			}
		});
		this.setOnMouseReleased(new EventHandler <MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				pressed=false;
				setImage(myself.staticGraphics);
				if (entered==true){
					myself.myWorker.work();
				}
			}
			
		});
	}
	public ImageButton(Image staticGraphics,Image enteredGraphics,Image pressedGraphics,ButtonWorker buttonWorker,ButtonWorker buttonWorker2){
		
		this.staticGraphics=staticGraphics;
		this.pressedGraphics=pressedGraphics;
		this.enteredGraphics=enteredGraphics;
		this.myWorker=buttonWorker;
		this.enteredWorker=buttonWorker2;
		this.setImage(staticGraphics);
		this.setOnMouseEntered(new EventHandler <MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				entered=true;
				System.out.println("is in!");
				if (pressed==false){
					setImage(myself.enteredGraphics);
					enteredWorker.work();
					if (playAudio)
					enteredAudio.play();
				}
//				entered=true;
			}
			
		});
		this.setOnMousePressed(new EventHandler <MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				pressed=true;
				if (!playAudio){
					illegalAudio.play();
				}else{
					pressedAudio.play();
				}
				setImage(myself.pressedGraphics);
			}
			
		});
		this.setOnMouseExited(new EventHandler <MouseEvent>(){

//			@SuppressWarnings("deprecation")
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				entered=false;
				//move.stop();
				if (pressed==false){
					setImage(myself.staticGraphics);
				}
			}
		});
		this.setOnMouseReleased(new EventHandler <MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				pressed=false;
				setImage(myself.staticGraphics);
				if (entered==true){
					myself.myWorker.work();
				}
			}
			
		});
	}
	
	
	
	public ButtonWorker getMyWorker() {
		return myWorker;
	}
	public void setMyWorker(ButtonWorker myWorker) {
		Platform.runLater(()->{
			this.myWorker = myWorker;
		});

	}
	public void setEnteredWorker(ButtonWorker enteredWorker){
		this.enteredWorker=enteredWorker;
	}
	public Image getStaticGraphics() {
		return staticGraphics;

	}
	public void setStaticGraphics(Image staticGraphics) {
		Platform.runLater(()->{
			System.out.println("Make Static Illegal");
			this.staticGraphics = staticGraphics;
			this.setImage(staticGraphics);
		});
	}
	public Image getPressedGraphics() {
		return pressedGraphics;
	}
	public void setPressedGraphics(Image pressedGraphics) {
		Platform.runLater(()->{
			this.pressedGraphics = pressedGraphics;
		});
	}
	public Image getEnteredGraphics() {
		return enteredGraphics;
	}
	public void setEnteredGraphics(Image enteredGraphics) {
		Platform.runLater(()->{
			this.enteredGraphics = enteredGraphics;
		});
	}
	public void setPlayAudio(boolean playAudio) {
		this.playAudio = playAudio;
	}
	
	
}
