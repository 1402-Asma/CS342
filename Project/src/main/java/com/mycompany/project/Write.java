/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author asmaa
 */
public class Write {
    private String title, TextArea;
                 
    public Write(String t,String a){
        
            this.title = t+".txt"; 
            this.TextArea= a;
    }
                 
    public void processing(){
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(title));
            file.write(TextArea);
            file.close();
            JOptionPane.showMessageDialog ( null , "File saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);   
        }
        catch(IOException e){
            JOptionPane.showMessageDialog ( null , "File system errors", "Error", JOptionPane.ERROR_MESSAGE);           
        }
                 
    }
    
}
