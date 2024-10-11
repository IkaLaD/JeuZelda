package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;

public class Arc implements Arme,Rechargeable
{
    private long derniereApelle;
    private boolean peutTirer;

    private final static int DURABILITE = ConstanteObjet.DURABILITE_ARC;
    private final static int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_ARC;
    private final static long DELAIE_UTILISATION = ConstanteObjet.DELAIE_UTILISATION_ARC;
    private final static int STACK_MAX = ConstanteObjet.STACK_MAX_ARC;

    private int durabilitee;
    private Fleche fleche;

    public Arc()
    {
        this.peutTirer = true;
        this.derniereApelle = -1;
        this.durabilitee = DURABILITE;
        this.fleche = null;
    }

    @Override
    public boolean estUtiliseePar(Entite entite)
    {
        if (peutTirer && fleche != null)
        {
            this.durabilitee--;
            this.fleche.setMonde(entite.getMonde());
            this.fleche.setNewPosition(entite.getPosition().getX(),entite.getPosition().getY());
            this.fleche.setDirection(entite.getDirection());
            this.fleche.setUtilisateur(entite);
            entite.getMonde().ajoutActeur(fleche);
            this.derniereApelle = System.currentTimeMillis();
            entite.getMonde().ajoutRechargeable(this);

            this.peutTirer = false;
            this.fleche = null;
        }
    }


    public void setFleche(Fleche fleche){ this.fleche = fleche; }
    @Override
    public long delaie() {
        return DELAIE_UTILISATION;
    }
    @Override
    public boolean cooldown()
    {
        boolean actionFait = false;

        long apelle = System.currentTimeMillis();

        if (apelle - derniereApelle >= delaie())
        {
            this.derniereApelle = -1;
            this.peutTirer = true;
            actionFait = true;
        }

        return actionFait;
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