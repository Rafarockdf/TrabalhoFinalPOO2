/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafam
 */
//Lembrar de usar try catch
public class testeConectarbanco {
    
    public static void main(String[] args) throws SQLException{
        Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Trabalho_Final_BD2","postgres","otavio2004");
        if (conexao != null){
            System.out.println("Certo");
            Statement stm = conexao.createStatement();
            String query = "select * from animal";
            ResultSet result = stm.executeQuery(query);
            while(result.next()){
                System.out.println("Info: "+result.getString("nome"));
            }
            
        }
        
    }
    
}
