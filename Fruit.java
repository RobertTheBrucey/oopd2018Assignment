/*****************************************************************************
* File:      Fruit.java                                                      *
* Author:    Sean Ashton * Student ID: 14866636                              *
* Unit:      COMP1001 Object Oriented Program Design                         *
* Purpose:   Fruit object                                                    *
* Reference: None.                                                           *
* Comments:  This comment block is the maximum width of 78 characters.       *
* Requires:  Food, SUtil                                                     *
* Created:   20/05/2018 * Last Modified: 28/05/2018                          *
*****************************************************************************/
import java.util.*;
public class Fruit extends Food
{

//PUBLIC CLASS CONSTANTS:
   public static final int MINPIECES = 1;
   public static final int MAXPIECES = 20;
   public static final double SPACEMULT = 1.95; //Piece to volume multiplier

//CLASS FIELDS:
   private String type;
   private int numPieces;
   private Calendar useBy;

//CONSTRUCTORS:
   /**************************************************************************
   * DEFAULT: //This should never be used
   * IMPORT: none
   * EXPORT: Object Reference
   * ASSERTION: ... is a valid default state
   **************************************************************************/
   public Fruit ()
   {
      super();
      type = "";
      numPieces = 1;
      useBy = Calendar.getInstance();
   }

   /**************************************************************************
   * ALTERNATE:
   * IMPORT: inName (String), inTemp (real), inPack (String), inType (String),
   *          inNumPieces (integer), inUseBy (Calendar)
   * EXPORT: Object Reference
   * ASSERTION: Creates object if import are valid, otherwise FAILS
   **************************************************************************/
   public Fruit ( String inName, double inTemp, String inPack, String inType,
                  int inNumPieces, Calendar inUseBy )
   {
      super(inName,inTemp,inPack);
      //Set methods copy Objects where necessary
      setType(inType);
      setUseBy(inUseBy);
      setNumPieces(inNumPieces);
   }

   /**************************************************************************
   * CSVSTRING:
   * IMPORT: tokens (ARRAY OF String)
   * EXPORT: Object Reference
   * ASSERTION: Creates object from CSV string
   **************************************************************************/
   public Fruit ( String[] tokens )
   {
      super(tokens[1],Double.parseDouble(tokens[4]),tokens[6]);
      setType(tokens[2]);
      setNumPieces(Integer.parseInt(tokens[3]));
      setUseBy(SUtil.stringToCal(tokens[5]));
   }

   /**************************************************************************
   * COPY:
   * IMPORT: toCopy (Fruit)
   * EXPORT: Object Reference
   * ASSERTION: Constructs an object with identical state to the import
   **************************************************************************/
   public Fruit ( Fruit toCopy )
   {
      super(toCopy);
      //get methods always return copies of Objects
      type = toCopy.getType();
      useBy = toCopy.getUseBy();
      numPieces = toCopy.getNumPieces();
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

   //Purpose: return a copy of class field useBy
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getUseBy
   * IMPORT: none
   * EXPORT: COPY OF useBy
   **************************************************************************/
   public Calendar getUseBy ()
   {
      return (Calendar)useBy.clone();
   }

   //Purpose: return class field numPieces
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getNumPieces
   * IMPORT: none
   * EXPORT: numPieces
   **************************************************************************/
   public int getNumPieces ()
   {
      return numPieces;
   }

   //Purpose: Compare equality between Food Objects
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
      if (inFood instanceof Fruit)
      {
         Fruit inFruit = (Fruit)inFood;
         if (super.equals(inFruit))
         {
            if (type.equals(inFruit.getType()))
            {
               if (numPieces == inFruit.getNumPieces())
               {
                  if (useBy.equals(inFruit.getUseBy()))
                  {
                     bool = true;
                  }
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
      String string = super.toString() + ". Type: " + type + 
               ". Number of Pieces: " + numPieces + ". Use By: " + 
                                    SUtil.calToString(useBy);
      return string;
   }

   //Purpose: return a copy of THIS Fruit
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE clone
   * IMPORT: none
   * EXPORT: fruit (Fruit)
   * ASSERTION: EXPORTs a new Fruit object with identical state to THIS
   **************************************************************************/
   @Override
   public Fruit clone ()
   {
      return new Fruit(this);
   }

   //Purpose: Generate a string to store
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
      String csv = "Fruit, " + getName() + ", " + type + ", " + numPieces + 
                     ", " + getTemperature() + ", " +
                      SUtil.calToString(useBy) + ", " + getPackaging();
      return csv;
   }
                                                         
   //Interface Methods
   /**************************************************************************
   * SUB MODULE calcExpiry
   * IMPORT: today (Calendar)
   * EXPORT: bool (boolean)
   * ASSERTION: bool will be true only if useBy is after today
   **************************************************************************/
   @Override
   public boolean calcExpiry ( Calendar today )
   {
      boolean bool;
      if (useBy.before(today))
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
   * ASSERTION: litres will be numPieces * 1.0 rounded up
   **************************************************************************/
   @Override
   public int calcSpace ( Food food )
   {
      int litres = (int)(Math.ceil((double)numPieces * SPACEMULT));
      return litres;
   }

//MUTATORS:
   //Purpose: set class field type with input sanitisation
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
         type = SUtil.csvSanitise(inType);
      }
      else
      {
         throw new IllegalArgumentException("Type must have data");
      }
   }

   //Purpose: set class field numPieces if input is within range
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE setNumPieces
   * IMPORT: inNumPieces (integer)
   * EXPORT: none
   * ASSERTION: Sets numPieces to inNumPieces if inNumPieces is a valid input
   **************************************************************************/
   public void setNumPieces ( int inNumPieces )
   {
      if (MINPIECES <= inNumPieces && inNumPieces <= MAXPIECES)
      {
         numPieces = inNumPieces;
      }
      else
      {
         throw new IllegalArgumentException("Number of pieces out of range");
      }
   }

   //Purpose: set useBy if input Date is now or in the future
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE setUseBy
   * IMPORT: inUseBy (Calendar)
   * EXPORT: none
   * ASSERTION: Sets useBy to inUseBy if inUseBy is a valid input
   **************************************************************************/
   public void setUseBy ( Calendar inUseBy )
   {
      Calendar today = Calendar.getInstance();
      if (inUseBy != null)
      {
         if (!inUseBy.before(today))
         {
            useBy = (Calendar)inUseBy.clone();
         }
         else
         {
            throw new IllegalArgumentException(
                           "Use by date must not be in the past");
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
