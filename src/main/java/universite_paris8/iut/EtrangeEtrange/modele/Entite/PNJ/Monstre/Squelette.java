package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Monstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.PNJ;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.SeDeplacerVers;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueMelee.ParametreAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

public class Squelette extends EntiteOffensif implements PNJ, SeDeplacerVers {

    private Joueur joueur;
    private Aetoile aetoile;
    private Epee epee;
    private long lastPathCalculationTime;

    public Squelette(double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Monde monde, double x, double y, Direction direction, Hitbox hitbox, Joueur joueur, Aetoile aetoile) {
        super( monde,  x,  y,  direction,  pv,  attaque,  defense,  attaqueSpecial ,  defenseSpecial,  vitesse, hitbox);

        this.joueur = joueur;
        this.aetoile = aetoile;
        this.epee = new Epee();
        this.lastPathCalculationTime = System.currentTimeMillis();
    }

    @Override
    public void action() {


        if (detecteJoueurDansRayon(5)){
            System.out.println("detecter");
            seDeplacerVers(joueur.getPosition());
            if (monde.joueurEstDansListe(monde.getActeursDansRayon(getPosition(), 1))){
                attaque(epee);
            }
        }
        else {
            seDeplaceAleatoire();
        }

    }

    private boolean joueurDetecte() {
        System.out.println("grfeg");
        return monde.joueurEstDansListe(monde.getActeursDansRayon(getPosition(),10));
    }

    private boolean detecteJoueurDansRayon(double rayon) {
        Position positionJoueur = getMonde().getJoueur().getPosition();
        double distance = Math.sqrt(Math.pow(positionJoueur.getX() - getPosition().getX(), 2) +
                Math.pow(positionJoueur.getY() - getPosition().getY(), 2));
        return distance <= rayon;
    }

    @Override
    protected double subitDegatPhysique(double degat, double forceEntite) {
        return (degat * forceEntite) / (getDefense() - (degat/6));
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecial, double forceEntite) {
        return (attaqueSpecial * forceEntite) / (getDefense() - (attaqueSpecial/6));
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
        } else {

        }

        // Vérifier si l'entité a atteint la prochaine positionq
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

            System.out.println("10");
            subitAttaque((Dommageable) arme);


        // Utilise l'arme avec les paramètres appropriés
        System.out.println("11");
        arme.utilise(new ParametreAttaqueEpee(this));
    }


    @Override
    public void lanceUnSort(int numSort) {

    }

    @Override
    public void unTour() {
        action();
    }

    @Override
    public void subitCollision(Acteur acteur) {

    }


    @Override
    public String typeActeur() {
        return "Squelette";
    }
}
