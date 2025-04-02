package interfaces.WindowActions;

import javafx.scene.input.MouseEvent;

public interface WindowControl {
    void setMinimizeWindowButton();

    void setMaximizeWindowButton();

    void setCloseWindow();

    void setDragWindow(MouseEvent dragEvent);

    void setPressedWindow(MouseEvent mouseEvent);

}
