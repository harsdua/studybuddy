package com.hd.studybuddy.model;


import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * This class represent the view of the list of the decks.
 */
public class DeckListViewCell extends ListCell<Deck>{
    private Text deckName;
    private VBox vBox;
    private Text deckTags;

    public DeckListViewCell(){
        super();
        deckName = new Text();
        deckTags = new Text();
        vBox = new VBox(deckName, deckTags);
    }

    @Override
    protected void updateItem(Deck deck, boolean empty){
        super.updateItem(deck, empty);
        if(deck != null && !empty){
            deckName.setText(deck.getTitle());
            deckTags.setText(deck.getTags().toString());
            deckTags.setFont(deckTags.getFont().font(13));
            setGraphic(vBox);
        }
        else{
            setGraphic(null);
        }
    }

}





/*

    @Override
    public void start(Stage primaryStage) {
        ObservableList<CustomThing> data = FXCollections.observableArrayList();
        data.addAll(new CustomThing("Cheese", 123), new CustomThing("Horse", 456), new CustomThing("Jam", 789));

        final ListView<CustomThing> listView = new ListView<CustomThing>(data);
        listView.setCellFactory(new Callback<ListView<CustomThing>, ListCell<CustomThing>>() {
            @Override
            public ListCell<CustomThing> call(ListView<CustomThing> listView) {
                return new CustomListCell();
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(listView);
        primaryStage.setScene(new Scene(root, 200, 250));
        primaryStage.show();
    }

    private class CustomListCell extends ListCell<CustomThing> {
        private HBox content;
        private Text name;
        private Text price;

        public CustomListCell() {
            super();
            name = new Text();
            price = new Text();
            VBox vBox = new VBox(name, price);
            content = new HBox(new Label("[Graphic]"), vBox);
            content.setSpacing(10);
        }

        @Override
        protected void updateItem(CustomThing item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) { // <== test for null item and empty parameter
                name.setText(item.getName());
                price.setText(String.format("%d $", item.getPrice()));
                setGraphic(content);
            } else {
                setGraphic(null);
            }
        }
 */