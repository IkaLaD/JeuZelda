package universite_paris8.iut.EtrangeEtrange.vues.Sprite.DropAuSol;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;

import java.util.ArrayList;

public class gestionAffichageSpriteDropAuSol implements ListChangeListener<DropAuSol> {
    private Pane pane;
    private ArrayList<SpriteDropAuSol> animationDropsAuSol;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.15), event -> {
        for(SpriteDropAuSol animationDropAuSol : animationDropsAuSol){
            animationDropAuSol.miseAJourAnimation();
        }
    }));

    public gestionAffichageSpriteDropAuSol(Pane pane){
        this.pane = pane;
        this.animationDropsAuSol = new ArrayList<>();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void onChanged(Change<? extends DropAuSol> change) {
        while(change.next()){
            for (DropAuSol dropAuSol: change.getAddedSubList()) {
                creeSprite(dropAuSol);
                System.out.println("test");
            }
            for (DropAuSol dropAuSol: change.getRemoved()) {
                suprimmerSprite(dropAuSol);
            }
        }
    }
    public void creeSprite(DropAuSol dropAuSol){
        String skin = dropAuSol.getObjet().getNom();


        SpriteDropAuSol animationDropAuSol = new SpriteDropAuSol(dropAuSol, skin);

        animationDropsAuSol.add(animationDropAuSol);
        pane.getChildren().add(animationDropAuSol.getSpriteDropAuSol());
        pane.getChildren().add(animationDropAuSol.getOmbre());

    }

    public void suprimmerSprite(DropAuSol dropAuSol){
        SpriteDropAuSol animationDropAuSol = null;
        for(int i = 0 ; i < animationDropsAuSol.size() ; i++){
            if(animationDropsAuSol.get(i).getId() == dropAuSol.getId()){
                animationDropAuSol = animationDropsAuSol.get(i);
            }
        }
        if(animationDropAuSol!=null) {
            pane.getChildren().remove(animationDropAuSol.getSpriteDropAuSol());
            pane.getChildren().remove(animationDropAuSol.getOmbre());
            animationDropsAuSol.remove(animationDropAuSol);
        }
    }
}
