package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.PieceOr;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ParametreMonstre;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Slime extends Entite {
    /**
     * Crée une nouvelle entité offensif.
     *
     * @param monde     Le monde dans lequel l'entité existe.
     * @param x         La position horizontale de l'entité.
     * @param y         La position verticale de l'entité.
     * @param direction La direction dans laquelle l'entité est orientée.
     * @param hitbox    La hitbox de l'entité.
     */


    public Slime(Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(monde, x, y, direction,
                ParametreMonstre.PV_SLIME,
                ParametreMonstre.DEFENSE_SLIME,
                ParametreMonstre.DEFENSE_SPECIALE_SLIME,
                ParametreMonstre.VITESSE_SLIME,
                hitbox);
    }

    @Override
    public void unTour()
    {
        deplacementAleatoire();
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
    public String typeActeur() {
        return "slime";
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

    @Override
    public Prompt getPrompt()
    {
       return  null;
    }

}
