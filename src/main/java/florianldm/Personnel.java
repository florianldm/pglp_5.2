package florianldm;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public final class Personnel implements Composant, Serializable {
    /** Nom. */
    private final String nom;
    /** Prenom. */
    private final String prenom;
    /** Naissance. */
    private final LocalDate naissance;
    /** Liste de téléphones. */
    private final ArrayList<String> tel;

    /**
     * Constructeur.
     * @param nom1 nom perso.
     * @param prenom1 prenom perso.
     * @param naissance1 date de naissance.
     * @param tel1 liste de tels.
     */
    public Personnel(final String nom1, final String prenom1, final LocalDate naissance1, final ArrayList<String> tel1) {
        this.nom = nom1;
        this.prenom = prenom1;
        this.naissance = naissance1;
        this.tel = new ArrayList<String>(tel1);
    }

    /**
     * Builder.
     */
    public static class Builder implements Composant {
        /**
         * nom.
         */
        private final String nom;
        /**
         * prenom.
         */
        private final String prenom;
        /**
         * naissance.
         */
        private final LocalDate naissance;

        /**
         * Optionnel: téléphones.
         */
        private ArrayList<String> tel;

        /**
         * Constructeur Builder parametres obligatoires.
         *
         * @param nom1       perso.
         * @param prenom1    perso.
         * @param naissance1 perso.
         */
        public Builder(final String nom1, final String prenom1, final LocalDate naissance1) {
            this.nom = nom1;
            this.prenom = prenom1;
            this.naissance = naissance1;
        }

        /**
         * Méthode ajouter téléphone.
         * @param tel1 telephone.
         * @return Builder.
         */
        public Builder telephone(final ArrayList<String> tel1) {
            tel = tel1;
            return this;
        }

        /**
         * Affiche le nom du personnel: hérité de Compossant.
         */
        @Override
        public void afficheNom() {
            System.out.println(this.nom);
        }

        /**
         * Build.
         * @return instance de personnel.
         */
        public Personnel build() {
            return new Personnel(this);
        }
    }

    /**
     * Constructeur.
     * @param builder b.
     */
    private Personnel(final Builder builder) {
        nom = builder.nom;
        prenom = builder.prenom;
        naissance = builder.naissance;
        tel = builder.tel;
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
     * @return p de type Personnel.
     */
    public Personnel deserialize(final String file) {
        Personnel p = null;
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            p = (Personnel) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return p;
    }

    /**
     * Retourne le nom.
     * @return nom du personnel.
     */
    public String nom() {
        return this.nom;
    }


    @Override
    public void afficheNom() {

    }
}
