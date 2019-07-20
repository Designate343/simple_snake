package org.openjfx.snake.objects;

import static org.openjfx.MainApp.CELL_SIZE;

public class SnakeBody {
    public static final int WIDTH = CELL_SIZE;
    public static final int HEIGHT = CELL_SIZE;

    private final int X_COORD;
    private final int Y_COORD;

    public SnakeBody(int x_COORD, int y_COORD) {
        X_COORD = x_COORD;
        Y_COORD = y_COORD;
    }

    public int getX_COORD() {
        return X_COORD;
    }

    public int getY_COORD() {
        return Y_COORD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnakeBody snakeBody = (SnakeBody) o;
        return X_COORD == snakeBody.X_COORD &&
                Y_COORD == snakeBody.Y_COORD;
    }

}
