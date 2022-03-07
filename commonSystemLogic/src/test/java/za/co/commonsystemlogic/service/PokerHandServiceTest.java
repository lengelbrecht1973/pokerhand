/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package za.co.commonsystemlogic.service;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.co.commonsystemlogic.constants.Face;
import za.co.commonsystemlogic.constants.Suit;
import za.co.commonsystemlogic.entity.Score;

/**
 *
 * @author lengelbrecht
 */
public class PokerHandServiceTest {
    
    public PokerHandServiceTest() {
    }
    
    /**
     * Test of shuffleDeck method, of class PokerHandService.
     */
    @Test
    public void testShuffleDeck() {
	
	PokerHandService instance = new PokerHandService();
	
	String[] result = instance.shuffleDeck();
	
	if(result.length != 52){
	    fail("There are too many cards in your deck");
	}
	
	String stringToTestOrder = "";
	List<String> deckAsList = Arrays.asList(result);
	for (String cardToCheck : deckAsList){
	    
	    //Check faces
	    stringToTestOrder = stringToTestOrder + cardToCheck.charAt(0);
	    
	    if(!stringToTestOrder.matches("[AKQJT98765432]+" )){
		fail("Invalid characters in deck.");
	    }
	    
	    //Check Suits
	    stringToTestOrder = "";
	    stringToTestOrder = stringToTestOrder + cardToCheck.charAt(1);
	    
	    if(!stringToTestOrder.matches("[HSDC]+" )){
		fail("Invalid characters in deck.");
	    }
	    stringToTestOrder = "";
	}
	
	if(stringToTestOrder.startsWith("AKQJT98765432")){
	    
	    fail("Your cards are not ordered.");
	}
	    
	
    }
    
 

    /**
     * Test of analyzeHand method, of class PokerHandService.
     */
    @Test
    public void testAnalyzeHand() {
	
	String[] hand = {"2H", "2C", "2D", "AC", "4C"};
	PokerHandService instance = new PokerHandService();
	Score result = instance.analyzeHand(hand);
		
	//This can be extedned later if time available to test for all the other score names,
	//for now just test thre--of-a-kind
	if(!result.getName().equals(("three-of-a-kind"))){
	    fail("Expected three-of-a-kind, got " + result.getName());
	}
    
	
    }
    
    //Test not more than five cards in hand during scoring
    @Test
    public void testAnalyzeHandMoreThanFiveCards() {
	
	String[] hand = {"2H", "2C", "2D", "AC", "4C", "5C"};
	PokerHandService instance = new PokerHandService();
	try{
	    Score result = instance.analyzeHand(hand);
	}
	catch(Exception e){
	    String expectedMessage = "wrong number of cards";
	    String actualMessage = e.getMessage();

	assertTrue(actualMessage.contains(expectedMessage));
	}
	
    
	
    }

    /**
     * Test of getFaceValue method, of class PokerHandService.
     */
    @Test
    public void testGetFaceValue() {
	
	String faceString = "A";
	PokerHandService instance = new PokerHandService();
	
	Face result = instance.getFaceValue(faceString);
	assertEquals("ACE", result.name());
	
	
    }

    /**
     * Test of getSuitValue method, of class PokerHandService.
     */
    @Test
    public void testGetSuitValue() {
	
	String suitString = "H";
	PokerHandService instance = new PokerHandService();

	Suit result = instance.getSuitValue(suitString);
	
	assertEquals("HEART", result.name());
	
	
    }

   
}
