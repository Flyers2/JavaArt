package art;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class DrawingPanel extends JPanel  {
    public ArrayList<ArrayList<custPoint>> allPoints = new ArrayList<ArrayList<custPoint>>();
    private ArrayList<custPoint> points;
    private Stack<ArrayList<custPoint>> undo = new Stack<ArrayList<custPoint>>();
    private Stack<ArrayList<custPoint>> redo = new Stack<ArrayList<custPoint>>();
    Point point;
    public DrawingPanel() {
         MouseAdapter mouseListener = new MouseAdapter() { 
            public void mousePressed(MouseEvent e) {
                System.out.println("pressed");
                points = new ArrayList<custPoint>();
                allPoints.add(points);
                custPoint point = new custPoint();
                point.custPoint(e.getX(), e.getY(),  2, "black");
                points.add(point);
                redo.clear();
                repaint();
            }
            public void mouseDragged(MouseEvent e) {
                System.out.println("dragged");
                custPoint point = new custPoint();
                point.custPoint(e.getX(), e.getY(),  2, "black");
                
                points.add(point);
                repaint();
            }
            public void mouseReleased(MouseEvent e) {
                //points.add(new Point(e.getX(), e.getY()));
                System.out.println("released");
            }
        };

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }
    
    public void paint(Graphics g) {
        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        
        for(ArrayList<custPoint> points : allPoints) {
            custPoint last = null;
            for(custPoint point : points) {
                if (last != null) {
                    g2.drawLine(last.x, last.y, point.x, point.y);
                }
                last = point;
               
            } 
        }
    }
    
    
    public void undo(){       
        undo.push(allPoints.remove(allPoints.size()-1));
        System.out.println(undo.size());
    }
    
    public void redo(){
        allPoints.add(undo.pop());
        repaint();
    }
}
