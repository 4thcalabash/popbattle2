package ui.awt.ImageButton;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends ImageView{
	/**
	 * staticGraohics->按钮平时状态
	 * pressedGraphics->按钮被按下状态
	 * myWorker->按钮被点击一次的动作执行类
	 */
	private ButtonWorker myWorker;
	private Image staticGraphics;
	private Image pressedGraphics;
	private Image enteredGraphics;
	private boolean entered=false;
	private boolean pressed=false;
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
				if (pressed==false){
					setImage(enteredGraphics);
				}
//				entered=true;
			}
			
		});
		this.setOnMousePressed(new EventHandler <MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				pressed=true;
				setImage(pressedGraphics);
			}
			
		});
		this.setOnMouseExited(new EventHandler <MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				entered=false;
				if (pressed==false){
					setImage(staticGraphics);
				}
			}
		});
		this.setOnMouseReleased(new EventHandler <MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				pressed=false;
				setImage(staticGraphics);
				if (entered==true){
					buttonWorker.work();
				}
			}
			
		});
	}
	public ButtonWorker getMyWorker() {
		return myWorker;
	}
	public void setMyWorker(ButtonWorker myWorker) {
		this.myWorker = myWorker;
	}
	public Image getStaticGraphics() {
		return staticGraphics;
	}
	public void setStaticGraphics(Image staticGraphics) {
		this.staticGraphics = staticGraphics;
	}
	public Image getPressedGraphics() {
		return pressedGraphics;
	}
	public void setPressedGraphics(Image pressedGraphics) {
		this.pressedGraphics = pressedGraphics;
	}
}
