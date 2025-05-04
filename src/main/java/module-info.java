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
    requires commons.math3;
    requires jdk.compiler;
    requires java.desktop;
    requires java.prefs;

    opens start.zine to javafx.fxml;
    exports start.zine;
    exports controller.curator.generalInfo;
    exports controller.curator.promotion;
    exports controller.curator.individualSupport;
    exports controller.curator.socialActivity;
    exports controller.admin;
    exports controller.curator.socialPassport;
    exports controller.curator.curatorDialog;
    exports data;
    exports tableView to org.hibernate.orm.core;
    exports hiberante.sessionFactory;
    exports hibernate.entity;
    exports enums;

    opens controller.admin.adminDialog to javafx.fxml;
    opens controller.admin to javafx.fxml;
    opens controller.curator to javafx.fxml;
    opens controller.curator.generalInfo to javafx.fxml;
    opens controller.curator.promotion to javafx.fxml;
    opens controller.curator.individualSupport to javafx.fxml;
    opens controller.curator.socialActivity to javafx.fxml;
    opens controller.curator.socialPassport to javafx.fxml;
    opens data to javafx.fxml;

    opens tableView to org.hibernate.orm.core, javafx.base;
    opens hibernate.entity to javafx.base, org.hibernate.orm.core;
    exports controller.passwordRecovery;
    opens controller.passwordRecovery to javafx.fxml;
    exports controller.login;
    opens controller.login to javafx.fxml;
    exports controller.curator;
    exports controller.extendedInformationAboutStudent;
    opens controller.extendedInformationAboutStudent to javafx.fxml;
    opens controller.curator.curatorDialog to javafx.fxml;
}
