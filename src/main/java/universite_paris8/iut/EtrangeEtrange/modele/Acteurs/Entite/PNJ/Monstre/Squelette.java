package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ParametreMonstre;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.PieceOr;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;

public class Squelette extends EntiteOffensif {
    private Joueur joueur;
    private Aetoile aetoile;
    private long lastPathCalculationTime;

    public Squelette(Monde monde, double x, double y, Direction direction, Hitbox hitbox, Joueur joueur, Aetoile aetoile) {
        super(monde, x, y, direction,
                ParametreMonstre.PV_SQUELETTE,
                ParametreMonstre.ATTAQUE_SQUELETTE,
                ParametreMonstre.DEFENSE_SQUELETTE,
                ParametreMonstre.ATTAQUE_SPECIALE_SQUELETTE,
                ParametreMonstre.DEFENSE_SPECIALE_SQUELETTE,
                ParametreMonstre.VITESSE_SQUELETTE,
                hitbox);
        this.joueur = joueur;
        this.aetoile = aetoile;
        this.lastPathCalculationTime = System.currentTimeMillis();
    }





    public void seDeplacerVers(Position joueurPosition) {
        if (aetoile == null) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastPathCalculationTime >= 3000 || aetoile.getChemin().isEmpty()) {
            aetoile.trouverChemin(getPosition(), joueurPosition);
            lastPathCalculationTime = currentTime;
            if (aetoile.getChemin().isEmpty()) {

                return;
            }
        }

        // Obtenir la prochaine position dans le chemin
        Position prochainePosition = aetoile.getChemin().get(0);

        // Calculer la direction vers la prochaine position
        double deltaX = prochainePosition.getX() - getPosition().getX();
        double deltaY = prochainePosition.getY() - getPosition().getY();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            setDirection(deltaX > 0 ? Direction.DROITE : Direction.GAUCHE);
        } else {
            setDirection(deltaY > 0 ? Direction.BAS : Direction.HAUT);
        }

        // Déplacer l'entité si elle peut se déplacer
        if (peutSeDeplacer()) {
            setSeDeplace(true);
            seDeplace(1);
        }

        // Vérifier si l'entité a atteint la prochaine position
        if (positionAtteinte(prochainePosition)) {
            aetoile.getChemin().remove(0); // Supprimer la position atteinte du chemin
            // Ajuster la position à des coordonnées arrondies au dixième
            setPosition(Math.round(getPosition().getX() * 10) / 10.0, Math.round(getPosition().getY() * 10) / 10.0);
        }


    }

    private boolean positionAtteinte(Position position) {
        return Math.abs(getPosition().getX() - position.getX()) < 0.1 && Math.abs(getPosition().getY() - position.getY()) < 0.1;
    }

    @Override
    public void attaque(Arme arme) {

    }

    @Override
    public void lanceUnSort(int numSort) {

    }

    @Override
    public void unTour() {
        if (monde.estDansRayon(getPosition(), 6)){
            seDeplacerVers(joueur.getPosition());
            if (monde.estDansRayon(getPosition(), 2)){
                attaque(new Arc());
            }
        }
        else {
            seDeplaceAleatoire();
        }
    }

    public void seDeplaceAleatoire(){
        if (peutSeDeplacer()) {
            if(Math.random()>0.95){
                setSeDeplace(false);
            }
            else {
                seDeplace(1);
            }
        }
        else if(Math.random()>0.95)
            setSeDeplace(true);

        if(Math.random()>0.95)
            setDirection(Direction.randomDirection());
    }



    @Override
    public String typeActeur() {
        return "Squelette";
    }

    @Override
    public void dropApresMort() {
        double x = getPosition().getX();
        double y = getPosition().getY();
        getMonde().ajouterDropAuSol(new DropAuSol(new PieceOr(), 1, new Position(x, y)));
        System.out.println("passage");
    }

    @Override
    public boolean estUnEnemie() {
        return true;
    }
}
