package florianldm;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Groupe implements Composant, Serializable {

    /** Nom du groupe. */
    private String nom;
    /** Collection représentant le personnel du groupe. */
    private Collection c;

    /**
     * Constructeur.
     */
    public Groupe() {
        c = new ArrayList();
    }

    /**
     * Ajout d'un personnel dans le groupe.
     * @param comp composant.
     */
    public void add(final Composant comp) {
        c.add(comp);
    }

    /**
     * Suppression d'un personnel du groupe.
     * @param comp composant.
     * @return boolean.
     */
    public boolean remove(final Composant comp) {
        if (!c.isEmpty()) {
            c.remove(comp);
            return true;
        } else {
            return false;
        }
    }

    /** Parcours du groupe et affiche les membres. */
    @Override
    public void afficheNom() {
        System.out.println("Avec iterator: ");
        for (Iterator i = c.iterator(); i.hasNext();) {
            Object objet = i.next();
            Composant composant = (Composant) objet;
            composant.afficheNom();
        }
    }

    /**
     * Permet d'écrire l'objet dans un fichier: sérialisation.
     * @param file chemin vers le fichier.
     */
    public void serialize(final String file) {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de lire un objet de fichier: désérialisation.
     * @param file chemin vers le fichier.
     * @return g ,un groupe lu.
     */
    public Groupe deserialize(final String file) {
        Groupe g = null;
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            g = (Groupe) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return g;
    }

    /**
     * Retourne la liste des personnels.
     * @return liste.
     */
    public Collection getC() {
        return c;
    }

    /**
     * Setter nom du groupe.
     * @param nom1 à ajouter.
     */
    public void setNom(final String nom1) {
        this.nom = nom1;
    }
}
