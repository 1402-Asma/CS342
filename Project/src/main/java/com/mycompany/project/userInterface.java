/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author asmaa
 */
public class userInterface extends JFrame{
    private JLabel l1;
    private JTextField t1;
    private JButton b1, b2, b3;
    private JTextArea t2;
    private JMenuBar mb;
    private JMenu m1;
    
    public userInterface(){
        super("Text Editor");
        this.setLocation(540,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*mb = new JMenuBar();
        m1 = new JMenu("FILE");
        mb.add(m1);*/
        
        Border border = BorderFactory.createLineBorder(Color.pink, 2);//color border
        //Title input field 
        l1 = new JLabel("Title");
        t1 = new JTextField(25);
        t1.setBorder(border);
        JPanel p1= new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1.add(l1);
        p1.add(t1);
        
        //Text Area Content
        t2 = new JTextArea(20,50);
        t2.setBorder(border);
        JPanel p2= new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2.add(t2);
        
        //Buttons
        b1 = new JButton("Save");
        b2 = new JButton("Search");
        b3 = new JButton("Clear");
        JPanel p3= new JPanel(new FlowLayout(FlowLayout.CENTER));
        p3.add(b1);
        p3.add(b2);
        p3.add(b3);
        
        //Containar panel for mange p panel in the frame
        JPanel p = (JPanel)this.getContentPane();
        p.add(p1,BorderLayout.NORTH);
        p.add(p2,BorderLayout.CENTER);
        p.add(p3,BorderLayout.SOUTH);
        
        this.pack();
        this.setVisible(true);
        
        b1.addActionListener(new SaveButton());
        b2.addActionListener(new SearchButton());
        b3.addActionListener(new ClearButton());
    }
    
    //***************************************************//
    class SaveButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int i=0;
            //Check entered title
            if (t1.getText().equals("")){
                JOptionPane.showMessageDialog(null , "Enter FILE NAME", "Error",JOptionPane.WARNING_MESSAGE);
                i = 1;
            }
            //Check entered content
            else if(t2.getText().equals(""))
                i = JOptionPane.showConfirmDialog(null , "No content\nDo you want to continue saving?", "Error",JOptionPane.YES_NO_OPTION);
            
            if( i == 0){
                Write r = new Write(t1.getText(),t2.getText());
                r.processing();
            }  
        }
    }
    
    //***************************************************//
    class SearchButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
           Search s = new Search();
        }
    }
    
    //***************************************************//
    class ClearButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            t1.setText("");
            t2.setText("");
        }
    }
}
