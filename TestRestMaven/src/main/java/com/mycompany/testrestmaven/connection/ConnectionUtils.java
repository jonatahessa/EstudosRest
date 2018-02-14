/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testrestmaven.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jonat
 */
public class ConnectionUtils {

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection(
                    "jdbc:mysql://tartarugas.mysql.uhserver.com/tartarugas?useTimezone=true&serverTimezone=UTC", "turtle1", "br@sil20");
        } catch (SQLException ex) {
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
            System.err.println("SQLException: ");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } catch (Exception e) {
            System.err.println("Problemas ao tentar conectar com o banco de dados: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
