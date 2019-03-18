package com.example.infraestructure.util;

import javax.swing.JOptionPane;

public class JOptionPaneFactory {

    public static void Error(String excepcion, String titulo) {
        
        JOptionPane.showMessageDialog(null, excepcion, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static void Message(String message, String titulo) {

        JOptionPane.showMessageDialog(null, message, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void Alert(String message, String titulo) {

        JOptionPane.showMessageDialog(null, message, titulo, JOptionPane.WARNING_MESSAGE);
    }
    
    public static int Confirm(String message, String titulo) {

        return JOptionPane.showConfirmDialog(null, message, titulo, JOptionPane.INFORMATION_MESSAGE);  
    }
    
}
