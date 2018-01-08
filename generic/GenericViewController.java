/////////////////////////////////////////////////////////////////////
/*
*/
/////////////////////////////////////////////////////////////////////
package rosas.lou.generic;

import java.lang.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import rosas.lou.*;
import rosas.lou.generic.GenericView;

/*
*/
public class GenericViewController implements ActionListener,
KeyListener{

   private GenericView _view;
   private Object      _model;

   {
      _view  = null;
      _model = null;
   }

   ////////////////////////Constructors//////////////////////////////
   /*
   Constructor of no arguments
   */
   public GenericViewController(){}

   /*
   Constructor accepting the View
   */
   public GenericViewController(GenericView view){
      this.view(view);
   }

   /*
   Constructor accepting the view and the model
   */
   public GenericViewController(GenericView view, Object model){
      this.view(view);
      this.model(model);
   }

   //////////////////////Public Methds///////////////////////////////
   /*
   */
   public void model(Object model){
      this._model = model;
   }

   /*
   */
   public void view(GenericView view){
      this._view = view;
   }

   /////////Implementation of the Listener Interfaces////////////////
   /*
   */
   public void actionPerformed(ActionEvent e){
      Object o = e.getSource();
      //Let us try something different
      this.handleJButtonItem(o);
      this.handleJMenuItem(o);
      this.handleJTextFieldItem(o);
   }

   /*
   */
   public void keyPressed(KeyEvent k){
      JButton jButton = null;
      try{
         jButton = (JButton)k.getSource();
         if(k.getKeyCode() == KeyEvent.VK_ENTER){
            jButton.doClick();
         }
      }
      catch(ClassCastException cce){}
   }

   /*
   */
   public void keyReleased(KeyEvent k){}

   /*
   */
   public void keyTyped(KeyEvent k){}

   ////////////////////Private Methods///////////////////////////////
   /*
   */
   private void handleJButtonItem(Object source){
      JButton jButton = null;
      try{
         jButton = (JButton)source;
         System.out.println(jButton.getActionCommand());
      }
      catch(ClassCastException cce){
      }
   }

   /*
   */
   private void handleJMenuItem(Object source){
      JMenuItem jMenuItem = null;
      try{
         jMenuItem = (JMenuItem)source;
         String ac = jMenuItem.getActionCommand();
         System.out.println(ac);
         if(ac.equals("Generic")){
            //Do Something with the view
         }
         else{ //Quit should always be the last option
            System.out.println("Good Bye");
            System.exit(0);
         }
      }
      catch(ClassCastException cce){
      }
   }

   /*
   */
   private void handleJTextFieldItem(Object source){
      JTextField jTextField = null;
      try{
         jTextField = (JTextField)source;
         System.out.println(jTextField.getText());
      }
      catch(ClassCastException cce){
      }
   }
}
