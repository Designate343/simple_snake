package org.openjfx.snake.game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import org.openjfx.snake.objects.Fruit;
import org.openjfx.snake.objects.Snake;

public class MainLoop implements EventHandler<ActionEvent> {

    private GraphicsContext graphicsContext;
    private Snake snake;
    private Fruit fruit;

    public MainLoop(GraphicsContext graphicsContext, Snake snake) {
        this.graphicsContext = graphicsContext;
        this.snake = snake;
        this.fruit = Fruit.spawn(this.snake);
    }

    @Override
    public void handle(ActionEvent event) {
        graphicsContext.clearRect(0,0,480,360);

        snake.drawSnake(graphicsContext);
        fruit.draw(graphicsContext);

        try {
            snake.moveSnake();
        } catch (Snake.SnakeDead snakeDead) {
            return;
        }

        if (snake.eats(fruit)) {
            fruit = Fruit.spawn(snake);
        }
    }

}
