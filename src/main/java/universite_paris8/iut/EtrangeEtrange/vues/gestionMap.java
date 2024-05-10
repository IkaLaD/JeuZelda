package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

import java.util.ArrayList;

public class gestionMap {
    private TilePane tilepane;
    private Monde monde;
    private Image grass = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sol/1.png");
    private Image stone = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sol/2.png");
    private Image gravel = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sol/3.png");
    private Image rose1 = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/traversable/1.png");
    private Image rose2 = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/traversable/2.png");
    private Image rose3 = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/traversable/3.png");
    private Image rose4 = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/traversable/4.png");
    private Image buisson = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/nontraversable/1.png");

    public gestionMap(Monde monde, TilePane tilePane){
        this.tilepane = tilePane;
        this.monde = monde;
        afficherMonde();
    }
    public void afficherMonde(){
        // Récupération de la première couche de la map.
        int[][] coucheMonde;
        ArrayList<int[][]> couchesMap = monde.getToutesLesCouches();

        // Génération de l'image
        for(int i = 0 ; i < 3 ; i++) { // Nombre de couches de la map
            for (int hauteur = 0; hauteur < Monde.getSizeMondeHauteur(); hauteur++) {
                for (int largeur = 0; largeur < Monde.getSizeMondeLargeur(); largeur++) {
                    int idTile = couchesMap.get(i)[hauteur][largeur];
                    ImageView imageView = getImage(i, idTile);
                    imageView.setX(largeur * Constantes.tailleTile);
                    imageView.setY(hauteur * Constantes.tailleTile);
                    tilepane.getChildren().add(imageView);
                }
            }
        }
    }

    public ImageView getImage(int coucheMap, int idTile){
        ImageView image;
        if(coucheMap == 0){
            switch (idTile){
                case 1 :
                    image = new ImageView(grass);
                    break;
                case 2 :
                    image = new ImageView(stone);
                    break;
                case 3:
                    image = new ImageView(gravel);
                    break;
                default:
                    image = new ImageView();
                    break;
            }
        }
        else if (coucheMap == 1){
            switch (idTile) {
                case 1:
                    image = new ImageView(rose1);
                    break;
                case 2:
                    image = new ImageView(rose2);
                    break;
                case 3:
                    image = new ImageView(rose3);
                    break;
                case 4:
                    image = new ImageView(rose4);
                    break;
                default:
                    image = new ImageView();
                    break;
            }
        }
        else{
            switch (idTile) {
                case 1:
                    image = new ImageView(buisson);
                    break;
                default:
                    image = new ImageView();
                    break;
            }
        }
        return image;
    }
}
