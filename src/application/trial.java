package application;

import java.util.List;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class trial extends Application {

	private Path generateCurvyPath(final double pathOpacity) {
		final Path path = new Path();
		path.getElements().add(new MoveTo(20, 20));
		path.getElements().add(new LineTo(100, 100));
//		path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
//	    path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
		path.setOpacity(pathOpacity);
		return path;
	}

	private PathTransition generatePathTransition(final Shape shape, final Path path) {
		final PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.seconds(8.0));
		pathTransition.setDelay(Duration.seconds(2.0));
		pathTransition.setPath(path);
		pathTransition.setNode(shape);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(Timeline.INDEFINITE);
		pathTransition.setAutoReverse(true);
		return pathTransition;
	}


	private void applyAnimation(final Group group) {
		final Circle circle = new Circle(20, 20, 15);
		circle.setFill(Color.DARKRED);
		final Path path = generateCurvyPath(1);
		group.getChildren().add(path);
		group.getChildren().add(circle);
		group.getChildren().add(new Circle(20, 20, 5));
		group.getChildren().add(new Circle(380, 240, 5));
		final PathTransition transition = generatePathTransition(circle, path);
		transition.play();
	}

	/**
	 * Start the JavaFX application
	 * 
	 * @param stage
	 *            Primary stage.
	 * @throws Exception
	 *             Exception thrown during application.
	 */
	@Override
	public void start(final Stage stage) throws Exception {
		final Group rootGroup = new Group();
		final Scene scene = new Scene(rootGroup, 600, 400);
		stage.setScene(scene);
		stage.setTitle("JavaFX 2 Animations");
		stage.show();
		applyAnimation(rootGroup);
	}

	public static void main(final String[] arguments) {
		launch(arguments);
	}
}
