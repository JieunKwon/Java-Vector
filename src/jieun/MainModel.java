////////////////////////////////////////////////////////////////////////////////////////////
//      Java 2 : Assignment 3
//      Task :  Line Intersection
////////////////////////////////////////////////////////////////////////////////////////////
//     - current src file : MainModel.java
//     - current src task : Model Component - find intersection for 2 lines
//     - created by : Jieun Kwon
//     - created date : April 13, 2018
//     - modified date : April 15, 2018
////////////////////////////////////////////////////////////////////////////////////////////

package jieun;

import song.Line;
import song.Vector2;

/**
 *
 * @author JIEUN KWON
 */
public class MainModel {
   
   // mem vars
    private Line line1;
    private Line line2;

    // Constructor
    public MainModel() {
        line1 = new Line();
        line2 = new Line();
    }

    // Constructor with params
    public MainModel(Line line1, Line line2) {
        this.line1 = line1;
        this.line2 = line2;
    }
    
    // getter for line1
    public Line getLine1() {
        return line1;
    }

    // setter for line1
    public void setLine1(Line line1) {
        this.line1 = line1;
    }

    // getter for line2
    public Line getLine2() {
        return line2;
    }

    // setter for line2
    public void setLine2(Line line2) {
        this.line2 = line2;
    }
    
    // setter for line1 with 4 params
    public void setLine1(float x1, float y1, float x2, float y2){
        this.line1 = new Line(x1, y1, x2, y2);
    }

    // setter for line2 with 4 params
    public void setLine2(float x1, float y1, float x2, float y2){
        this.line2 = new Line(x1, y1, x2, y2);
    }
   
    // return Vector2 for intersection of 2 lines
    public Vector2 getIntersectPoint(){
   
       Vector2 v2 = new Vector2();
       v2 = this.line1.intersect(line2);
         
       return v2;
    } 
    
    // get Line Equation for Line 1 then return string 
    public String getLine1Equation(){
    
        String le = new String();
        
        // Line Equation : y = mx + c
        //-------------------------------
        // calculate m and y
        // m = -(d.y/-d.x) 
        float m = -(line1.getDirection().y / -line1.getDirection().x);
        
        // c =  (d.y*p.x - d.x * p.y) / -d.x
        float c = ( line1.getDirection().y * line1.getPoint().x - line1.getDirection().x * line1.getPoint().y) / -line1.getDirection().x;
     
        // make le
        if(m == 0)
            le = "y = 0";
        else
            le = "y = " +  String.format("%.2f", m) + "x";
        
        if( c != 0) 
             
            le = le +  " + " + String.format("%.2f",c) ;
 
        // return
        return le ;
    }
    
    // get Line Equation for Line 2 then return string 
    public String getLine2Equation(){
    
        String le = new String();
     
        // Line Equation : y = mx + c
        //-------------------------------
        
        // calculate m and c
        float m = -(line2.getDirection().y / -line2.getDirection().x);
        float c = ( line2.getDirection().y * line2.getPoint().x - line2.getDirection().x * line2.getPoint().y) / -line2.getDirection().x;
        
        // make le
        if(m == 0)
            le = "y = 0";
        else
            le = "y = " +  String.format("%.2f", m) + "x";
        
        if( c != 0) 
            le = le +  " + " + String.format("%.2f",c) ;
        
        // retuen
        return le ;
    }  
}
