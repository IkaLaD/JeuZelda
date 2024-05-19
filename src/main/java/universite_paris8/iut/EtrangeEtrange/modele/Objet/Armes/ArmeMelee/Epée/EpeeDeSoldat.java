package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée;

import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionSurObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.GestionDegat.DegatParEpee;

public class EpeeDeSoldat extends Epee
{
    public EpeeDeSoldat() {
        super(new Hitbox(1,1));
    }

    @Override
    public double degatPhysique() {
        return 2;
    }

    @Override
    public double degatSpecial() {
        return 0;
    }

    @Override
    public double portee() {
        return 0.8;
    }

    @Override
    public double angle() {
        return 180;
    }

    @Override
    public double delaieEntreCoup() {
        return 1;
    }

    @Override
    public String getNom() {
        return "Epée De Soldat";
    }



}
