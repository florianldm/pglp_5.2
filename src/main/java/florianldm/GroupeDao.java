package florianldm;

import java.util.*;

public class GroupeDao implements Dao<Groupe> {
    /** Liste de groupes. */
    private List<Groupe> groupes = new ArrayList<>();

    /**
     * Méthode get.
     * @param id .
     * @return optionnal de type groupe.
     */
    @Override
    public Optional<Groupe> get(final long id) {
        return Optional.ofNullable(groupes.get((int) id));
    }

    /**
     * Retourne la liste de groupes.
     * @return liste.
     */
    @Override
    public List<Groupe> getAll() {
        return groupes;
    }

    /**
     * Ajouter un groupe.
     * @param groupe Groupe.
     */
    @Override
    public void save(final Groupe groupe) {
        groupes.add(groupe);
    }

    /**
     * Mise à jour.
     * @param groupe le groupe.
     * @param params tab de parametres.
     */
    @Override
    public void update(final Groupe groupe, final String[] params) {
        //groupe.setNom(Objects.requireNonNull(params[0], "Le nom ne peut pas être nul"));
        //groupes.add(groupe);
        int index = 0;
        int ok = 0;
        for (Iterator i = this.groupes.iterator(); i.hasNext();) {
            Object objet = i.next();
            Groupe g = (Groupe) objet;
            if(g.getNom().equals(params[0])) {
                ok = 1;
                break;
            }
            index ++;
        }

        if(ok == 1) {
            groupes.remove(index);
            groupes.add(groupe);
        }
    }

    /**
     * Supprime un groupe.
     * @param groupe .
     */
    @Override
    public void delete(final Groupe groupe) {
        groupes.remove(groupe);
    }
}
