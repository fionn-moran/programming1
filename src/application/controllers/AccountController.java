package application.controllers;

import javafx.event.ActionEvent;
import application.Main;

public class AccountController {

    public void handleLogBtn(ActionEvent e) throws Exception {
        Main.set_pane(1);
    }

    public void handleRegBtn(ActionEvent e) throws Exception {
        Main.set_pane(2);
    }
}
