package florianldm;

public class FabriqueDao {

    /**
     * Méthode retournant un GroupeDao.
     * @return GroupeDao.
     */
    public static Dao<Groupe> getGroupeDao() {
        return new GroupeDao();
    }

    /**
     * Méthode retournant un PersonnelDao.
     * @return PersonnelDao.
     */
    public static Dao<Personnel> getPersonnelDao() {
        return new PersonnelDao();
    }
}
