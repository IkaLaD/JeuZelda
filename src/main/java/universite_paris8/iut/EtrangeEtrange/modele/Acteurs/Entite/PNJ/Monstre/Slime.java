package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.PieceOr;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Slime extends EntiteOffensif {
    /**
     * Crée une nouvelle entité offensif.
     *
     * @param monde     Le monde dans lequel l'entité existe.
     * @param x         La position horizontale de l'entité.
     * @param y         La position verticale de l'entité.
     * @param direction La direction dans laquelle l'entité est orientée.
     * @param hitbox    La hitbox de l'entité.
     */

    private Aetoile aetoile;
    private long lastPathCalculationTime;
    private Joueur joueur;

    public Slime(Monde monde, double x, double y, Direction direction, Hitbox hitbox, Aetoile aetoile, Joueur joueur) {
        super(monde, x, y, direction, 100, 30, 20, 20, 50, 0.005, hitbox);
        this.aetoile = aetoile;
        this.lastPathCalculationTime = System.currentTimeMillis();
        this.joueur = joueur;
    }

    @Override
    public void unTour()
    {
        seDeplacerVers(joueur.getPosition());
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


    public void deplacementAleatoire(){
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
    public void subitCollision(Acteur acteur){

    }

    @Override
    public String typeActeur() {
        return "slime";
    }

    @Override
    public void dropApresMort() {
        double x = getPosition().getX();
        double y = getPosition().getY();
        getMonde().ajouterDropAuSol(new DropAuSol(new PieceOr(), 1, new Position(x, y)));
    }

    @Override
    public boolean estUnEnemie() {
        return true;
    }

    @Override
    public void subitAttaque(Dommageable causeDegat) {
        enlevePv(20);
    }



    @Override
    protected double subitDegatPhysique(double attaqueEntite, double degatArme) {
        return 0;
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecialEntite, double degatArme) {
        return 0;
    }

    @Override
    public void attaque(Arme arme) {

    }

    @Override
    public Prompt getPrompt()
    {
       return  null;
    }

    @Override
    public void lanceUnSort(int numSort) {

    }
}
