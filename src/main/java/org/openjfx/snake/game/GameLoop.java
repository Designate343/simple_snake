package org.openjfx.snake.game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import org.openjfx.snake.objects.Fruit;
import org.openjfx.snake.objects.Snake;

import static org.openjfx.MainApp.*;

public class GameLoop implements EventHandler<ActionEvent> {

    private GraphicsContext graphicsContext;
    private Snake snake;
    private Fruit fruit;
    private int fruitEaten = 0;

    public GameLoop(GraphicsContext graphicsContext, Snake snake) {
        this.graphicsContext = graphicsContext;
        this.snake = snake;
        this.fruit = Fruit.spawn(this.snake);
    }

    @Override
    public void handle(ActionEvent event) {
        clearBoard();

        snake.drawSnake(graphicsContext);
        fruit.draw(graphicsContext);

        try {
            snake.moveSnake();
        } catch (Snake.SnakeDead snakeDead) {
            clearBoard();

            snake.drawDeadSnake(graphicsContext);

            graphicsContext.fillText("GAME OVER", (BOARD_WIDTH / 2) - 50, BOARD_HEIGHT / 2);
            graphicsContext.fillText("YOU ATE " + fruitEaten + " FRUITS",
                    (BOARD_WIDTH / 2) - 50, (BOARD_HEIGHT / 2) + CELL_SIZE);
            return;
        }

        if (snake.eats(fruit)) {
            fruitEaten++;
            fruit = Fruit.spawn(snake);
        }
    }

    private void clearBoard() {
        graphicsContext.clearRect(0,0,BOARD_WIDTH,BOARD_HEIGHT);
    }

}
