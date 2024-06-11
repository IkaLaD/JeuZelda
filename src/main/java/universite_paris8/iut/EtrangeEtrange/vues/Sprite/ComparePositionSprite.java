package universite_paris8.iut.EtrangeEtrange.vues.Sprite;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite.SpriteEntite;

import java.util.Comparator;

public class ComparePositionSprite implements Comparator<SpriteEntite> {

    @Override
    public int compare(SpriteEntite o1, SpriteEntite o2) {
        if(o1.getEntite().getPosition().getY()>o2.getEntite().getPosition().getY()) {
            o1.getSpriteEntite().toFront();
            if(!(o1.getEntite().typeActeur()=="Joueur"))
                o1.getSpriteVie().toFront();
            return 1;
        }
        return -1;
    }
}
