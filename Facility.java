/*****************************************************************************
* File:      Facility.java                                                   *
* Author:    Sean Ashton * Student ID: 14866636                              *
* Unit:      COMP1001 Object Oriented Program Design                         *
* Purpose:   Stores several Storage Objects and provides interface for       *
*            interaction                                                     *
* Reference: None.                                                           *
* Comments:  This comment block is the maximum width of 78 characters.       *
* Requires:  SUtil, Storage, Food                                            *
* Created:   20/05/2018 * Last Modified: 27/05/2018                          *
*****************************************************************************/
import java.io.*;
public class Facility
{
//PUBLIC CLASS CONSTANTS:
   public static final String[] STORES  = {"Freezer","Fridge","Pantry"};

//CLASS FIELDS:
   private Storage[] storages;

//CONSTRUCTORS://Currently must construct Freezer/Fridge/Pantry [0,1,2]
   /**************************************************************************
   * DEFAULT:
   * IMPORT: none
   * EXPORT: Object Reference
   * ASSERTION: ... is a valid default state
   **************************************************************************/
   public Facility ()
   {
      storages = new Storage[3];
      storages[0] = new Storage (STORES[0],50,-27.0,-5.0);
      storages[1] = new Storage (STORES[1],50,-2.0,6.0);
      storages[2] = new Storage (STORES[2],50,8.0,25.0);
   }

   /**************************************************************************
   * ALTERNATE:
   * IMPORT: freeCap (integer), fridCap (integer), pantCap (integer)
   * EXPORT: Object Reference
   * ASSERTION: Creates object if import are valid, otherwise FAILS
   **************************************************************************/
   public Facility ( int freeCap, int fridCap, int pantCap )
   {
      storages = new Storage[3];
      storages[0] = new Storage (STORES[0],freeCap,-27.0,-5.0);
      storages[1] = new Storage (STORES[1],fridCap,-2.0,6.0);
      storages[2] = new Storage (STORES[2],pantCap,8.0,25.0);
   }

   /**************************************************************************
   * FILE INPUT:
   * IMPORT: fileName (String)
   * EXPORT: Object Reference
   * ASSERTION: Attempts to construct facility from fileName file or FAILS
   **************************************************************************/
   public Facility ( String fileName )
   {
      BufferedReader reader = SUtil.getReader(fileName);
      int freeCap, fridCap, pantCap, cap;
      freeCap = fridCap = pantCap = -1;
      String line = "";
      String[] tokens;
      for (int i = 0; i <= 2; i++)
      {
         try {line = reader.readLine();} catch (IOException e) { }
         tokens = line.split(", ");
         cap = Integer.parseInt(tokens[1]);
         if (tokens[0].equals("Freezer"))
         {
            freeCap = cap;
         }
         else if (tokens[0].equals("Fridge"))
         {
            fridCap = cap;
         }
         else if (tokens[0].equals("Pantry"))
         {
            pantCap = cap;
         }
      }//END FOR
      if (freeCap == -1 || fridCap == -1 || pantCap == -1)
      {
         throw new IllegalArgumentException("Storage setup missing");
      }
      else
      {
         storages = new Storage[3];
         storages[0] = new Storage (STORES[0],freeCap,-27.0,-5.0);
         storages[1] = new Storage (STORES[1],fridCap,-2.0,6.0);
         storages[2] = new Storage ( STORES[2],pantCap,8.0,25.0);
         importFood(reader);
      }
   }

   /**************************************************************************
   * COPY:
   * IMPORT: toCopy (Facility)
   * EXPORT: Object Reference
   * ASSERTION: Constructs an object with identical state to the import
   **************************************************************************/ 
   public Facility ( Facility toCopy )
   {
      storages = toCopy.getStorages();
   }

//ACCESSORS:
   //Purpose: Return storages for use in copying THIS
   //Date Modified: 20/05/2018
   /**************************************************************************
   * SUB MODULE getStorages
   * IMPORT: none
   * EXPORT: COPY OF storages
   **************************************************************************/
   public Storage[] getStorages ()
   {
      return storages.clone();
   }

   //Purpose: Display Storage contents
   //Date Modified: 20/05/2018
   /**************************************************************************
   * SUB MODULE displayContents
   * IMPORT: store (integer)
   * EXPORT: none
   * ASSERTION:
   **************************************************************************/
   public void displayContents ( int store )
   {
      System.out.println(storages[store]);
      System.out.println(storages[store].displayFood());
   }

   /**************************************************************************
   * SUB MODULE equals
   * IMPORT: inFacility (Facility)
   * EXPORT: bool (boolean)
   * ASSERTION: Checks for if inName has identical state to THIS
   **************************************************************************/
   public boolean equals ( Facility inFacility )
   {
      boolean bool = false;
      if (storages.equals(inFacility.getStorages()))
      {
         bool = true;
      }
      return bool;
   }
   
   //Purpose: return a specific storage container
   //Date Modified: 23/05/2018
   /**************************************************************************
   * SUB MODULE getStorage
   * IMPORT: inStore (integer)
   * EXPORT: storage (Storage)
   * ASSERTION: return the Storage container at array index inStore
   **************************************************************************/
   public Storage getStorage ( int inStore )
   {
      Storage storage;
      if (inStore >= 0 && inStore < storages.length)
      {
         storage = storages[inStore];
      }
      else
      {
         throw new IllegalArgumentException ("Storage doesn't exist");
      }
      return storage;
   }

   //Purpose: return a specific storage container
   //Date Modified: 23/05/2018
   /**************************************************************************
   * SUB MODULE getStorage
   * IMPORT: inStore (String)
   * EXPORT: storage (Storage)
   * ASSERTION: return the Storage container at array index inStore
   **************************************************************************/
   public Storage getStorage ( String inStore )
   {
      int i = 0;
      Storage storage = null;
      do
      {
         if (inStore.equals(STORES[i]))
         {
            storage = storages[i];
         }
         else
         {
            i++;
         }
      } while (i < storages.length && storage == null);
      if (storage == null)
      {
         throw new IllegalArgumentException ("Storage doesn't exist");
      }
      return storage;
   }

   /**************************************************************************
   * SUB MODULE toString
   * IMPORT: none
   * EXPORT: string (String)
   * ASSERTION: EXPORTs a descriptive String of object state
   **************************************************************************/
   public String toString ()
   {
      String string = "";
      for (int i = 0; i < storages.length; i++)
      {
          string += storages[i]+"\n";
      }
      return string;
   }

   /**************************************************************************
   * SUB MODULE clone
   * IMPORT: none
   * EXPORT: facility (Facility)
   * ASSERTION: EXPORTs a new Name object with identical state to THIS
   **************************************************************************/
   public Facility clone ()
   {
       return new Facility (this);
   }

//MUTATORS:
   /**************************************************************************
   * SUB MODULE setStorages
   * IMPORT: inStorages (ARRAY OF Storage)
   * EXPORT: none
   * ASSERTION: Sets storages to inStorage if inStorage is a valid input
   **************************************************************************/
   public void setStorages ( Storage[] inStorages )
   {
      if (inStorages != null)
      {
         storages = inStorages.clone();
      }
      else
      {
         throw new NullPointerException ("Null object reference");
      }
   }

//DOING METHODS:
   //Purpose: Write out contents of storage to csv file
   //Date Modified: 23/05/2018
   /**************************************************************************
   * SUB MODULE writeCSV
   * IMPORT: fileName (String)
   * EXPORT: none
 * ASSERTION: writes out the details of storage in 3 lines then all food items
   **************************************************************************/
   public void writeCSV ( String fileName )
   {
      PrintWriter pw = SUtil.getWriter(fileName);
      for (int i = 0; i < storages.length; i++)
      {
         pw.println(storages[i].getCSV());
      }
      for (int i = 0; i < storages.length; i++)
      {
         String[] tempArray = storages[i].getCSVArray();
         for (int j = 0; j < tempArray.length; j++)
         {
            pw.println(tempArray[j]);
            //Use line by line writing to ensure compatibility (as opposed to
            //  \n to connect everything)
         }
      }
      SUtil.closeWriter(pw);
   }


   //Purpose: add a food to applicable storage
   //Date Modified: 23/05/2018
   /**************************************************************************
   * SUB MODULE addFood
   * IMPORT: food (Food)
   * EXPORT: success (boolean)
   * ASSERTION: add food to applicable storage or throw exception
   **************************************************************************/
   public boolean addFood ( Food food )
   {
      boolean success = false;
      int i = 0;
      boolean running = true;
      do
      {
         if (food.getTemperature() > storages[i].getMinTemp() &&
            food.getTemperature() < storages[i].getMaxTemp())
         {
            success = storages[i].addFood(food);
            running = false;
         }
         else
         {
            i++;
         }
      }while (running && i < storages.length);
      if (running)
      {
         throw new IllegalStateException("Food outside of temperature range");
      }
      return success;
   }

   //Purpose: import a file 
   //Date Modified:
   /**************************************************************************
   * SUB MODULE importFood
   * IMPORT: reader (Scanner)
   * EXPORT: none
   * ASSERTION: reader all lines, adding food when valid
   **************************************************************************/
   public void importFood ( BufferedReader reader )
   {
      String line;
      Food food;
      String[] tokens;
      try
      {
         while ((line = reader.readLine()) != null)
         {
            food = null;
            try
            {
               tokens = line.split(", ");
               if (tokens[0].equals("Meat") && tokens.length == 7)
               {
                  food = new Meat(tokens);
               }
               else if (tokens[0].equals("Grain") && tokens.length == 7)
               {
                  food = new Grain(tokens);
               }
               else if (tokens[0].equals("Fruit") && tokens.length == 7)
               {
                  food = new Fruit(tokens);
               }
               else if (tokens[0].equals("Vegetable") && tokens.length == 6)
               {
                  food = new Vegetable(tokens);
               }
            } catch (IllegalArgumentException e)
            {
               System.out.println("Error with food: " + line);
            }

            if (food != null)
            {
               addFood(food);
            }
            else
            {
               System.out.println("Error with food: " + line);
            }
         }
      } catch (IOException e) { System.out.println("Error reading file"); }
   }
   
   //Purpose: Find all expired food and remove it
   //Date Modified: 23/05/2018
   /**************************************************************************
   * SUB MODULE findExpired
   * IMPORT: none
   * EXPORT: none
   * ASSERTION: call findExpired in all Storages
   **************************************************************************/
   public void findExpired ()
   {
      for (int i = 0; i < storages.length; i++)
      {
         storages[i].findExpired();
      }
   }

//PRIVATE METHODS:
}
