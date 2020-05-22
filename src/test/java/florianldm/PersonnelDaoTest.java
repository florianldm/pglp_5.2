package florianldm;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class PersonnelDaoTest {

    /**
     * Test initialisation.
     */
    @Test
    public void initPersonnel() {
        LocalDate d = LocalDate.of(1989, Month.DECEMBER,5);
        Personnel p = new Personnel.Builder("Martin", "Jean", d).build();
        Assert.assertTrue(p != null);
    }

    /**
     * Test de serialisation et deserialisation.
     */
    @Test
    public void testSerialisation() {
        File file = new File("personnel.p");
        LocalDate d = LocalDate.of(1989, Month.DECEMBER,5);
        Personnel p = new Personnel.Builder("Martin", "Jean", d).build();
        p.serialize("personnel.p");
        Personnel p1 = p.deserialize("personnel.p");
        if (file.delete()) {
            Assert.assertEquals(p1.nom(),p.nom());
        }
        else Assert.assertEquals("1","2");
    }

    /**
     * Test de la méthode save.
     */
    @Test
    public void saveTest() {
        PersonnelDao PDAO = new PersonnelDao();
        Personnel P = new Personnel.Builder("Jean", "Marc", LocalDate.of(1987, Month.DECEMBER, 14)).build();
        PDAO.save(P);
        System.out.println(PDAO.getAll().size());
        assertEquals(1, PDAO.getAll().size());
        assertEquals(PDAO.getAll().get(0).nom(), "Jean");
    }

    /**
     * Test d'une mise à jour sur un personnel dans la liste.
     * On ajoute un personnel, on le cherche, puis on applique la modification.
     * On s'assure que la mise a jour est effectuée.
     */
    @Test
    public void updateTest() {
        PersonnelDao PDAO = new PersonnelDao();
        String[] Nom = {"Pierre"};
        Personnel P = new Personnel.Builder("Pierre", "Marc", LocalDate.of(1987, Month.DECEMBER, 14)).build();
        Personnel P_avec_maj = new Personnel.Builder("Jean", "Marc", LocalDate.of(1987, Month.DECEMBER, 14)).build();
        PDAO.getAll().add(P);
        PDAO.update(P_avec_maj, Nom);

        Assert.assertEquals(PDAO.getAll().get(0).nom(), "Jean");
    }

    /**
     * Test de la suppression d'un personnel.
     * On ajoute, puis on le supprime de la liste.
     */
    @Test
    public void deleteTest() {
        PersonnelDao PDAO = new PersonnelDao();
        Personnel P = new Personnel.Builder("Pierre", "Marc", LocalDate.of(1987, Month.DECEMBER, 14)).build();
        PDAO.getAll().add(P);
        PDAO.delete(P);

        Assert.assertEquals(PDAO.getAll().size(), 0);
    }
}
