package universite_paris8.iut.EtrangeEtrange.vues.Sprite;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain.Lambda;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Guerrier;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

import java.util.ArrayList;

public class gestionAffichageSprite implements ListChangeListener<Entite> {
    private Pane paneEntite;
    private ArrayList<AnimationSprite> animationSprites;
    public gestionAffichageSprite(Pane paneEntite){
        this.paneEntite = paneEntite;
        this.animationSprites = new ArrayList<>();
    }
    @Override
    public void onChanged(Change<? extends Entite> change) {
        while(change.next()){
            for (Entite entite : change.getAddedSubList()) {
                creeSprite(entite);
            }
            for(Entite entite : change.getRemoved()) {
                suprimmerSprite(entite);
            }
        }
    }
    /**
     * Choisi le skin de sprite adéquat en fonction de la class de l'entité, et crée son sprite animé qui est directement ajouté à la vue
     * @param entite
     */
    public void creeSprite(Entite entite){
        String skin;
        if (entite.getClass().equals(Guerrier.class)) {
            skin = "chevalier";
        } else if (entite.getClass().equals(Lambda.class)) {
            skin = "squelette";
        } else {
            Rectangle rectangle = new Rectangle(entite.getPosition().getX(),entite.getPosition().getY(),entite.getHitbox().getLargeur()* Constantes.tailleTile,entite.getHitbox().getHauteur()*Constantes.tailleTile);
            rectangle.setTranslateX(entite.getPosition().getX()*Constantes.tailleTile-32);
            rectangle.setTranslateY(entite.getPosition().getY()*Constantes.tailleTile-64);
            rectangle.setFill(Color.ORANGE);
            paneEntite.getChildren().add(rectangle);
            return;
        }

        AnimationSprite animationSprite = new AnimationSprite(entite, skin);

        /*
        ATTENTION : Il faut modifier les méthodes du code, pour que l'animation de marche se déclenche lorsque l'entite
        marche, et non l'activer dès le début que l'entite est ajouter à la liste
         */
        animationSprite.debutAnimationMarche();
        animationSprites.add(animationSprite);

        paneEntite.getChildren().add(animationSprite.getSpriteEntite());
        paneEntite.getChildren().add(animationSprite.ajoutBarrePv());
    }

    /**
     * Suprimme le sprite de la vue dès qu'une entité est retiré de la liste des entités du monde
     * @param entite
     */
    public void suprimmerSprite(Entite entite){
        AnimationSprite animationSprite = null;
        for(int i = 0 ; i < animationSprites.size() ; i++){
            if(animationSprites.get(i).getId() == entite.getId()){
                animationSprite = animationSprites.get(i);
            }
        }
        if(animationSprite!=null) {
            paneEntite.getChildren().remove(animationSprite.getSpriteVie());
            paneEntite.getChildren().remove(animationSprite.getSpriteEntite());
        }
    }
}
