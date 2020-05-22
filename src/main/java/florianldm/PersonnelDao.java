package florianldm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class PersonnelDao implements Dao<Personnel> {

    /** Liste de personnels. */
    private List<Personnel> personnels = new ArrayList<Personnel>();

    /**
     * Getter.
     * @param id .
     * @return optional type Personnel.
     */
    @Override
    public Optional<Personnel> get(final long id) {
        return Optional.empty();
    }

    /**
     * Retourne Liste de personnels.
     * @return liste.
     */
    @Override
    public List<Personnel> getAll() {
        return personnels;
    }

    /**
     * Ajout de personnel.
     * @param personnel .
     */
    @Override
    public void save(final Personnel personnel) {
        personnels.add(personnel);
    }

    /**
     * Mise à jour Personnel.
     * @param personnel avec les maj appliquées .
     * @param args Nom du personnel à mettre à jour args[0].
     */
    @Override
    public void update(final Personnel personnel, final String[] args) {
        System.out.println("Maj Personnel");
        int index = 0;
        int ok = 0;
        for (Iterator i = this.personnels.iterator(); i.hasNext();) {
            Object objet = i.next();
            Personnel p = (Personnel) objet;
            if (p.nom().equals(args[0])) {
                ok = 1;
                break;
            }
            index++;
        }

        if (ok == 1) {
            personnels.remove(index);
            personnels.add(personnel);
        }
    }

    /**
     * Supprime un personnel.
     * @param personnel .
     */
    @Override
    public void delete(final Personnel personnel) {
        personnels.remove(personnel);
    }
}
