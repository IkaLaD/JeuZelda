package universite_paris8.iut.EtrangeEtrange.modele.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.EtrangeEtrange.modele.Acteur;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Monstre.Slime;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import universite_paris8.iut.EtrangeEtrange.vues.Sprite.DropAuSol.gestionAffichageSpriteDropAuSol;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite.GestionAffichageSpriteEntite;

import universite_paris8.iut.EtrangeEtrange.vues.Sprite.GestionActeur;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Monde {
    /**
     * Taille du monde (généré aléatoirement)
     * Ces valeurs ne servent que pour tester le fonctionnement de la scrolling map, elles seront supprimées lorsque les tests seront finis.
     */
    private static final int sizeMondeHauteur = 33;
    private static final int sizeMondeLargeur = 33;
    private static final double xPointDeDepart = 17;
    private static final double yPointDeDepart = 17;
    private int[][] sol;
    private int[][] traversable;
    private int[][] nontraversable;
    /**
     * Ici sont stocké les informations des éléments du monde traversables (ex : buissons, fleurs, hautes herbes, etc.)
     */

    private ArrayList<Rechargeable> rechargeables = new ArrayList<>();


    private Joueur joueur;

    private ObservableList<DropAuSol> dropsAuSol;


    private ObservableList<Acteur> acteurs = FXCollections.observableArrayList();


    private long tour = 0;


    /**
     * Liste des identifiants des éléments du structureMonde :
     */

    public Monde(){
        this.sol = new int[sizeMondeHauteur][sizeMondeLargeur];

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


        for (int i = 0;i<5;i++)
        {
            for (int j = 0;j<5;j++)
            {
                if (nontraversable[i][j] == -1)
                    this.ajoutActeur(new Slime(this,i,j,Direction.HAUT,new Hitbox(0.1,0.1)));


            }
        }
    }

    /**
     * Permet de créer la map en récupérant les données dans un fichier qui a pour chemin d'accès le paramètre "nom"
     * @param nom
     */
    public Monde(String nom)
    {



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



        for (int i = 0;i<5;i++)
        {
            for (int j = 0;j<5;j++)
            {
                if (nontraversable[i][j] == -1)
                    this.ajoutActeur(new Slime(this,i,j,Direction.HAUT,new Hitbox(0.1,0.1)));
            }
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


    public void enleverDropAuSol(DropAuSol dropAuSol){
        this.dropsAuSol.remove(dropAuSol);
    }

    public void setJoueur(Joueur joueur){
        this.joueur = joueur;
    }
    public Joueur getJoueur(){
        return this.joueur;
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




    public void ajoutActeur(Acteur acteur)
    {
        this.acteurs.add(acteur);
    }


    public void unTour()
    {
        this.tour++;

        for(int i = acteurs.size() -1 ; i>=0 ; i--){
            acteurs.get(i).unTour();
        }
        for(int i = acteurs.size()-1 ; i>=0 ; i--){
            Acteur acteur1 = acteurs.get(i);
            for(int j = acteurs.size()-1 ; j>=0 ; j--){
                Acteur acteur2 = acteurs.get(j);

                if(collisionAvecActeur(acteur1,acteur2) && acteur1 != acteur2){
                    acteur1.causeCollision(acteur2);
                    acteur2.subitCollision(acteur1);
                }
            }
        }

        for(int i = acteurs.size() -1 ; i>=0 ; i--){
            Acteur acteur = acteurs.get(i);
            if(acteur.plusDePv())
                acteurs.remove(i);
        }

        for(int i = rechargeables.size()-1 ; i>=0 ; i--)
        {
            Rechargeable rechargeable = rechargeables.get(i);
            System.out.println("boucle");
            if(rechargeable.getTourApelle() + rechargeable.delaie() == tour)
            {
                System.out.println("valider");
                rechargeable.cooldown();
                this.rechargeables.remove(rechargeable);
                System.out.println(rechargeables.size());


            }
            else{
                System.out.println("pas valider");
            }

        }
    }

    public void ajoutRechargeable(Rechargeable rechargeable)
    {
        this.rechargeables.add(rechargeable);
        rechargeable.setTourApelle(tour);
        System.out.println(tour + "     apelle ");
    }


    public boolean estHorsMap(Acteur acteur)
    {
        boolean collision;

        Position position = acteur.getPosition();
        Direction direction = acteur.getDirection();
        Hitbox hitbox = acteur.getHitbox();
        double vitesse = acteur.getVitesse();



        if (direction == Direction.BAS) {
            collision = hitbox.getPointLePlusEnBas(position.getY()) + vitesse >= Monde.getSizeMondeHauteur();
        } else if (direction == Direction.HAUT) {
            collision = hitbox.getPointLePlusEnHaut(position.getY()) - vitesse < 0;
        } else if (direction == Direction.DROITE) {
            collision = hitbox.getPointLePlusADroite(position.getX()) + vitesse >= Monde.getSizeMondeLargeur();
        } else if (direction == Direction.GAUCHE) {
            collision = hitbox.getPointLePlusAGauche(position.getX()) - vitesse < 0;
        }
        else
        {
            collision = false;
        }

        return collision;
    }



    public boolean collisionMap(Acteur acteur)
    {

        Position position = acteur.getPosition();
        Direction direction = acteur.getDirection();
        Hitbox hitbox = acteur.getHitbox();
        double vitesse = acteur.getVitesse();

        double x = position.getX()+ vitesse*direction.getX();
        double y = position.getY()+ vitesse*direction.getY();

        // Extremité de la hitbox, calculer dans le if en dessous en fonction de la direction (on prend extremite gauche et droite si on va vers le haut ou le bas)
        double extremite1;
        double extremite2;

        if (direction == Direction.BAS  || direction == Direction.HAUT)
        {
            extremite1 = hitbox.getPointLePlusAGauche(x);
            extremite2 = hitbox.getPointLePlusADroite(x);
        }
        else
        {
            extremite1 = hitbox.getPointLePlusEnHaut(y);
            extremite2 = hitbox.getPointLePlusEnBas(y);
        }

        boolean colision = false;
        int cpt = (int) extremite1;


        while (cpt <= extremite2 && !colision)
        {
            if (direction == Direction.BAS) {
                colision = nontraversable[(int) (hitbox.getPointLePlusEnBas(y))][cpt] != -1;
            } else if (direction == Direction.HAUT) {
                colision = nontraversable[(int) (hitbox.getPointLePlusEnHaut(y))][cpt] != -1;
            } else if (direction == Direction.DROITE) {
                colision = nontraversable[cpt][(int) (hitbox.getPointLePlusADroite(x))] != -1;
            } else if (direction == Direction.GAUCHE) {
                colision = nontraversable[cpt][(int) (hitbox.getPointLePlusAGauche(x))] != -1;
            }
            cpt++;
        }


        return colision;
    }

    public boolean collisionAvecActeur(Acteur acteur1,Acteur acteur2)
    {
        Position pos1 = acteur1.getPosition();
        Hitbox hitbox1 = acteur1.getHitbox();

        Position pos2 = acteur2.getPosition();
        Hitbox hitbox2 = acteur2.getHitbox();

        double x1Min = hitbox1.getPointLePlusAGauche(pos1.getX());
        double y1Min = hitbox1.getPointLePlusEnHaut(pos1.getY());
        double x1Max = hitbox1.getPointLePlusADroite(pos1.getX());
        double y1Max = hitbox1.getPointLePlusEnBas(pos1.getY());

        double x2Min = hitbox2.getPointLePlusAGauche(pos2.getX());
        double y2Min = hitbox2.getPointLePlusEnHaut(pos2.getY());
        double x2Max = hitbox2.getPointLePlusADroite(pos2.getX());
        double y2Max = hitbox2.getPointLePlusEnBas(pos2.getY());

        boolean collisionX = x1Min < x2Max && x1Max > x2Min;
        boolean collisionY = y1Min < y2Max && y1Max > y2Min;

        return collisionX && collisionY;
    }


    public boolean collision(Acteur acteur)
    {
        return !(collisionMap(acteur) && !collisionAvecActeurs(acteur));
    }

    private boolean collisionAvecActeurs(Acteur acteur1)
    {
        boolean aCollision = false;

        for (int i = 0;i<acteurs.size() && !aCollision;i++)
        {
            Acteur acteur2 = acteurs.get(i);

            if (collisionAvecActeur(acteur1,acteur2) && acteur2 != acteur1)
                aCollision = true;
        }

        return aCollision;
    }

    public void setListenerActeur(GestionActeur listenerActeur)
    {
        this.acteurs.addListener(listenerActeur);
    }


    public void setListenerListeDropsAuSol(gestionAffichageSpriteDropAuSol gestionAffichageDropAuSol) {
        this.dropsAuSol.addListener(gestionAffichageDropAuSol);
    }







    public void setListenerListeEntites(GestionAffichageSpriteEntite gestionAffichageSprite) {
        this.acteurs.addListener(gestionAffichageSprite);
    }

    public void enleveActeur(Acteur acteur) {
        this.acteurs.remove(acteur);
    }







    public Acteur interactionAvecActeur()
    {
        Acteur act = null;
        double distance = -1;


        for (Acteur acteur : acteurs)
        {
            double distancePretendant = estEnFace(acteur);

             if (distancePretendant > distance)
             {
                 act = acteur;
                 distance = distancePretendant;
             }
        }

        return act;
    }

    private double estEnFace(Acteur acteur)
    {
        final double yDistanceMax = 0.5;
        final double xDistanceMax = 0.2;

        double dX;
        double dY;
        Direction directionJoueur = joueur.getDirection();


        if (directionJoueur == Direction.BAS ||directionJoueur == Direction.HAUT)
        {
            dX = Math.abs(acteur.getPosition().getX() - joueur.getPosition().getX());
            dY = Math.abs(acteur.getPosition().getY() - joueur.getPosition().getY());
        }
        else
        {
            dY = Math.abs(acteur.getPosition().getX() - joueur.getPosition().getX());
            dX = Math.abs(acteur.getPosition().getY() - joueur.getPosition().getY());
        }

        return dX <= xDistanceMax && dY <= yDistanceMax ?  dX+dY : -1;
    }




}















































