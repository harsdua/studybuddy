package com.hd.studybuddy.controller.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import com.hd.studybuddy.controller.StudyBuddyApplication;
import com.hd.studybuddy.controller.managers.DeckManager;
import com.hd.studybuddy.controller.managers.LoginManager;
import com.hd.studybuddy.model.Deck;
import com.hd.studybuddy.view.MainMenuViewController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class MainMenuController extends Controller {
    private final DeckManager deckManager;
    private final String username;
    private final LoginManager loginManager;

    public MainMenuController(Stage stage, String username, LoginManager loginManager) {
        this.stage = stage;
        this.deckManager = new DeckManager();
        this.deckManager.initialize(username);
        this.username = username;
        this.loginManager = loginManager;
    }
    
    @Override
    public void show() {
        try {
            ArrayList<Deck> model = deckManager.retrieveAllDecks();

            FXMLLoader loader = new FXMLLoader(StudyBuddyApplication.class.getResource("/com/hd/studybuddy/studybuddy/resources/MainMenuView.fxml"));
            Parent root = loader.load();

            MainMenuViewController controller = loader.getController();
            controller.setModel(model);
            controller.setMainMenuListener(new MainMenuViewController.MainMenuListener() {
                @Override
                public void onAddDeckBtnClicked() {
                    openAddDeckWindow();
                }
                @Override
                public void onEditDeckClicked(Deck deckToEdit) {
                    if (deckToEdit == null) {
                        controller.noToEditDeckSelectedError();
                    }
                    else {
                        openEditDeckWindow(deckToEdit);
                    }
                }
                @Override
                public void onOptionsClicked() {
                    showOptionsStats();
                }

                @Override
                public void onStudyDeckClicked(Deck deckToStudy) {
                    if (deckToStudy == null) {
                        controller.noToStudyDeckSelectedError();
                    }
                    else if (deckToStudy.isEmpty()) {
                        controller.deckSelectedEmptyError();
                    }
                    else {
                        showStudyDeck(deckToStudy);
                    }
                }
                @Override
                public void refreshDeckList() {
                    controller.refreshDeckListView(deckManager.retrieveAllDecks());
                }

                @Override
                public void onImportDeckClicked() {
                    File file = controller.showFileOpenDialog(stage);
                    try {
                        Scanner sc = new Scanner(file);
                        if (file.length() == 0){
                            controller.importDeckFileEmptyError();
                        }
                        else{
                            MainMenuController.this.importDeck(sc);
                            this.refreshDeckList();
                            controller.importDeckSuccessful();
                        }
                        sc.close();
                    } catch (NullPointerException | FileNotFoundException e) {
                        controller.notToImportDeckSelectedError();
                    }
                }

                @Override
                public void onExportDeckClicked(Deck deckToExport) {

                    if (deckToExport == null) {
                        controller.notToExportDeckSelectedError();
                    }
                    else{
                        File file = controller.showFileSaveDialog(stage);
                        try {
                            PrintWriter pw = new PrintWriter(file);
                            MainMenuController.this.exportDeck(pw, deckToExport);
                            pw.close();
                            controller.exportDeckSuccessful();
                        }
                        catch (NullPointerException | FileNotFoundException e) {
                            controller.exportDeckFileError();
                        }
                    }
                }
            });
            controller.show(stage, root);
        } catch (IOException e) {
            showAlert();
            e.printStackTrace();
        }
    }

    private void openAddDeckWindow() {
        Stage newStage = new Stage();
        AddDeckController addDeckController = new AddDeckController(newStage, new AddDeckController.AddDeckControllerListener() {
            @Override
            public void addDeck(String deckName, String tags) {
                HashSet<String> tagsSet = new HashSet<>();
                tagsSet.addAll(Arrays.asList(tags.split(",")));
                Deck deck = new Deck(deckName, tagsSet);
                deckManager.addDeck(deck);
                show();
            }
        });
        addDeckController.show();
    }

    private void openEditDeckWindow(Deck deckToEdit) {
        Stage newStage = new Stage();
        EditDeckController editDeckController = new EditDeckController(newStage, deckToEdit, deckManager);
        editDeckController.show();
    }

    private void showOptionsStats() {
        OptionsStatsController optionsStatsController = new OptionsStatsController(stage, new OptionsStatsController.OptionsStatsControllerListener() {
            @Override
            public void showMainMenu() {
                MainMenuController.this.show();
            }
            @Override
            public void modifyPassword(String newPassword) {
                loginManager.modifyPassword(username, newPassword);
            }
        });
        optionsStatsController.show();
    }

    private void showStudyDeck(Deck deckToStudy) {
        StudyDeckController studyDeckController = new StudyDeckController(stage, deckToStudy, new StudyDeckController.StudyDeckControllerListener() {
            @Override
            public void showMainMenu() {
                MainMenuController.this.show();
            }
        });
        studyDeckController.show();
    }


    /**
     * Import a deck from a text file
     * @param sc the scanner to read the deck from the file
     */
    private void importDeck(Scanner sc) {
        deckManager.createDeckFromFile(sc);
    }

    /**
     * Export a deck to a text file
     * @param printWriter the printWriter to write the deck on the file
     * @param deckToExport the deck to export to the file
     */
    private void exportDeck(PrintWriter printWriter, Deck deckToExport) {
        deckManager.createFileFromDeck(printWriter, deckToExport);
    }


}
