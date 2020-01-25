/*****************************************************************************
* File:      Menu.java                                                       *
* Author:    Sean Ashton * Student ID: 14866636                              *
* Unit:      COMP1001 Object Oriented Program Design                         *
* Purpose:   Display a menu of options                                       *
* Reference: None.                                                           *
* Comments:  This comment block is the maximum width of 78 characters.       *
* Requires:  Facility, SUtil, Food                                           *
* Created:   15/05/2018 * Last Modified: 28/05/2018                          *
*****************************************************************************/
import java.util.*;
public class Menu
{

//PUBLIC CLASS CONSTANTS:
   public static final double MIN_TEMP = Food.MINTEMPA;
   public static final double MAX_TEMP = Food.MAXTEMPC;

//CLASS FIELDS:
   private static Facility facility;

   //Purpose: Present a menu to the user and accept interaction
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE run
   * IMPORT: none
   * EXPORT: none
   * ASSERTION: 
   **************************************************************************/
   public static void run ()
   {
      Facility facility = null;
      int sel;
      boolean run = true;
      do
      {
         System.out.println( "1: Add food" );
         System.out.println( "2: Remove food" );
         System.out.println( "3: Display Contents" );
         System.out.println( "4: Find Expired" );
         System.out.println( "5: Read in storage" );
         System.out.println( "6: Write out storage" );
         System.out.println( "7: Exit" );

         sel = SUtil.inputInt("Please enter a menu option (1-7)",1,7);
      
         switch(sel)
         {
            case 1: addFood(facility);
               break;
            case 2: removeFood(facility);
               break;
            case 3: displayContents(facility);
               break;
            case 4:
               if (facility != null)
               {
                  facility.findExpired();
                  System.out.println( "Expired food has been removed" );
               }
               else
               {
                  System.out.println( "Storage must be read in from a file " +
                                       "first" );
               }
               break;
            case 5: readInStorage(facility);
               break;
            case 6: writeOutStorage(facility);
               break;
            case 7: run = false;
               break;
         }//END CASE
      } while (run);
   }

   //Purpose: Construct a food from user input and add it to facility
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE addFood
   * IMPORT: none
   * EXPORT: none
   * ASSERTION: Get user input to create a Food to add to facility
   **************************************************************************/
   public static void addFood( Facility facility )
   {
      if (facility != null)
      {
         Food food = makeFood();
         boolean success = false;
         try
         {
            success = facility.addFood(food);
         }
         catch (IllegalStateException e)
         {
            System.out.println( "Error with food: " + e.getMessage());
         }
         if (!success)
         {
            System.out.println( "Storage location for food is full" );
         }
      }
      else
      {
         System.out.println( "Storage must be read in from a file" +
                                          " before adding food");
      }
   }

   //Purpose: Make a food from user input
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE makeFood
   * IMPORT: none
   * EXPORT: food (Food)
   * ASSERTION: food will be a valid food
   **************************************************************************/
   public static Food makeFood ()
   {
      int sel;
      Food food = null;
      System.out.println( "What kind of food would you like to add?" );
      System.out.println( "1: Meat" );
      System.out.println( "2: Grain" );
      System.out.println( "3: Fruit" );
      System.out.println( "4: Vegetable" );
      sel = SUtil.inputInt("Food type (1-4):",1,4);
      System.out.println( "Name of food?" );
      Scanner sc = new Scanner(System.in);
      String name = sc.nextLine();
      double temp = SUtil.inputReal("Storage temperature of food?",MIN_TEMP,
                                                               MAX_TEMP);
      System.out.println( "What is the food's packaging?" );
      String packaging = sc.nextLine();
      Calendar today = Calendar.getInstance();
      Calendar date;
      String type;
      double weight;
      try
      {
         switch (sel)
         {
            case 1: //Meat construct
               System.out.println( "What is the cut of meat?" );
               String cut = sc.nextLine();
               weight = SUtil.inputReal("What is the weight of the" +
                                                      " meat?", 0.2, 5.0);
               date = SUtil.inputDate("What is the Use By Date" +
                                             " of the meat?", today );
               food = new Meat(name, temp, packaging, cut, weight, date);
               break;

            case 2: //Grain construct
               System.out.println( "What is the type of grain?" );
               type = sc.nextLine();
               double volume = SUtil.inputReal("What is the volume of the" +
                                                   " grain?", 0.2, 5.0);
               date = SUtil.inputDate("What is the Before Before Date " +
                                          "of the grain?", today );
               food = new Grain(name, temp, packaging, type, volume, date);
               break;

            case 3: //Fruit construct
               System.out.println( "What is the type of fruit?" );
               type = sc.nextLine();
               int numPiece = SUtil.inputInt("How many pieces of fruit?",
                                                         1, 20);
               date = SUtil.inputDate("What is the Use By Date of " +
                                          "the fruit?", today );
               food = new Fruit(name, temp, packaging, type, numPiece,date);
               break;

            case 4: //Vegetable construct
               weight = SUtil.inputReal("What is the weight of the " +
                                             "vegetable?", 0.2, 5.0);
               date = SUtil.inputDate("What is the Before Before Date of " +
                                          "the vegetable?", today );
               food = new Vegetable(name, temp, packaging, weight, date);
               break;
         }
      }
      catch (IllegalArgumentException e)
      {
         System.out.println( "There was a problem with an input field:" );
         System.out.println( e.getMessage() );
      }
      return food;
   }

   //Purpose: Select and remove a specific food from facility
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE removeFood
   * IMPORT: none
   * EXPORT: none
   * ASSERTION: Prompts for food to delete
   **************************************************************************/
   public static void removeFood ( Facility facility )
   {
      int sel = 0;
      if (facility != null)
      {
         System.out.println( "Which storage would you like to remove from?" );
         System.out.println( "1: Freezer" );
         System.out.println( "2: Fridge" );
         System.out.println( "3: Pantry" );
         sel = SUtil.inputInt("Storage location (1-3)?",1,3);
         Storage storage = facility.getStorage(sel-1);
         do
         {
            System.out.println( "Which food would you like to remove? " +
                     "(Enter 0 to list contents)" );
            //array indexes from 0 but we display from 1
            sel = SUtil.inputInt("Food to remove?",0,
                                       storage.getFoodCount());
            if (sel == 0)
            {
               System.out.println( storage.displayFood() );
            }
            else
            {
               try
               {
                  storage.removeFood(sel-1);
               }
               catch (IllegalArgumentException e)
               {
                  System.out.println(e.getMessage());
               }
            }
         }while (sel == 0);
      }
      else
      {
         System.out.println( "Storage must be read in from a file first" );
      }
   }

   //Purpose: Display the contents of all storages
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE displayContents
   * IMPORT: none
   * EXPORT: none
   * ASSERTION: Displays all food in a specific location
   **************************************************************************/
   public static void displayContents ( Facility facility )
   {
      int sel;
      if (facility != null)
      {
         System.out.println( "Which storage would you like to display" +
                                       " contents of?");
         System.out.println( "1: Freezer" );
         System.out.println( "2: Fridge" );
         System.out.println( "3: Pantry" );
         sel = SUtil.inputInt("Storage location (1-3)?",1,3);
         Storage storage = facility.getStorage(sel-1);
         System.out.println( storage.displayFood() );
      }
      else
      {
         System.out.println( "Storage must be read in from a file first" );
      }
   }

   //Purpose: Select and read in a file to construct facility
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE readInStorage
   * IMPORT: none
   * EXPORT: none
   * ASSERTION: Check for file and construct a facility with it.
   **************************************************************************/
   public static void readInStorage ( Facility facility )
   {
      if (facility == null)
      {
         System.out.println( "File name for storage input?" );
         Scanner sc = new Scanner(System.in);
         String fileName = sc.nextLine();
         try
         {
            facility = new Facility(fileName);
         } 
         catch (IllegalArgumentException e)
         {
            System.out.println( "Error with file: " + e.getMessage() );
         }
         catch (NullPointerException e)
         {
            System.out.println( "Error with file: " + e.getMessage() );
         }
      }
      else
      {
         System.out.println( "Storage has already been read in from file." );
      }
   }

   //Purpsoe: Prompt for filename to write facility to
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE writeOutStorage
   * IMPORT: none
   * EXPORT: none
   * ASSERTION: Write out storages to file after promt
   **************************************************************************/
   public static void writeOutStorage ( Facility facility )
   {
      if (facility != null)
      {
         System.out.println( "File name for storage output?" );
         Scanner sc = new Scanner(System.in);
         String fileName = sc.nextLine();
         try
         {
            facility.writeCSV(fileName);
         }
         catch (IllegalArgumentException e)
         {
            System.out.println( "Error with file: " + e.getMessage() );
         }
      }
      else
      {
         System.out.println( "Storage must be read in from a file first" );
      }
   }
}
      
