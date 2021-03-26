package application.controllers;

import application.Main;
import javafx.event.ActionEvent;

public class AdminMenuController {
    public void handleReservationBtn(ActionEvent e) throws Exception {
        Main.set_pane(6);
    }

    public void handleUserBtn(ActionEvent e) throws Exception {
        Main.set_pane(7);
    }

    public void handleHomeBtn(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }

    public void handleResourceBtn(ActionEvent e) throws Exception {
        Main.set_pane(9);
    }
}
