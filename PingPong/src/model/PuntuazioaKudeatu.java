/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author moneo.asier
 */
public class PuntuazioaKudeatu {
    
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:db/Puntuazioa.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    
    public void partidaGehitu(int jokalari1, int jokalari2) {
        String sql = "INSERT INTO Puntuazioa(jokalari1,jokalari2) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, jokalari1);
            pstmt.setInt(2, jokalari2);
            pstmt.executeUpdate();
            System.out.println("\nPartida gehitu da.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
   public void terminoakImprimatu() {
        String sql = "SELECT partidaid, jokalari1, jokalari2 FROM Puntuazioa";

        try (Connection conn = this.connect();
               PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
           
            // loop through the result set
            while (rs.next()) {
              
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } 
    
    
}
