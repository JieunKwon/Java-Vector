////////////////////////////////////////////////////////////////////////////////
//      TestLine.java
// ========================
// main class to test Vector2 and Line  
//
// AUTHOR: Jieun Kwon
// CREATED: Feb  2, 2018
// UPDATED: Feb 17, 2018
//
// Update History:
// Feb 5: final test for Vector2 class
// Feb 11: final test for Line class 
// Feb 17: add to make 2 lines with user input values using Scanner
////////////////////////////////////////////////////////////////////////////////
package jieun;

import java.util.Scanner;

public class TestLine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        /* ------------------------------------------------------
        // TEST : Vector2 class
        Vector2 v1 = new Vector2(1, 2);
        Vector2 v2 = new Vector2(4, 5);  
        System.out.println("v1 + v2: " + v1.add(v2));
        System.out.println("v1 length : " + v1.getLength());
        System.out.println("v1 scale 4.2: " + v1.scale(4.2f));
        ------------------------------------------------------  */ 
        
        /* ------------------------------------------------------
        // TEST : Line class 
        Line line1 = new Line(1, 2,  3, 6);
        Line line2 = new Line(0,10, 10, 5);
        // Test for no intersection 
        // Line line2 = new Line(2, 4, 6, 12);
        ------------------------------------------------------  */
        
        // Ask float values to make lines
        float userNumber[] = new float[8];      // for saving input values
        int counter = 0;                        // for checking loop
        Scanner userInfo = new Scanner(System.in);      // instance of Scanner 
        
        // loop for asking 8 values 
        System.out.println("Please enter total 8 float numbers for making 2 lines");
        do {
            // print message to user
            // ----------------------
            // => Line 1:
            // Point 1, X-axis: 
            // Point 1, Y-axis:
            // ----------------------
            if ((counter % 4) == 0)
                System.out.print("=> Line " + ( counter / 4 + 1) + ":\n");
            if ((counter % 2) == 0)
                System.out.print( "Point " + ( ( (counter % 4) / 2) + 1 ) + ", X-axis: ");
            else
                System.out.print( "Point " + ( ( (counter % 4) / 2) + 1 ) + ", Y-axis: "); 
             
            // check exception for number value and try again
            try {
                userNumber[counter] = userInfo.nextFloat();     // save to Array
                counter++;
            } catch (Exception ex) {
                System.out.println("-- Invalid Input. Try to enter a float number --");
                userInfo.next();
            }
        } while (counter < 8);
        
 
        // close Scanner
        userInfo.close();
         
        // create 2 lines with user's input values
        Line line1 = new Line(userNumber[0], userNumber[1],  userNumber[2], userNumber[3]);
        Line line2 = new Line(userNumber[4], userNumber[5],  userNumber[6], userNumber[7]);
        
        // print information of lines
        System.out.println(line1);
        System.out.println(line2);
 
        // find intersenction between 2 lines and print the result
        if(line1.isIntersected(line2))      // check if they are intersected
        { 
            Vector2 p = line1.intersect(line2);
            System.out.println("Intersect Point: (" + p.x + ", " + p.y + ")");
        }
        else
        {
            System.out.println("Not Intersected");
        } 
    }
    
}
