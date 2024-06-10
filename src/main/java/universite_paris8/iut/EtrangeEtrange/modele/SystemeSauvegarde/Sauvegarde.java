package universite_paris8.iut.EtrangeEtrange.modele.SystemeSauvegarde;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegat;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParEntite;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParEpee;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParProjectile;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Humanoide;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Families.Familie;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Families.Loup;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain.Lambda;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain.Squelette;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Archer;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Guerrier;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.EpeeDeSoldat;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Consommable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.ObjetConteneur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.PetitSac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.FlecheSimple;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.*;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Inventaire;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Sauvegarde {
    /**
     * Structure sauvegarde fichier (fichier JSON) :
     *
     */

    private String nomSauvegarde;
    private Monde monde;
    public Sauvegarde(String nomSauvegarde, Monde monde) throws IOException {
        this.nomSauvegarde = nomSauvegarde;
        this.monde = monde;
        creationFichierSauvegarde();
        ecritureFichierSauvegarde();
    }

    public void creationFichierSauvegarde(){
        try{
            File file = new File("src/main/resources/universite_paris8/iut/EtrangeEtrange/Sauvegardes/"+nomSauvegarde+".txt");
            if(file.createNewFile()) {
                System.out.println("Fichier sauvegarde crée");

                // Méthode création sauvegarde
            }
            else
                System.out.println("Une sauvegarde porte déjà ce nom !");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ecritureFichierSauvegarde() throws IOException {
        // Root
        JSONObject sauvegarde = new JSONObject();

        // Objet map
        JSONArray couchesMaps = new JSONArray();
        sauvegarde.append("map", couchesMaps);

        // Ajout liste des éléments des 3 couches de la map
        couchesMaps.put(0, creationMapJson(monde.getSol()));
        couchesMaps.put(1, creationMapJson(monde.getTraversable()));
        couchesMaps.put(2, creationMapJson(monde.getNontraversable()));

        // Objet de toutes les entités du jeu
        JSONObject entites = new JSONObject();
        sauvegarde.append("entites", entites);
            // Objet pour le joueur
            entites.append("joueur", creationActeurJson(monde.getJoueur()));

            // Objet pour tout le reste des entités
            JSONArray listeEntites = new JSONArray();
            entites.append("listeEntites", listeEntites);
            for(Acteur acteur : monde.getEntities()){
                listeEntites.put(creationActeurJson(acteur));
            }

        // Objet drops au sol
        JSONObject dropsAuSol = new JSONObject();
        sauvegarde.append("dropsAuSol", dropsAuSol);

            // Objet liste drop au sol
            JSONArray listeDropAuSol = new JSONArray();
            dropsAuSol.append("listeDropAuSol", listeDropAuSol);
            for(DropAuSol dropAuSol : monde.getDropAuSol()){
                listeDropAuSol.put(creationDropAuSolJSON(dropAuSol));
            }

        // Objet ActionDegat
        JSONObject actionDegat = new JSONObject();
        sauvegarde.append("actionDegat", actionDegat);

            // Objet liste actionDegat
            JSONArray listeActionDegat = new JSONArray();
            actionDegat.append("listeActionDegat", listeActionDegat);
            for(ActionDegat actionDegat1 : monde.getCauseDegats())
                listeActionDegat.put(creationActionDegatJSON(actionDegat1));

        // Ecriture de l'objet JSON dans un fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/universite_paris8/iut/EtrangeEtrange/Sauvegardes/"+nomSauvegarde+".txt"))) {
            writer.write("{");
            writer.newLine();

            int count = 0;
            int size = sauvegarde.length();
            for (String key : sauvegarde.keySet()) {
                Object value = sauvegarde.get(key);
                writer.write("\"" + key + "\": " + value.toString());
                count++;
                if (count < size) {
                    writer.write(",");
                }
                writer.newLine();
                writer.flush(); // Force write to disk to manage memory better
            }

            writer.write("}");
            writer.newLine();
            writer.flush();
            System.out.println("JSON Object written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONArray creationMapJson(int[][] couches){
        JSONArray map = new JSONArray();
        for(int i = 0 ; i < Monde.getSizeMondeHauteur() ; i++){
            for(int j = 0 ; j < Monde.getSizeMondeLargeur() ; j++)
                map.put(couches[i][j]);
        }
        return map;
    }

    public JSONObject creationActeurJson(Acteur acteur){
        JSONObject acteurJSON = new JSONObject();

        acteurJSON.append("id", acteur.getId());
        acteurJSON.append("seDeplace", acteur.isSeDeplace());
        acteurJSON.append("hitbox", creationHitboxJSON(acteur.getHitbox()));
        acteurJSON.append("direction", creationDirectionJSON(acteur.getDirection()));
        acteurJSON.append("position", creationPositionJSON(acteur.getPosition()));
        acteurJSON.append("statsVitesse", creationVitesseJSON(acteur.getStatsVitesse()));
        acteurJSON.append("pv", creationPvJSON(acteur.getStatsPv()));

        if(acteur instanceof Entite){
            if(acteur instanceof EntiteOffensif){
                if (acteur instanceof Humanoide){
                    if(acteur instanceof Joueur){
                        acteurJSON.append("peutCourrir", ((Joueur) acteur).isPeuCourir());
                        if(acteur instanceof Guerrier)
                            acteurJSON.append("acteurType", "guerrier");
                        else if(acteur instanceof Archer)
                            acteurJSON.append("acteurType", "archer");
                    }
                    if(acteur instanceof Lambda) {
                        acteurJSON.append("acteurType", "lambda");
                    }
                    acteurJSON.append("objetMainGauche", creationObjetJSON(((Humanoide) acteur).getObjetMainGauche()));
                    acteurJSON.append("objetMainDroite", creationObjetJSON(((Humanoide) acteur).getObjetMainDroite()));
                    acteurJSON.append("sac", creationObjetConteneurJSON(((Humanoide) acteur).getSac()));
                }
                else if(acteur instanceof RoiSquelette){
                    acteurJSON.append("dernierTempsAttaque", ((RoiSquelette) acteur).getDernierTempsAttaque());
                    acteurJSON.append("delaiAttaque", ((RoiSquelette) acteur).getDelaiAttaque());
                    acteurJSON.append("positionInitiale", creationPositionJSON(((RoiSquelette) acteur).getPositionInitiale()));
                    acteurJSON.append("etapeAttaque", ((RoiSquelette) acteur).getEtapeAttaque());
                    acteurJSON.append("positionMilieu", creationPositionJSON(((RoiSquelette) acteur).getPositionMilieu()));
                    acteurJSON.append("position5_2", creationPositionJSON(((RoiSquelette) acteur).getPosition5_2()));
                    acteurJSON.append("joueurDetecte", ((RoiSquelette) acteur).isJoueurDetecte());
                    acteurJSON.append("distanceDetection", ((RoiSquelette) acteur).getDistanceDetection());
                    acteurJSON.append("acteurType", "roiSquelette");
                }
                else if(acteur instanceof Squelette){
                    acteurJSON.append("joueur", creationActeurJson(((Squelette) acteur).getJoueur()));
                    acteurJSON.append("lastPathCalculationTime", ((Squelette) acteur).getLastPathCalculationTime());
                    acteurJSON.append("acteurType", "squelette");
                }
                else if(acteur instanceof Familie){
                    if(acteur instanceof Loup){
                        acteurJSON.append("acteurType", "loup");
                    }
                    acteurJSON.append("joueur", creationActeurJson(((Familie) acteur).getJoueur()));
                    acteurJSON.append("estFamilier", ((Familie) acteur).isEstFamilier());
                    acteurJSON.append("rayonDetection", ((Familie) acteur).getRayonDetection());
                    acteurJSON.append("lastPathCalculationTime", ((Familie) acteur).getLastPathCalculationTime());
                }
                acteurJSON.append("statsAttaque", creationStatsAttaque(((EntiteOffensif) acteur).getStatsAttaque()));
                acteurJSON.append("statsAttaqueSpecial", creationStatsAttaqueSpeciale(((EntiteOffensif) acteur).getStatsAttaqueSpecial()));
            }
            acteurJSON.append("statsDefense", creationStatsDefense(((Entite) acteur).getStatsDefense()));
            acteurJSON.append("statsDefenseSpecial",creationStatsDefenseSpeciale(((Entite) acteur).getStatsDefenseSpecial()));
        }
        return acteurJSON;
    }

    public JSONObject creationDropAuSolJSON(DropAuSol dropAuSol){
        JSONObject dropAuSolJSON = new JSONObject();
        dropAuSolJSON.append("id", dropAuSol.getId());
        dropAuSolJSON.append("position", creationPositionJSON(dropAuSol.getPosition()));
        dropAuSolJSON.append("objet", creationObjetJSON(dropAuSol.getObjet()));
        dropAuSolJSON.append("quantite", dropAuSol.getQuantite());

        return dropAuSolJSON;
    }

    public JSONObject creationActionDegatJSON(ActionDegat actionDegat){
        JSONObject actionDegatJSON = new JSONObject();

        if(actionDegat instanceof ActionDegatParEntite){
            if(actionDegat instanceof ActionDegatParEpee)
                actionDegatJSON.append("type", "epee");
            else if(actionDegat instanceof ActionDegatParProjectile)
                actionDegatJSON.append("type", "projectile");
        }
        return actionDegatJSON;
    }

    public JSONObject creationHitboxJSON(Hitbox hitbox){
        JSONObject hitboxJSON = new JSONObject();
        hitboxJSON.put("hauteur", hitbox.getHauteur());
        hitboxJSON.put("largeur", hitbox.getLargeur());

        return hitboxJSON;
    }

    public JSONObject creationDirectionJSON(Direction direction){
        JSONObject directionJSON = new JSONObject();
        directionJSON.append("x", direction.getX());
        directionJSON.append("y", direction.getY());
        return directionJSON;
    }

    public JSONObject creationStatsAttaque(Attaque attaque){
        JSONObject attaqueJSON = new JSONObject();
        attaqueJSON.put("attaqueMaximum", attaque.getAttaqueMaximum());
        attaqueJSON.put("attaque", attaque.getAttaque());
        return attaqueJSON;
    }
    public JSONObject creationStatsAttaqueSpeciale(AttaqueSpecial attaque){
        JSONObject attaqueJSON = new JSONObject();
        attaqueJSON.put("attaqueSpecialeMaximum", attaque.getAttaqueSpecialMaximum());
        attaqueJSON.put("attaqueSpeciale", attaque.getAttaqueSpecial());
        return attaqueJSON;
    }

    public JSONObject creationPositionJSON(Position position){
        JSONObject positionJSON = new JSONObject();
        positionJSON.append("x", position.getX());
        positionJSON.append("y", position.getY());
        return positionJSON;
    }
    public JSONObject creationVitesseJSON(Vitesse vitesse){
        JSONObject vitesseJSON = new JSONObject();
        vitesseJSON.append("vitesseMaximum", vitesse.getVitesseMaximum());
        vitesseJSON.append("vitesse", vitesse.getVitesse());
        return vitesseJSON;
    }

    public JSONObject creationPvJSON(Pv pv){
        JSONObject pvJSON = new JSONObject();
        pvJSON.append("pvMaximum", pv.getPvMaximum());
        pvJSON.append("pv", pv.getPv());
        return pvJSON;
    }

    /**
     * Cette méthode ne s'occupe pas de la création des objets de type "Conteneur"
     * @param objet
     * @return
     */
    public JSONObject creationObjetJSON(Objet objet){
        JSONObject objectJSON = new JSONObject();

        objectJSON.put("id", objet.getObjetID());
        if(objet instanceof LivreMagique) {
            objectJSON.put("type", "livreMagique");
        }
        else if (objet instanceof Consommable){
            if(objet instanceof Potion){
                objectJSON.put("type", "potion");
            }
        }
        else if (objet instanceof Arme){
            if(objet instanceof Arc){
                objectJSON.put("type", "arc");
            }
            else if(objet instanceof Epee){
                if(objet instanceof EpeeDeSoldat)
                    objectJSON.put("type", "epeeDeSoldat");
            }
        }
        else if(objet instanceof Projectile){
            if(objet instanceof Fleche){
                if(objet instanceof FlecheSimple){
                    objectJSON.put("type", "flecheSimple");
                }
            }
            objectJSON.put("hitbox", creationHitboxJSON(((Projectile) objet).getHitbox()));
            objectJSON.put("positionOrigine", creationPositionJSON(((Projectile) objet).getPosition()));
            objectJSON.put("position", creationPositionJSON(((Projectile) objet).getPosition()));
            objectJSON.put("vitesse", creationVitesseJSON(((Projectile) objet).getVitesse()));
            objectJSON.put("direction", creationDirectionJSON(((Projectile) objet).getDirection()));
            objectJSON.put("aToucherUneCible", ((Projectile) objet).isaToucherUneCible());
        }
        return objectJSON;
    }

    public JSONArray creationListeArray(ArrayList arrayList){
        JSONArray jsonArray = new JSONArray();
        for(int i = 0 ; i < arrayList.size() ; i++)
            jsonArray.put(arrayList.get(i));
        return jsonArray;
    }

    public JSONObject creationStatsDefense(Defense defense){
        JSONObject defenseJSON = new JSONObject();
        defenseJSON.put("defenseMaximum", defense.getDefenseMaximum());
        defenseJSON.put("defense", defense.getDefense());
        return defenseJSON;
    }
    public JSONObject creationStatsDefenseSpeciale(DefenseSpecial defense){
        JSONObject defenseJSON = new JSONObject();
        defenseJSON.put("defenseMaximumSpecial", defense.getDefenseSpecial());
        defenseJSON.put("defenseSpecial", defense.getDefenseSpecial());
        return defenseJSON;
    }

    public JSONObject creationObjetConteneurJSON(ObjetConteneur objetConteneur){
        JSONObject objetConteneurJSON = new JSONObject();
        objetConteneurJSON.append("taille", objetConteneur.getTailleMax());

        if(objetConteneur instanceof Sac){
            if(objetConteneur instanceof PetitSac)
                objetConteneurJSON.put("type", "petitSac");
        }
        else if(objetConteneur instanceof Carquois)
            objetConteneurJSON.put("type", "carquois");

        JSONArray listeObjetInventaire = new JSONArray();
        objetConteneurJSON.append("listeObjet", listeObjetInventaire);
        Inventaire inventaire = objetConteneur.getInv();

        for(int i = 0 ; i < inventaire.getTailleMax() ; i++){
            if(!inventaire.getEmplacement(i).estVide()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("quantite", inventaire.getEmplacement(i).quantiteObjet());
                jsonObject.put("objet", creationObjetJSON(inventaire.getEmplacement(i).objetDansLemplacement()));
                listeObjetInventaire.put(jsonObject);
            }

        }

        return objetConteneurJSON;
    }

}
