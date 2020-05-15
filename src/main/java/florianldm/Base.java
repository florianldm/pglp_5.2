package florianldm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Création BD.
 */
public abstract class Base {

    /**
     * Gestion de la base de données.
     */
    public static void create() throws SQLException {
        DriverManager.getConnection("jdbc:derby:bd; create = true");
    }

    /**
     * Suppression des tables de la BD.
     * @param c la connexion à la BD.
     */
    public static void drop(final Connection c) {
        try {
            Statement s = c.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
