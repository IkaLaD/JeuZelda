package universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesAffichage;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;


public class SpriteEntite
{
    private static GestionAffichageSpriteEntite gestionAffichageSpriteEntite;

    private Acteur entite;
    private ImageView SpriteEntite;
    private Rectangle SpriteVie;
    private ColorAdjust effetCouleur;

    private ImageView ombre;
    private boolean appliquerEffet;
    private int id;
    private int skin;
    private int vitesse;
    private int cptImageChange;
    private int image;

    /**
     * La class est uniquement adapté pour le joueur pour le moment (les sprites de chevalier)
     */
    public SpriteEntite(Acteur entite, int skin, int vitesse, double colorAdjust)
    {
        this.image = 0;
        this.SpriteEntite = new ImageView("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sprite/"+skin+"/bas1.png");
        this.entite = entite;
        this.skin = skin;
        this.id = entite.getID();
        this.vitesse = vitesse;
        this.cptImageChange = 0;



        if(!(entite instanceof Joueur))
            this.SpriteVie = new Rectangle();

        // On lie le sprite et l'entité par un même identifiant
        this.SpriteEntite.setId(entite.getID()+"");

        // Listener pour lié position de l'entité et de son sprite
        entite.getPosition().getXProperty().addListener((obs, old, nouv)->
                SpriteEntite.setTranslateX(entite.getPosition().getX() * ConstantesAffichage.tailleTile-32)
        );
        entite.getPosition().getYProperty().addListener((obs, old, nouv)->
                SpriteEntite.setTranslateY(entite.getPosition().getY() * ConstantesAffichage.tailleTile-64)
        );

        effetCouleur = new ColorAdjust();
        effetCouleur.setHue(colorAdjust);
        SpriteEntite.setEffect(effetCouleur);
        // Listener pour savoir si on doit appliquer un effet suite à un dégat ou un heal
        entite.getStatsPv().getPvActuelleProperty().addListener((obs, old, nouv) -> {
            demarrerEffet();
        });

        //
        SpriteEntite.setTranslateX(entite.getPosition().getX() * ConstantesAffichage.tailleTile-32);
        SpriteEntite.setTranslateY(entite.getPosition().getY() * ConstantesAffichage.tailleTile-64);
    }



    public void demarrerEffet(){
        appliquerEffet=true;
        effetCouleur.setBrightness(0.65);
    }

    public void arreterEffet(){
        effetCouleur.setBrightness(0);
        appliquerEffet=false;
    }

    public Rectangle ajoutBarrePv(){
        // Listener pour que la couleur et la taille de la barre change
        entite.getStatsPv().getPvActuelleProperty().addListener((obs, old, nouv) -> {
            SpriteVie.setWidth(ConstantesAffichage.tailleTile * (entite.getStatsPv().getPv() / entite.getStatsPv().getPvMaximum()));
            if(entite.getStatsPv().getPv() / entite.getStatsPv().getPvMaximum() > 2.0/3.0)
                SpriteVie.setFill(Color.GREEN);
            else if(entite.getStatsPv().getPv() / entite.getStatsPv().getPvMaximum() >1.0/3.0)
                SpriteVie.setFill(Color.YELLOW);
            else
                SpriteVie.setFill(Color.RED);
        });

        // Listener pour que la barre d'hp suivent le sprite de l'entité
        entite.getPosition().getXProperty().addListener((obs, old, nouv)->
                SpriteVie.setTranslateX(entite.getPosition().getX()* ConstantesAffichage.tailleTile-32)
        );
        entite.getPosition().getYProperty().addListener((obs, old, nouv)->
                SpriteVie.setTranslateY(entite.getPosition().getY()* ConstantesAffichage.tailleTile-48)
        );


        // On execute une première fois le code du listener pour qu'il sapplique dès le début du lancement du jeu
        SpriteVie.setTranslateX(entite.getPosition().getX()* ConstantesAffichage.tailleTile-32);
        SpriteVie.setTranslateY(entite.getPosition().getY()* ConstantesAffichage.tailleTile-48);
        SpriteVie.setHeight(5);
        SpriteVie.setWidth(ConstantesAffichage.tailleTile * (entite.getStatsPv().getPv() / entite.getStatsPv().getPvMaximum()));

        if(entite.getStatsPv().getPv() / entite.getStatsPv().getPvMaximum() > 2.0/3.0)
            SpriteVie.setFill(Color.GREEN);
        else if(entite.getStatsPv().getPv() / entite.getStatsPv().getPvMaximum() > 1.0/3.0)
            SpriteVie.setFill(Color.YELLOW);
        else
            SpriteVie.setFill(Color.RED);

        return SpriteVie;
    }

    public ImageView ajoutOmbre() {
        ombre = new ImageView(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Ombre/ombre.png"));
        ombre.setOpacity(0.85); // Réduire l'opacité de l'ombre

        // Déplacer l'ombre selon la position de l'entité
        entite.getPosition().getXProperty().addListener((obs, old, nouv) ->
                ombre.setTranslateX(entite.getPosition().getX() * ConstantesAffichage.tailleTile - 19)
        );
        entite.getPosition().getYProperty().addListener((obs, old, nouv) ->
                ombre.setTranslateY(entite.getPosition().getY() * ConstantesAffichage.tailleTile - 0)
        );

        ombre.setTranslateX(entite.getPosition().getX() * ConstantesAffichage.tailleTile - 19);
        ombre.setTranslateY(entite.getPosition().getY() * ConstantesAffichage.tailleTile - 0);

        return ombre;
    }


    public void miseAJourAnimation(){
        int face = 0;
        if(cptImageChange%vitesse==0) {
            if (entite.getDirection() == Direction.BAS) {
                face = 1;
            } else if (entite.getDirection() == Direction.HAUT) {
                face = 0;
            } else if (entite.getDirection() == Direction.DROITE) {
                face = 3;
            } else if (entite.getDirection() == Direction.GAUCHE) {
                face = 2;
            }
            SpriteEntite.setImage(gestionAffichageSpriteEntite.getImagesSprite().get(this.skin)[face][image]);
            if(image==5)
                image=0;
            else
                image++;
        }



        cptImageChange++;
    }

    public void finAnimationMarche(){
        image=0;
        int face = 0;
        if (entite.getDirection() == Direction.BAS) {
            face = 1;
        } else if (entite.getDirection() == Direction.HAUT) {
            face = 0;
        } else if (entite.getDirection() == Direction.DROITE) {
            face = 3;
        } else if (entite.getDirection() == Direction.GAUCHE) {
            face = 2;
        }
        this.SpriteEntite.setImage(gestionAffichageSpriteEntite.getImagesSprite().get(this.skin)[face][image]);
    }

    public ImageView getSpriteEntite(){
        return SpriteEntite;
    }

    public Rectangle getSpriteVie(){
        return SpriteVie;
    }

    public int getId(){
        return this.id;
    }

    public Acteur getEntite() {
        return entite;
    }
    public boolean getAppliquerEffet(){
        return this.appliquerEffet;
    }

    public void setAppliquerEffet(boolean bool){
        this.appliquerEffet=bool;
    }

    public static void setGestionAffichageSpriteEntite(GestionAffichageSpriteEntite nouveau){
        gestionAffichageSpriteEntite = nouveau;
    }
}