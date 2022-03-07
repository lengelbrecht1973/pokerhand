package za.co.docketsystem;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import za.co.commonsystemlogic.service.PokerHandService;
import za.co.commonsystemlogic.entity.Score;

/**
 *
 * @author lengelbrecht
 *
 * This is a simple project to deal five cards to a user and then score the
 * hand. The algorithm for scoring a pokerhand is borrowed from
 * https://rosettacode.org/wiki/Poker_hand_analyser#Java
 *
 * The process flow: 1. Shuffle a standard deck of poker cards. The borrowed
 * algorithm uses simple characters like QH for queen of Hearts. 2. Take the top
 * five cards from the shuffled deck. 3. Get the score and print the poker score
 * name. 4. Display the hand in meaningfull English - for instance Queen of
 * Hearts instead of QH
 *
 */
@EnableAutoConfiguration
public class PokerSystemApplication {

    public static void main(String[] args) {
	PokerHandService pokerHandService = new PokerHandService();
	System.out.println("\n Shuffling deck . . . . .");
	String[] shuffledDeck = pokerHandService.shuffleDeck();  //Shuffle the deck

	//Take the first five cards from the shuffled deck
	String[] handDealt = new String[5];
	handDealt[0] = shuffledDeck[0];
	handDealt[1] = shuffledDeck[1];
	handDealt[2] = shuffledDeck[2];
	handDealt[3] = shuffledDeck[3];
	handDealt[4] = shuffledDeck[4];

	//Get the score
	Score score = pokerHandService.analyzeHand(handDealt);
	System.out.println("\nYou scored: " + score.getName() + "\n");

	//The borrowed alogorith uses cards in die form of for instance QH for Queen of hearts and 2H for 2 of hearts
	//NOt to spend too much time of rewriting the alogorith for our own Card class, it is decided to use the original
	//Display in readable format
	pokerHandService.displayHandInMeaningFullText(score.getHand());
    }

}
