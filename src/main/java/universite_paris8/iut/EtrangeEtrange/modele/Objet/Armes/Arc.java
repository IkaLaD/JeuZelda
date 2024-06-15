package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;

public class Arc implements Arme,Rechargeable
{
    private long tourMonde;
    private boolean peuTirer;

    private final static int DURABILITE = ConstanteObjet.DURABILITE_ARC;
    private final static int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_ARC;
    private final static long DELAIE_UTILISATION = ConstanteObjet.DELAIE_UTILISATION_ARC;
    private final static int STACK_MAX = ConstanteObjet.STACK_MAX_ARC;

    private int durabilitee;
    private Fleche fleche;

    public Arc()
    {
        this.peuTirer = true;
        this.tourMonde = 0;
        this.durabilitee = DURABILITE;
        this.fleche = null;
    }

    @Override
    public void utilise(Entite entite)
    {
        if (peuTirer)
        {
            this.durabilitee--;
            this.fleche.setMonde(entite.getMonde());
            this.fleche.setNewPosition(entite.getPosition().getX(),entite.getPosition().getY());
            this.fleche.setDirection(entite.getDirection());

            entite.getMonde().ajoutActeur(fleche);
            entite.getMonde().ajoutRechargeable(this);

            this.peuTirer = false;
            this.fleche = null;
        }
    }


    public void setFleche(Fleche fleche){ this.fleche = fleche; }
    @Override
    public long delaie() {
        return DELAIE_UTILISATION;
    }
    @Override
    public void cooldown() { peuTirer = true;}
    @Override
    public void setTourApelle(long tourApelle) {
        this.tourMonde = tourApelle;
    }
    @Override
    public long getTourApelle() {
        return tourMonde;
    }
    @Override
    public String getNom() {
        return "arc";
    }
    @Override
    public int stackMax() {
        return STACK_MAX;
    }
    @Override
    public double durabilitee() {
        return durabilitee;
    }
    @Override
    public int prixAchat() {
        return PRIX_ACHAT;
    }

}
