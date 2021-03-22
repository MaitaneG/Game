/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.sql.DriverManager;

/**
 *
 * @author asier
 */
public class PuntuazioarenTableModela extends AbstractTableModel {
    private ArrayList<Integer> jokalari2 = new ArrayList<>();
    private ArrayList<Integer> jokalari1 = new ArrayList<>();
    private ArrayList<Integer> idak = new ArrayList<>();
    private String[] zutabeIzenak = {"Partida Id","Jokalaria 1", "Jokalaria 2"};

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:db/Partidak.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public PuntuazioarenTableModela() {

        String sql = "SELECT partidaid, jokalari1, jokalari2 FROM Puntuazioa";

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                idak.add(rs.getInt("partidaid"));
                jokalari1.add(rs.getInt("jokalari1"));
                jokalari2.add(rs.getInt("jokalari2"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public int getRowCount() {
        return idak.size();
    }

    @Override
    public String getColumnName(int col) {
        return zutabeIzenak[col];
    }

    @Override
    public int getColumnCount() {
        return zutabeIzenak.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return idak.get(row);
            case 1:
                return jokalari1.get(row);
            default:
                return jokalari2.get(row);
        }

    }

}
