package universite_paris8.iut.EtrangeEtrange.modele.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegat;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParEpee;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParProjectile;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Sommet;

import universite_paris8.iut.EtrangeEtrange.vues.Sprite.DropAuSol.gestionAffichageSpriteDropAuSol;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite.GestionAffichageSpriteEntite;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.GestionActionDegat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Monde {
    /**
     * Taille du monde (généré aléatoirement)
     * Ces valeurs ne servent que pour tester le fonctionnement de la scrolling map, elles seront supprimées lorsque les tests seront finis.
     */
    private static final int sizeMondeHauteur = 33;
    private static final int sizeMondeLargeur = 33;
    private static final double xPointDeDepart = 23;
    private static final double yPointDeDepart = 23;
    /**
     * Ici sont stocké les informations des sols du monde (ex : sol)
     */
    private int[][] sol;
    private int[][] traversable;
    private int[][] nontraversable;
    private ObservableList<Entite> entites;
    private Joueur joueur;
    private ObservableList<DropAuSol> dropsAuSol;
    private ObservableList<ActionDegat> causeDegats;




    /**
     * Liste des identifiants des éléments du structureMonde :
     */

    public Monde(){
        this.sol = new int[sizeMondeHauteur][sizeMondeLargeur];
        this.entites = FXCollections.observableArrayList();
        causeDegats =  FXCollections.observableArrayList();
        this.joueur = null;

        this.dropsAuSol = FXCollections.observableArrayList();
    }

    /**
     * Méthode création de monde à partir d'une TiledMap
     * @param chemin
     * @param nommap
     */
    public Monde(String chemin, String nommap, int hauteur, int largeur)
    {
        causeDegats =  FXCollections.observableArrayList();
        this.entites = FXCollections.observableArrayList();
        this.sol = new int[hauteur][largeur];
        this.traversable = new int[hauteur][largeur];
        this.nontraversable = new int[hauteur][largeur];
        this.dropsAuSol = FXCollections.observableArrayList();

        ArrayList<int[][]> coucheMap = new ArrayList<>();
        coucheMap.add(this.sol);
        coucheMap.add(this.traversable);
        coucheMap.add(this.nontraversable);


        String[] fichiers = {"sol", "traversable", "nontraversable"};

        for(int i = 0 ; i < coucheMap.size() ; i++) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(chemin+"/"+nommap+"_"+fichiers[i]+".csv"));
                String ligne;
                int ligneIndex = 0;

                while ((ligne = reader.readLine()) != null && ligneIndex < hauteur) {
                    String[] block = ligne.split(",");

                    for (int j = 0; j < hauteur && j < block.length; j++)
                        coucheMap.get(i)[ligneIndex][j] = Integer.parseInt(block[j]);

                    ligneIndex++;
                }

            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Erreur de format dans le fichier : " + e.getMessage());
            }
        }
    }

    /**
     * Permet de créer la map en récupérant les données dans un fichier qui a pour chemin d'accès le paramètre "nom"
     * @param nom
     */
    public Monde(String nom)
    {
        causeDegats =  FXCollections.observableArrayList();
        this.entites = FXCollections.observableArrayList();
        this.dropsAuSol = FXCollections.observableArrayList();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(nom));

            String ligneY = reader.readLine();
            String ligneX = reader.readLine();
            int y = Integer.parseInt(ligneY);
            int x = Integer.parseInt(ligneX);

            this.sol = new int[y][x];

            String ligne;
            int ligneIndex = 0;

            while ((ligne = reader.readLine()) != null && ligneIndex < y)
            {
                String[] block = ligne.split(" ");

                for (int i = 0; i < x && i < block.length; i++)
                    this.sol[ligneIndex][i] = Integer.parseInt(block[i]);

                ligneIndex++;
            }

        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
        catch (NumberFormatException e)
        {
            System.err.println("Erreur de format dans le fichier : " + e.getMessage());
        }
    }







    public int[][] getSol() {
        return sol;
    }
    public int[][] getNontraversable(){
        return nontraversable;
    }
    public int[][] getTraversable(){
        return this.traversable;
    }
    /**
     * Génération totalement aléatoire d'un monde (pour les tests).
     */
    public void generationAléatoire(){
        for(int i = 0; i < this.sol.length ; i++){
            for(int j = 0; j < this.sol[i].length ; j++){
                this.sol[i][j] = (int)(Math.random()*3)+1;
            }
        }
    }


    public ArrayList<Entite> getEntites(Position pos, double rayon)
    {
        ArrayList<Entite> entitesDansRayon = new ArrayList<>();

        for (Entite entite : this.entites)
        {
            if (calculerDistance(entite.getPosition(), pos) <= rayon) {
                entitesDansRayon.add(entite);
            }
        }

        return entitesDansRayon;
    }

    public ObservableList<Entite> getObservableListEntites(){
        return this.entites;
    }
    public void ajouterDropAuSol(DropAuSol dropAuSol){
        this.dropsAuSol.add(dropAuSol);
    }
    public void supprimerDropAuSol(DropAuSol dropAuSol){
        this.dropsAuSol.remove(dropAuSol);
    }


    private double calculerDistance(Position pos1, Position pos2)
    {
        double dx = pos2.getX() - pos1.getX();
        double dy = pos2.getY() - pos1.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    public void enleveEntite(Entite entite)
    {
        this.entites.remove(entite);
    }

    public static double getxPointDeDepart(){
        return xPointDeDepart;
    }

    public static double getyPointDeDepart() {
        return yPointDeDepart;
    }

    public static int getSizeMondeHauteur(){
        return sizeMondeHauteur;
    }

    public static int getSizeMondeLargeur() {
        return sizeMondeLargeur;
    }
    public ArrayList<DropAuSol> getDropAuSol(){
        ArrayList<DropAuSol> dropAuSols = new ArrayList<>();
        dropAuSols.addAll(this.dropsAuSol);
        return dropAuSols;
    }
    public void ajoutEntite(Entite entite)
    {
        this.entites.add(entite);
        entite.getStatsPv().getPvActuelleProperty().addListener((old, obs, nouv)-> {
            if (entite.getStatsPv().getPv() <= 0)
                entites.remove(entite);
        });
    }

    public void enleverDropAuSol(DropAuSol dropAuSol){
        this.dropsAuSol.remove(dropAuSol);
    }

    public void setJoueur(Joueur joueur){
        this.joueur = joueur;
    }
    public Joueur getJoueur(){
        return this.joueur;
    }

    public ArrayList<Entite> getEntities()
    {
        return new ArrayList<>(entites);
    }

    public ArrayList<int[][]> getToutesLesCouches(){
        ArrayList<int[][] > couches = new ArrayList<>();
        couches.add(this.sol);
        couches.add(this.traversable);
        couches.add(this.nontraversable);
        return couches;
    }

    public int getTileType(int x, int y) {
        if (x >= 0 && x < sol[0].length && y >= 0 && y < sol.length) {
            return sol[y][x];
        } else {
            return -1;
        }
        
    }

    public void setListenerListeEntites(GestionAffichageSpriteEntite gestionAffichageSprite){
        entites.addListener(gestionAffichageSprite);
    }

    public void setListenerProjectile(GestionActionDegat gestionCauseDegats)
    {
        this.causeDegats.addListener(gestionCauseDegats);
    }

    public void ajoutCauseDegat(ActionDegat causeDegat)
    {
        this.causeDegats.add(causeDegat);
    }



    public void miseAjourCauseDegats()
    {
        for (int i = causeDegats.size()-1; i>=0; i--)
            causeDegats.get(i).miseAjour();
    }

   public void enleveCauseDegat(ActionDegat causeDegat)
   {
       this.causeDegats.remove(causeDegat);
   }


    public Sommet[][] getSommet()
    {
         return  null;
    }


    public void setListenerListeDropsAuSol(gestionAffichageSpriteDropAuSol gestionAffichageDropAuSol) {
        this.dropsAuSol.addListener(gestionAffichageDropAuSol);
    }


    public void verificationCollisionAvecArme()
    {
        for (int i = entites.size()-1;i>=0;i--)
        {
            Entite entite = entites.get(i);

            for (int j = causeDegats.size()-1;j>=0;j--)
            {
                ActionDegat causeDegat = causeDegats.get(j);

                if (entite.getSurface().collision(causeDegat.surfaceDegat()))
                    entite.subitDegat(causeDegat);
            }
        }
    }

    public ArrayList<Entite> getEntitesA() {
        ArrayList<Entite> entitesDansRayon = new ArrayList<>();

        for (Entite entite : this.entites)
        {
            entitesDansRayon.add(entite);

        }

        return entitesDansRayon;
    }

    public void enleveActionDegat(ActionDegat actionDegat) {
        causeDegats.remove(actionDegat);
    }

    public void ajoutActionDegat(ActionDegat actionDegat) {
        causeDegats.add(actionDegat);
    }
}















































