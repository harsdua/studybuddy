package com.hd.studybuddy.view;

import com.hd.studybuddy.model.Deck;
import com.hd.studybuddy.model.DeckListViewCell;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.util.ArrayList;


/**
 * The controller for the main menu
 */
public class MainMenuViewController {
    @FXML
    private Label errorLabelMainMenu;
    @FXML
    private ListView<Deck> deckListView;
    @FXML
    private TextField filterTextField;

    private ArrayList<Deck> allDecks;
    private MainMenuListener listener;

    public interface MainMenuListener {
        /**
         * Opens AddDeckView
         */
        void onAddDeckBtnClicked();
        /**
         * Opens EditDeckView if a deck was selected
         * @param deckToEdit : deck selected by the user
         */
        void onEditDeckClicked(Deck deckToEdit);
        /**
         * Redirects the user to the OptionStatsView
         */
        void onOptionsClicked();
        /**
         * Redirects the user to the StudyDeckView if a deck was selected
         * @param deckToStudy : deck selected by the user
         */
        void onStudyDeckClicked(Deck deckToStudy);
        /**
         * Returns all the decks of the user
         */
        void refreshDeckList();

        /**
         * Open the user's files to choose something to import
         */
        void onImportDeckClicked();

        /**
         * After the user selected a deck in their deck list they can export it and save it in thier files
         * @param deckToExport : deck selected by the user
         */
        void onExportDeckClicked(Deck deckToExport);

    }

    public void setMainMenuListener(MainMenuListener mainMenuListener) {
        this.listener = mainMenuListener;
    }

    public void setModel(ArrayList<Deck> model) {
        this.allDecks = model;
        deckListView.setStyle("-fx-font-size: 2em ;");
        deckListView.getItems().addAll(allDecks);
        deckListView.setCellFactory(new Callback<ListView<Deck>, ListCell<Deck>>() {
            @Override
            public ListCell<Deck> call(ListView<Deck> deckListView) {
                return new DeckListViewCell();
            }
        });
    }

    public void show(Stage stage, Parent root) {
        stage.getScene().setRoot(root);
        stage.setWidth(600);
        stage.setHeight(550);
        stage.setTitle("StudyBuddy - Main Menu");
    }

    @FXML
    public void onAddDeckClick() {
        listener.onAddDeckBtnClicked();
    }

    @FXML
    public void onEditDeckClick() {
        Deck deckToEdit = deckListView.getSelectionModel().getSelectedItem();
        listener.onEditDeckClicked(deckToEdit);
    }

    public void noToEditDeckSelectedError() {
        errorLabelMainMenu.setTextFill(Color.RED);
        errorLabelMainMenu.setText("You must select a deck before editing a deck !");
    }

    @FXML
    public void onOptionsClick() {
        listener.onOptionsClicked();
    }

    @FXML
    public void onFilterClick(){
        listener.refreshDeckList();
        if(filterTextField.getText().isBlank()){
            return;
        }
        deckListView.getItems().removeIf(deck -> !deck.getTags().contains(filterTextField.getText()));
    }

    @FXML
    public void onStudyDeckClick() {
        Deck deckToStudy = deckListView.getSelectionModel().getSelectedItem();
        listener.onStudyDeckClicked(deckToStudy);
    }

    @FXML
    public void noToStudyDeckSelectedError() {
        errorLabelMainMenu.setTextFill(Color.RED);
        errorLabelMainMenu.setText("You must select a deck before studying a deck !");
    }

    @FXML
    public void deckSelectedEmptyError() {
        errorLabelMainMenu.setTextFill(Color.RED);
        errorLabelMainMenu.setText("You must add cards to the deck before studying a deck !");
    }

    @FXML
    public void notToImportDeckSelectedError() {
        errorLabelMainMenu.setTextFill(Color.RED);
        errorLabelMainMenu.setText("You must select a file to import a deck !");
    }

    @FXML
    public void importDeckFileEmptyError() {
        errorLabelMainMenu.setTextFill(Color.RED);
        errorLabelMainMenu.setText("The file is empty !");
    }

    @FXML
    public void importDeckSuccessful() {
        errorLabelMainMenu.setTextFill(Color.GREEN);
        errorLabelMainMenu.setText("The deck was imported successfully !");
    }

    @FXML
    public void notToExportDeckSelectedError() {
        errorLabelMainMenu.setTextFill(Color.RED);
        errorLabelMainMenu.setText("You must select a deck before exporting it !");
    }

    public void exportDeckFileError() {
        errorLabelMainMenu.setTextFill(Color.RED);
        errorLabelMainMenu.setText("Could not create the file");
    }

    @FXML
    public void exportDeckSuccessful() {
        errorLabelMainMenu.setTextFill(Color.GREEN);
        errorLabelMainMenu.setText("The deck was exported successfully !");
    }

    @FXML
    public void refreshDeckListView(ArrayList<Deck> decks){
        deckListView.getItems().clear();
        deckListView.getItems().addAll(decks);
    }

    @FXML
    public void onImportDeckClick() {
        listener.onImportDeckClicked();
    }

    @FXML
    public void onExportDeckClick(){
        Deck deckToExport = deckListView.getSelectionModel().getSelectedItem();
        listener.onExportDeckClicked(deckToExport);
    }

    /**
     * Show a file open dialog to choose which file to import
     * @return the file to import
     */
    public File showFileOpenDialog(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TEXT files", "*.txt"));
        fileChooser.setTitle("Import Deck");
        File file = fileChooser.showOpenDialog(stage);
        return file;
    }

    /**
     * Show a file save dialog to choose where to save the deck
     * @return the file to save
     */
    public File showFileSaveDialog(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TEXT files", "*.txt"));
        fileChooser.setTitle("Export Deck");
        File file = fileChooser.showSaveDialog(stage);
        return file;
    }
}