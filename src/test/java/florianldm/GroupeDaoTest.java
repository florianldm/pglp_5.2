package florianldm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

public class GroupeDaoTest {

    /** Date anniversaire. */
    LocalDate d, d1;
    /** Personnels. */
    Personnel p, p1;
    /** Groupe. */
    Groupe g;

    /**
     * Initialisation d'un groupe.
     */
    @Before
    public void initGroupe() {
        d = LocalDate.of(1989, Month.DECEMBER,5);
        d1 = LocalDate.of(1967, Month.AUGUST,14);
        p = new Personnel.Builder("Martin", "Jean", d).build();
        p1 = new Personnel.Builder("Pierre", "Michel", d1).build();
        g = new Groupe();
    }

    /**
     * Test de l'ajout d'un personnel dans groupe.
     */
    @Test
    public void testAjout() {
        g.add(p);
        g.add(p1);
        Assert.assertNotNull(g);
    }

    /**
     * Test de la suppression + suppression d'un groupe vide.
     */
    @Test
    public void testSuppression() {
        g.remove(p);
        g.remove(p1);
        Assert.assertFalse(g.remove(p));
    }

    /**
     * Test de serialisation.
     */
    @Test
    public void testSerialisation() {
        File file = new File("groupe.g");
        g.serialize("groupe.g");
        Groupe g1 = g.deserialize("groupe.g");
        if (file.delete()) {
            Assert.assertEquals(g.getC(),g1.getC());
        }
        else Assert.assertEquals("4","2");
    }

    /**
     * Test de l'ajout d'un groupe.
     */
    @Test
    public void testSave() {
        GroupeDao G = new GroupeDao();
        G.save(g);
        Assert.assertEquals(G.getAll().size(), 1);
    }

    /**
     * Test mise à jour d'un groupe.
     */
    @Test
    public void testUpdate() {
        GroupeDao G = new GroupeDao();
        Groupe G1 = new Groupe("G1");
        Groupe G2 = new Groupe("G2");
        G.save(G1);
        String[] Liste = {"G1"};
        G.update(G2, Liste);

        Assert.assertEquals(G.getAll().get(0).getNom(), "G2");
    }

    /**
     * Test d'une suppression d'un groupe.
     */
    @Test
    public void testDelete() {
        GroupeDao G = new GroupeDao();
        Groupe G1 = new Groupe();
        G.save(G1);
        G.delete(G1);

        Assert.assertEquals(G.getAll().size(), 0);
    }
}
