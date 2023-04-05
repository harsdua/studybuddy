package com.hd.studybuddy.view;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The controller for the login window
 */
public class LoginViewController {

    @FXML
    private TextField registrationUsername;
    @FXML
    private PasswordField registrationPassword;
    @FXML
    private PasswordField retypedRegistrationPassword;
    @FXML
    private Label registrationErrorLabel;
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Label loginErrorLabel;
    private LoginListener listener;

    public interface LoginListener {
        /**
         * Redirects the user to the MainMenu if the password and the username are valid
         * @param username : username entered by the user in LoginView
         * @param password : password entered by the user in LoginView
         */
        void onLoginClick(String username, String password);
        /**
         * Registers a new user with the username and the password
         * @param username : username entered by the user in LoginView
         * @param password : password entered by the user in LoginView
         */
        void onRegisterClick(String username, String password);
    }

    public void setListener(LoginListener loginListener) {
        this.listener = loginListener;
    }

    public void show(Stage stage, Parent root) {
        stage.setScene(new Scene(root, 800, 557));
        stage.setTitle("StudyBuddy");
        stage.show();
    }

    /**
     * This methode is used when the user click on the register button, and check the inputs.
     */
    @FXML
    public void onRegisterClick() {
        registrationErrorLabel.setTextFill(Color.RED);
        boolean isSamePassword = registrationPassword.getText().equals(retypedRegistrationPassword.getText());
        boolean areAnyFieldsEmpty = registrationUsername.getText().isEmpty()
                || registrationPassword.getText().isEmpty()
                || retypedRegistrationPassword.getText().isEmpty();
        if (!isSamePassword) {
            registrationErrorLabel.setText("Passwords do not match");
        }
        else if (areAnyFieldsEmpty) {
            registrationErrorLabel.setText("One or more fields are empty");
        }
        else {
            listener.onRegisterClick(registrationUsername.getText(), registrationPassword.getText());
        }
    }
    @FXML
    public void registrationSuccessful() {
        registrationErrorLabel.setTextFill(Color.GREEN);
        registrationErrorLabel.setText("Registration successful. You can now login");
    }
    @FXML
    public void usernameTakenError() {
        registrationErrorLabel.setText("Username is already taken. Please choose another one");
    }
    @FXML
    public void invalidPasswordError() {
        registrationErrorLabel.setText("Password is not strong enough. Must contain at least 7 characters)");
    }

    /**
     * This method is used when the user click on the login button, and check the inputs.
     */
    @FXML
    public void onLoginClick() {
        listener.onLoginClick(loginUsername.getText(), loginPassword.getText());
    }
    @FXML
    public void loginSuccessful() {
        loginErrorLabel.setTextFill(Color.GREEN);
        loginErrorLabel.setText("Login successful");
    }
    @FXML
    public void invalidCredentialsError() {
        loginErrorLabel.setText("Invalid login credentials");
    }
}