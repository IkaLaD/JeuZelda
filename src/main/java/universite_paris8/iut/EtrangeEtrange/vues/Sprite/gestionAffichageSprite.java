package universite_paris8.iut.EtrangeEtrange.vues.Sprite;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain.Lambda;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Guerrier;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

import java.util.ArrayList;

public class gestionAffichageSprite implements ListChangeListener<Entite> {
    private Pane paneEntite;
    private ArrayList<AnimationSprite> animationSprites;
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.15), event -> {
        for(AnimationSprite animationSprite : animationSprites){
            if(animationSprite.getEntite().isSeDeplace())
                animationSprite.miseAJourAnimation();
            else
                animationSprite.finAnimationMarche();
        }
    }));
    public gestionAffichageSprite(Pane paneEntite){
        this.paneEntite = paneEntite;
        this.animationSprites = new ArrayList<>();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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
            skin = "pnjtest";
        }
        AnimationSprite animationSprite = new AnimationSprite(entite, skin);

        animationSprites.add(animationSprite);
        paneEntite.getChildren().add(animationSprite.getSpriteEntite());

        // On ajoute une barre de vie visible uniquement si ce n'est pas le joueur
        if(!(entite instanceof Joueur))
            paneEntite.getChildren().add(animationSprite.ajoutBarrePv());
    }

    /**
     * Utiliser pour ajouter le joueur dans les sprites dans l'ajouter aux entités du monde
     * @param joueur
     */
    public void ajouterJoueur(Joueur joueur){
        creeSprite(joueur);
        joueur.getPv().getPvActuelleProperty().addListener((obs, old, nouv)->{
            if(joueur.getPv().getPvActuelle()<=0)
                suprimmerSprite(joueur);
        });
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
            animationSprites.remove(animationSprite);
        }
    }
}