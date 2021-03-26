package application.controllers;

import application.Main;
import javafx.event.ActionEvent;

public class UserMenuController {

    public void handleReservationBtn(ActionEvent e) throws Exception {
        Main.set_pane(5);
    }

    public void handleUserBtn(ActionEvent e) throws Exception {
        Main.set_pane(8);
    }

    public void handleHomeBtn(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }
}
