/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.project;
import javax.swing.*;
import java.awt.*;
import java.awt.event. *;
import java.sql.*;

/**
 *
 * @author asmaa
 */
public class Project extends JFrame{
    private JLabel l1,l2;
    private JTextField t1;
    private JPasswordField ps1;
    private JButton b1,b2;
    private JPanel p;
    
    public Project(String title){
        super(title);
        this.setLocation(540,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //login
        l1 = new JLabel("Login");
        t1 = new JTextField(20);
        //t1.setForeground(Color.BLUE);
       
        //password
        l2 = new JLabel("Password");
        ps1 = new JPasswordField(20);
        JPanel p1= new JPanel(new BorderLayout());
        p1.setLayout(new GridLayout(2,2));
        //Color my = new Color(165,127,178);
        //p1.setBackground(my);
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(ps1);
        
        //enter and cancel button
        b1 = new JButton("Enter");
        b2 = new JButton("Cancel");
        b1.addActionListener(new EnterButton());
        b2.addActionListener(new CancelButton());
        JPanel p2= new JPanel(new FlowLayout(FlowLayout.CENTER));
        //Color my1 = new Color(141,215,207);
        //p2.setBackground(my1);
        p2.add(b1);
        p2.add(b2);
        
        //panel for manage the locatin of p1,p2
        JPanel p3= new JPanel();
        p3.setLayout(new GridLayout(2,1));
        p3.add(p1);
        p3.add(p2);
        
        //containar panel for mange p panel in the frame
        p = (JPanel)this.getContentPane();
        p.setLayout(new FlowLayout(FlowLayout.CENTER));
        p.add(p3);
        //Color my2 = new Color(52,37,69);
        //p.setBackground(my2);
        
        this.pack();
        this.setVisible(true);
        
    }
    
    //***************************************************//
    class EnterButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int a=0;
            try{
                Connection db = DriverManager.getConnection("jdbc:ucanaccess://project.accdb");
                Statement st = db.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM [project]");//return set of lines
               
                while(rs.next()){
                    
                    if((rs.getString(2).equals(t1.getText())) && (rs.getString(3).equals(ps1.getText()))){
                        if(rs.getString(4).equals("admin")){
                            adminInterface a1 = new adminInterface();a++;break;
                        }
                        else if(rs.getString(4).equals("user")){
                            userInterface u1= new userInterface();a++;break;
                        }  
                    } 
                }
                if(a==0){
                    JOptionPane.showMessageDialog ( null , "Check your login and passsword", "Input Error", JOptionPane.ERROR_MESSAGE );
                }
                db.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"SQL Error: " + ex.getMessage());
            }
        }     
    }
        
    //***************************************************//    
    class CancelButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            t1.setText("");
            ps1.setText("");
        } 
    }

    //***************************************************//
    public static void main(String[] args) {
        Project p1= new Project("Login page"); 
    }
}
