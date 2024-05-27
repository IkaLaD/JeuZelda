package universite_paris8.iut.EtrangeEtrange.vues.Menus.Inventaire;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

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
    private int caseVerouilleInventaire;

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
        this.caseVerouilleInventaire = -1; // Négatif = pas de case sélectionné dans l'inventaire
        listenerTailleSac();
        initialisationInventaire();
        affichageInventaire(joueur.getSac());
    }

    /**
     * Première initialisation de l'inventaire (fond, cases de stockages, titres)
     */
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
        setParamatresImageViewObjetInventaire(objetMainDroite, true);
        setParamatresImageViewObjetInventaire(objetMainGauche, true);

        // Ajout du futur emplacement des images pour les mains droite et gauche
        conteneurObjetMainGauche.getChildren().add(objetMainGauche);
        conteneurObjetMainDroite.getChildren().add(objetMainDroite);
    }

    /**
     * Permet de remettre à la taille et la position necéssaire les images des objets dans l'inventaire et dans les mains
     * @param imageView
     * @param objetMain
     */
    public void setParamatresImageViewObjetInventaire(ImageView imageView, boolean objetMain){
        imageView.setScaleX(1.3);
        imageView.setScaleY(1.3);
        if(objetMain) {
            imageView.setTranslateX(16);
            imageView.setTranslateY(16);
        }
    }

    /**
     * Actualise l'affichage de l'inventaire dès que celui-ci change de taille
     */
    public void listenerTailleSac(){
        joueur.getSac().getTailleMaxProperty().addListener((obs, old ,nouv)->{
            affichageInventaire(joueur.getSac());
        });
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
        quantiteObjetInventaire.getChildren().clear();
        objetMainGauche.setImage(null);
        objetMainDroite.setImage(null);

        // On affiche pour chaque case de l'inventaire la case de stockage, l'objet à l'intérieur et sa quantité
        Image image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Inventaire/caseStockage.png");
        for(int i = 0 ; i < sac.getTailleMax() ; i++) {
            caseStockageInventaire.getChildren().add(new ImageView(image));
            ImageView imageView = null;
            // Si l'emplacement de l'inventaire n'est pas vide, on ajoute l'objet qui y est présent à l'écran
            if (joueur.getSac().objetALemplacement(i) != null) {
                imageView = new ImageView(getImageObjet(joueur.getSac().objetALemplacement(i).getClass()));
                // Aggrandisement de l'icône de l'objet
                setParamatresImageViewObjetInventaire(imageView, false);
            }

            // S'il y a un objet, on ajoute son image sinon une case vide
            if (imageView != null) {
                TextField textField = ajouterQuantite(joueur.getSac().getInv().getEmplacement(i).quantiteObjet());
                // Ajout de l'image de l'objet et de sa quantité dans la case
                objetsInventaire.getChildren().add(imageView);
                quantiteObjetInventaire.getChildren().add(textField);
            } else {
                // Si pas d'objet, ont rempli la case avec du vide
                objetsInventaire.getChildren().add(new ImageView());
                quantiteObjetInventaire.getChildren().add(new ImageView());
            }
        }
        if(joueur.getObjetMainDroite()!=null)
            objetMainDroite.setImage(getImageObjet(joueur.getObjetMainDroite().getClass()));
        if(joueur.getObjetMainGauche()!=null)
            objetMainGauche.setImage(getImageObjet(joueur.getObjetMainGauche().getClass()));

    }

    public TextField ajouterQuantite(int quantite){
        TextField textField = new TextField();
        textField.setAlignment(Pos.BOTTOM_RIGHT);
        textField.setBackground(Background.EMPTY);
        textField.setEditable(false);
        textField.setMaxSize(64, 64);
        textField.setMinSize(64, 64);
        textField.setText(quantite+"");
        textField.setStyle("-fx-text-fill: white;");
        return textField;
    }

    /**
     * Permet de laisser en surbrillance la case qui à été sélectionné par le joueur dans l'inventaire
     * @param emplacement
     */
    public void caseVerouille(int emplacement){
        int tailleInventaire = caseStockageInventaire.getChildren().size();
        ColorAdjust colorReset = new ColorAdjust();

        if (caseVerouilleInventaire<tailleInventaire && caseVerouilleInventaire>=0) {
            caseStockageInventaire.getChildren().get(caseVerouilleInventaire).setEffect(colorReset);
        }
        conteneurObjetMainDroite.getChildren().get(0).setEffect(colorReset);
        conteneurObjetMainGauche.getChildren().get(0).setEffect(colorReset);


        caseVerouilleInventaire = emplacement;
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.8);

        if(caseVerouilleInventaire<tailleInventaire)
            caseStockageInventaire.getChildren().get(emplacement).setEffect(colorAdjust);
        else
            if(caseVerouilleInventaire==tailleInventaire)
                conteneurObjetMainDroite.getChildren().get(0).setEffect(colorAdjust);
            else
                conteneurObjetMainGauche.getChildren().get(0).setEffect(colorAdjust);
    }

    /**
     * Permet d'afficher la case de l'inventaire que le joueur survole en surbrillance, et de réinitialiser la précédente qui l'était
     * @param integerProperty
     */
    public void listenerCaseSurvole(IntegerProperty integerProperty){
        integerProperty.addListener((obs, old, nouv)-> {
            int survole = integerProperty.get();
            System.out.println(survole);
            int tailleInventaire = caseStockageInventaire.getChildren().size();
            ColorAdjust reset = new ColorAdjust();
            for (int i = 0; i < tailleInventaire; i++)
                if (i != caseVerouilleInventaire)
                    caseStockageInventaire.getChildren().get(i).setEffect(reset);

            if(tailleInventaire!=caseVerouilleInventaire)
                conteneurObjetMainDroite.getChildren().get(0).setEffect(reset);
            if(tailleInventaire+1!=caseVerouilleInventaire)
                conteneurObjetMainGauche.getChildren().get(0).setEffect(reset);


            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(0.8);
            if (survole>=tailleInventaire){
                if(survole==tailleInventaire+1)
                    conteneurObjetMainGauche.getChildren().get(0).setEffect(colorAdjust);
                else
                    conteneurObjetMainDroite.getChildren().get(0).setEffect(colorAdjust);
            }
            else {
                if (survole >= 0)
                    caseStockageInventaire.getChildren().get(integerProperty.get()).setEffect(colorAdjust);
            }
        });
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
