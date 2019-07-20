package org.openjfx.snake.objects;

import javafx.scene.canvas.GraphicsContext;

import java.util.concurrent.ThreadLocalRandom;

import static org.openjfx.MainApp.*;

public class Fruit {

    public static final int WIDTH = CELL_SIZE;
    public static final int HEIGHT = CELL_SIZE;

    private final int X_COORD;
    private final int Y_COORD;

    public Fruit(int x_COORD, int y_COORD) {
        X_COORD = x_COORD;
        Y_COORD = y_COORD;
    }

    public int getX_COORD() {
        return X_COORD;
    }

    public int getY_COORD() {
        return Y_COORD;
    }

    public static Fruit spawn(Snake snake) {
        int randX = ThreadLocalRandom.current().nextInt(0, BOARD_WIDTH / CELL_SIZE) * CELL_SIZE;
        int randY = ThreadLocalRandom.current().nextInt(0, BOARD_HEIGHT / CELL_SIZE) * CELL_SIZE;

        boolean collides = snake.collides(randX, randY);
        while (collides) {
            randX = ThreadLocalRandom.current().nextInt(0, BOARD_WIDTH / CELL_SIZE) * CELL_SIZE;
            randY = ThreadLocalRandom.current().nextInt(0, BOARD_HEIGHT / CELL_SIZE) * CELL_SIZE;
            collides = snake.collides(randX, randY);
        }

        return new Fruit(randX, randY);
    }

    public boolean equals(SnakeBody snakeBody) {
        return snakeBody.getX_COORD() == X_COORD && snakeBody.getY_COORD() == Y_COORD;
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.fillRect(X_COORD, Y_COORD, WIDTH, HEIGHT);
    }

}
