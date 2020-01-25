/*****************************************************************************
* File:      SUtil.java                                                      *
* Author:    Sean Ashton * Student ID: 14866636                              *
* Unit:      COMP1001 Object Oriented Program Design                         *
* Purpose:   Utility Methods                                                 *
* Reference: None.                                                           *
* Comments:  This comment block is the maximum width of 78 characters.       *
* Requires:  Nothing. List dependencies here                                 *
* Created:   12/04/2018 * Last Modified: 12/04/2018                          *
*****************************************************************************/
import java.util.*;
import java.io.*;
public class SUtil
{
/*****************************************************************************
* Purpose: Ask user for Integer input                                        *
* Date Modified: 12/04/2018                                                  *
******************************************************************************
* SUB MODULE inputInt                                                        *
*   IMPORT: prompt(String), min, max (integer)                               *
*   EXPORT: userInt (integer)                                                *
*   ASSERTION: userInt will be an Integer between min and max inclusive      *
*****************************************************************************/
   public static int inputInt ( String prompt, int min, int max )
   {
      String error = "";
      int userInt = min-1;
      Scanner sc = new Scanner(System.in);
      do
      {
         try
         {
            System.out.println(error);
            System.out.println(prompt);

            userInt = sc.nextInt();

            if (userInt < min)
            {
               error = "Integer must be greater than or equal to " + min;
            }
            else if (userInt > max)
            {
               error = "Integer must be less than or equal to " + max;
            }
            //ASSERTION error will contain the error message if invalid inpu
         }
         catch (InputMismatchException e)
         {
            error = "Input must be an integer";
            sc.next();
         }
      } while ( userInt < min || userInt > max );
      //ASSERTION userInt will contain an Integer between min and max
      return userInt;
   }//END getInteger

/*****************************************************************************
* Purpose: Check equality of real numbers
* Date Modified: 17/05/2018
******************************************************************************
* SUB MODULE realEquals
* IMPORT: realA (real), realB (real), tolerance (real)
* EXPORT: bool (boolean)
* ASSERTION: bool will be true only when realA and realB are within tolerance
*****************************************************************************/
   public static boolean realEquals ( double realA, double realB,
                                       double tolerance )
   {
      boolean bool = (Math.abs(realA - realB) < tolerance);
      return bool;
   }//END realEquals

   //Purpose: Removes problem characters for csv storage
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE csvSanitise
   * IMPORT: inString (String)
   * EXPORT: string (String)
   * ASSERTION: string will be inString minus any problem characters
   **************************************************************************/
   public static String csvSanitise ( String inString )
   {
      String string = inString.replaceAll(",","");
      return string;
   }


   //Purpose: Ask user for real number input
   //Date Modified: 27/05/2018
   /**************************************************************************
    * SUB MODULE inputReal
    * IMPORT: prompt (String), min (real), max (real)
    * EXPORT: userReal (real)
    * ASSERTION: userReal will be a real between min and max inclusive
    *************************************************************************/
   public static double inputReal ( String prompt, double min, double max )
   {
      String error = "";
      double userReal = min-1.0;
      Scanner sc = new Scanner(System.in);
      do
      {
         try
         {
            System.out.println(error);
            System.out.println(prompt);

            userReal = sc.nextDouble();

            if (userReal < min)
            {
               error = "Number must be greater than or equal to " + min;
            }
            else if (userReal > max)
            {
               error = "Number must be less than or equal to " + max;
            }
            //ASSERTION error will contain the error message if invalid inpu
         }
         catch (InputMismatchException e)
         {
            error = "Input must be a number";
            sc.next();
         }
      } while ( userReal < min || userReal > max );
      //ASSERTION userInt will contain an Integer between min and max
      return userReal;
   }

   //Purpose: Gets a date from the user not before inDate
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE inputDate
   * IMPORT: inDate (Calendar)
   * EXPORT: date (Calendar)
   * ASSERTION: date will not be before inDate
   **************************************************************************/
   public static Calendar inputDate ( String prompt, Calendar inDate )
   {
      Calendar date = null;
      Scanner sc = new Scanner(System.in);
      String userDate,error;
      error = "";
      String[] tokens;
      int[] ints = new int[3];
      do
      {
         System.out.println(error);
         System.out.println(prompt);
         System.out.println("Please input a date after " + 
                                          calToString(inDate));
         System.out.println("In the format dd/mm/yyyy");
         userDate = sc.nextLine();

         if (userDate.matches("(\\d{1,2}/){2}\\d{4}"))
         {
            tokens = userDate.split("/");
            for (int i = 0; i <= 2; i++)
            {
               ints[i] = Integer.parseInt(tokens[i]);
               //www.tutorialspoint.com/java/number_parseint.htm
            }
            //Month is 0 based
            ints[1]--;
            date = Calendar.getInstance();
            date.setLenient(false);
            //Validate date by disallowing leniency
            try
            {
               date.set(ints[2],ints[1],ints[0]);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
               error = "Invalid date: " + e.getMessage();
            }
         }
         else
         {
            error = "Date must in the format of dd/mm/yyyy";
            //ASSERTION error will contain the error message if invalid input
         }
      } while (date == null);
      return date;
   }//END inputDate

   //Purpose: Open a file and construct a writer
   //Date Modified: 25/05/2018
   /**************************************************************************
    * SUB MODULE getWriter
    * IMPORT: fileName (String)
    * EXPORT: writer (PrintWriter)
    * ASSERTION: writer will be a PrintWriter to fileName or FAIL
    *************************************************************************/
   public static PrintWriter getWriter ( String fileName )
   {
      FileOutputStream fileStrm = null;
      PrintWriter writer = null;
      try
      {
         fileStrm = new FileOutputStream(fileName);
         writer = new PrintWriter(fileStrm);
      }
      catch (IOException e)
      {
         if (fileStrm != null) 
         {
            try { fileStrm.close(); } catch (IOException ex2) { }
         }
      }
      return writer;
   }

   //Purpose: C;pse a writer previously opened with getWriter
   //Date Modified: 25/05/2018
   public static void closeWriter ( PrintWriter writer )
   {
      if (writer != null) 
      {
         writer.close();
      }
   }

   //Purpose: Open a file and construct a reader
   //Date Modified: 25/05/2018
   /**************************************************************************
    * SUB MODULE getReader
    * IMPORT: fileName (String)
    * EXPORT: sc (Reader)
    * ASSERTION: Opens a file, sets up a reader and returns it
    *************************************************************************/
    public static BufferedReader getReader ( String fileName )
    {
       FileInputStream fileStrm = null;
       InputStreamReader rdr;
       BufferedReader sc;
       try
       {
          fileStrm = new FileInputStream(fileName);
          rdr = new InputStreamReader(fileStrm);
          sc = new BufferedReader(rdr);
       }
       catch (IOException e)
       {
          if (fileStrm != null) 
          { 
             try { fileStrm.close(); } catch (IOException ex2) { }
          }
          sc = null;
       }
       return sc;
    }
 
   //Purpose: Close a reader previously opened with getReader
   //Date Modified: 25/05/2018
   /**************************************************************************
    * SUB MODULE closeReader
    * IMPORT: reader (Reader)
    * EXPORT: none
    * ASSERTION: attempts to close the Reader file
    *************************************************************************/
    public static void closeReader ( BufferedReader reader )
    {
       if (reader != null)
       {
          try { reader.close(); } catch (IOException e) { }
       }
    }

   //Purpose: Convert a String to Calendar
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE stringToCal
   * IMPORT: string (String)
   * EXPORT: cal (Calendar)
   * ASSERTION: Converts a dd/mm/yyyy date to a calendar
   **************************************************************************/
   public static Calendar stringToCal ( String string )
   {
      Calendar cal = null;
      if (string.matches("(\\d{1,2}/){2}\\d{4}"))
      {
         String[] tokens = string.split("/");
         int[] ints = new int[3];
         for (int i = 0; i <= 2; i++)
         {
            ints[i] = Integer.parseInt(tokens[i]);
         }
         //Month is 0 based
         ints[1]--;
         cal = Calendar.getInstance();
         cal.setLenient(false);
         try
         {
            cal.set(ints[2],ints[1],ints[0]);
         }
         catch (ArrayIndexOutOfBoundsException e)
         {
            throw new IllegalArgumentException("Invalid date: " + 
                                       e.getMessage());
         }
      }
      else
      {
         throw new IllegalArgumentException("Invalid date format");
      }
      return cal;
   }
      
   //Purpose: Convert a Calendar to a String
   //Date Modified: 28/05/2018
   /**************************************************************************
   * SUB MODULE calToString
   * IMPORT: cal (Calendar)
   * EXPORT: string (String)
   * ASSERTION: Converts a calendar into a string dd/mm/yyyy
   **************************************************************************/
   public static String calToString ( Calendar cal )
   {
      int day, month, year;
      String string = "";
      day = cal.get(Calendar.DATE);
      month = (cal.get(Calendar.MONTH)+1);
      year = cal.get(Calendar.YEAR);

      if (day <= 9)
      {
         string = "0" + day + "/";
      }
      else
      {
         string = day + "/";
      }
      if (month <= 9)
      {
         string += "0" + month + "/" + year;
      }
      else
      {
         string += month + "/" + year;
      }
      return string;
   }
}//END Class
