import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class AetoileTest {
    private Monde monde;
    private Aetoile aetoile;

    @BeforeEach
    public void setUp() {
        // Initialiser un monde avec des obstacles stratégiques pour tester plusieurs chemins
        monde = new Monde("", "", 10, 10); // suppose une adaptation pour tester
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                monde.getNontraversable()[i][j] = -1; // Tout est traversable
            }
        }
        // Placer des obstacles
        monde.getNontraversable()[5][1] = 1;
        monde.getNontraversable()[5][2] = 1;
        monde.getNontraversable()[5][3] = 1;
        monde.getNontraversable()[5][5] = 1;
        monde.getNontraversable()[5][6] = 1;
        monde.getNontraversable()[5][7] = 1;

        aetoile = new Aetoile(monde);
    }


    /*Ce test vérifie que le chemin trouvé par A* est effectivement le plus court. La valeur 14 est déterminée en comptant manuellement les pas du chemin le plus court autour des obstacles.*/
    @Test
    public void testShortestPath() {
        Position start = new Position(0, 5);
        Position end = new Position(9, 5);
        var path = aetoile.trouverChemin(start, end);
        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals(14, path.size(), "Le chemin trouvé doit être le plus court possible.");
    }
}
