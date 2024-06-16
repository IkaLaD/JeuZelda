package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Humanoide;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action.ActionVendre;

import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action.Soigner;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.*;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.TypeObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Emplacement;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;
import java.util.Random;

public class Marchand extends Humanoide implements Dropable
{

    private int cycle;
    private Sac sac;

    private Prompt prompt;

    public Marchand(Monde monde, double x, double y, Direction direction) {
        super(monde, x, y, direction, 10, 10, 10, 10, 10, 0.5, new Hitbox(0.5,0.5), null, null, null);
        this.cycle = 0;
        this.sac = new Sac();

        initPrompt();

    }

    @Override
    public void unTour()
    {
        cycle++;

        if (cycle % 2000 == 0)
        {
            remplieAleatoirementMarchandise();
            sac.ajoutItem(new Epee());
            sac.ajoutItem(new Arc());
            sac.ajoutItem(new Potion());
            cycle = 0;
        }
    }

    @Override
    public void subitCollision(Acteur acteur) {

    }

    @Override
    public String typeActeur() {
        return "marchand";
    }

    @Override
    public void dropApresMort() {

    }

    @Override
    public boolean estUnEnemie() {return false;}

    @Override
    public  void subitAttaque(Dommageable causeDegat, EntiteOffensif entiteOffensif){}

    private void initPrompt()
    {
        Prompt racine = new Prompt("Bonjour ! Que vous ramene ici ?",null);

        Prompt reponseRacine1 = new Prompt("Voici ce que je propose.",new ActionVendre(this));



        Prompt reponseRacine2 = new Prompt("Vous avez entendu parlé du monstre qui rôde dans dans les coins",null);


        racine.ajoutPrompt(reponseRacine1,"J'aimerais marchander un peu avec avec vous");
        racine.ajoutPrompt(reponseRacine2,"J'aimerais parler un peu..");

        Prompt reponseReponceRacine2 = new Prompt("Faite attention...   D'ailleur, attendez je vais vous soigner !",new Soigner(monde.getJoueur()));
        reponseRacine2.ajoutPrompt(reponseReponceRacine2,"");

        prompt = racine;
    }





    @Override
    public void attaque() {

    }

    @Override
    public void lanceUnSort(int numSort) {

    }




    private void remplieAleatoirementMarchandise()
    {
        Random rdm = new Random();
        TypeObjet[] typeObjets = TypeObjet.values();

        for (int i = 0;i<5;i++)
        {
            TypeObjet typeObjet = typeObjets[rdm.nextInt(typeObjets.length)];
            Objet objet = TypeObjet.nouvelleInstance(typeObjet);

            this.sac.ajoutItem(objet);

            if(objet.stackMax() > 3)
            {
                for (int j = 0; j < rdm.nextInt(objet.stackMax()/2);j++)
                    this.sac.ajoutItem(TypeObjet.nouvelleInstance(typeObjet));
            }

        }
    }

    @Override
    public void drop() {
        for (Emplacement<Objet> objets : sac.getInventaire())
        {
            ArrayList<Objet> obs = objets.enleverToutLesObjets();

            for (Objet objet : obs)
            {
                monde.ajouterDropAuSol(new DropAuSol(objet, obs.size(), new Position(position.getX(), position.getY())));
            }
        }



    }

    public Sac getMarchandise()
    {
        return sac;
    }

    public Prompt getPrompt(){
        return prompt;
    }

}
