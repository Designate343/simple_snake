package org.openjfx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.openjfx.snake.game.HandleInput;
import org.openjfx.snake.game.GameLoop;
import org.openjfx.snake.objects.Snake;


public class MainApp extends Application {

    public static final int BOARD_WIDTH = 480;
    public static final int BOARD_HEIGHT = 360;
    public static final int CELL_SIZE = 20;
    private static final Duration ANIMATION_SPEED = Duration.millis(50);

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT, Color.WHITE);

        final Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        root.getChildren().addAll(canvas);

        final Snake snake = new Snake();

        GameLoop gameLoop = new GameLoop(gc, snake);

        scene.setOnKeyPressed(new HandleInput(snake));

        Timeline timeline = new Timeline(
                new KeyFrame(ANIMATION_SPEED, gameLoop)
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        stage.setTitle("Snake by me");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
