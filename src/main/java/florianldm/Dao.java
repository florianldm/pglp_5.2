package florianldm;

import java.util.List;
import java.util.Optional;

/**
 * Interface pour DAO.
 * @param <T> type T.
 */
public interface Dao<T> {
    /** Méthode get.
     * @param id .
     * @return T.
     * */
    Optional<T> get(long id);
    /** Méthode getAll.
     * @return List de type T.*/
    List<T> getAll();
    /** Méthode save.
     * @param t de type T.
     */
    void save(T t);
    /** Méthode update.
     * @param t de type T.
     * @param params tableau de param.*/
    void update(T t, String[] params);
    /** Méthode delete.
     * @param t de type T.*/
    void delete(T t);
}
