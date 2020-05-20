package florianldm;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;

public class GroupeDaoJDBC extends DaoAbstrait {

    /**
     * Constructeur.
     * @param c connexion à la BD.
     */
    public GroupeDaoJDBC(final Connection c) {
        super(c);
    }

    /**
     * Cherche un groupe dans la BD avec un nom donné.
     * Pour chaque nom de personne dans le groupe on recherche ses infos et on .
     * reconstruit la liste de personnels.
     * @param o groupe à chercher.
     * @return Groupe.
     */
    @Override
    public Object get(final Object o) {
        Groupe g = (Groupe) o;
        String nom_g = g.getNom();
        String nom_p = "";
        try {
            PreparedStatement requete = c.prepareStatement("SELECT * FROM Groupe WHERE nom = ?");
            requete.setString(1, nom_g);
            ResultSet r = requete.executeQuery(requete.toString());
            while (r != null){
                while (r.next()) {
                    nom_p = r.getString(2);
                    PreparedStatement requete2 = c.prepareStatement("SELECT * FROM Personnel WHERE nom = ?");
                    requete2.setString(1, nom_p);
                    ResultSet r2 = requete2.executeQuery(requete2.toString());
                    Personnel p = new Personnel.Builder(r2.getString(1), r2.getString(2), r2.getDate(3).toLocalDate()).build();
                    g.add(p);
                }
                if (requete.getMoreResults()) {
                    r = requete.getResultSet();
                }
                else {
                    r.close();
                    r = null;
                }
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return g;
    }

    /**
     * Permet d'insérer un groupe dans la BD.
     * Pour le groupe à insérer, on ajoute pour chaque personne .
     * du groupe un tuple nom groupe - nom personne.
     * @param o le groupe (pour le nom).
     * @return null.
     * @throws SQLException
     */
    @Override
    public Object save(final Object o) throws SQLException {
        Groupe g = (Groupe) o;
        Collection liste = g.getC();
        String nom_P = "";
        String nom_G = "";

        try {
            for (Iterator i = liste.iterator(); i.hasNext();) {
                PreparedStatement requete = c.prepareStatement("INSERT INTO Groupe VALUES (?, ?)");
                Object objet = i.next();
                Personnel p = (Personnel) objet;
                nom_P = p.nom();
                nom_G = g.getNom();
                requete.setString(1, nom_G);
                requete.setString(2, nom_P);
                requete.execute();
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;
    }

    /**
     * Mettre à jour le nom d'un groupe.
     * @param o le groupe.
     * @return null.
     * @throws SQLException .
     */
    @Override
    public Object update(final Object o) throws SQLException {
        Groupe g = (Groupe) o;
        String nom_G = g.getNom();

        try {
            PreparedStatement requete = c.prepareStatement("UPDATE Groupe SET nom = ? WHERE nom = ?");
            requete.setString(1, nom_G);
            requete.setString(2, nom_G);
            ResultSet r = requete.executeQuery(requete.toString());
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;
    }

    /**
     * Suppression d'un groupe dans la table.
     * @param o le groupe à supprimer.
     * @return null.
     * @throws SQLException .
     */
    @Override
    public Object delete(final Object o) throws SQLException {
        Groupe g = (Groupe) o;
        String nom_G = g.getNom();
        try {
            PreparedStatement requete = c.prepareStatement("DELETE FROM Groupe WHERE nom = ?");
            requete.setString(1, nom_G);
            ResultSet r = requete.executeQuery(requete.toString());
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;
    }
}
