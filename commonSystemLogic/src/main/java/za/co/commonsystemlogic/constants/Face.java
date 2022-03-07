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
public enum Face {

    ACE("A"),
    KING("K"),
    QUEEN("Q"),
    JACK("J"),
    TEN("T"),
    NINE("9"),
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2");

    private final String oneLetterForAnalyzing;
    private final Face enumFormOneLetter;

    Face(String oneLetterForAnalyzing) {
	this.oneLetterForAnalyzing = oneLetterForAnalyzing;
	this.enumFormOneLetter = null;
    }

    public String getOneLetterForAnalyzing() {
	return oneLetterForAnalyzing;
    }

    public static Face getEnumFormOneLetter(String letter) throws Exception {
	List<Face> faces = Arrays.asList(Face.values());
	Face faceValue = null;
	for (Face face : faces) {
	    if (face.getOneLetterForAnalyzing().equals(letter)) {
		faceValue = face;
	    }

	}
	if (faceValue == null) {
	    throw new Exception(letter + " could not be converted to a Face Value");
	} else {
	    return faceValue;
	}
    }
}
