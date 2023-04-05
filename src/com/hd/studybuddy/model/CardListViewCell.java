package com.hd.studybuddy.model;

import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * This class represent the view of a card(recto-verso).
 */
public class CardListViewCell extends ListCell<Card> {
    private Label question;
    private Label answer;
    private GridPane gridPane;

    public CardListViewCell() {
        super();
        question = new Label();
        answer = new Label();
        gridPane = new GridPane();
        gridPane.setPrefWidth(super.getPrefWidth());
        gridPane.setHgap(15.0);
        gridPane.add(question, 0, 0);
        gridPane.add(answer, 1, 0);
    }
    @Override
    protected void updateItem(Card card, boolean empty){
        super.updateItem(card, empty);
        if(card != null && !empty){
            question.setText(card.getQuestion() + " :");
            answer.setText(card.getAnswer());
            setGraphic(gridPane);
        }
        else{
            setGraphic(null);
        }
    }
}
