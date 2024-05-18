package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import org.json.*;

import java.io.*;
import java.util.ArrayList;

public class gestionAffichageMap {
    private ArrayList<TilePane> TilePaneCouchesMonde;
    private Monde monde;
    private Image grass = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sol/1.png");
    private Image gravel = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sol/2.png");
    private Image stone1 = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sol/3.png");
    private Image stone2 = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sol/4.png");
    private Image stone3 = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sol/5.png");
    private Image stone4 = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sol/6.png");
    private Image rose1 = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/traversable/1.png");
    private Image rose2 = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/traversable/2.png");
    private Image rose3 = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/traversable/3.png");
    private Image rose4 = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/traversable/4.png");
    private Image buisson = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/nontraversable/1.png");

    public gestionAffichageMap(Monde monde, TilePane sol, TilePane traversable, TilePane nontraversable){
        this.TilePaneCouchesMonde = new ArrayList<>();
        this.TilePaneCouchesMonde.add(sol);
        this.TilePaneCouchesMonde.add(traversable);
        this.TilePaneCouchesMonde.add(nontraversable);
        this.monde = monde;
    }

    /**
     * Récupères les tableaus dans la classe monde et affiche les tuiles qui y sont associées.
     */
    public void afficherMonde(){
        // Récupération de la première couche de la map.
        ArrayList<int[][]> couchesMap = monde.getToutesLesCouches();

        // Génération de l'image
        for(int i = 0 ; i < 3 ; i++) { // Nombre de couches de la map
            for (int hauteur = 0; hauteur < Monde.getSizeMondeHauteur(); hauteur++) {
                for (int largeur = 0; largeur < Monde.getSizeMondeLargeur(); largeur++) {
                    int idTile = couchesMap.get(i)[hauteur][largeur];
                    ImageView imageView = getImage(i, idTile);
                    TilePaneCouchesMonde.get(i).getChildren().add(imageView);
                }
            }
        }
        afficherMondeJSON();
    }

    /**
     * Permet d'afficher la map depuis les fichiers JSON des jeux de tuiles crée avec le logiciel Tiled
     */
    public void afficherMondeJSON(){
        String[] fichiers = {"sol", "traversable", "nontraversable"};
        ArrayList<int[][]> couchesMap = monde.getToutesLesCouches();

        for(int i = 0 ; i < 3 ; i++){
            // Lecture du fichier JSON
            StringBuilder json = new StringBuilder();
            try {
                BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/"+fichiers[i]+"/"+fichiers[i]+".tsj"));
                String ligne;

                while ((ligne = reader.readLine()) != null) {
                    json.append(ligne);
                }

            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Erreur de format dans le fichier : " + e.getMessage());
            }

            JSONObject jsonObject = new JSONObject(json.toString());

            // On récupère les id de tuiles avec les images associées.
            JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("tiles"));
            TilePane tilePane = TilePaneCouchesMonde.get(i);
            System.out.println(i);
            for(int h = 0 ; h < Monde.getSizeMondeHauteur() ; h++){
                for(int l = 0 ; l < Monde.getSizeMondeLargeur() ; l++){
                    if(couchesMap.get(i)[h][l]!=-1) {
                        String chemin = jsonArray.getJSONObject(couchesMap.get(i)[h][l]).getString("image");
                        tilePane.getChildren().add(new ImageView("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/" + fichiers[i] + "/" + chemin));
                    }
                    else{
                        tilePane.getChildren().add(new ImageView());
                    }
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
                    image = new ImageView(gravel);
                    break;
                case 3:
                    image = new ImageView(stone1);
                    break;
                case 4:
                    image = new ImageView(stone2);
                    break;
                case 5:
                    image = new ImageView(stone3);
                    break;
                case 6:
                    image = new ImageView(stone4);
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
