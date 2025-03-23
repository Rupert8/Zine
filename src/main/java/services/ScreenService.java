package services;

import com.sun.source.util.SourcePositions;
import controller.socialPassport.SocialPassport;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ScreenService {
    private static double prevX;
    private static double prevY;
    private static double prevWidth;
    private static double prevHeight;
    private static boolean isMaximized = false;

    private static double dragX;
    private static double dragY;

    public static void minimized_Window(Button minimizedButton) {
        Stage stage = (Stage) minimizedButton.getScene().getWindow();
        stage.setIconified(true);
    }

    public static void close_Window() {
        javafx.application.Platform.exit();
    }

    public static void maximized_Window(Window window) {
        Stage stage = (Stage) window;

        if (!isMaximized) {
            // Зберігаємо поточний розмір і позицію перед розгортанням
            prevX = stage.getX();
            prevY = stage.getY();
            prevWidth = stage.getWidth();
            prevHeight = stage.getHeight();

            // Розгортаємо вікно, залишаючи панель задач видимою
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());

            isMaximized = true;
        } else if(Double.isNaN(prevX) || Double.isNaN(prevY) || Double.isNaN(prevWidth) || Double.isNaN(prevHeight)) {

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            stage.setWidth(bounds.getWidth() * 0.8);
            stage.setHeight(bounds.getHeight() * 0.8);
            stage.setResizable(true);
            stage.setMaximized(false);

            prevX = stage.getX();
            prevY = stage.getY();
            prevWidth = stage.getWidth();
            prevHeight = stage.getHeight();
            isMaximized = false;
        } else {
            // Повертаємо вікно до попередніх розмірів
            stage.setX(prevX);
            stage.setY(prevY);
            stage.setWidth(prevWidth);
            stage.setHeight(prevHeight);

            isMaximized = false;
        }
    }

    public static void makeResizable(Stage stage, Scene scene) {
        final double border = 10; // Товщина області для зміни розміру

        scene.setOnMouseMoved(event -> {
            double x = event.getSceneX();
            double y = event.getSceneY();
            double width = scene.getWidth();
            double height = scene.getHeight();

            Cursor cursor = Cursor.DEFAULT;

            if (x > width - border && y > height - border) {
                cursor = Cursor.SE_RESIZE;
            } else if (x > width - border) {
                cursor = Cursor.E_RESIZE;
            } else if (y > height - border) {
                cursor = Cursor.S_RESIZE;
            }

            scene.setCursor(cursor);
        });

        scene.setOnMouseDragged(event -> {
            double x = event.getSceneX();
            double y = event.getSceneY();
            double width = scene.getWidth();
            double height = scene.getHeight();
            double stageMinWidth = stage.getMinWidth();
            double stageMinHeight = stage.getMinHeight();

            if (scene.getCursor() == Cursor.SE_RESIZE) {
                double newWidth = x;
                double newHeight = y;
                if (newWidth > stageMinWidth) {
                    stage.setWidth(newWidth);
                }
                if (newHeight > stageMinHeight) {
                    stage.setHeight(newHeight);
                }
            } else if (scene.getCursor() == Cursor.E_RESIZE) {
                double newWidth = x;
                if (newWidth > stageMinWidth) {
                    stage.setWidth(newWidth);
                }
            } else if (scene.getCursor() == Cursor.S_RESIZE) {
                double newHeight = y;
                if (newHeight > stageMinHeight) {
                    stage.setHeight(newHeight);
                }
            }
        });
    }

    public static void paneDragged(MouseEvent event, HBox hBox) {
        Stage stage = (Stage) hBox.getScene().getWindow();
        stage.setY(event.getScreenY() - dragY);
        stage.setX(event.getScreenX() - dragX);
        //System.out.println("працює");
    }

    public static void panePressed(MouseEvent event) {
        // Запам’ятовуємо різницю між позицією курсора та координатами вікна
        dragX = event.getSceneX();
        dragY = event.getSceneY();
    }

}
