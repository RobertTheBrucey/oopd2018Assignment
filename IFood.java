/*****************************************************************************
* File:      IFood.java                                                      *
* Author:    Sean Ashton * Student ID: 14866636                              *
* Unit:      COMP1001 Object Oriented Program Design                         *
* Purpose:   Interface to be implemented per design spec                     *
* Reference: None.                                                           *
* Comments:  This comment block is the maximum width of 78 characters.       *
* Requires:  Nothing. List dependencies here                                 *
* Created:   19/05/2018 * Last Modified: 19/05/2018                          *
*****************************************************************************/
import java.util.*;
public interface IFood
{
   public boolean calcExpiry( Calendar today );
   // imports today's date and export true if this food item
   // has reached it's expiry date, false otherwise

   public int calcSpace( Food food );
   // imports a Food class object, and export an integer
   // specifying the volume in litres of storage required
}

/* Addtional information:
 * Each subclass of Food has a different calculation for space:
 * Meat - weight * 0.86 roudned up
 * Grain - volume * 1.0 rounded up
 * Fruit - number of pieces * 1.95 rounded ip
 * Vegetables - weight * 1.025 rounded up
 */

