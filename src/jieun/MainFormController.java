////////////////////////////////////////////////////////////////////////////////////////////
//      Java 2 : Assignment 3
//      Task :  Line Intersection
////////////////////////////////////////////////////////////////////////////////////////////
//     - current src file : MainFormController.java
//     - current src task : Controller Component for MainForm
//     - created by : Jieun Kwon
//     - created date : April 6, 2018
//     - modified date : April 15, 2018
////////////////////////////////////////////////////////////////////////////////////////////

package jieun;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import song.Vector2;

public class MainFormController implements Initializable
{
    // constants
    private static final int PADDING = 10;      // pixels
    private static final int UNIT_COUNT = 10;   // # of units only on positive side

    // member vars
    private int width;                  // width of drawing area
    private int height;                 // height of drawing area
    private int centerX;                // center X in screen space
    private int centerY;                // center y in screen space
    private double mouseX;              // screen coordinate x
    private double mouseY;              // screen coordinate y
    private double coordRatio;          // map screen coord to logical coord, s/l
    private double coordX;              // logical coordinate x
    private double coordY;              // logical coordinate y
    private Line[] hLines;              // horizontal grid lines
    private Line[] vLines;              // vertical grid lines
    
    
    // move to medel ///////////////////////
    private MainModel model;

    // JavaFX controls
    private Rectangle rectClip;         // clipping rectangle
    @FXML
    private Pane paneView;
    @FXML
    private Pane paneControl;
    @FXML
    private Label labelCoord;
    @FXML
    private Line line1a;
    @FXML
    private Line line1b;
    @FXML
    private Line line1c;
    @FXML
    private Line line2a;
    @FXML
    private Line line2b;
    @FXML
    private Line line2c;
    @FXML
    private Circle point1a;
    @FXML
    private Circle point1b;
    @FXML
    private Circle point2a;
    @FXML
    private Circle point2b;
    @FXML
    private Circle pointIntersect;
    @FXML
    private Label lblL1X1;
    @FXML
    private Label lblL1Y1;
    @FXML
    private Slider sliderL1X1;
    @FXML
    private Slider sliderL1Y1;
    @FXML
    private Slider sliderL1X2;
    @FXML
    private Slider sliderL1Y2;
    @FXML
    private Label lblL1X2;
    @FXML
    private Label lblL1Y2;
    @FXML
    private Slider sliderL2X1;
    @FXML
    private Slider sliderL2X2;
    @FXML
    private Slider sliderL2Y2;
    @FXML
    private Slider sliderL2Y1;
    @FXML
    private Label lblL2X1;
    @FXML
    private Label lblL2Y1;
    @FXML
    private Label lblL2X2;
    @FXML
    private Label lblL2Y2;
    @FXML
    private Label lblIntersect;
    @FXML
    private Label lblLine1;
    @FXML
    private Label lblLine2;


    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // model
        model = new MainModel();
        
        // init line arrays
        initGrid();
       
        // set clip region for drawing area
        rectClip = new Rectangle(500, 500);
        paneView.setClip(rectClip);

        // init label for intersect
        lblIntersect.setText("( 0.000, 0.000 )");
        
        // update width and height of drawing area
        ChangeListener resizeListener = (ov, oldV, newV) -> handleViewResized();
        paneView.widthProperty().addListener(resizeListener);
        paneView.heightProperty().addListener(resizeListener);
        
        // property bringing for slide
        // Line 1
        lblL1X1.textProperty().bind(sliderL1X1.valueProperty().asString("%.1f"));
        lblL1Y1.textProperty().bind(sliderL1Y1.valueProperty().asString("%.1f"));
        lblL1X2.textProperty().bind(sliderL1X2.valueProperty().asString("%.1f"));
        lblL1Y2.textProperty().bind(sliderL1Y2.valueProperty().asString("%.1f"));
        
        //Line2
        lblL2X1.textProperty().bind(sliderL2X1.valueProperty().asString("%.1f"));
        lblL2Y1.textProperty().bind(sliderL2Y1.valueProperty().asString("%.1f"));
        lblL2X2.textProperty().bind(sliderL2X2.valueProperty().asString("%.1f"));
        lblL2Y2.textProperty().bind(sliderL2Y2.valueProperty().asString("%.1f"));
        
        // define ChangeListener for all sliders
        ChangeListener listener = (ov, oldV, newV) -> updateLines();
        
        // register the listener to all sliders
        // Line1 
        sliderL1X1.valueProperty().addListener(listener);
        sliderL1Y1.valueProperty().addListener(listener);
        sliderL1X2.valueProperty().addListener(listener);
        sliderL1Y2.valueProperty().addListener(listener);
        
        // Line2
        sliderL2X1.valueProperty().addListener(listener);
        sliderL2Y1.valueProperty().addListener(listener);
        sliderL2X2.valueProperty().addListener(listener);
        sliderL2Y2.valueProperty().addListener(listener);
 
        
    }
 

    ///////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleMouseMoved(MouseEvent event)
    {
        mouseX = event.getX();
        mouseY = event.getY();
        coordX = (mouseX - centerX) / coordRatio;
        coordY = (height - mouseY - centerY) / coordRatio;
        labelCoord.setText(String.format("(%.1f, %.1f)", coordX, coordY));
    }



    ///////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleMouseDragged(MouseEvent event)
    {
        mouseX = event.getX();
        mouseY = event.getY();
        coordX = (mouseX - centerX) / coordRatio;
        coordY = (height - mouseY - centerY) / coordRatio;
        labelCoord.setText(String.format("(%.1f, %.1f)", coordX, coordY));
    }



    ///////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleMousePressed(MouseEvent event)
    {
    }



    ///////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleMouseReleased(MouseEvent event)
    {
    }



    ///////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleMouseExited(MouseEvent event)
    {
        labelCoord.setText("");
    }



    ///////////////////////////////////////////////////////////////////////////
    private void initGrid()
    {
        int lineCount = UNIT_COUNT * 2 + 1; // both side plus 1 at enter
        hLines = new Line[lineCount];
        vLines = new Line[lineCount];

        // create line objects
        for(int i = 0; i < lineCount; ++i)
        {
            hLines[i] = new Line();
            hLines[i].setStrokeWidth(0.2);
            hLines[i].setStroke(Color.GRAY);
            paneView.getChildren().add(hLines[i]);
            hLines[i].toBack();

            vLines[i] = new Line();
            vLines[i].setStrokeWidth(0.2);
            vLines[i].setStroke(Color.GRAY);
            paneView.getChildren().add(vLines[i]);
            vLines[i].toBack();
        }

        // for center line
        hLines[lineCount / 2].setStroke(Color.BLACK);
        hLines[lineCount / 2].setStrokeWidth(0.4);
        vLines[lineCount / 2].setStroke(Color.BLACK);
        vLines[lineCount / 2].setStrokeWidth(0.4);

        // layout grid lines
        updateGrid();
    }



    ///////////////////////////////////////////////////////////////////////////
    private void handleViewResized()
    {
        width = (int)paneView.getWidth();
        height = (int)paneView.getHeight();

        // compute the ratio of scrren to virtual = s / v
        double dim = Math.min(width, height) - (PADDING * 2);
        coordRatio = dim / (UNIT_COUNT * 2.0);

        centerX = (int)(width * 0.5 + 0.5);
        centerY = (int)(height * 0.5 + 0.5);
     
        // update clipping region
        rectClip.setWidth(width);
        rectClip.setHeight(height);

        updateGrid();
        updateLines();
    }



    ///////////////////////////////////////////////////////////////////////////
    private void updateGrid()
    {
        int dim;    // square dimension
        int xGap, yGap;

        if(width > height)
        {
            dim = height - (PADDING * 2);
            xGap = (int)((width - dim) * 0.5 + 0.5);
            yGap = PADDING;
        }
        else
        {
            dim = width - (PADDING * 2);
            xGap = PADDING;
            yGap = (int)((height - dim) * 0.5 + 0.5);
        }
        double step = dim / (UNIT_COUNT * 2.0);

        for(int i = 0; i < hLines.length; ++i)
        {
            hLines[i].setStartX(xGap);
            hLines[i].setStartY(yGap + (int)(step * i + 0.5));
            hLines[i].setEndX(width - xGap);
            hLines[i].setEndY(yGap + (int)(step * i + 0.5));

            vLines[i].setStartX(xGap + (int)(step * i + 0.5));
            vLines[i].setStartY(yGap);
            vLines[i].setEndX(xGap + (int)(step * i + 0.5));
            vLines[i].setEndY(height - yGap);
        }
    }
 
    ///////////////////////////////////////////////////////////////////////////
    // update Line1 and Line2 
    private void updateLines()
    {
        // get logical coords of all points
        float l1x1 = (float)sliderL1X1.getValue();      // Line1 - x1
        float l1y1 = (float)sliderL1Y1.getValue();      // Line1 - y1
        float l1x2 = (float)sliderL1X2.getValue();      // Line1 - x2
        float l1y2 = (float)sliderL1Y2.getValue();      // Line1 - y2
        
        float l2x1 = (float)sliderL2X1.getValue();      // Line2 - x1
        float l2y1 = (float)sliderL2Y1.getValue();      // Line2 - y1
        float l2x2 = (float)sliderL2X2.getValue();      // Line2 - x2
        float l2y2 = (float)sliderL2Y2.getValue();      // Line2 - y2
        
        // Vector for extension line
        Vector2 v = new Vector2();      // direction Vector
        Vector2 p ;

        // --------------------- Line 1 ----------------------------------
        // Line 1 - Point 1
        point1a.setCenterX(centerX + (coordRatio * l1x1));
        point1a.setCenterY(centerY + (coordRatio * -l1y1));
        
        // Line 1 - Point 2
        point1b.setCenterX(centerX + (coordRatio * l1x2));
        point1b.setCenterY(centerY + (coordRatio * -l1y2));
        
        // draw line 1 between 2 Vectors : // draw line 2 between 2 Vectors : a--------------b
        line1a.setStartX(point1a.getCenterX());
        line1a.setStartY(point1a.getCenterY());
        line1a.setEndX(point1b.getCenterX());
        line1a.setEndY(point1b.getCenterY());
       
        // draw 2 side-lines (extended from each of a and b) : ------------a-----------b--------------
        v.set(l1x2 - l1x1, l1y2 - l1y1);
        v.normalize();
        p = v.clone().scale(50).add(new Vector2(l1x2, l1y2));
        line1b.setStartX(point1b.getCenterX());
        line1b.setStartY(point1b.getCenterY());
        line1b.setEndX(p.x * coordRatio + centerX);
        line1b.setEndY(-p.y * coordRatio + centerY);
 
        // draw line extended 
        p = v.clone().scale(-50).add(new Vector2(l1x1, l1y1));
        line1c.setStartX(point1a.getCenterX());
        line1c.setStartY(point1a.getCenterY());
        line1c.setEndX(p.x * coordRatio + centerX);
        line1c.setEndY(-p.y * coordRatio + centerY);       

        // --------------------- Line 2 ----------------------------------
        // Line 2 - Point 1
        point2a.setCenterX(centerX + (coordRatio * l2x1));
        point2a.setCenterY(centerY + (coordRatio * -l2y1));
        
        // Line 2 - Point 2
        point2b.setCenterX(centerX + (coordRatio * l2x2));
        point2b.setCenterY(centerY + (coordRatio * -l2y2));
        
        // draw line 2 between 2 Vectors : a--------------b
        line2a.setStartX(point2a.getCenterX());
        line2a.setStartY(point2a.getCenterY());
        line2a.setEndX(point2b.getCenterX());
        line2a.setEndY(point2b.getCenterY());
       
        // draw 2 side-lines (extended from each of a and b) : ------------a-----------b--------------
        v.set(l2x2 - l2x1, l2y2 - l2y1);
        v.normalize();
        p = v.clone().scale(50).add(new Vector2(l2x2, l2y2));
        line2b.setStartX(point2b.getCenterX());
        line2b.setStartY(point2b.getCenterY());
        line2b.setEndX(p.x * coordRatio + centerX);
        line2b.setEndY(-p.y * coordRatio + centerY);
 
        p = v.clone().scale(-50).add(new Vector2(l2x1, l2y1));
        line2c.setStartX(point2a.getCenterX());
        line2c.setStartY(point2a.getCenterY());
        line2c.setEndX(p.x * coordRatio + centerX);
        line2c.setEndY(-p.y * coordRatio + centerY);       
      
        //----------------Find intersaction point---------------------------
         
        // set two lines to model
        model.setLine1(l1x1, l1y1, l1x2, l1y2);
        model.setLine2(l2x1, l2y1, l2x2, l2y2);
        
        // find intersenction vector2 
        Vector2 ipoint = model.getIntersectPoint();
        
        // print to label ( 0.000, 0.000 )
        if(ipoint.y == -0)      // prevent (0, -0)
            ipoint.y = 0;
        
        // set label 
        lblIntersect.setText( "( " + String.format("%.3f", ipoint.x) + ", " + String.format("%.3f", ipoint.y) + " )");
        
        // move the inersect point
        pointIntersect.setCenterX( ipoint.x * coordRatio + centerX );
        pointIntersect.setCenterY( (-ipoint.y * coordRatio) + centerY);
    
        //----------------Find Line equation-------------------------------
        // get lineEquatiion string and set in label  
        lblLine1.setText(model.getLine1Equation());     // line1
        lblLine2.setText(model.getLine2Equation());     // line2
    }

    // Event Handle - Reset button
    @FXML
    private void handleButtonReset(ActionEvent event) {
        
        // reset for Line1 
        sliderL1X1.valueProperty().set(-5.0);
        sliderL1Y1.valueProperty().set(5.0);
        sliderL1X2.valueProperty().set(5.0);
        sliderL1Y2.valueProperty().set(-5.0);
        
        // reset for Line2
        sliderL2X1.valueProperty().set(-5.0);
        sliderL2Y1.valueProperty().set(-5.0);
        sliderL2X2.valueProperty().set(5.0);
        sliderL2Y2.valueProperty().set(5.0);
       
    }
}
