package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Squelette;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque.SortilegePluitDeFleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Orbe;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ParametreMonstre;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class RoiSquelette extends EntiteOffensif
{

    private long dernierTempsAttaque;
    private long delaiAttaque = 200;
    private Position positionInitiale;
    private int etapeAttaque;
    private Position positionMilieu;
    private Position position5_2;
    private boolean joueurDetecte = false;
    private double distanceDetection = 5.0;

    public RoiSquelette(Monde monde, double x, double y, Direction direction) {
        super(monde, x, y, direction,
                ParametreMonstre.PV_ROI_SQUELETTE,
                ParametreMonstre.ATTAQUE_ROI_SQUELETTE,
                ParametreMonstre.DEFENSE_ROI_SQUELETTE,
                ParametreMonstre.ATTAQUE_SPECIALE_ROI_SQUELETTE,
                ParametreMonstre.DEFENSE_SPECIALE_ROI_SQUELETTE,
                ParametreMonstre.VITESSE_ROI_SQUELETTE,
                new Hitbox(1, 1));
        this.dernierTempsAttaque = System.currentTimeMillis();
        this.positionInitiale = new Position(x, y);
        this.etapeAttaque = 0;
        this.positionMilieu = new Position(5.5, 27.5);
        this.position5_2 = new Position(2, 27.5);
        setPosition(x, y); // Positionnement initial du Roi Squelette
    }


    @Override
    public void attaque() {
        Epee epee = new Epee();
        epee.utilise(this);
    }

    @Override
    public void lanceUnSort(int numSort) {
        Sortilege sortilege = new SortilegePluitDeFleche();
        sortilege.utilise(this);
    }

    @Override
    public void unTour() {
        // Vérifie si le joueur a été détecté
        if (!joueurDetecte) {
            if (detecteJoueurDansRayon(distanceDetection)) {
                joueurDetecte = true;
                setSeDeplace(true);
            } else {
                return;
            }
        }


        long tempsActuel = System.currentTimeMillis();
        if (tempsActuel - dernierTempsAttaque >= delaiAttaque)
        {

            switch (etapeAttaque) {
                case 0:

                    seDeplacerVers(positionMilieu);
                    if (positionAtteinte(positionMilieu)) {
                        grandeAttaqueCirculaire();
                        etapeAttaque++;
                    }
                    break;
                case 1:
                    seDeplacerVers(position5_2);
                    if (positionAtteinte(position5_2)) {

                        new Orbe().utilise(this);



                        etapeAttaque++;
                    }
                    break;
                case 2:
                    seDeplacerVers(positionMilieu);
                    if (positionAtteinte(positionMilieu)) {
                        invoquerSquelettes();
                        etapeAttaque++;
                    }
                    break;
                case 3:
                    seDeplacerVers(position5_2);
                    if (positionAtteinte(position5_2)) {
                        grandeAttaqueCirculaire();
                        etapeAttaque = 0; // Recommencer le cycle
                    }
                    break;
            }
            dernierTempsAttaque = tempsActuel;
        }
    }




    // Détecte si le joueur est dans un certain rayon autour du Roi Squelette
    private boolean detecteJoueurDansRayon(double rayon) {
        Position positionJoueur = getMonde().getJoueur().getPosition();
        double distance = Math.sqrt(Math.pow(positionJoueur.getX() - getPosition().getX(), 2) +
                Math.pow(positionJoueur.getY() - getPosition().getY(), 2));
        return distance <= rayon;
    }

    // Effectue une grande attaque circulaire
    private void grandeAttaqueCirculaire() {
        Position centre = getPosition();
        double rayon = 1.5; // Rayon d'exemple pour l'attaque circulaire
        // Vérifie si la hitbox du joueur est dans le rayon de l'attaque
        if (getMonde().getJoueur().getHitbox().estDansCercle(centre, rayon)) {
        }
    }

    // Invoque des squelettes pour aider le Roi Squelette
    private void invoquerSquelettes()
    {
        Position positionHaut = new Position(getPosition().getX(), getPosition().getY()-2);
        Position positionBas = new Position(getPosition().getX(), getPosition().getY()+2);
        Squelette squeletteGauche = new Squelette( getMonde(), positionHaut.getX(), positionHaut.getY(), Direction.BAS, new Hitbox(0.5, 0.5), getMonde().getJoueur(), new Aetoile(getMonde()));
        Squelette squeletteDroite = new Squelette( getMonde(), positionBas.getX(), positionBas.getY(), Direction.BAS, new Hitbox(0.5, 0.5), getMonde().getJoueur(), new Aetoile(getMonde()));
        getMonde().ajoutActeur(squeletteGauche);
        getMonde().ajoutActeur(squeletteDroite);
    }

    // Déplace le Roi Squelette vers une destination donnée
    private void seDeplacerVers(Position destination)
    {
        double deltaX = destination.getX() - getPosition().getX();
        double deltaY = destination.getY() - getPosition().getY();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            setDirection(deltaX > 0 ? Direction.DROITE : Direction.GAUCHE);
        } else {
            setDirection(deltaY > 0 ? Direction.BAS : Direction.HAUT);
        }
            seDeplace(1);
    }

    // Vérifie si le Roi Squelette a atteint une certaine position
    private boolean positionAtteinte(Position position) {return Math.abs(getPosition().getX() - position.getX()) < 0.1 && Math.abs(getPosition().getY() - position.getY()) < 0.1;}


    @Override
    public String typeActeur() {
        return "roisquelette";
    }

    @Override
    public void dropApresMort() {TypeCompetence.COURIR.getCompetence().monterDeNiveau(monde.getJoueur());}

    @Override
    public boolean estUnEnemie() {
        return true;
    }




}
