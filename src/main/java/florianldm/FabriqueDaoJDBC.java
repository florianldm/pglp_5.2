package florianldm;

import java.sql.Connection;

public class FabriqueDaoJDBC {
    /** Connexion vers la BD. */
    private static Connection c;

    /**
     * Constructeur FabriqueDaoJDBC.
     * @param c1 connexion vers BD.
     */
    public FabriqueDaoJDBC(final Connection c1) {
        c = c1;
    }

    /**
     * Retourne un PersonnelDaoJDBC.
     * @return .
     */
    public static PersonnelDaoJDBC getPersonnelDao() {
        return new PersonnelDaoJDBC(c);
    }

    /**
     * Retourne un GroupeDaoJDBC.
     * @return .
     */
    public static GroupeDaoJDBC getGroupeDao() {
        return new GroupeDaoJDBC(c);
    }


}


