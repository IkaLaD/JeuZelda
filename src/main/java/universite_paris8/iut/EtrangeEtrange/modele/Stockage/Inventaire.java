package universite_paris8.iut.EtrangeEtrange.modele.Stockage;


import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

import java.util.ArrayList;

public class Inventaire implements Conteneur
{
    private int taille;
    private Emplacement[] inventaire;

    public Inventaire(int taille)
    {
        this.taille = taille;
        this.inventaire = new Emplacement[taille];

        for (int i = 0;i < this.inventaire.length;i++)
            this.inventaire[i] = new Emplacement();
    }

    public void ajoutItem(Objet objet)
    {
        Emplacement emplacement = null;

        if (objet.stackMax() > 1)
            emplacement = chercheEmplacementStackable(objet);

        if (emplacement == null)
            emplacement = chercheEmplacementVide();


        if (emplacement != null)
            emplacement.ajoutObjet(objet);
    }
    public Emplacement chercheEmplacementStackable(Objet objet)
    {
        Emplacement emplacement = null;
        boolean emplacementTrouver = false;

        for (int i = 0; i < this.inventaire.length && !emplacementTrouver ;i++)
        {
            Emplacement emplacementAverifier = this.inventaire[i];

            if (emplacementAverifier.peuEncoreStacker() &&emplacementAverifier.estDeMemeClass(objet))
            {
                emplacement = emplacementAverifier;
                emplacementTrouver = true;
            }
        }

        return emplacement;
    }
    public Emplacement chercheEmplacementVide()
    {
        Emplacement emplacement = null;
        boolean emplacementTrouver = false;

        for (int i = 0; i < this.inventaire.length && !emplacementTrouver;i++)
        {
            Emplacement emplacementAverifier = this.inventaire[i];

            if (emplacementAverifier.estVide())
            {
                emplacement = emplacementAverifier;
                emplacementTrouver = true;
            }
        }

        return emplacement;
    }

    public void vider() {
        for (Emplacement emplacement : inventaire) {
            emplacement.vider();
        }
    }
    public boolean estPlein()
    {
        boolean plein = true;

        for (int i = 0; i < inventaire.length; i++)
        {
            if (inventaire[i].estVide())
                plein = false;
        }

        return plein;
    }
    public boolean estVide()
    {
        boolean vide = true;

        for (int i = 0; i < inventaire.length; i++)
        {
            if (!inventaire[i].estVide())
                vide = false;
        }

        return vide;
    }

    public int nombresObjets()
    {
        int total = 0;
        for (Emplacement emplacement : inventaire) {
            total += emplacement.quantiteObjet();
        }
        return total;
    }

    public int getTailleMax()
    {
        return this.taille;
    }


    public ArrayList<Objet> retourneObjets(int emplacement) {
        ArrayList<Objet> objets = new ArrayList<>();

        if (emplacement >= 0 && emplacement < this.inventaire.length && !this.inventaire[emplacement].estVide())
            objets.addAll(this.inventaire[emplacement].retourneToutLesObject());

        return objets;
    }


    public Objet retourneObjet(int emplacement)
    {
        Objet objet = null;

        if (emplacement >= 0 && emplacement < this.inventaire.length && !this.inventaire[emplacement].estVide())
            objet = this.inventaire[emplacement].retourneUnObjet();

        return objet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventaire{");
        for (Emplacement emplacement : inventaire) {
            if (!emplacement.estVide()) {
                sb.append(emplacement.nomObjet());
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }

}
