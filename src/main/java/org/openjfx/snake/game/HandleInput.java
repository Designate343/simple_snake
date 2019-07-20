package org.openjfx.snake.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.openjfx.snake.Direction;
import org.openjfx.snake.objects.Snake;

public class HandleInput implements EventHandler<KeyEvent> {

    private final Snake snake;

    public HandleInput(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT && snake.getCurrentDirection() != Direction.RIGHT) {
            snake.setCurrentDirection(Direction.LEFT);
        } else if (event.getCode() == KeyCode.RIGHT && snake.getCurrentDirection() != Direction.LEFT) {
            snake.setCurrentDirection(Direction.RIGHT);
        } else if (event.getCode() == KeyCode.UP && snake.getCurrentDirection() != Direction.DOWN) {
            snake.setCurrentDirection(Direction.UP);
        } else if (event.getCode() == KeyCode.DOWN && snake.getCurrentDirection() != Direction.UP) {
            snake.setCurrentDirection(Direction.DOWN);
        }
    }
}
