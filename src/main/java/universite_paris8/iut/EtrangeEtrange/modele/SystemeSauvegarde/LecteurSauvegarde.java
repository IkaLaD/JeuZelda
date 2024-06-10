package universite_paris8.iut.EtrangeEtrange.modele.SystemeSauvegarde;

import org.json.JSONArray;
import org.json.JSONObject;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Families.Familie;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Families.Loup;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain.Squelette;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Archer;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Guerrier;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.EpeeDeSoldat;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.ObjetConteneur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.PetitSac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.FlecheSimple;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.*;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Emplacement;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Inventaire;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class LecteurSauvegarde {

    private JSONObject sauvegarde;
    private Monde monde;

    public LecteurSauvegarde(String path, Monde monde){
        StringBuilder jsonData = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonData.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.sauvegarde = new JSONObject(jsonData.toString());
        this.monde = monde;
    }


    public void initialisationMap(){
        ArrayList<int[][]> couchesMap = new ArrayList<>();

        couchesMap.add(monde.getSol());
        couchesMap.add(monde.getTraversable());
        couchesMap.add(monde.getNontraversable());

        JSONArray coucheJSON = sauvegarde.getJSONArray("map");

        for(int c = 0 ; c < 3 ; c++){
            int compteur = 0 ;
            JSONArray coucheJSONunitaire = coucheJSON.getJSONArray(c);
            for(int i = 0 ; i < Monde.getSizeMondeHauteur() ; i++){
                for(int j = 0 ; j < Monde.getSizeMondeLargeur() ; j++){
                    if(compteur<3*Monde.getSizeMondeLargeur()*Monde.getSizeMondeHauteur()) {
                        couchesMap.get(c)[i][j] = coucheJSONunitaire.getInt(compteur);
                        compteur++;
                    }
                }
            }
        }
    }

    public void initialisationActeursMonde(){
        monde.setJoueur((Joueur)lecteurActeurJson(sauvegarde.getJSONObject("entites").getJSONObject("joueur")));
        for(int i = 0 ; i < sauvegarde.getJSONObject("entites").getJSONArray("listeEntites").length() ; i++){
            monde.ajoutEntite((Entite)lecteurActeurJson(sauvegarde.getJSONObject("entites").getJSONArray("listeEntites").getJSONObject(i)));
        }

        for(int i = 0 ; i < sauvegarde.getJSONObject("dropsAuSol").getJSONArray("listeDropAuSol").length() ; i++){
            monde.ajouterDropAuSol(lecteurDropAuSolJSON((JSONObject) sauvegarde.getJSONObject("dropsAuSol").getJSONArray("listeDropAuSol").get(i)));
        }
    }



    public Hitbox lecteurHitBoxJSON(JSONObject hitboxJSON){
        return new Hitbox(hitboxJSON.getDouble("hauteur"),hitboxJSON.getDouble("largeur"));
    }

    public Acteur lecteurActeurJson(JSONObject acteur){
        Acteur acteur1 = null;
        String type = acteur.getString("acteurType");
        boolean seDeplace = acteur.getBoolean("seDeplace");
        Hitbox hitbox = lecteurHitBoxJSON(acteur.getJSONObject("hitbox"));
        Direction direction = lecteurDirectionJSON(acteur.getJSONObject("direction"));
        Position position = lecteurPositionJSON(acteur.getJSONObject("position"));
        Vitesse vitesse = lecteurVitesseJSON(acteur.getJSONObject("vitesse"));
        Pv pv = lecteurPvJSON(acteur.getJSONObject("pv"));


        if(type=="guerrier" || type=="archer" || type=="lambda" || type=="roiSquelette" ||type=="squelette" || type=="loup"){
            Defense defense = lecteurStatsDefense(acteur.getJSONObject("statsDefense"));
            DefenseSpecial defenseSpecial = lecteurStatsDefenseSpeciale(acteur.getJSONObject("statsDefenseSpecial"));
            if(type=="guerrier" || type=="archer" || type=="lambda" || type=="roiSquelette" ||type=="squelette" || type=="loup"){
                Attaque attaque = lecteurStatsAttaque(acteur.getJSONObject("statsAttaque"));
                AttaqueSpecial attaqueSpecial = lecteurStatsAttaqueSpeciale(acteur.getJSONObject("statsAttaqueSpecial"));
                if (type=="guerrier" || type=="archer" || type=="lambda"){
                    Objet objetmaingauche = lecteurObjetJSON(acteur.getJSONObject("objetMainGauche"));
                    Objet objetmaindroite = lecteurObjetJSON(acteur.getJSONObject("objetMainDroite"));
                    ObjetConteneur objetConteneur = lecteurObjetConteneurJSON(acteur.getJSONObject("sac"));
                    boolean peutCourrir = acteur.getBoolean("peutCourrir");
                    if(type=="guerrier" || type=="archer"){
                        if(type=="guerrier") {
                            acteur1 = new Guerrier(monde, position.getX(), position.getY(), direction);
                        }
                        else if(type=="archer")
                            acteur1 = new Archer((Sac) objetConteneur, objetmaingauche, objetmaindroite, monde, position.getX(), position.getY(), direction, hitbox);

                        ((Joueur) acteur1).setPeuCourir(peutCourrir);
                        ((Joueur) acteur1).setCarquois((Carquois)objetConteneur);
                    }

                }
                else if(type=="roiSquelette"){
                    acteur1 = new RoiSquelette(pv.getPv(), 1,1 ,1,1,1,monde, 1,1, direction, hitbox);
                    ((RoiSquelette) acteur1).setDernierTempsAttaque(acteur.getLong("dernierTempsAttaque"));
                    ((RoiSquelette) acteur1).setDelaiAttaque(acteur.getLong("delaiAttaque"));
                    ((RoiSquelette) acteur1).setPositionInitiale(lecteurPositionJSON(acteur.getJSONObject("positionInitiale")));
                    ((RoiSquelette) acteur1).setPositionMilieu(lecteurPositionJSON(acteur.getJSONObject("positionMilieu")));
                    ((RoiSquelette) acteur1).setPosition5_2(lecteurPositionJSON(acteur.getJSONObject("position5_2")));
                    ((RoiSquelette) acteur1).setEtapeAttaque(acteur.getInt("etapeAttaque"));
                    ((RoiSquelette) acteur1).setJoueurDetecte(acteur.getBoolean("joueurDetecte"));
                    ((RoiSquelette) acteur1).setDistanceDetection(acteur.getDouble("distanceDetection"));

                }
                else if(type=="squelette"){
                    acteur1 = new Squelette(1, 1, 1, 1, 1, 1, monde, 1, 1, direction, hitbox, monde.getJoueur(), new Aetoile(monde));
                    ((Squelette) acteur1).setLastPathCalculationTime(acteur.getLong("lastPathCalculationTime"));
                }
                else if(type=="loup"){
                    acteur1 = new Loup(monde.getJoueur(), monde, 1, 1, direction, hitbox, new Aetoile(monde));
                    ((Loup) acteur1).setEstFamilier(acteur.getBoolean("estFamilier"));
                    ((Loup) acteur1).setRayonDetection(acteur.getDouble("rayonDetection"));
                    ((Loup) acteur1).setLastPathCalculationTime(acteur.getLong("lastPathCalculationTime"));
                }
                ((EntiteOffensif) acteur1).setStatsAttaque(attaque);
            }


        }
        acteur1.setSeDeplace(seDeplace);
        acteur1.setHitbox(hitbox);
        acteur1.setStatsVitesse(vitesse);
        acteur1.setStatsPv(pv);
        acteur1.setPosition(position.getX(), position.getY());

        return acteur1;
    }

    public DropAuSol lecteurDropAuSolJSON(JSONObject dropAuSol){
        Objet objet = lecteurObjetJSON(dropAuSol.getJSONObject("objet"));
        return new DropAuSol(objet, dropAuSol.getInt("quantite"), lecteurPositionJSON(dropAuSol.getJSONObject("position")), monde.getJoueur());
    }

    public Direction lecteurDirectionJSON(JSONObject direction){
        return new Direction(direction.getInt("x"), direction.getInt("y"));
    }

    public Attaque lecteurStatsAttaque(JSONObject attaque){
        Attaque attaque1 = new Attaque(attaque.getDouble("attaque"));
        attaque1.setAttaqueMaximum(attaque.getDouble("attaqueMaximum"));
        return attaque1;
    }
    public AttaqueSpecial lecteurStatsAttaqueSpeciale(JSONObject attaque){
        AttaqueSpecial attaqueSpecial = new AttaqueSpecial(attaque.getDouble("attaqueSpeciale"));
        attaqueSpecial.setAttaqueSpecialMaximum(attaque.getDouble("attaqueSpecialeMaximum"));
        return attaqueSpecial;
    }

    public Position lecteurPositionJSON(JSONObject position){
        return new Position(position.getDouble("x"),position.getDouble("y"));
    }
    public Vitesse lecteurVitesseJSON(JSONObject vitesse){
        Vitesse vitesse1 = new Vitesse(vitesse.getDouble("vitesse"));
        vitesse1.setVitesseMaximum(vitesse.getDouble("vitesseMaximum"));
        return vitesse1;
    }

    public Pv lecteurPvJSON(JSONObject pvJSON){
        Pv pv = new Pv(pvJSON.getDouble("pv"));
        pv.setPvMaximum(pvJSON.getDouble("pvMaximum"));
        return pv;
    }

    /**
     * Cette méthode ne s'occupe pas de la création des objets de type "Conteneur"
     * @param objet
     * @return
     */
    public Objet lecteurObjetJSON(JSONObject objet){
        Objet objet1 = null;
        if(objet.getString("type")=="livreMagique")
            objet1 = new LivreMagique();
        else if(objet.getString("type")=="potion")
            objet1 = new Potion();
        else if(objet.getString("type")=="arc")
            objet1 = new Arc();
        else if(objet.getString("type")=="epeeDeSoldat")
            objet1 = new EpeeDeSoldat();
        else if(objet.getString("type")=="flecheSimple")
            objet1 = new FlecheSimple();

        return objet1;
    }

    public Defense lecteurStatsDefense(JSONObject defense){
        Defense defense1 = new Defense(defense.getDouble("defense"));
        defense1.setDefenseMaximum(defense.getDouble("defenseMaximum"));
        return defense1;
    }
    public DefenseSpecial lecteurStatsDefenseSpeciale(JSONObject defense){
        DefenseSpecial defenseSpecial = new DefenseSpecial(defense.getDouble("defenseSpecial"));
        defenseSpecial.setDefenseSpecialMaximum(defense.getDouble("defenseMaximumSpecial"));
        return defenseSpecial;
    }

    public ObjetConteneur lecteurObjetConteneurJSON(JSONObject objetConteneur){
        String type = objetConteneur.getString("type");
        ObjetConteneur objetConteneur1 = null;

        if(type=="petitSac"){
            objetConteneur1 = new PetitSac();
        }
        if(type=="carquois")
            objetConteneur1 = new Carquois();


        JSONArray listeObjetInventaire = objetConteneur.getJSONArray("listeObjet");
        Inventaire inventaire = new Inventaire(objetConteneur.getInt("taille"));

        for(int i = 0 ; i < inventaire.getTailleMax() ; i++){
            for(int j = 0 ; j < listeObjetInventaire.getJSONObject(i).getInt("quantite") ; i++)
                inventaire.ajoutItem(lecteurObjetJSON(listeObjetInventaire.getJSONObject(i).getJSONObject("objet")));
        }

        return objetConteneur1;
    }
}
