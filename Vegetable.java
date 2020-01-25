/*****************************************************************************
* File:      Vegetable.java                                                  *
* Author:    Sean Ashton * Student ID: 14866636                              *
* Unit:      COMP1001 Object Oriented Program Design                         *
* Purpose:   Vegetable object                                                *
* Reference: None.                                                           *
* Comments:  This comment block is the maximum width of 78 characters.       *
* Requires:  Food, Calendar, SUtil                                           *
* Created:   19/05/2018 * Last Modified: 28/05/2018                          *
*****************************************************************************/
import java.util.*;
public class Vegetable extends Food
{

//PUBLIC CLASS CONSTANTS:
   public static final double WEIGHTMIN = 0.2;
   public static final double WEIGHTMAX = 5.0;
   public static final double SPACEMULT = 1.025;
    // weight to volume multiplier

//CLASS FIELDS:
   private double weight;
   private Calendar bestBefore;

//CONSTRUCTORS:
   /**************************************************************************
   * DEFAULT: // This should never be used
   * IMPORT: none
   * EXPORT: Object Reference
   * ASSERTION: ... is a valid default state
   **************************************************************************/
   public Vegetable ()
   {
      super();
      weight = 0.2; //Smallest weight
      bestBefore = Calendar.getInstance();
   }

   /**************************************************************************
   * ALTERNATE:
   * IMPORT: inName (String), inTemp (real), inPack (String),
   *          inWeight (real), inBestBefore (Calendar)
   * EXPORT: Object Reference
   * ASSERTION: Creates object if import are valid, otherwise FAILS
   **************************************************************************/
   public Vegetable ( String inName, double inTemp, String inPack,
                     double inWeight, Calendar inBestBefore )
   {
      super(inName,inTemp,inPack);
      //Set methods will make copies of Objects passed to them
      setBestBefore(inBestBefore);
      setWeight(inWeight);
   }

   /**************************************************************************
   * CSVSTRING:
   * IMPORT: tokens (ARRAY OF String)
   * EXPORT: Object Reference
   * ASSERTION: Creates object from CSV string
   **************************************************************************/
   public Vegetable ( String[] tokens )
   {
      super(tokens[1],Double.parseDouble(tokens[3]),tokens[5]);
      setWeight(Double.parseDouble(tokens[2]));
      setBestBefore(SUtil.stringToCal(tokens[4]));
   }

   /**************************************************************************
   * COPY:
   * IMPORT: toCopy (Vegetable)
   * EXPORT: Object Reference
   * ASSERTION: Constructs an object with identical state to the import
   **************************************************************************/
   public Vegetable ( Vegetable toCopy )
   {
      super(toCopy);
      bestBefore = toCopy.getBestBefore();
      weight = toCopy.getWeight();
   }

//ACCESSORS:
   //Purpose: return copy of class field bestBefore
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getBestBefore
   * IMPORT: none
   * EXPORT: COPY OF bestBefore
   **************************************************************************/
   public Calendar getBestBefore ()
   {
      return (Calendar)bestBefore.clone();
   }

   //Purpose: return class field weight
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getWeight
   * IMPORT: none
   * EXPORT: weight
   **************************************************************************/
   public double getWeight ()
   {
      return weight;
   }

   //Purpose: checks equality between Food Objects
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE equals
   * IMPORT: inFood (Food)
   * EXPORT: bool (boolean)
   * ASSERTION: Checks for if inFood has identical state to THIS
   **************************************************************************/
   public boolean equals ( Food inFood )
   {
      boolean bool = false;
      if (inFood instanceof Vegetable)
      {
         Vegetable inVeg = (Vegetable)inFood;
         if (super.equals(inFood))
         {
            if (SUtil.realEquals(weight,inVeg.getWeight(),0.01))
            {
               if (bestBefore.equals(inVeg.getBestBefore()))
               {
                  bool = true;
               }
            }
         }
      }
      return bool;
   }

   //Purpose: Return a descriptive string of object state
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE toString
   * IMPORT: none
   * EXPORT: string (String)
   * ASSERTION: EXPORTs a descriptive String of object state
   **************************************************************************/
   public String toString ()
   {
      String string = super.toString() + ". Weight: " + weight + 
               ". Best Before: " + SUtil.calToString(bestBefore);
      return string;
   }

   //Purpose: return a copy of THIS Vegetable
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE clone
   * IMPORT: none
   * EXPORT: vegetable (Vegetable)
   * ASSERTION: EXPORTs a new Vegetable object with identical state to THIS
   **************************************************************************/
   @Override
   public Vegetable clone ()
   {
      return new Vegetable(this);
   }

   //Purpose: generate a string to store
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getCSV
   * IMPORT: none
   * EXPORT: csv (String)
   * ASSERTION: EXPORTS a string for storage in a csv file
   **************************************************************************/
   @Override
   public String getCSV ()
   {
      String csv = "Vegetable, " + getName() +", " + weight + ", " +
              getTemperature() + ", " + SUtil.calToString(bestBefore) +
               ", " + getPackaging();
      return csv;
   }

//Interface methods
   /**************************************************************************
   * SUB MODULE calcExpiry
   * IMPORT: today (Calendar)
   * EXPORT: bool (boolean)
   * ASSERTION: bool will be true only if bestBefore is after today
   **************************************************************************/
   @Override
   public boolean calcExpiry ( Calendar today )
   {
      boolean bool;
      if (bestBefore.before(today))
      {
         bool = true;
      }
      else
      {
         bool = false;
      }
      return bool;
   }

   /**************************************************************************
   * SUB MODULE calcSpace
   * IMPORT: food (Food)
   * EXPORT: litres (integer)
   * ASSERTION: litres will be weight * 1.0 rounded up
   **************************************************************************/
   @Override
   public int calcSpace ( Food food )
   {
      int litres = (int)(Math.ceil(weight * SPACEMULT));
      return litres;
   }

//MUTATORS:
   //Purpose: set class field weight if input is within range
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE setWeight
   * IMPORT: inWeight (real)
   * EXPORT: none
   * ASSERTION: Sets weight to inWeight if inWeight is a valid input
   **************************************************************************/
   public void setWeight ( double inWeight )
   {
      if (WEIGHTMIN <= inWeight && inWeight <= WEIGHTMAX)
      {
         weight = inWeight;
      }
      else
      {
         throw new IllegalArgumentException("Weight out of range");
      }
   }

   //Purpose: set class field bestBefore if input date is now or future
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE setBestBefore
   * IMPORT: inBestBefore (Calendar)
   * EXPORT: none
* ASSERTION: Sets bestBefore to inBestBefore if inBestBefore is a valid input
   **************************************************************************/
   public void setBestBefore ( Calendar inBestBefore )
   {
      Calendar today = Calendar.getInstance();
      if (inBestBefore != null)
      {
         if (!inBestBefore.before(today))
         {
            bestBefore = (Calendar)inBestBefore.clone();
         }
         else
         {
            throw new IllegalArgumentException(
                        "Best before must not be in the past");
         }
      }
      else
      {
         throw new NullPointerException();
      }
   }

//DOING METHODS:

//PRIVATE METHODS:
}
