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
public class adminInterface extends JFrame{
    private JLabel l1;
    private JTextField t1;
    private JButton b1, b2, b3,b4;
    private JTextArea t2;
    
    public adminInterface(){
        super("Text Editor");
        this.setLocation(540,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Border border = BorderFactory.createLineBorder(Color.YELLOW, 2);//color border
         
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
        b4 = new JButton("Statistics");
        JPanel p3= new JPanel(new FlowLayout(FlowLayout.CENTER));
        p3.add(b1);
        p3.add(b2);
        p3.add(b3);
        p3.add(b4);
        
        //Containar panel for mange p panel in the frame
        JPanel p = (JPanel)this.getContentPane();
        p.add(p1,BorderLayout.NORTH);
        p.add(p2,BorderLayout.CENTER);
        p.add(p3,BorderLayout.SOUTH);
        
        this.pack();
        this.setVisible(true);
        
        //Even buttons
        b1.addActionListener(new SaveButton());
        b2.addActionListener(new SearchButton());
        b3.addActionListener(new ClearButton());
        b4.addActionListener(new StatisticsButton());

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
        public void actionPerformed(ActionEvent e)
        {
           Search s = new Search();
        }
    }
    
    //***************************************************//
    class ClearButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            t1.setText("");
            t2.setText("");
        }
    }
    
    //***************************************************// 
    class StatisticsButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            openStatisticsFrame o = new openStatisticsFrame() ;
        }
    }
    
    //***************************************************//
    class openStatisticsFrame extends JFrame{
        private JLabel la;
        private JTextField t1;
        private JButton bu1,bu2,bu3;
       
        public openStatisticsFrame(){
        super("Statistics");
        this.setLocation(540,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Border border = BorderFactory.createLineBorder(Color.YELLOW, 2);//color border
        
        la = new  JLabel("File for Statistics");
        t1 = new JTextField(30);
        t1.setBorder(border);
        bu1 = new JButton("Calculate");
        bu2 = new JButton("Clear");
        bu3 = new JButton("Cancel");
        
        JPanel p = (JPanel)this.getContentPane();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        p.add(la);
        p.add(t1);
        p.add(bu1);
        p.add(bu2);
        p.add(bu3);
        
        this.pack();
        this.setVisible(true);
        
        bu1.addActionListener(new CalculateButton());
        bu2.addActionListener(new Clear());
        bu3.addActionListener(new cancel());

       }
        
        //------------------------------------------//
        class Clear implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                t1.setText("");
            }
        }
        //------------------------------------------//
        class cancel implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
         }
         
         //------------------------------------------//
        class  CalculateButton implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                    BufferedReader reader = new BufferedReader(new FileReader(t1.getText()+".txt"));
                    String line;
                    int greaterThanEqual10 = 0;
                    int lessThan10 = 0;

                    while ((line = reader.readLine()) != null)
                    {
                        String[] parts = line.split(" ");                                     
                        int value = Integer.parseInt(parts[parts.length-1]);
                        if (value >= 10) 
                        {
                            greaterThanEqual10++;
                        }
                        else
                        {                  
                            lessThan10++;                                                              
                        }
                    }
                    reader.close();
                    // Display the results
                    String RR = "Number of lines with number >= 10 : "+greaterThanEqual10+ "\n" + "Number of lines with number < 10 :"+ lessThan10;
                    if (!RR.isEmpty())
                    {
                       JOptionPane.showMessageDialog(null, RR ,"Statistics Results", JOptionPane.INFORMATION_MESSAGE);
                    }                  
                }
                catch(FileNotFoundException ex){
                    JOptionPane.showMessageDialog(null , "File Not found", "Error",JOptionPane.ERROR_MESSAGE);
                }
                catch (IOException ex){
                    JOptionPane.showMessageDialog(openStatisticsFrame.this,"Error reading file: "+ ex.getMessage(),
                                      "File Read Error",JOptionPane.ERROR_MESSAGE);
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "No valid data found in the file","No Statistics Available", JOptionPane.WARNING_MESSAGE);
                }
            }         
        }
    }                
}
