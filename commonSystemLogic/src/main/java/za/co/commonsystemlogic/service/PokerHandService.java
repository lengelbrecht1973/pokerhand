package za.co.commonsystemlogic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import za.co.commonsystemlogic.constants.Face;
import za.co.commonsystemlogic.constants.Suit;
import za.co.commonsystemlogic.entity.Card;
import za.co.commonsystemlogic.entity.Score;

/**
 * @author lengelbrecht
 * 
 * Simple service containing method to shuffle a deck of cards, analyze the hand
 * dealt and display meaningful card names according to face and suit
 *
 */
//@Transactional(readOnly = false)
//@Service
public class PokerHandService {

    public PokerHandService() {
    }

    final static String faces = "AKQJT98765432";
    final static String suits = "HDSC";
    final static String[] deck = buildDeck();

    //Shuffle the deck of cards
    public String[] shuffleDeck() {
	
	List<String> deckAsList = Arrays.asList(deck);
	
	
	Collections.shuffle(deckAsList);
	Object[] shuffledObjectArray = deckAsList.toArray();
	String[] shuffledStringArray = Arrays.copyOf(shuffledObjectArray, shuffledObjectArray.length, String[].class);
	return shuffledStringArray;
    }

    //The algorith borrowed from https://rosettacode.org/wiki/Poker_hand_analyser#Java
    public Score analyzeHand(final String[] hand) {
	if (hand.length != 5) {
	    return new Score("invalid hand: wrong number of cards", -1, hand);
	}

	if (new HashSet<>(Arrays.asList(hand)).size() != hand.length) {
	    return new Score("invalid hand: duplicates", -1, hand);
	}

	int[] faceCount = new int[faces.length()];
	long straight = 0, flush = 0;
	for (String card : hand) {

	    int face = faces.indexOf(card.charAt(0));
	    if (face == -1) {
		return new Score("invalid hand: non existing face", -1, hand);
	    }
	    straight |= (1 << face);

	    faceCount[face]++;

	    if (suits.indexOf(card.charAt(1)) == -1) {
		return new Score("invalid hand: non-existing suit", -1, hand);
	    }
	    flush |= (1 << card.charAt(1));
	}

	// shift the bit pattern to the right as far as possible
	while (straight % 2 == 0) {
	    straight >>= 1;
	}

	// straight is 00011111; A-2-3-4-5 is 1111000000001
	boolean hasStraight = straight == 0b11111 || straight == 0b1111000000001;

	// unsets right-most 1-bit, which may be the only one set
	boolean hasFlush = (flush & (flush - 1)) == 0;

	if (hasStraight && hasFlush) {
	    return new Score("straight-flush", 9, hand);
	}

	int total = 0;
	for (int count : faceCount) {
	    if (count == 4) {
		return new Score("four-of-a-kind", 8, hand);
	    }
	    if (count == 3) {
		total += 3;
	    } else if (count == 2) {
		total += 2;
	    }
	}

	if (total == 5) {
	    return new Score("full-house", 7, hand);
	}

	if (hasFlush) {
	    return new Score("flush", 6, hand);
	}

	if (hasStraight) {
	    return new Score("straight", 5, hand);
	}

	if (total == 3) {
	    return new Score("three-of-a-kind", 4, hand);
	}

	if (total == 4) {
	    return new Score("two-pair", 3, hand);
	}

	if (total == 2) {
	    return new Score("one-pair", 2, hand);
	}

	return new Score("high-card", 1, hand);
    }

    //Print the face value of a card - for instance Queen
    public Face getFaceValue(String faceString) {
	try {
	    return Face.getEnumFormOneLetter(faceString);
	} catch (Exception e) {
	    System.out.println("Exeption: " + e.getMessage());
	}
	return null;
    }

    //Print the face value of a card - for instance Hearts
    public Suit getSuitValue(String suitString) {
	try {
	    return Suit.getEnumFormOneLetter(suitString);
	} catch (Exception e) {
	    System.out.println("Exeption: " + e.getMessage());
	}
	return null;
    }

    //The borrowed alogorith uses cards in die form of for instance QH for Queen of hearts and 2H for 2 of hearts
    //Not to spend too much time of rewriting the alogorith for our own Card class, it is decided to use the original
    //developers card format and then convert to own objects.
    //The alogorith can later be redeveloped
    //The next lines of code gives proper text for the facevalue (for instance Queen) and the suitvalue (for instance Hearts)
    public void displayHandInMeaningFullText(String[] handOfCards) {
	List<Card> handOfCardsForDisplay = new ArrayList<>();
	for (String hand : handOfCards) {

	    Card card = new Card();
	    try {
		card.setFace(getFaceValue(Character.toString(hand.charAt(0))));
		card.setSuit(getSuitValue(Character.toString(hand.charAt(1))));
		handOfCardsForDisplay.add(card);
	    } catch (Exception e) {
		System.out.println("Exeption: " + e.getMessage());
	    }

	}
	System.out.println("You were dealt with");
	for (Card card : handOfCardsForDisplay) {
	    System.out.println(card.getFace() + " of " + card.getSuit() + "S");
	}
    }

    //create the deck of cards
    private static String[] buildDeck() {
	String[] dck = new String[suits.length() * faces.length()];
	int i = 0;
	for (char s : suits.toCharArray()) {
	    for (char f : faces.toCharArray()) {
		dck[i] = "" + f + s;
		i++;
	    }
	}
	return dck;
    }

}
