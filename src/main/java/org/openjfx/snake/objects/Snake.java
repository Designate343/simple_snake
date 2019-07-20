package org.openjfx.snake.objects;

import javafx.scene.canvas.GraphicsContext;
import org.openjfx.snake.Direction;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

import static org.openjfx.MainApp.*;
import static org.openjfx.snake.Direction.*;
import static org.openjfx.snake.objects.SnakeBody.HEIGHT;
import static org.openjfx.snake.objects.SnakeBody.WIDTH;

public class Snake {

    private final Deque<SnakeBody> snake;
    private Direction currentDirection = RIGHT;

    public Snake() {
        Deque<SnakeBody> snake = new ArrayDeque<>(3);
        snake.push(new SnakeBody(60, 60));
        snake.push(new SnakeBody(80, 60));
        snake.push(new SnakeBody(100, 60));

        this.snake = snake;
    }

    public void drawSnake(GraphicsContext graphicsContext) {
        for (var body : snake) {
            graphicsContext.fillRect(body.getX_COORD(), body.getY_COORD(), WIDTH, HEIGHT);
        }
    }

    public void moveSnake() throws SnakeDead {
        Direction direction = this.currentDirection;
        if (direction == UP) {
            SnakeBody prevHead = getOldHead();
            int newY = setCoordBackInBounds(prevHead.getY_COORD() - CELL_SIZE, BOARD_HEIGHT);

            setNewHead(prevHead.getX_COORD(), newY);
        } else if (direction == DOWN) {
            SnakeBody prevHead = getOldHead();
            int newY = setCoordBackInBounds(prevHead.getY_COORD() + CELL_SIZE, BOARD_HEIGHT);

            setNewHead(prevHead.getX_COORD(), newY);
        } else if (direction == LEFT) {
            SnakeBody prevHead = getOldHead();
            int newX = setCoordBackInBounds(prevHead.getX_COORD() - CELL_SIZE, BOARD_WIDTH);

            setNewHead(newX, prevHead.getY_COORD());
        } else if (direction == RIGHT) {
            SnakeBody prevHead = getOldHead();
            int newX = setCoordBackInBounds(prevHead.getX_COORD() + CELL_SIZE, BOARD_WIDTH);

            setNewHead(newX, prevHead.getY_COORD());
        }

    }

    public boolean collidesWithObject(int x, int y) {
        for (var snakeSegment : snake) {
            if (x == snakeSegment.getX_COORD() && y == snakeSegment.getY_COORD()) {
                return true;
            }
        }
        return false;
    }

    public boolean eats(Fruit fruit) {
        var head = snake.peekFirst();
        boolean eats = fruit.equals(head);
        if (!eats) {
            snake.removeLast();
        }
        return eats;
    }

    private void setNewHead(int x, int y) throws SnakeDead {
        var newHead = new SnakeBody(x, y);

        if (checkForCollision(newHead)) {
            throw new SnakeDead();
        }

        snake.push(newHead);
    }

    private SnakeBody getOldHead() {
        SnakeBody prevHead = snake.peekFirst();
        Objects.requireNonNull(prevHead);
        return prevHead;
    }

    private int setCoordBackInBounds(int newCoord, int limit) {
        if (newCoord >= limit) {
            newCoord = 0;
        } else if (newCoord < 0) {
            newCoord = limit - CELL_SIZE;
        }
        return newCoord;
    }

    private boolean checkForCollision(SnakeBody newHead) {
        for (var bodySegment : snake) {
            if (bodySegment.equals(newHead)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return snake.isEmpty();
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public class SnakeDead extends Exception {
        public SnakeDead() {
            super();
        }
    }
}
