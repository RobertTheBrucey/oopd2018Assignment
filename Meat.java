/*****************************************************************************
* File:      Meat.java                                                       *
* Author:    Sean Ashton * Student ID: 14866636                              *
* Unit:      COMP1001 Object Oriented Program Design                         *
* Purpose:   Meat object                                                     *
* Reference: None.                                                           *
* Comments:  This comment block is the maximum width of 78 characters.       *
* Requires:  Food, SUtil                                                     *
* Created:   28/05/2018 * Last Modified: 28/05/2018                          *
*****************************************************************************/
import java.util.*;
public class Meat extends Food
{

//PUBLIC CLASS CONSTANTS:
   public static final double WEIGHTMIN = 0.2;
   public static final double WEIGHTMAX = 5.0;
   public static final double SPACEMULT = 0.86;
   //Weight to volume multiplier for IFood implementation

//CLASS FIELDS:
   private String cut;
   private double weight;
   private Calendar useBy;

//CONSTRUCTORS:
   /**************************************************************************
   * DEFAULT: //This should never be called
   * IMPORT: none
   * EXPORT: Object Reference
   * ASSERTION: ... is a valid default state
   **************************************************************************/
   public Meat ()
   {
      super();
      cut = "";
      weight = 0.2; //Smallest meat cut
      useBy = Calendar.getInstance(); //Set expiry to today
   }

   /**************************************************************************
   * ALTERNATE:
   * IMPORT: inName (String), inTemp (real), inPack (String), inCut (String),
   *          inWeight (real), inUseBy (Calendar)
   * EXPORT: Object Reference
   * ASSERTION: Creates object if import are valid, otherwise FAILS\
   **************************************************************************/
   public Meat ( String inName, double inTemp, String inPack, String inCut,
                  double inWeight, Calendar inUseBy )
   {
      super(inName,inTemp,inPack);
      //set methods all copy Objects where appropriate
      setCut(inCut);
      setUseBy(inUseBy);
      setWeight(inWeight);
   }

   /**************************************************************************
   * CSVSTRING:
   * IMPORT: tokense (ARRAY OF String)
   * EXPORT: Object Reference
   * ASSERTION: Creates object from CSV string
   **************************************************************************/
   public Meat ( String[] tokens )
   {
      super(tokens[1],Double.parseDouble(tokens[4]),tokens[6]);
      setCut(tokens[2]);
      setWeight(Double.parseDouble(tokens[3]));
      setUseBy(SUtil.stringToCal(tokens[5]));
   }

   /**************************************************************************
   * COPY:
   * IMPORT: toCopy (Meat)
   * EXPORT: Object Reference
   * ASSERTION: Constructs an object with identical state to the import
   **************************************************************************/
   public Meat ( Meat toCopy )
   {
      super(toCopy);
      //get methods always return copies of Objects
      cut = toCopy.getCut();
      useBy = toCopy.getUseBy();
      weight = toCopy.getWeight();
   }

//ACCESSORS:
   //Purpose: Return copy of cut
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getCut
   * IMPORT: none
   * EXPORT: COPY OF cut
   **************************************************************************/
   public String getCut ()
   {
      return new String(cut);
   }

   //Purpose: Return copy of useBy
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

   //Purpose: Return weight
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

   //Purpose: Check equality between Food Objects
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
      if (inFood instanceof Meat)
      {
         Meat inMeat = (Meat)inFood;
         if (super.equals(inMeat))
         {
            if (cut.equals(inMeat.getCut()))
            {
               //Check for real equality with tolerance
               if (SUtil.realEquals(weight,inMeat.getWeight(),0.01))
               {
                  if (useBy.equals(inMeat.getUseBy()))
                  {
                     bool = true;
                  }
               }
            }
         }
      }
      return bool;
   }

   //Purpose: Return a desciptive string of THIS Object's state
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE toString
   * IMPORT: none
   * EXPORT: string (String)
   * ASSERTION: EXPORTs a descriptive String of object state
   **************************************************************************/
   public String toString ()
   {
      String string = super.toString() + ". Cut: " + cut + ". Weight: " +
               weight + ". Use By: " + SUtil.calToString(useBy);
      return string;
   }

   //Purpose: Return copy of THIS Meat
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE clone
   * IMPORT: none
   * EXPORT: meat (Meat)
   * ASSERTION: EXPORTs a new Meat object with identical state to THIS
   **************************************************************************/
   @Override
   public Food clone ()
   {
      Food food = new Meat(this);
      return food;
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
      String csv = "Meat, " + getName() + ", " + cut + ", " + weight + ", " +
               getTemperature() + ", " + SUtil.calToString(useBy) + ", " +
               getPackaging();
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
      boolean bool = false;
      //Check if the useBy date is before today
      if (useBy.before(today))
      {
         bool = true;
      }
      return bool;
   }

   /**************************************************************************
   * SUB MODULE calcSpace
   * IMPORT: food (Food)
   * EXPORT: litres (integer)
   * ASSERTION: litres will be weight * 0.86 rounded up
   **************************************************************************/
   @Override
   public int calcSpace ( Food food )
   {
      int litres = (int)(Math.ceil(weight * SPACEMULT));
      return litres;
   }

//MUTATORS:
   //Purpose: set class field cut with sanitation
   //Date Modified: 20/05/2018
   /**************************************************************************
   * SUB MODULE setCut
   * IMPORT: inCut (String)
   * EXPORT: none
   * ASSERTION: Sets cut to inCut if inCut is a valid input
   **************************************************************************/
   public void setCut ( String inCut )
   {
      if (inCut != null)
      {
         //Remove commas etc from input
         cut = SUtil.csvSanitise(inCut);
      }
      else
      {
         throw new IllegalArgumentException("Cut must have data");
      }
   }

   //Purpose: set class field weight if in range
   //Date Modified: 20/05/2018
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

   //Purpose: set class field useBy if now or in the future
   //Date Modified: 20/05/2018
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
