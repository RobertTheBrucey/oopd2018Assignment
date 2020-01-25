/*****************************************************************************
* File:      Food.java                                                       *
* Author:    Sean Ashton * Student ID: 14866636                              *
* Unit:      COMP1001 Object Oriented Program Design                         *
* Purpose:   Abstract class for all Foods                                    *
* Reference: None.                                                           *
* Comments:  This comment block is the maximum width of 78 characters.       *
* Requires:  SUtil                                                           *
* Created:   28/05/2018 * Last Modified: 28/05/2018                          *
*****************************************************************************/
import java.util.*;
public abstract class Food implements IFood
{

//PUBLIC CLASS CONSTANTS:
   public static final double MINTEMPA = -27.0; //Freezer lower limit
   public static final double MAXTEMPA = -5.0; //Freezer higher limit
   public static final double MINTEMPB = -2.0; //Fridge lower limit
   public static final double MAXTEMPB = 6.0; //Fridge higher limit
   public static final double MINTEMPC = 8.0; //Pantry lower limit
   public static final double MAXTEMPC = 25.0; //Pantry upper limit

//CLASS FIELDS:
   private String name;
   private double temperature;
   private String packaging;

//CONSTRUCTORS:
   /**************************************************************************
   * DEFAULT: //This constructor shouldn't ever be used
   * IMPORT: none
   * EXPORT: Object Reference
   * ASSERTION: ... is a valid default state
   **************************************************************************/
   public Food ()
   {
      name = "";
      temperature = 24.0; //Assume room temperature
      packaging = "";
   }

   /**************************************************************************
   * ALTERNATE: //set methods will make copies of Object where necessary
   * IMPORT: inName (String), inTemp (real), inPack (String)
   * EXPORT: Object Reference
   * ASSERTION: Creates object if import are valid, otherwise FAILS
   **************************************************************************/
   public Food ( String inName, double inTemp, String inPack )
   {
      setName(inName);
      setPackaging(inPack);
      setTemperature(inTemp);
   }

   /**************************************************************************
   * COPY: //get methods always return a copy of Objects
   * IMPORT: toCopy (Food)
   * EXPORT: Object Reference
   * ASSERTION: Constructs an object with identical state to the import
   **************************************************************************/
   public Food ( Food toCopy )
   {
      //All get methods return copies of the requested data
      name = toCopy.getName();
      packaging = toCopy.getPackaging();
      temperature = toCopy.getTemperature();
   }

//ACCESSORS:
   //Purpose: Return copy of name
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getName
   * IMPORT: none
   * EXPORT: COPY OF name
   **************************************************************************/
   public String getName ()
   {
      return new String(name);
   }

   //Purpose: Return copy of packaging
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getPackaging
   * IMPORT: none
   * EXPORT: COPY OF packaging
   **************************************************************************/
   public String getPackaging ()
   {
      return new String (packaging);
   }

   //Purpose: Return temperature
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getTemperature
   * IMPORT: none
   * EXPORT: temperature
   **************************************************************************/
   public double getTemperature ()
   {
      return temperature;
   }

   //Purpose: Check equality between Food Objects
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE equals
   * IMPORT: inFood (Food)
   * EXPORT: bool (boolean)
   * ASSERTION: Checks for if inName has identical state to THIS
   **************************************************************************/
   public boolean equals ( Food inFood )
   {
      boolean bool = false;
      if (name.equals(inFood.getName()))
      {
         if (packaging.equals(inFood.getPackaging()))
         {
            //real numbers are checked for equality with a tolerance
            if (SUtil.realEquals(temperature,
                                    inFood.getTemperature(),0.01))
            {
               bool = true;
            }
         }
      }
      return bool;
   }

   //Purpose: Return a descriptive string of THIS state
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE toString
   * IMPORT: none
   * EXPORT: string (String)
   * ASSERTION: EXPORTs a descriptive String of object state
   **************************************************************************/
   public String toString ()
   {
      String string = "Name: " + name + ". Temperature: " + temperature + 
      ". Packaging: " + packaging;
      return string;
   }

   //Purpose: Return copy of THIS Food Object
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE clone
   * IMPORT: none
   * EXPORT: food (Food)
   * ASSERTION: EXPORTs a new Name object with identical state to THIS
   * ALGORITHM: ABSTRACT
   **************************************************************************/
   public abstract Food clone ();

   //Purpose: Export a string to store in a csv file
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getCSV
   * IMPORT: none
   * EXPORT: csv (String)
   * ALGORITHM: ABSTRACT
   **************************************************************************/
   public abstract String getCSV ();

   //Interface methods
   /**************************************************************************
   * SUB MODULE calcExpiry
   * IMPORT: today (Calendar)
   * EXPORT: bool (Boolean)
   * ALGORITHM: ABSTRACT
   **************************************************************************/
   public abstract boolean calcExpiry ( Calendar today );

   /**************************************************************************
   * SUB MODULE calcSpace
   * IMPORT: food (Food)
   * EXPORT: litres (integer)
   * ALGORITHM: ABSTRACT
   **************************************************************************/
   public abstract int calcSpace ( Food food );

//MUTATORS:
   //Purpose: set class field name if not NULL, in a sanitised format
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE setName
   * IMPORT: inName (String)
   * EXPORT: none
   * ASSERTION: Sets name to inName if inName is a valid input
   **************************************************************************/
   public void setName ( String inName )
   {
      if (inName != null)
      {
         //Remove commas(in particular) from name
         name = SUtil.csvSanitise(new String(inName));
      }
      else
      {
         throw new IllegalArgumentException("Name must contain data");
      }
   }

   //Purpose: set class field packaging if not NULL, in a sanitised format
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE setPackaging
   * IMPORT: inPack (String)
   * EXPORT: none
   * ASSERTION: Sets packaging to inPack if inPack is a valid input
   **************************************************************************/
   public void setPackaging ( String inPack )
   {
      if (inPack != null)
      {
         packaging = SUtil.csvSanitise(new String(inPack));
      }
      else
      {
         throw new IllegalArgumentException("Packaging must contain data");
      }
   }

   //Purpose: set class field temperature if in valid range per spec
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE setTemperature
   * IMPORT: inTemp (real)
   * EXPORT: none
   * ASSERTION: Sets temperature to inTemp if inTemp is a valid input
   **************************************************************************/
   public void setTemperature ( double inTemp )
   {
      if ((MINTEMPA <= inTemp && inTemp <= MAXTEMPA) ||
            (MINTEMPB <= inTemp && inTemp <= MAXTEMPB) ||
               (MINTEMPC <= inTemp && inTemp <= MAXTEMPC))
      {
         temperature = inTemp;
      }
      else
      {
         throw new IllegalArgumentException("Food Temperature out of range");
      }
   }

//DOING METHODS:

//PRIVATE METHODS:
}
