package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Monstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

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
    public Slime(Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(monde, x, y, direction, 100, 30, 20, 20, 50, 0.025, hitbox);
    }

    @Override
    public void unTour()
    {
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
    public void subitCollision(Acteur acteur) {

    }

    @Override
    public String typeActeur() {
        return "slime";
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
    public void dropApresMort() {

    }

    @Override
    public void attaque(Arme arme) {

    }

    @Override
    public Prompt getPrompt()
    {
        Prompt prompt = new Prompt("Je suis un slime");
        Prompt prompt1 = new Prompt("OKKKKKKKKKKKKKKKKKKKK");
        Prompt prompt2 = new Prompt("aaaaaaaaaaaaaaaHEHG");
        prompt.ajoutPrompt(prompt1,"teeeeeeeeeee");
        prompt.ajoutPrompt(prompt2,"eegzgezgzgzgz");

        return prompt;
    }

    @Override
    public void lanceUnSort(int numSort) {

    }
}
