/*****************************************************************************
* File:      Grain.java                                                      *
* Author:    Sean Ashton * Student ID: 14866636                              *
* Unit:      COMP1001 Object Oriented Program Design                         *
* Purpose:   Grain object                                                    *
* Reference: None.                                                           *
* Comments:  This comment block is the maximum width of 78 characters.       *
* Requires:  Food, Calendar, SUtil                                           *
* Created:   19/05/2018 * Last Modified: 28/05/2018                          *
*****************************************************************************/
import java.util.*;
public class Grain extends Food
{

//PUBLIC CLASS CONSTANTS:
   public static final double VOLUMEMIN = 0.2;
   public static final double VOLUMEMAX = 5.0;
   public static final double SPACEMULT = 1.0;
   //Volume multiplier for IFood implementation

//CLASS FIELDS:
   String type;
   double volume;
   Calendar bestBefore;

//CONSTRUCTORS:
   /**************************************************************************
   * DEFAULT: //This should never be used
   * IMPORT: none
   * EXPORT: Object Reference
   * ASSERTION: ... is a valid default state
   **************************************************************************/
   public Grain ()
   {
      super();
      type = "";
      volume = 0.2; //Set minimum size for default
      bestBefore = Calendar.getInstance();
   }

   /**************************************************************************
   * ALTERNATE:
   * IMPORT: inName (String), inTemp (real), inPack (String), inType (String),
   *          inVolume (real), inBestBefore (Calendar)
   * EXPORT: Object Reference
   * ASSERTION: Creates object if import are valid, otherwise FAILS
   **************************************************************************/
   public Grain ( String inName, double inTemp, String inPack,
                  String inType, double inVolume, Calendar inBestBefore )
   {
      super(inName,inTemp,inPack);
      //Validation and copying done by set methods - avoid duplicate code
      setType(inType);
      setBestBefore(inBestBefore);
      setVolume(inVolume);
   }

   /**************************************************************************
   * CSVSTRING:
   * IMPORT: tokens (ARRAY OF String)
   * EXPORT: Object Reference
   * ASSERTION: Creates object from CSV string
   **************************************************************************/
   public Grain ( String[] tokens )
   {
      super(tokens[1],Double.parseDouble(tokens[4]),tokens[6]);
      setType(tokens[2]);
      setVolume(Double.parseDouble(tokens[3]));
      setBestBefore(SUtil.stringToCal(tokens[5]));
   }

   /**************************************************************************
   * COPY:
   * IMPORT: toCopy (Grain)
   * EXPORT: Object Reference
   * ASSERTION: Constructs an object with identical state to the import
   **************************************************************************/
   public Grain ( Grain toCopy )
   {
      super(toCopy);
      //get methods always return copies of Objects
      type = toCopy.getType();
      bestBefore = toCopy.getBestBefore();
      volume = toCopy.getVolume();
   }

//ACCESSORS:
   //Purpose: return a copy of class field type
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getType
   * IMPORT: none
   * EXPORT: COPY OF type
   **************************************************************************/
   public String getType ()
   {
      return new String(type);
   }

   //Purpose: return a copy of class field bestBefore
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

   //Purpose: return class field volume
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getVolume
   * IMPORT: none
   * EXPORT: volume
   **************************************************************************/
   public double getVolume ()
   {
      return volume;
   }

   //Purpose: Check equality between Food items
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
      if (inFood instanceof Grain)
      {
         Grain inGrain = (Grain)inFood;
         if (super.equals(inGrain))
         {
            if (type.equals(inGrain.getType()))
            {
               //Compare real with tolerance
               if (SUtil.realEquals(volume,inGrain.getVolume(),0.01))
               {
                  if (bestBefore.equals(inGrain.getBestBefore()))
                  {
                     bool = true;
                  }
               }
            }
         }
      }
      return bool;
   }

   //Purpose: return a descriptive string of Grain state
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE toString
   * IMPORT: none
   * EXPORT: string (String)
   * ASSERTION: EXPORTs a descriptive String of object state
   **************************************************************************/
   public String toString ()
   {
      String string = super.toString() + ". Type: " + type + ". Volume: " +
               volume + ". Best Before: " + SUtil.calToString(bestBefore);
      return string;
   }

   //Purpose: return a copy of THIS Grain
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE clone
   * IMPORT: none
   * EXPORT: grain (Grain)
   * ASSERTION: EXPORTs a new Grain object with identical state to THIS
   **************************************************************************/
   @Override
   public Grain clone ()
   {
      return new Grain(this);
   }

   //Purpose: Generate a string to store
   //Date Modified: 23/05/2018
   /**************************************************************************
   * SUB MODULE getCSV
   * IMPORT: none
   * EXPORT: csv (String)
   * ASSERTION: EXPORTS a string for storage in a csv file
   **************************************************************************/
   @Override
   public String getCSV ()
   {
      String csv = "Grain, " + getName() + ", " + type + ", " + volume + 
                    ", " + getTemperature() + ", " +
                     SUtil.calToString(bestBefore) + ", " + getPackaging();
      return csv;
   }

   //Interface Methods
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
         bool = false;
      }
      else
      {
         bool = true;
      }
      return bool;
   }

   /**************************************************************************
   * SUB MODULE calcSpace
   * IMPORT: food (Food)
   * EXPORT: litres (integer)
   * ASSERTION: litres will be volume * 1.0 rounded up
   **************************************************************************/
   @Override
   public int calcSpace ( Food food )
   {
      int litres = (int)(Math.ceil(volume * SPACEMULT));
      return litres;
   }

//MUTATORS:
   //Purpose: Set class field type with sanitation
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE setType
   * IMPORT: inType (String)
   * EXPORT: none
   * ASSERTION: Sets type to inType if inType is a valid input
   **************************************************************************/
   public void setType ( String inType )
   {
      if (inType != null)
      {
         //Remove problematic characters from String
         type = SUtil.csvSanitise(inType);
      }
      else
      {
         throw new IllegalArgumentException("Type must have data");
      }
   }

   //Purpose: Set volume if input is within range
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE setVolume
   * IMPORT: inVolume (real)
   * EXPORT: none
   * ASSERTION: Sets volume to inVolume if in is a valid input
   **************************************************************************/
   public void setVolume ( double inVolume )
   {
      if (VOLUMEMIN <= inVolume && inVolume <= VOLUMEMAX)
      {
         volume = inVolume;
      }
      else
      {
         throw new IllegalArgumentException("Volume out of range");
      }
   }

   //Purpose: Set Best Before if input is in the future or now
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
         //Best before must be later than today (otherwise why store it?)
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
         throw new NullPointerException("Best before must contain data");
      }
   }

//DOING METHODS:

//PRIVATE METHODS:
}
