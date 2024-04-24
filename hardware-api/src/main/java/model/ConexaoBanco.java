package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    public static Connection con() throws SQLException{
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/medtech", "root", "kauanunes");
        } catch (SQLException e){
            System.out.println("Deu erro aqui");
            return null;
        }

    }

}
