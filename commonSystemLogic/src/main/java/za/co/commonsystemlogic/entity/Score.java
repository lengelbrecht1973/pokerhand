/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.commonsystemlogic.entity;

import java.util.Arrays;
import lombok.Data;

/**
 *
 * @author lengelbrecht
 * Borrowed with the algorithm from https://rosettacode.org/wiki/Poker_hand_analyser#Java
 */
@Data
public class Score {

    private int weight;
    private String name;
    private String[] hand;

    public Score(String n, int w, String[] h) {
	weight = w;
	name = n;
	hand = h != null ? h.clone() : h;
    }

    @Override
    public String toString() {
	return Arrays.toString(hand) + " " + name;
    }
}
