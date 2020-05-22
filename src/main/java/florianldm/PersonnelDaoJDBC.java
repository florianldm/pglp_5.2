package florianldm;

import java.sql.*;
import java.time.ZoneId;

public class PersonnelDaoJDBC extends DaoAbstrait {

    /**
     * Constructeur.
     * @param c connexion à la BD.
     */
    public PersonnelDaoJDBC(final Connection c) {
        super(c);
    }

    /**
     * Retrouve un personnel avec un nom.
     * @param o le personnel.
     * @return le personnel trouvé.
     */
    @Override
    public Object get(final Object o) {
            Personnel p = (Personnel) o;
            Personnel p1 = null;
            String nomp = p.nom();
            try {
                PreparedStatement requete = c.prepareStatement(
                        "SELECT * FROM Personnel WHERE nom = ?");
                requete.setString(1, nomp);
                ResultSet r = requete.executeQuery(requete.toString());
                p1 = new Personnel.Builder(r.getString(1),
                        r.getString(2),
                        r.getDate(3).toLocalDate()).build();
            } catch (SQLException s) {
                s.printStackTrace();
            }
            return p1;
    }

    /**
     * Ajoute un personnel dans la BD.
     * @param o le personnel à ajouter.
     * @return null.
     */
    @Override
    public Object save(final Object o) {
        Personnel p = (Personnel) o;
        String nomp = p.nom();
        try {
            PreparedStatement requete = c.prepareStatement(
                    "INSERT INTO Personnel VALUES (?, ?, ?)");
            requete.setString(1, nomp);
            requete.setString(2, p.getPrenom());
            Date d = (Date) Date.from(p.getNaissance().
                    atStartOfDay(ZoneId.systemDefault()).toInstant());
            requete.setDate(3, d);
            ResultSet r = requete.executeQuery(requete.toString());
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;
    }

    /**
     * Mettre à jour un personnel dans la BD.
     * @param o le personnel.
     * @return null.
     */
    @Override
    public Object update(final Object o) {
        Personnel p = (Personnel) o;
        String nomp = p.nom();
        try {
            PreparedStatement requete = c.prepareStatement(
                    "UPDATE Personnel SET Nom = ?, Prenom = ?, "
                            + "Naissance = ? WHERE nom = ?");
            requete.setString(1, nomp);
            requete.setString(2, p.getPrenom());
            Date d = (Date) Date.from(p.getNaissance().
                    atStartOfDay(ZoneId.systemDefault()).toInstant());
            requete.setDate(3, d);
            requete.setString(4, nomp);
            ResultSet r = requete.executeQuery(requete.toString());
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;
    }

    /**
     * Supprime un personnel.
     * @param o le personnel à supprimer.
     * @return null.
     */
    @Override
    public Object delete(final Object o) {
        Personnel p = (Personnel) o;
        String nomp = p.nom();
        try {
            PreparedStatement requete = c.prepareStatement(
                    "DELETE FROM Personnel WHERE Nom = ? AND Prenom = ?");
            requete.setString(1, nomp);
            requete.setString(2, p.getPrenom());
            ResultSet r = requete.executeQuery(requete.toString());
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;
    }
}
