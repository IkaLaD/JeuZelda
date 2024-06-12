package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain.HumainPNJ;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Interagisable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.PetitSac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.TypeObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

import java.util.Random;

public class Marchand extends HumainPNJ implements Interagisable
{

    private int cycle;
    private Sac sac;
    public Marchand(Monde monde, double x, double y, Direction direction, double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Hitbox hitbox, Sac sac, Objet objetMainGauche, Objet objetMainDroite) {
        super(monde, x, y, direction, pv, attaque, defense, attaqueSpecial, defenseSpecial, vitesse, hitbox, sac, objetMainGauche, objetMainDroite);
        this.cycle = 0;
        this.sac = new PetitSac();
    }

    @Override
    public void unTour()
    {
        cycle++;

        if (cycle % 2000 == 0)
            remplieAleatoirementMarchandise();


    }

    @Override
    public void subitCollision(Acteur acteur) {

    }

    @Override
    public String typeActeur() {
        return null;
    }

    @Override
    public void subitDegat(Dommageable causeDegat) {

    }

    @Override
    public void attaque(Arme arme) {

    }



    @Override
    public void lanceUnSort(int numSort) {

    }

    @Override
    public void action() {

    }

    @Override
    public Prompt prompt() {
        return null;
    }

    @Override
    public void interagir(Joueur joueur)
    {

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
}
