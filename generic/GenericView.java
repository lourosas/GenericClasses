/********************************************************************
********************************************************************/
package rosas.lou.generic;

import java.lang.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;
import myclasses.*;
import rosas.lou.generic.*;

/*
*/
public class GenericView extends GenericJFrame{

   private short width;
   private short height;

   private ButtonGroup buttonGroup;
   private ButtonGroup menuItemGroup;
   private GenericViewController genericViewController;

   {
      width  = 400;
      height = 170;
      buttonGroup = null;
      menuItemGroup = null;
      genericViewController = null;
   }

   /////////////////////////Constructors/////////////////////////////
   /*
   Contstructor of no arguments
   */
   public GenericView(){
      this("",null);
   }

   /*
   */
   public GenericView(String title){
      this(title, null);
   }

   /*
   */
   public GenericView(String title, Object controller){
      super(title);
      //Set up the GUI
      this.setUpGUI(controller);
      this.setResizable(false);
      this.setVisible(true);
   }

   ///////////////////Public Methods/////////////////////////////////
   ///////////////////Private Methods////////////////////////////////
   /*
   */
   private JPanel setCenterPanel(Object controller){
      final int TEXTLENGTH = 30;
      JPanel centerPanel   = new JPanel();
      JTextField textField = new JTextField();
      try{
         centerPanel.setBorder(BorderFactory.createEtchedBorder());
         textField.setName("Text Field");
         textField.setColumns(TEXTLENGTH);
         textField.addActionListener((ActionListener)controller);
         centerPanel.add(textField);
      }
      catch(ClassCastException cce){
         cce.printStackTrace();
      }
      finally{
         return centerPanel;
      }
   }

   /*
   */
   private JPanel setNorthPanel(Object controller){
      JPanel northPanel    = new JPanel();
      String genericString = new String("A Generic View");
      JLabel genericLabel  = 
                    new JLabel(genericString, SwingConstants.CENTER);
      //Set up the Border
      northPanel.setBorder(
                       BorderFactory.createEmptyBorder(10,10,10,10));
      northPanel.add(genericLabel);
      return northPanel;
   }

   /*
   */
   private JPanel setSouthPanel(Object controller){
      JPanel southPanel = new JPanel();
      southPanel.setBorder(BorderFactory.createEtchedBorder());
      southPanel.add(this.setUpButtonPanel(controller));

      return southPanel;
   }

   /*
   */
   private JPanel setUpButtonPanel(Object controller){
      JPanel buttonPanel = new JPanel();
      try{
         buttonPanel.setBorder(
                        BorderFactory.createEmptyBorder(10,10,10,1));
         this.buttonGroup      = new ButtonGroup();
         //Create the first button (a generic button)
         JButton genericButton = new JButton("Generic");
         genericButton.addActionListener((ActionListener)controller);
         genericButton.addKeyListener((KeyListener)controller);
         buttonPanel.add(genericButton);
         this.buttonGroup.add(genericButton);
      }
      catch(ClassCastException cce){
         cce.printStackTrace();
      }
      finally{
         return buttonPanel;
      }
   }

   /*
   */
   private JMenu setUpFileMenu(Object controller){
      JMenu file = new JMenu("File");
      try{
         file.setMnemonic(KeyEvent.VK_F);

         //Generic Menu Item
         JMenuItem generic = new JMenuItem("Generic", 'G');
         generic.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
                                              InputEvent.CTRL_MASK));
         generic.addActionListener((ActionListener)controller);
         this.menuItemGroup.add(generic);
         file.add(generic);

         //Add Separator
         file.addSeparator();

         //Quit Menu Item
         JMenuItem quit = new JMenuItem("Quit", 'Q');
         quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                                              InputEvent.CTRL_MASK));
         quit.addActionListener((ActionListener)controller);
         this.menuItemGroup.add(quit);
         file.add(quit);
      }
      catch(ClassCastException cce){
         cce.printStackTrace();
      }
      finally{
         return file;
      }
   }

   /*
   */
   private JMenuBar setUpMenuBar(Object controller){
      JMenuBar jmenuBar = new JMenuBar();

      this.menuItemGroup = new ButtonGroup();
      //Set up the File Menu
      jmenuBar.add(this.setUpFileMenu(controller));

      return jmenuBar;
   }

   /*
   */
   private void setUpGUI(Object controller){
      try{
         if(controller == null){
            this.genericViewController=new GenericViewController(this);
         }
         else{
            this.genericViewController =
                                 (GenericViewController)controller;
            this.genericViewController.view(this);
         }
         Container contentPane = this.getContentPane();
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         this.setSize(this.width, this.height);
         this.setLocation((int)((dim.getWidth() - this.width)/2),
                          (int)((dim.getHeight()- this.height)/2));
         contentPane.add(this.setNorthPanel(
                                         this.genericViewController),
                                         BorderLayout.NORTH);
         contentPane.add(this.setCenterPanel(
                                         this.genericViewController),
                                         BorderLayout.CENTER);
         contentPane.add(this.setSouthPanel(
                                         this.genericViewController),
                                         BorderLayout.SOUTH);
         this.setJMenuBar(this.setUpMenuBar(
                                        this.genericViewController));
      }
      catch(ClassCastException cce){
         cce.printStackTrace();
      }
   }
}

/*******************************************************************/
