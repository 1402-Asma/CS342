/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author asmaa
 */
public class Search extends JFrame {
    private JLabel l1, l2;
    private JTextField t1, t2;
    private JTextArea a1;
    private JButton b1, b2, b3;
    
    public Search(){
        super("Search");
        this.setLocation(540,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Border border1 = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);//color border
        
        l1 = new JLabel("File to Search:");
        t1 = new JTextField(30);
        t1.setBorder(border1);
        JPanel p1= new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1.add(l1);
        p1.add(t1);
        
        l2 = new JLabel("Keywords");
        t2 = new JTextField(20);
        t2.setBorder(border1);
        b1 = new JButton("Search");
        b1.addActionListener(new SearchButton());
        b2 = new JButton("Clear");
        b2.addActionListener(new ClearButton());
        b3 = new JButton("Cancel"); 
        b3.addActionListener(new CancelButton());
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2.add(l2);
        p2.add(t2);
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        
        a1 = new JTextArea(20,50);
        a1.setBorder(border1);
        JPanel p3= new JPanel(new FlowLayout(FlowLayout.CENTER));
        p3.add(a1);
        
        JPanel p = (JPanel)this.getContentPane();
        Color m= new Color(165,127,178);
        p.setBackground(m);
        p.add(p1,BorderLayout.NORTH);
        p.add(p2,BorderLayout.CENTER);
        p.add(p3,BorderLayout.SOUTH);
        
        this.pack();
        this.setVisible(true);
        
    }
    
    class SearchButton implements ActionListener{
         @Override
        public void actionPerformed(ActionEvent e) {
            if (t1.getText().equals(""))
                 JOptionPane.showMessageDialog(null , "Enter the FILE NAME", "Error",JOptionPane.WARNING_MESSAGE);
            else if(t2.getText().equals(""))
                 JOptionPane.showMessageDialog(null , "Enter the KEYWORD", "Error",JOptionPane.WARNING_MESSAGE);
            else{
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(t1.getText()+".txt"));
                    String line;
                    while ((line = reader.readLine()) != null)
                    {
                        String[] parts = line.split(" ");
                        for(int i = 0; i < parts.length;i++){
                            if(t2.getText().equals(parts[i])){
                                a1.append(line+"\n");
                           }
                        } 
                    }
                    if(a1.getText().equals(""))
                        JOptionPane.showMessageDialog(null ,"\""+t1.getText()+"\" Not found :(\nTry again", "Error",JOptionPane.ERROR_MESSAGE);
                    reader.close();
                }
                catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null , "File Not found", "Error",JOptionPane.ERROR_MESSAGE);
                }
                catch (IOException ex){
                    JOptionPane.showMessageDialog(null , "Input Output Exception", "Error",JOptionPane.ERROR_MESSAGE);
                }
            }   
        }
    }
    
    //***************************************************//
    class ClearButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            t1.setText("");
            t2.setText("");
            a1.setText("");
        }
    }
    
    //***************************************************//
    class CancelButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){  
            dispose();
        }
    }  
}
