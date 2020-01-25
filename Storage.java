/*****************************************************************************
* File:      Storage.java                                                    *
* Author:    Sean Ashton * Student ID: 14866636                              *
* Unit:      COMP1001 Object Oriented Program Design                         *
* Purpose:   Store Food within a temperature range                           *
* Reference: None.                                                           *
* Comments:  This comment block is the maximum width of 78 characters.       *
* Requires:  SUtil                                                           *
* Created:   20/05/2018 * Last Modified: 28/05/2018                          *
*****************************************************************************/
import java.util.*;
public class Storage
{

//PUBLIC CLASS CONSTANTS:
   public static final double MINTEMP = -273.15;
    //Temperature must be above absolute 0
   public static final double MAXTEMP = 5660.0;
    //Temperature must be below the boiling point of Tungsten

//CLASS FIELDS:
   private String name;
   private Food[] food;
   private double minTemp;
   private double maxTemp;
   private int foodCount;

//CONSTRUCTORS:
   /**************************************************************************
   * DEFAULT: // This should generally not be used
   * IMPORT: none
   * EXPORT: Object Reference
   * ASSERTION: ... is a valid default state
   **************************************************************************/
   public Storage ()
   {
      name = "Storage";
      food = new Food[100];
      minTemp = 8.0;
      maxTemp = 25.0;
      foodCount = 0;
   }

   /**************************************************************************
   * ALTERNATE:
   * IMPORT: inName (String), inCapacity (integer), inMinTemp (real),
   *          inMaxTemp (real)
   * EXPORT: Object Reference
   * ASSERTION: Creates object if import are valid, otherwise FAILS
   **************************************************************************/
   public Storage ( String inName, int inCapacity, double inMinTemp,
                     double inMaxTemp )
   {
      //set methods copy Objects passed to them
      //set methods also validate input
      setName(inName);

      if (inCapacity > 0)
      {
         food = new Food[inCapacity];
         foodCount = 0;
      }
      else
      {
         throw new IllegalArgumentException("Capacity must greater than 0");
      }

      setMinTemp(inMinTemp);

      if (inMaxTemp < inMinTemp)
      {
         throw new IllegalArgumentException(
               "Max Temperature must be less than min temperature");
      }
      else
      {
         setMaxTemp(inMaxTemp);
      }
   }

   /**************************************************************************
   * COPY:
   * IMPORT: toCopy (Storage)
   * EXPORT: Object Reference
   * ASSERTION: Constructs an object with identical state to the import
   **************************************************************************/
   public Storage ( Storage toCopy )
   {
      name = toCopy.getName();
      food = toCopy.getFood();
      minTemp = toCopy.getMinTemp();
      maxTemp = toCopy.getMaxTemp();
      foodCount = toCopy.getFoodCount();
   }

//ACCESSORS:
   //Purpose: Return a copy class field name
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
   
   //Purpose: Return class field minTemp
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getMinTemp
   * IMPORT: none
   * EXPORT: minTemp
   **************************************************************************/
   public double getMinTemp ()
   {
      return minTemp;
   }
   
   //Purpose: Return class field maxTemp
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getMaxTemp
   * IMPORT: none
   * EXPORT: maxTemp
   **************************************************************************/
   public double getMaxTemp ()
   {
      return maxTemp;
   }

   //Purpose: Return size of food array
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getCapacity
   * IMPORT: none
   * EXPORT: capacity
   * ASSERTION:
   **************************************************************************/
   public int getCapacity ()
   {
      return food.length;
   }

   //Purpose: Return copy of class field food
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getFood
   * IMPORT: none
   * EXPORT: COPY OF food
   **************************************************************************/
   public Food[] getFood ()
   {
      return food.clone();
   }

   //Purpose: Return class field foodCount
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getFoodCount
   * IMPORT: none
   * EXPORT: foodCount
   **************************************************************************/
   public int getFoodCount ()
   {
      return foodCount;
   }

   //Purpose: Return free space
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getFreeSpace
   * IMPORT: none
   * EXPORT: freeSpace
   * ASSERTION: Calculates free space and returns it
   **************************************************************************/
   public int getFreeSpace ()
   {
      int freeSpace = food.length - foodCount;
      return freeSpace;
   }

   //Purpose: Check equality between Storage Objects
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE equals
   * IMPORT: inStorage (Storage)
   * EXPORT: bool (boolean)
   * ASSERTION: Checks for if inStorage has identical state to THIS
   **************************************************************************/
   public boolean equals ( Storage inStorage )
   {
      boolean bool = false;
      if (name.equals(inStorage.getName()))
      {
         if (foodCount == inStorage.getFoodCount())
         {
            if (SUtil.realEquals(minTemp,inStorage.getMinTemp(),0.0001))
            {
               if (SUtil.realEquals(maxTemp,inStorage.getMaxTemp(),0.0001))
               {
                  if (getCapacity()  == inStorage.getCapacity())
                  {
                     //Leave array check until last for efficiency
                     if (food.equals(inStorage.getFood()))
                     {
                        bool = true;
                     }
                  }
               }
            }
         }
      }
      return bool;
   }

   //Purpose: Return a descriptive string of Object
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE toString
   * IMPORT: none
   * EXPORT: string (String)
   * ASSERTION: EXPORTs a descriptive String of object state
   **************************************************************************/
   public String toString ()
   {
      String string = "Name: " + name + ". Capacity: " + getCapacity() +
               "Food Count: " + foodCount + ". Free Space" + getFreeSpace() +
               ". Min Temperature: " + minTemp + ". Max Temperature: " +
               maxTemp;
      return string;
   }

   //Purpose: Return a copy of THIS Storage
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE clone
   * IMPORT: none
   * EXPORT: storage (Storage)
   * ASSERTION: EXPORTs a new Name object with identical state to THIS
   **************************************************************************/
   public Storage clone ()
   {
      return new Storage(this);
   }

//MUTATORS:
   //Purpose: set class field name with sanitisation 
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
         name = SUtil.csvSanitise(inName);
      }
      else
      {
         throw new IllegalArgumentException("Name must have data");
      }
   }

   //Purpose: set class field minTemp
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE setMinTemp
   * IMPORT: inTemp (real)
   * EXPORT: none
   * ASSERTION: Sets minTemp to inTemp if inTemp is a valid input
   **************************************************************************/
   public void setMinTemp ( double inTemp )
   {
      if (MINTEMP < inTemp && inTemp < MAXTEMP)
      {
         minTemp = inTemp;
      }
      else
      {
         throw new IllegalArgumentException("Min Temperature out of range");
      }
   }

   //Purpose: set class field maxTemp
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE setMaxTemp
   * IMPORT: inTemp (real)
   * EXPORT: none
   * ASSERTION: Sets maxTemp to inTemp if inTemp is a valid input
   **************************************************************************/
   public void setMaxTemp ( double inTemp )
   {
      if (MINTEMP < inTemp && inTemp < MAXTEMP)
      {
         maxTemp = inTemp;
      }
      else
      {
         throw new IllegalArgumentException("Max Temperature out of range");
      }
   }

//DOING METHODS:
   //Purpose: add a Food Object to storage if there is space
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE addFood
   * IMPORT: inFood (Food)
   * EXPORT: success (boolean)
   * ASSERTION: Checks for space then adds Food to food array
   **************************************************************************/
   public boolean addFood ( Food inFood )
   {
      boolean success;
      if (getFreeSpace() > 0)
      {
         food[foodCount] = inFood.clone();
         foodCount++;
         success = true;
      }
      else
      {
         success = false;
      }
      return success;
   }

   //Purpose: remove a Food Object from storage and shuffle Food down
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE removeFood
   * IMPORT: inFood (Food)
   * EXPORT: none
   * ASSERTION: remove Food from food array
   **************************************************************************/
   public void removeFood ( Food inFood )
   {
      //When adding Food a copy is always made, so no duplicates can exist
      boolean shuffle = false;
      int tempFC = foodCount;
      for (int i = 0; i < tempFC; i++)
      {
         //Remove the exact object of inFood
         if (!shuffle && food[i] == inFood)
         {
            food[i] = null;
            shuffle = true;
            foodCount--;
         }
         if (shuffle)
         {
            if (i < foodCount)
            {
               food[i] = food[i+1];
               food[i+1] = null;
            }
         }
      }
   }

   //Purpose: remove a Food Object from storage and shuffle Food down
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE removeFood
   * IMPORT: index (integer)
   * EXPORT: none
   * ASSERTION: remove food at index index from food array
   **************************************************************************/
   public void removeFood ( int index )
   {
      if (0 <= index && index < foodCount)
      {
         removeFood(food[index]);
      }
      else
      {
         throw new IllegalArgumentException("Food not found");
      }
   }

   //Purpose: Return long string of all Food Objects in food array
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE displayFood
   * IMPORT: none
   * EXPORT: string (String)
   * ASSERTION: EXPORTs a string with all food items
   **************************************************************************/
   public String displayFood ()
   {
      String string = "";
      for (int i = 0; i < foodCount; i++)
      {
         //Output starting from 1 for natural viewing
         string = string + (i+1) + ": " + food[i].toString() + "\n";
      }
      return string;
   }

   //Purpose: find Expired food and remove them
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE findExpired
   * IMPORT: none
   * EXPORT: count (integer)
   * ASSERTION: remove expired Food from food array
   **************************************************************************/
   public int findExpired ()
   {
      Calendar today = Calendar.getInstance();
      int i = 0;
      int count = 0;
      while (i < foodCount)
      {
         if (food[i].calcExpiry(today))
         {
            count++;
            System.out.println("Expired food: " + food[i] + "removed.");
            removeFood(i);
            /* Rewriting the removeFood index method would be more efficient,
            which is why it's in use here.
            removeFood shuffles the array down and changes foodCount, so it
            is not done here */
         }
         else
         {
            i++;
         }
      }
      return count;
   }

   //Purpose: EXPORT an array of csv strings to store to file
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getCSVArray
   * IMPORT: none
   * EXPORT: csvs (ARRAY OF String)
   * ASSERTION: csvs will contain ready to store strings of all Foods
   **************************************************************************/
   public String[] getCSVArray ()
   {
      String[] csvs = new String[foodCount];
      for (int i = 0; i < foodCount; i++)
      {
         csvs[i] = food[i].getCSV();
      }
      return csvs;
   }

   //Purpose: Generate a line to write to csv
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE getCSV
   * IMPORT: none
   * EXPORT: csv (String)
   * ASSERTION: EXPORTS "Storagename, capacity"
   **************************************************************************/
   public String getCSV ()
   {
      String csv = name + ", " + getCapacity();
      return csv;
   }

//PRIVATE METHODS:
} 
