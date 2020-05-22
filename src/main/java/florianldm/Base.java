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
     * @param c1 la connexion à la BD.
     */
    public static void drop(final Connection c1) {
        try {
            Statement s = c1.createStatement();
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
    public static void dropPersonnel(final Statement s) throws SQLException {
        try {
            s.execute("DROP TABLE Personnel");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Création table Personnel.
     * @param c1 connexion à la BD.
     * @throws SQLException .
     */
    public static void createPersonnel(final Connection c1)
            throws SQLException {
        try {
            Statement s = c1.createStatement();
            s.execute("CREATE TABLE Personnel ('Nom VARCHAR(40),"
                    + "Prenom VARCHAR(40), Naissance DATE'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Suppression table Groupe.
     * @param s le statement.
     * @throws SQLException .
     */
    public static void dropGroupe(final Statement s) throws SQLException {
        try {
            s.execute("DROP TABLE Groupe");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Création table Groupe.
     * @param c1 connexion à la BD.
     * @throws SQLException .
     */
    public static void createGroupe(final Connection c1) throws SQLException {
        try {
            Statement s = c1.createStatement();
            s.execute("CREATE TABLE Groupe "
                    + "('Nom VARCHAR(40), Nom_P VARCHAR(40)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
