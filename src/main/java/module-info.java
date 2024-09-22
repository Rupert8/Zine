module start.zine {
    requires javafx.controls;
    requires javafx.fxml;


    opens start.zine to javafx.fxml;
    exports start.zine;
}