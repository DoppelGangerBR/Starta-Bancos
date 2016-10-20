/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alvaro
 */
public class EntidadeConexao {
   
    
    public Connection Conecta() throws SQLException{
        String url  = "jdbc:postgresql://localhost:5432/";
        String user = "postgres";
        String pass = "123";
        return DriverManager.getConnection(url,user,pass);   
    }
    public Connection Conecta2() throws SQLException{
        String url  = "jdbc:postgresql://localhost:5432/ordem_servico_db_teste";
        String user = "postgres";
        String pass = "123";       
        return DriverManager.getConnection(url,user,pass);   
    }
}
