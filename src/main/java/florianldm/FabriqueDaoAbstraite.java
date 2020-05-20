package florianldm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabriqueDaoAbstraite {

    /**
     * Permet la connexion à la BD.
     * @return c connexion à la BD.
     */
    public Connection connectBD() {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:derby:bd; create = true");
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return c;
    }

    /**
     * Selon la valeur donnée en parametres.
     * @return une fabrique CRUD ou JDBC.
     */
    public Object crudOrJDBC(final String type) {
        Connection bd = connectBD();
        if (bd != null) {
            if (type.equals("jdbc")) {
                return new FabriqueDaoJDBC(bd);
            }
            else if (type.equals("crud")) {
                return new FabriqueDao();
            }
        }
        return null;
    }


}
