package risingBalloon;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application{
	
	public static Duration currentVal = Duration.millis(3000);
	
	public void start(Stage primaryStage) {
		
		Pane pane = new Pane();
		
		ImageView imageView = new ImageView("balloon/balloons.jpg");
		Text instructions = new Text("Press Up Arrow to Increase Speed");
		Text instructions2 = new Text("Press Dn Arrow to Decrease Speed");
		instructions.setX(30);
		instructions.setY(100);
		instructions2.setX(760);
		instructions2.setY(100);
		pane.getChildren().addAll(imageView, instructions, instructions2);
		
		PathTransition pt = new PathTransition(Duration.millis(10000), new Line(500,1050,500,-300), imageView);
		pt.setCycleCount(Timeline.INDEFINITE);
		pt.play();
		
		
		Scene scene = new Scene(pane, 1000, 1000);
		primaryStage.setTitle("Rising Balloons");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		scene.setOnKeyPressed(e -> {
			switch(e.getCode()) {
			case UP: 
				pt.stop();
				if(pt.getDuration().greaterThanOrEqualTo(new Duration(1000)))
					pt.setDuration(pt.getDuration().subtract(new Duration(1000)));
				pt.play();
				break;
			case DOWN:
				pt.stop();
				pt.setDuration(pt.getDuration().add(new Duration(1000)));
				pt.play();
				break;
			default:
				System.out.println("Error");
			}
		});
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
