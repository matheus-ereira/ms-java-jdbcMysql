/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testes.jdbcmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author matheus
 */
public class ProjectApplication {

    public static void main(String[] args) {
        //Por questões de segurança, o correto é utilizar variaveis no application properties para essas conexões de banco.
        //Mas para fins de teste, irei deixar as configurações hard code.
        String JdbcURL = "jdbc:mysql://localhost:3306/sms";
        String Username = "root";
        String password = "root";
        
        Connection con;
        PreparedStatement pstmt;
        ResultSet rst;
        
        String myQuery = "select Id,Name,Type from Client";
        
        try {
            //Executa conexão com o banco
            con = DriverManager.getConnection(JdbcURL, Username, password);
            pstmt = con.prepareStatement(myQuery);
            //Executa select
            rst = pstmt.executeQuery();
            System.out.println("Id\t\tClient_Name\t\tType");
            //Mostra o resultado na tela
            while (rst.next()) {
                if (rst.getString(3) != null && rst.getString(3).equals("VIP")) {
                    System.out.print(rst.getInt(1));
                    System.out.print("\t\t" + rst.getString(2));
                    System.out.print("\t\t\t" + rst.getString(3));
                    System.out.println();
                }
            }
            
            con.close();
        } catch (SQLException exec) {
            exec.printStackTrace();
        }
    }
}
