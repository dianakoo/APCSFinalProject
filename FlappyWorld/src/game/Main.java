package game;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HEIGHT = 400;

	private Button button = null;
	private Group root = null;
	private ImageView bkgrd = null;
	private Node flappy = null;

	private Timeline down;

	private void addActionEventHandler() {
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				down = new Timeline();
				down.setCycleCount(1);
				
				KeyValue kv = new KeyValue(flappy.translateYProperty(), -1000, new Interpolator() {
					
					@Override
					protected double curve(double t) {
						// TODO Auto-generated method stub
						return 0.8 * t - 2 * Math.pow(t, 2);
					}
				});
				
				KeyFrame flap = new KeyFrame(Duration.millis(3000), kv);
				down.getKeyFrames().add(flap);
				down.setAutoReverse(false);
				down.play();
				
			}
		});
	}

	private void addMouseEventHandler() {
		root.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				down = new Timeline();
				down.setCycleCount(1);
				
				KeyValue kv = new KeyValue(flappy.translateYProperty(), -1000, new Interpolator() {
					
					@Override
					protected double curve(double t) {
						// TODO Auto-generated method stub
						double y = 0.8 * t - 2 * Math.pow(t, 2);
						
						return y;
					}
				});
				
				KeyFrame flap = new KeyFrame(Duration.millis(3000), kv);
				down.getKeyFrames().add(flap);
				down.setAutoReverse(false);
				down.play();
				

				String url = getClass().getResource("/flap.mp3").toString();
				Media m = new Media(url);
				MediaPlayer player = new MediaPlayer(m);
				player.play();

			}
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create a Group
		root = new Group();

		// TODO 1: add background
		bkgrd = new ImageView(getClass().getResource("/background.png")
				.toString());

		// TODO 2: add Flappy
		flappy = new ImageView(getClass().getResource("/flappy.png").toString());
		flappy.layoutXProperty().set(WINDOW_WIDTH / 2);
		flappy.layoutYProperty().set(WINDOW_HEIGHT / 2);

		// TODO 3: add Button
		button = new Button("Start");
		button.layoutXProperty().set(WINDOW_WIDTH / 2);

		// Add controls
		root.getChildren().add(bkgrd);
		root.getChildren().add(flappy);
		root.getChildren().add(button);

		// TODO 4: add action handler to the button
		addActionEventHandler();

		// TODO 5: add mouse handler to the scene
		addMouseEventHandler();

		// Animation

		// Create scene and add to stage
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
