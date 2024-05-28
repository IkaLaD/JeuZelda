package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.Fleche;

public class Carquois extends ObjetConteneur<Fleche>
{

    public Carquois() {
        super(1);
    }

    @Override
    public String getNom() {
        return "Carquois";
    }

    @Override
    public int stackMax() {
        return 1;
    }


    public Fleche retourneUneFleche()
    {
        return retourneObjet(0);
    }

    @Override
    public Fleche objetALemplacement(int emplacement) {
        return super.objetALemplacement(emplacement);
    }
}
