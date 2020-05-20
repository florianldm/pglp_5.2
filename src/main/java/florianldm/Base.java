package florianldm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Création BD.
 */
public abstract class Base {
    /** Connexion avec BD. */
    private static Connection c;

    /**
     * Gestion de la base de données.
     * Etablissement de la connexion.
     */
    public static void create() throws SQLException {
        try {
            c = DriverManager.getConnection("jdbc:derby:bd; create = true");
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Suppression des tables de la BD.
     * @param c la connexion à la BD.
     */
    public static void drop(final Connection c) {
        try {
            Statement s = c.createStatement();
            dropPersonnel(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime la table Personnel.
     * @param s le statement.
     * @throws SQLException .
     */
    private static void dropPersonnel(final Statement s) throws SQLException {
        try {
            s.execute("DROP TABLE Personnel");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Création table Personnel.
     * @param c connexion à la BD.
     * @throws SQLException .
     */
    private static void createPersonnel(final Connection c) throws SQLException {
        try {
            Statement s = c.createStatement();
            s.execute("CREATE TABLE Personnel ('Nom VARCHAR(40), Prenom VARCHAR(40), Naissance DATE'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
