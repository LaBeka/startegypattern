package cards.strategies;

import cards.CardDealingStrategy;
import cards.trials.Card;
import cards.trials.Deck;

import java.util.*;

public class Fool implements CardDealingStrategy {
    private Deck deck;
    private int cardsPerHand;

    public Fool(int cardsPerHand) {
        this.cardsPerHand = cardsPerHand;
        deck = new Deck();
    }

    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<cards.trials.Card>> foolStrategy = new HashMap<>();

        for(var i = 1; i<= players; i++){
            foolStrategy.put("Player " + i, new ArrayList<>());
        }

        List<Card> restCards = deck.restCards();
        for(var j = 1; j <= players; j++){ // each player

            if(restCards.isEmpty()) return foolStrategy;

            for(var i = 1; i <= cardsPerHand; i++){ // 1 round
                Optional<Card> card = deck.dealCard();
                if(card.isEmpty()){
                    break;
                }
                foolStrategy.get("Player " + j).add(card.get());
            }
        }

        int stackSize = deck.size();
        foolStrategy.put(deck.getNameStack(), new ArrayList<>());
        for (int i = 0; i < stackSize; i++) {
            Optional<Card> card = deck.dealCard();
            if(card.isEmpty()) break;
            foolStrategy.get(deck.getNameStack()).add(card.get());
        }
        return foolStrategy;
    }
}
