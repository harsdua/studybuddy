package com.hd.studybuddy.controller.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import com.hd.studybuddy.controller.StudyBuddyApplication;
import com.hd.studybuddy.controller.managers.LoginManager;
import com.hd.studybuddy.view.LoginViewController;

import java.io.IOException;

public class LoginController extends Controller {
    private final LoginControllerListener listener;
    private LoginManager loginManager;

    public LoginController(Stage stage, LoginControllerListener listener) {
        this.stage = stage;
        this.listener = listener;
    }

    @Override
    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(StudyBuddyApplication.class.getResource("/com/hd/studybuddy/studybuddy/resources/LoginView.fxml"));
            Parent root = loader.load();
            LoginViewController controller = loader.getController();
            controller.setListener(new LoginViewController.LoginListener() {
                @Override
                public void onLoginClick(String username, String password) {
                    boolean isValidLogin = loginManager.verifyLoginCredentials(username, password);
                    if (isValidLogin) {
                        controller.loginSuccessful();
                        listener.showMainMenu(username, loginManager);
                    }
                    else {
                        controller.invalidCredentialsError();
                    }
                }
                @Override
                public void onRegisterClick(String username, String password) {
                    boolean isValidUsername = loginManager.isValidNewUser(username);
                    boolean isValidPassword = loginManager.isValidPassword(password);
                    if (!isValidUsername) {
                        controller.usernameTakenError();
                    }
                    else if (!isValidPassword) {
                        controller.invalidPasswordError();
                    }
                    else {
                        loginManager.register(username, password);
                        controller.registrationSuccessful();
                    }
                }
            });
            loginManager = new LoginManager();
            controller.show(stage, root);
        } catch (IOException e) {
            showAlert();
        }
    }

    public interface LoginControllerListener {
        void showMainMenu(String username, LoginManager loginManager);
    }
}
