package universite_paris8.iut.EtrangeEtrange.vues.Menus.Inventaire;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import universite_paris8.iut.EtrangeEtrange.controller.SwitchScene;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class gestionAffichageInventaire {

    /**
     * TilePane qui va stocker les images des objets dans l'inventaire
     */
    private TilePane objetsInventaire;
    /**
     * Tilepane qui va stocker les images des cases de stockage de l'inventaire
     */
    private TilePane caseStockageInventaire;
    /**
     * Pane qui permet de stocker l'ImageView de la case de stockage de la main droite et l'ImageView de l'objet dans la main droite
     */
    public TilePane quantiteObjetInventaire;
    /**
     * Pane qui permet de stocker l'ImageView de la case de stockage de la main droite et l'ImageView de l'objet dans la main droite
     */
    private Pane conteneurObjetMainDroite;
    /**
     * ImageView de l'objet dans la main droite
     */
    private ImageView objetMainDroite;
    /**
     * Pane qui permet de stocker l'ImageView de la case de stockage de la main droite et l'ImageView de l'objet dans la main gauche
     */
    private Pane conteneurObjetMainGauche;
    /**
     * ImageView de l'objet dans la main gauche
     */
    private ImageView objetMainGauche;

    /**
     * Texte "Inventaire" dans le menu inventaire
     */
    private ImageView titreInventaire;
    /**
     * Texte "Main droite" dans le menu inventaire
     */
    private ImageView titreMainDroite;
    /**
     * Texte "Main gauche" dans le menu inventaire
     */
    private ImageView titreMainGauche;
    private Joueur joueur;

    public gestionAffichageInventaire(Joueur joueur, TilePane objetsInventaire, TilePane caseStockageInventaire, TilePane quantiteInventaire, Pane conteneurObjetMainDroite,
                                      ImageView objetMainDroite, Pane conteneurObjetMainGauche, ImageView objetMainGauche,
                                      ImageView titreInventaire, ImageView titreMainDroite, ImageView titreMainGauche){
        this.joueur = joueur;
        this.objetsInventaire = objetsInventaire;
        this.caseStockageInventaire = caseStockageInventaire;
        this.quantiteObjetInventaire = quantiteInventaire;
        this.conteneurObjetMainDroite = conteneurObjetMainDroite;
        this.conteneurObjetMainGauche = conteneurObjetMainGauche;
        this.objetMainDroite = objetMainDroite;
        this.objetMainGauche = objetMainGauche;
        this.titreInventaire = titreInventaire;
        this.titreMainDroite = titreMainDroite;
        this.titreMainGauche = titreMainGauche;
        initialisationInventaire();
        gestionInventaire();
    }

    public void initialisationInventaire(){
        // Ajout des textes de la page inventaire : "Inventaire", "Main droite", "Main Gauche"
        titreInventaire.setImage(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Inventaire/InventaireTitre.png"));
        titreMainDroite.setImage(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Inventaire/MainDroiteTitre.png"));
        titreMainGauche.setImage(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Inventaire/MainGaucheTitre.png"));

        // Ajout des cases de stockage pour la main droite et la main gauche et des images des objets qui y seront présents
        Image caseStockage = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Inventaire/caseStockage.png");
        ImageView caseStockageMainDroite = new ImageView(caseStockage);
        ImageView caseStockageMainGauche = new ImageView(caseStockage);
        conteneurObjetMainGauche.getChildren().add(caseStockageMainGauche);
        conteneurObjetMainDroite.getChildren().add(caseStockageMainDroite);

        // Création des ImageView qui contiendront par la suite les images des objets présents dans les mains droite et gauche
        objetMainDroite = new ImageView();
        objetMainGauche = new ImageView();
        // Quelques propriétés pour agrandir l'image et pour la placer correctement dans la case
        setParamatresImageViewObjetMain(objetMainDroite, true);
        setParamatresImageViewObjetMain(objetMainGauche, true);

        // Ajout du futur emplacement des images pour les mains droite et gauche
        conteneurObjetMainGauche.getChildren().add(objetMainGauche);
        conteneurObjetMainDroite.getChildren().add(objetMainDroite);
    }

    public void setParamatresImageViewObjetMain(ImageView imageView, boolean objetMain){
        imageView.setScaleX(1.3);
        imageView.setScaleY(1.3);
        if(objetMain) {
            imageView.setTranslateX(16);
            imageView.setTranslateY(16);
        }
    }
    public void gestionInventaire(){
        // recupère l'image de l'objet présent dans la main droite
        if(joueur.getObjetMainDroite()!=null)
            objetMainDroite.setImage(getImageObjet(joueur.getObjetMainDroite().getClass()));
        // recupère l'image de l'objet présent dans la main gauche
        if(joueur.getObjetMainGauche()!=null)
            objetMainGauche.setImage(getImageObjet(joueur.getObjetMainGauche().getClass()));

        joueur.getSac().getTailleMaxProperty().addListener((obs, old ,nouv)->{
            affichageInventaire(joueur.getSac());
        });
        affichageInventaire(joueur.getSac());
    }

    /**
     * Méthode qui permet d'afficher les cases de stockages de l'inventaire et leur contenu
     * (Utiliser aussi en tant que listeners voir utilisation)
     * @param sac
     */
    public void affichageInventaire(Sac sac){
        // On efface le précédent affichage
        objetsInventaire.getChildren().clear();
        caseStockageInventaire.getChildren().clear();

        // On affiche pour chaque case de l'inventaire la case de stockage, l'objet à l'intérieur et sa quantité
        Image image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Inventaire/caseStockage.png");
        for(int i = 0 ; i < sac.getTailleMax() ; i++){
            caseStockageInventaire.getChildren().add(new ImageView(image));
            ImageView imageView = null;
            // Si l'emplacement de l'inventaire n'est pas vide, on ajoute l'objet qui y est présent à l'écran
            if(joueur.getSac().objetALemplacement(i)!=null) {
                imageView = new ImageView(getImageObjet(joueur.getSac().objetALemplacement(i).getClass()));
                // Aggrandisement de l'icône de l'objet
                setParamatresImageViewObjetMain(imageView, false);
            }

            // S'il y a un objet, on ajoute son image sinon une case vide
            if(imageView!=null) {
                TextField textField = new TextField();
                textField.setAlignment(Pos.BOTTOM_RIGHT);
                textField.setBackground(Background.EMPTY);
                textField.setMaxSize(64, 64);
                textField.setMinSize(64, 64);
                textField.setText(joueur.getSac().getInv().getEmplacement(i).quantiteObjet()+"");
                textField.setStyle("-fx-text-fill: white;");
                // Ajout de l'image de l'objet et de sa quantité dans la case
                objetsInventaire.getChildren().add(imageView);
                quantiteObjetInventaire.getChildren().add(textField);
            }
            else {
                // Si pas d'objet, on rempli la case avec du vide
                objetsInventaire.getChildren().add(new ImageView());
                quantiteObjetInventaire.getChildren().add(new ImageView());
            }
        }
    }

    /**
     * Méthode qui renvoie l'icone de l'objet par rapport à sa classe
     * @param objet
     * @return
     */
    public Image getImageObjet(Class<? extends Objet> objet){
        if (objet.equals(Arc.class))
            return new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/objet/icone/arc.png");

        System.out.println("Pas d'objet");
        return null;
    }
}
