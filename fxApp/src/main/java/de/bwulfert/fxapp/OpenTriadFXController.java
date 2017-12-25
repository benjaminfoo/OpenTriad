package de.bwulfert.fxapp;

import de.bwulfert.engine.OpenTriad;
import de.bwulfert.engine.controller.*;
import de.bwulfert.engine.model.Card;
import de.bwulfert.engine.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OpenTriadFXController implements Initializable, BattlefieldDelegate {

    @FXML
    GridPane battleField;

    @FXML
    Label player1Score;

    @FXML
    Label player2Score;

    @FXML
    VBox player1CardList;

    @FXML
    VBox player2CardList;

    @FXML
    Button startGame;

    @FXML
    Button nextTurn;

    @FXML
    Button exitGame;

    @FXML
    Label roundCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OpenTriad openTriad = new OpenTriad();
        openTriad.loadCards();

        BattlefieldController battlefieldController = new BattlefieldController(this);
        startGame.setOnAction(onClick -> {
            MoveDelegate playerMoveDelegate = new DefaultMoveDelegate();
            playerMoveDelegate.setActiveDeck(new PreselectedCardChooser().chooseCards(openTriad.getCards()));

            MoveDelegate cpuMoveDelegate = new DefaultMoveDelegate();
            cpuMoveDelegate.setActiveDeck(new PreselectedCardChooser().chooseCards(openTriad.getCards()));
            battlefieldController.initialize(
                    new PlayerController(
                            new Player("Test Player 1"),
                            playerMoveDelegate
                    ),
                    new PlayerController(
                            new Player("Test Player 2"),
                            cpuMoveDelegate
                    )
            );
            battlefieldController.nextTurn();
        });

        nextTurn.setOnAction(onClick -> {
            battlefieldController.nextTurn();
        });

        exitGame.setOnAction(onClick -> {
            System.exit(1);
        });

        nextTurn.setDisable(true);
    }

    @Override
    public void onGameStarted(BattlefieldController battlefieldController, PlayerController firstPlayer, PlayerController secondPlayer) {
        player1Score.setText(firstPlayer.getPlayer().getName() + "\n" + firstPlayer.getScore());
        player2Score.setText(secondPlayer.getPlayer().getName() + "\n" + secondPlayer.getScore());

        for (Card card : firstPlayer.getMoveDelegate().getActiveDeck().getCards()) {
            player1CardList.getChildren().add(new Label(card.toString()));
        }

        for (Card card : secondPlayer.getMoveDelegate().getActiveDeck().getCards()) {
            player2CardList.getChildren().add(new Label(card.toString()));
        }

        startGame.setDisable(true);
        nextTurn.setDisable(false);
    }

    @Override
    public void onTurnFinished(BattlefieldController battlefieldController, PlayerController firstPlayer, PlayerController secondPlayer, int turnCount) {
        player1Score.setText(firstPlayer.getPlayer().getName() + "\n" + firstPlayer.getScore());
        player2Score.setText(secondPlayer.getPlayer().getName() + "\n" + secondPlayer.getScore());

        player1CardList.getChildren().clear();
        for (Card card : firstPlayer.getMoveDelegate().getActiveDeck().getCards()) {
            player1CardList.getChildren().add(new Label(card.toString()));
        }

        player2CardList.getChildren().clear();
        for (Card card : secondPlayer.getMoveDelegate().getActiveDeck().getCards()) {
            player2CardList.getChildren().add(new Label(card.toString()));
        }

        Card[][] battleField = battlefieldController.getBattlefield().getBattleField();
        for (int y = 0; y < battleField.length; y++) {
            for (int x = 0; x < battleField[y].length; x++) {
                Card card = battleField[y][x];
                // if a card has been set to the gameboard and
                // there is no item in the gridview
                if (card != null && Utils.getNodeFromGridPane(this.battleField, x, y) == null) {
                    this.battleField.add(new Label(card.toString()), x, y);
                }
            }
            System.out.println("");
        }

        roundCount.setText("Round: " + turnCount);
    }

    @Override
    public void onGameFinished(BattlefieldController battlefieldController, PlayerController winner) {
        System.out.println("Game finished, the winner is: " + winner.getPlayer().getName());
        nextTurn.setDisable(true);
    }
}
