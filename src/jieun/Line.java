////////////////////////////////////////////////////////////////////////////////
//      Line.java
// ========================
// a general purpose class to define line
//
// AUTHOR: Jieun Kwon
// CREATED: Feb  5, 2018
// UPDATED: Feb 14, 2018
////////////////////////////////////////////////////////////////////////////////
package jieun;
 
public class Line {
    
    // member variables
    private Vector2 point;          // any point on the line    
    private Vector2 direction;      // p1 - p0
    
    // Constructor: without params
    public Line() 
    {
        set(0, 0, 0, 0);
    }
    
    // Constructor: with 2 params (Vector2, Vector2) 
    public Line(Vector2 point, Vector2 direction) 
    {
        set(point, direction);
    }
    
    // Constructor: with 2 params (float, float) 
    public Line(float slope, float intercept) 
    {
        set(slope, intercept);
    }                        
    
    // Constructor: with 4 params (float, float, float, float) 
    public Line(float x1, float y1, float x2, float y2)
    {
        set(x1, y1, x2, y2);        
    }  
    
    // Method : set variables with 2 params (Vector2, Vector2) 
    public void set(Vector2 point, Vector2 direction) 
    {
        setPoint(point);
        setDirection(direction);
    }
    
    // Method - overloading : set variables with 2 params (float, float) 
    public void set(float slope, float intercept) 
    {
        point = new Vector2(0, intercept);
        direction = new Vector2(1, slope);
    }
    
    // Method - overloading : set variables with 4 params (float, float, float, float) 
    public void set(float x1, float y1, float x2, float y2)
    {
        point = new Vector2(x1, y1);
        direction = new Vector2(x2-x1, y2-y1);
    }        

    // mutator : set point value (Vector2)
    public void setPoint(Vector2 point)
    {
        this.point = new Vector2(point.x, point.y);
    }
    
    // accessor : return point value
    public Vector2 getPoint()
    {
        return point;
    }
      
    // mutator : set direction value (Vector2)
    public void setDirection(Vector2 direction)
    {
        this.direction = new Vector2(direction.x, direction.y);
    }
    
    // accessor : return direction value
    public Vector2 getDirection()
    {
        return direction;
    }
            
    // method : find the intersection point between current line and other line from param (Line)  
    public Vector2 intersect(Line line)
    { 
        float a = getDirection().y;
        float b = -getDirection().x;
        float c = ( getPoint().x * a ) + ( b * getPoint().y );
        
        float d = line.getDirection().y;
        float e = -line.getDirection().x;
        float f = ( line.getPoint().x * d ) + ( e * line.getPoint().y );
 
        return new Vector2( (c * e - b * f) / (a * e - b * d), (a * f - c * d) / (a * e - b * d) );
    }
    
    // method : determine if 2 lines are intersected or not and return boolean value
    public boolean isIntersected(Line line)
    { 
        return ( ( getDirection().y * (-line.getDirection().x ) - ( -getDirection().x) * line.getDirection().y ) != 0 );
    }
    
    // method - overriding : toString 
    @Override
    public String toString() 
    {
        return "Line\n====\n" + "    point: (" + getPoint().x + ", " + getPoint().y + ")\ndirection: (" + getDirection().x + ", " + getDirection().y + ")\n--------------------------------" ;
    }
    
}
