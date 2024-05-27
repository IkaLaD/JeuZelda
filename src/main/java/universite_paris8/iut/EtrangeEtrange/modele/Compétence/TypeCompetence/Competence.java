package universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

public abstract class Competence
{
    private boolean estDebloquer;
    protected int niveauCompetence;
    public Competence()
    {
        this.estDebloquer = false;
        this.niveauCompetence = 0;
    }

    public boolean estDebloquer()
    {
        return this.estDebloquer;
    }

    public void debloquer()
    {
        this.estDebloquer = true;
    }

    public abstract int niveauMax();
    public abstract void monterDeNiveau(Joueur joueur);


}
