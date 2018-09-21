////////////////////////////////////////////////////////////////////////////////
//      Vector2.java
// ========================
// a general purpose class to define 2D vector
//
// AUTHOR: Jieun Kwon
// CREATED: Feb 2, 2018
// UPDATED: Feb 4, 2018
////////////////////////////////////////////////////////////////////////////////
package jieun;

public class Vector2 {
    
    // member variables
    public float x;     // for X-axis 
    public float y;     // for Y-axis
    
    // constructor : no params
    public Vector2(){
        set(0, 0);
    }
    
    // constructor : with 2 params 
    public Vector2(float x, float y){
        set(x, y);
    }
    
    // method : set variables (float x, float y) 
    public void set(float x, float y){
        this.x = x;
        this.y = y;   
    }
    
    // method - overloading : set variables with param (Vector2)  
    public void set(Vector2 v){
        set(v.x, v.y);
    }
    
    // method - overriding : toString 
    @Override
    public String toString(){
        return "Vector2(" + x + ", " + y + ")";
    }
    
    // method : return a copy (clone) of the current Vector2
    public Vector2 clone(){
        return new Vector2(x,y);    
    }
    
    // method : add vector to current vector and return the current vector 
    public Vector2 add(Vector2 v)
    {
        x += v.x; // x = x + v.x
        y += v.y; // y = y + v.y
       
        return this;    
    }

    // method - overloading : vector addition with 2 params (Vector2, Vector2) : v1 <= v1 + v2
    public static Vector2 add(Vector2 v1, Vector2 v2)
    {
        v1.x += v2.x; // v1.x = v1.x + v2.x
        v1.y += v2.y; // v1.y = v1.y + v2.y 
        
        return v1;  
    }
    
    // method : subtract vector to current vector and return the current vector 
    public Vector2 subtract(Vector2 v)
    {
        x -= v.x; // x = x - v.x
        y -= v.y; // y = y - v.y
       
        return this;    
    }
  
    // method - overloading : vector subtraction with 2 params (Vector2, Vector2) : v1 <= v1 - v2
    public Vector2 subtract(Vector2 v1, Vector2 v2)
    {
        v1.x -= v2.x; // v1.x = v1.x - v2.x
        v1.y -= v2.y; // v1.y = v1.y - v2.y 
        
        return v1;  
    }
    
    // method : vector multiply a scalar and return the result : v1 <= v1 * scalar
    public Vector2 scale(float scalar)
    {
        x *= scalar; // x = x * scalar
        y *= scalar; // y = y * scalar
       
        return this;    
    }

    // method : calculate inner product and return the result : 𝑎∙𝑏 = (𝑎.𝑥×𝑏.𝑥) + (𝑎.𝑦×𝑏.𝑦)
    public float dot(Vector2 v)
    {
        return (x * v.x) + (y * v.y);    
    }
    
    // method : calculate the length of the vector and return the result 
    public float getLength()
    {
        return (float)Math.sqrt( x*x + y*y );
    }
    
    // method : make the vector with unit length and return the result vector
    public Vector2 normalize()
    {
        x = x / getLength();
        y = y / getLength();
        
        return this;
    }
    
}
