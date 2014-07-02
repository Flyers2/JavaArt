package art;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import javax.swing.*;
import java.io.Serializable;

public class Art extends JFrame implements Serializable {
    DrawingPanel panel;
    
    public Art() {
        panel = new DrawingPanel();
       
        add(panel);
        
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        //JLabel title = new JLabel("art", JLabel.CENTER);
        //add(title, BorderLayout.NORTH);
        
        JMenuBar menus = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        
         JMenuItem save = new JMenuItem("save");
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 save();
            }
        });
        JMenuItem load = new JMenuItem("load");       
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 load();
            }
        });
        JMenuItem undo = new JMenuItem("undo");        
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.undo();
            }
        });
        JMenuItem redo = new JMenuItem("redo");        
        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 panel.redo();
            }
        });
        file.add(save);
        file.add(load);
        edit.add(undo);
        edit.add(redo);
        menus.add(file);
        menus.add(edit);
        this.setJMenuBar(menus);
       
    }
    
    public static void main(String[] args) {
           new Art();
    }
    
    public void  save(){
        try  
      {  
       FileOutputStream fileOut = new FileOutputStream("drawings.ser");  
       ObjectOutputStream outStream = new ObjectOutputStream(fileOut);  
       outStream.writeObject(panel);  
       outStream.close();  
       fileOut.close();  
      }catch(IOException i)  
      {  
       i.printStackTrace();  
      }  
    }

    public void load() {
         try  
       {  
          FileInputStream fileIn =new FileInputStream("drawings.ser");  
          ObjectInputStream in = new ObjectInputStream(fileIn);  
          panel = (DrawingPanel) in.readObject();  
          in.close();  
          fileIn.close(); 
          add(panel);
          panel.repaint();
       }catch(IOException i)  
       {  
          i.printStackTrace();  
          return;  
       }catch(ClassNotFoundException c)  
       {  
          System.out.println("file not found");  
          c.printStackTrace();  
          return;  
       }  
    }
    
    
    
    
}
