package ui.awt.ImageButton;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DialogableImageView extends ImageView {
	public static final int DELTAHEIGHT=30;
	public static final int DELTAWIDTH=30;
	public DialogableImageView(String information,Pane father,int height,int width){
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("entered");
				new InfoDialog(father,information,height,width,(int)e.getScreenX()-DELTAWIDTH,(int)e.getScreenY()-height+DELTAHEIGHT);
			}
		});
	}
	public DialogableImageView (String information,Pane father,int height,int width,String flag){
		this.setOnMouseEntered(e->{
			new InfoDialog(father,information,height,width,0,0,"");
		});
	}
}
