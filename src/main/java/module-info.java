module start.zine {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.management;
    requires jakarta.mail;
    requires jakarta.activation;
    requires org.apache.poi.ooxml;

    opens start.zine to javafx.fxml;
    exports start.zine;
    exports controller.generalInfo;
    exports controller.promotion;
    exports controller.individualSupport;
    exports controller.socialActivity;
    exports controller.admin;
    exports controller;
    exports data;
    exports tableView to org.hibernate.orm.core;

    opens controller.admin.adminDialog to javafx.fxml;
    opens controller.admin to javafx.fxml;
    opens controller.curator to javafx.fxml;
    opens controller.generalInfo to javafx.fxml;
    opens controller.promotion to javafx.fxml;
    opens controller.individualSupport to javafx.fxml;
    opens controller.socialActivity to javafx.fxml;
    opens controller to javafx.fxml;
    opens data to javafx.fxml;

    opens tableView to org.hibernate.orm.core, javafx.base;
    opens hibernate.entity to javafx.base, org.hibernate.orm.core;
    exports controller.passwordRecovery;
    opens controller.passwordRecovery to javafx.fxml;
}
