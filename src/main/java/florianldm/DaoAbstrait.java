package florianldm;

import java.sql.Connection;

public abstract class DaoAbstrait<T> {
    /** Pour connexion à la BD. */
    private Connection c;

    /**
     * Constructeur.
     * @param c1 connexion à la BD.
     */
    public DaoAbstrait(final Connection c1) {
        this.c = c1;
    }

    /**
     * Méthode get.
     * @param t à obtenir.
     * @return T.
     */
    public abstract T get(T t);

    /**
     * Méthode save.
     * @param t à sauvegarder.
     * @return T.
     */
    public abstract T save(T t);

    /**
     * Méthode update.
     * @param t à mettre à jour.
     * @return T.
     */
    public abstract T update(T t);

    /**
     * Méthode delete.
     * @param t à supprimer.
     * @return T.
     */
    public abstract  T delete(T t);

}
