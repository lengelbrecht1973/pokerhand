/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.commonsystemlogic.constants;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author lengelbrecht
 */
public enum Suit {

    SPADE("S"),
    HEART("H"),
    DIAMOND("D"),
    CLUB("C");

    private final String oneLetterForAnalyzing;

    Suit(String oneLetterForAnalyzing) {
	this.oneLetterForAnalyzing = oneLetterForAnalyzing;
    }

    public String getOneLetterForAnalyzing() {
	return oneLetterForAnalyzing;
    }

    public static Suit getEnumFormOneLetter(String letter) throws Exception {
	List<Suit> suits = Arrays.asList(Suit.values());
	Suit suitValue = null;
	for (Suit suit : suits) {
	    if (suit.getOneLetterForAnalyzing().equals(letter)) {
		suitValue = suit;
	    }

	}
	if (suitValue == null) {
	    throw new Exception(letter + " could not be converted to a Suit Value");
	} else {
	    return suitValue;
	}
    }
}
