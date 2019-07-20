package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;

public class FXMLController {

    @FXML
    private Rectangle rectangle;
    
    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
    }

    public void moveRectangle() {
        rectangle.setX(100);
        rectangle.setY(100);
    }
}
