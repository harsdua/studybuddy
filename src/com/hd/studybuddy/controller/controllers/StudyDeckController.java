package com.hd.studybuddy.controller.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import com.hd.studybuddy.controller.StudyBuddyApplication;
import com.hd.studybuddy.controller.managers.StudyDeckManager;
import com.hd.studybuddy.model.Deck;
import com.hd.studybuddy.view.StudyDeckViewController;

import java.io.IOException;

public class StudyDeckController extends Controller {
    private final StudyDeckManager studyDeckManager;
    private final StudyDeckControllerListener listener;

    public StudyDeckController(Stage stage, Deck deck, StudyDeckControllerListener listener) {
        this.stage = stage;
        this.studyDeckManager = new StudyDeckManager(deck);
        this.listener = listener;
    }
    @Override
    public void show() {
        try {
            String model = studyDeckManager.getQuestion();

            FXMLLoader loader = new FXMLLoader(StudyBuddyApplication.class.getResource("/com/hd/studybuddy/studybuddy/resources/StudyDeckView.fxml"));
            Parent root = loader.load();

            StudyDeckViewController controller = loader.getController();
            controller.setModel(model);
            controller.setStudyDeckListener(new StudyDeckViewController.StudyDeckListener() {
                @Override
                public void onCardClicked() {
                    controller.showCardAnswer(studyDeckManager.getAnswer());
                }
                @Override
                public void backMenuBtnClicked() {
                    listener.showMainMenu();
                }
                @Override
                public void knowledgeBtnClicked(int priority) {
                    studyDeckManager.setPriority(priority);
                    if (!studyDeckManager.goNext()) {
                        listener.showMainMenu();
                    }
                    else {
                        controller.setNextCard(studyDeckManager.getQuestion());
                    }
                }
            });
            controller.show(stage, root);
        } catch (IOException e) {
            showAlert();
        }
    }

    public interface StudyDeckControllerListener {
        void showMainMenu();
    }
}
