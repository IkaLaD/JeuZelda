package universite_paris8.iut.EtrangeEtrange.vues.Sprite.DropAuSol;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;

public class SpriteDropAuSol {

    private static int tailleFlottementMax = 20;
    private DropAuSol dropAuSol;
    private String skin;
    private ImageView SpriteDropAuSol;
    private ImageView ombre;
    private int id;
    private IntegerProperty positionFlottement;
    private boolean animationDescendante;

    public SpriteDropAuSol(DropAuSol dropAuSol, String skin){
        this.dropAuSol = dropAuSol;
        this.skin = skin;

        this.ombre = new ImageView("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/objet/ombre.png");
        this.ombre.setTranslateX(dropAuSol.getPosition().getX()*Constantes.tailleTile);
        this.ombre.setTranslateY(dropAuSol.getPosition().getY()*Constantes.tailleTile);

        this.SpriteDropAuSol = new ImageView("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/objet/icone/"+skin+".png");
        this.positionFlottement = new SimpleIntegerProperty((int)(Math.random()*tailleFlottementMax));
        this.SpriteDropAuSol.setTranslateX(dropAuSol.getPosition().getX()*Constantes.tailleTile);
        this.positionFlottement.addListener((obs, old, nouv)->
                this.SpriteDropAuSol.setTranslateY(dropAuSol.getPosition().getY()*Constantes.tailleTile+this.positionFlottement.get()-32)
        );
        this.id = dropAuSol.getId();
        this.animationDescendante = false;
    }

    /**
     * Permet de crée l'effet de flottement des objets au sol.
     */
    public void miseAJourAnimation(){
        if(animationDescendante){
            if(positionFlottement.get()<=0) {
                animationDescendante = false;
                positionFlottement.set(positionFlottement.get()+4);
            }
            else
                positionFlottement.set(positionFlottement.get()-4);
        }
        else{
            if(positionFlottement.get()>=tailleFlottementMax) {
                animationDescendante = true;
                positionFlottement.set(positionFlottement.get()-4);
            }
            else
                positionFlottement.set(positionFlottement.get()+4);
        }
    }

    public int getId() {
        return id;
    }

    public ImageView getSpriteDropAuSol() {
        return SpriteDropAuSol;
    }

    public ImageView getOmbre(){
        return ombre;
    }
}
