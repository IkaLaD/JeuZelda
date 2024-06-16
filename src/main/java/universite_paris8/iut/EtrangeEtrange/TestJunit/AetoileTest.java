import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.List;

public class AetoileTest {

    private Monde mondeMock;
    private Aetoile aetoile;

    @BeforeEach
    public void setup() {
        mondeMock = mock(Monde.class);
        when(mondeMock.getSizeMondeHauteur()).thenReturn(10);
        when(mondeMock.getSizeMondeLargeur()).thenReturn(10);
        when(mondeMock.getNontraversable()).thenReturn(new int[10][10]); // Un tableau initialisé à 0 (tout traversable)

        aetoile = new Aetoile(mondeMock);
    }

    @Test
    public void testTrouverCheminCheminExiste() {
        // Configuration du monde pour un chemin simple
        int[][] nonTraversable = new int[10][10];
        nonTraversable[5][5] = -1; // Un seul bloc non traversable
        when(mondeMock.getNontraversable()).thenReturn(nonTraversable);

        aetoile.mettreAJourGraphe();

        Position start = new Position(0, 0);
        Position end = new Position(9, 9);

        List<Position> chemin = aetoile.trouverChemin(start, end);

        // Vérifier que le chemin n'est pas vide et correct
        assertFalse(chemin.isEmpty(), "Le chemin ne devrait pas être vide.");
        assertTrue(chemin.contains(end), "Le chemin doit contenir la position de fin.");
    }

    @Test
    public void testTrouverCheminCheminInexistant() {
        // Configuration du monde pour bloquer le chemin
        int[][] nonTraversable = new int[10][10];
        for (int i = 0; i < 10; i++) {
            nonTraversable[i][i] = -1; // Diagonale non traversable bloquant de start à end
        }
        when(mondeMock.getNontraversable()).thenReturn(nonTraversable);

        aetoile.mettreAJourGraphe();

        Position start = new Position(0, 0);
        Position end = new Position(9, 9);

        List<Position> chemin = aetoile.trouverChemin(start, end);

        // Vérifier que le chemin est vide
        assertTrue(chemin.isEmpty(), "Le chemin devrait être vide car il n'y a pas de route possible.");
    }
}
