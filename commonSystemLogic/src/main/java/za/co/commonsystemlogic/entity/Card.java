/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.commonsystemlogic.entity;

import lombok.Data;
import za.co.commonsystemlogic.constants.Face;
import za.co.commonsystemlogic.constants.Suit;

/**
 *
 * @author lengelbrecht
 */
@Data
public class Card {
   private Face face;
   private Suit suit;
}
