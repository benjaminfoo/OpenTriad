package org.owls.opentriad.controller;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.owls.opentriad.OpenTriad;
import org.owls.opentriad.R;
import org.owls.opentriad.modell.Card;
import org.owls.opentriad.modell.Competitor;
import org.owls.opentriad.modell.UserProfile;
import org.owls.opentriad.modell.ai.RandomBot;
import org.owls.opentriad.ui.customviews.BattleFieldView;
import org.owls.opentriad.ui.customviews.CardView;
import org.owls.opentriad.ui.customviews.DeckView;
import org.owls.opentriad.ui.customviews.delegates.BattleFieldViewDelegates;
import org.owls.opentriad.ui.customviews.delegates.DeckViewDelegates;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * The GameController handles the main-game-mechanics and manages game specific rules.
 * This is the class where everything happens!
 *
 * TODO: reduce view references if possible...?
 */
public class GameController implements DeckViewDelegates, BattleFieldViewDelegates {

    // root layout of the mainactivity
    // this is also used for context references!
    public View activityView;
    private Context context;

    //
    public BattleFieldView battleFieldView;

    //
    public CardController cardController;

    //
    public UserProfileController userProfileController;

    // the main player
    public Competitor player;
    public DeckView playerDeck;

    // the opponent
    public RandomBot opponent;
    public DeckView opponentDeck;

    // the current entity that controls the game flow
    public Competitor current;

    TextView gameLog;

    public GameController(View activityView) {
        this.activityView = activityView;
        this.context = activityView.getContext();

        this.gameLog = (TextView) activityView.findViewById(R.id.gameLog);
        this.battleFieldView = (BattleFieldView) activityView.findViewById(R.id.cardboardView);

        this.cardController = new CardController(activityView.getContext());

        this.userProfileController = new UserProfileController(context);

        this.player = new Competitor(userProfileController.loadUserprofile().name, cardController.generateDeck(player));

        this.opponent = new RandomBot("Bot B", cardController.generateDeck(opponent));

        playerDeck = (DeckView) activityView.findViewById(R.id.player_deck);
        playerDeck.initializePlayerData(player);

        opponentDeck = (DeckView) activityView.findViewById(R.id.opponent_deck);
        opponentDeck.initializePlayerData(opponent);

        battleFieldView.setBattleFieldViewDelegates(this);
        playerDeck.setDeckViewDelegates(this);

        onScoreChanged(player, OpenTriad.COMPETITORS_INITIAL_SCORE);
        onScoreChanged(opponent, OpenTriad.COMPETITORS_INITIAL_SCORE);

        startGame();
    }

    /**
     * Sets the first player and starts the turn loop
     * TODO: flip coin for player start!
     */
    public void startGame() {
        current = opponent;
        startNewTurn();
    }

    @Override
    public void activeCardSet(Card card) {
        gameLog.setText("Choosen card: " + card.name);
    }

    @Override
    public void onEmptyCardViewClick(CardView cardView, int row, int pos) {
        if (current == player && playerDeck.hasActiveCard()) {
            battleFieldView.battleField.setCard(row, pos, playerDeck.useActiveCard());
            endCurrentTurn();
        } else if (current == opponent && !current.isBot && opponentDeck.hasCardsLeft()) {
            battleFieldView.battleField.setCard(row, pos, opponentDeck.useActiveCard());
            endCurrentTurn();
        }
    }

    private void startNewTurn() {
        current.active = true;
        gameLog.setText("It\'s " + current.name + "\'s turn!");

        if(current.isBot){
            gameLog.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RandomBot randomBot = (RandomBot) current;
                    DeckView currentDeck = current == player ? playerDeck : opponentDeck;
                    randomBot.computeActiveCard(currentDeck);

                    int[] position = randomBot.computeCardPosition();
                    while (battleFieldView.positionAlreadyInUse(position[0], position[1])){
                        position = randomBot.computeCardPosition();
                    }

                    battleFieldView.battleField.setCard(position[0], position[1], currentDeck.useActiveCard());

                    endCurrentTurn();

                }
            }, OpenTriad.AI_TIME_PER_TURN);
        }
    }

    public void endCurrentTurn(){
        current.active = false;
        gameLog.setText(current.name + context.getString(R.string.ingame_competitor_set_card));

        if(current == player){
            current = opponent;
        } else {
            current = player;
        }

        if(battleFieldView.allSlotsSet()){

            boolean draw = player.score == opponent.score;
            boolean playerWon = player.score > opponent.score;

            if(draw){
                gameLog.setText(context.getString(R.string.ingame_game_result_draw));
            } else {
                gameLog.setText((playerWon ? player.name : opponent.name) + " " + context.getString(R.string.ingame_game_result_competitor_wins_the_game));
            }

            UserProfileController userProfileController = new UserProfileController(activityView.getContext());
            UserProfile defaultUserprofile = userProfileController.loadUserprofile();

            if (draw){
                defaultUserprofile.draws++;
            }else if(playerWon){
                defaultUserprofile.wins++;
            } else {
                defaultUserprofile.looses++;
            }

            userProfileController.saveUserProfile(defaultUserprofile);

        } else {
            startNewTurn();
        }
    }

    @Override
    public void onSetCardViewClick(CardView cardView, int row, int pos) {

    }

    @Override
    public void onScoreChanged(Competitor owner, int newScore) {
        TextView score = (TextView) activityView.findViewById(owner == player ? R.id.player_score : R.id.opponent_score);
        score.setText("" + newScore);

    }
}
