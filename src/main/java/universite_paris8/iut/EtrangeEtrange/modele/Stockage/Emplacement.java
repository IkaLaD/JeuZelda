package universite_paris8.iut.EtrangeEtrange.modele.Stockage;


import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

import java.util.ArrayList;
public class Emplacement
{
    private int stackPossible;
    private ArrayList<Objet> objets;

    public Emplacement()
    {
        this.objets = new ArrayList<>();
        this.stackPossible = 1;
    }

    public void ajoutObjet(Objet objet)
    {
        if (this.objets.isEmpty())
        {
            stackPossible = objet.stackMax();
            this.objets.add(objet);
        }
        else if (this.objets.get(0).getClass().equals(objet.getClass()))
        {
            if (this.objets.size()+1 < stackPossible)
                this.objets.add(objet);
        }
    }

    public Objet retourneUnObjet()
    {
        return this.objets.remove(0);
    }

    public ArrayList<Objet> retourneToutLesObject()
    {
        ArrayList<Objet> nvList = new ArrayList<>(this.objets);
        vider();
        return nvList;
    }

    public void vider()
    {
        this.objets.clear();
    }
    public int quantiteObjet()
    {
        return this.objets.size();
    }

    public boolean estVide()
    {
        return this.objets.isEmpty();
    }

    public boolean estDeMemeClass(Objet objet)
    {
        return this.objets.get(0).getClass().equals(objet.getClass());
    }

    public boolean peuEncoreStacker()
    {
        return quantiteObjet()+1 < this.stackPossible;
    }

    public String nomObjet()
    {
        String nom = "";

        if (!this.objets.isEmpty())
            nom = this.objets.get(0).getNom();

        return nom;
    }
}
