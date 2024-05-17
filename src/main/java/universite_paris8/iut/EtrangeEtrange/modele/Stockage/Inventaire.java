package universite_paris8.iut.EtrangeEtrange.modele.Stockage;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Emplacement;

import java.util.ArrayList;
import java.util.Date;

public class Inventaire<T extends Objet> implements Conteneur<T>
{
    private int taille;
    private Emplacement<T>[] inventaire;

    public Inventaire(int taille)
    {
        this.taille = taille;
        this.inventaire = (Emplacement<T>[]) new Emplacement[taille];

        for (int i = 0;i < this.inventaire.length;i++)
            this.inventaire[i] = new Emplacement<>();
    }

    public boolean ajoutItem(T objet)
    {
        boolean ajoutReussi = false;

        Emplacement<T> emplacement = null;

        if (objet.stackMax() > 1)
            emplacement = chercheEmplacementStackable(objet);

        if (emplacement == null)
            emplacement = chercheEmplacementVide();


        if (emplacement != null) {
            emplacement.ajoutObjet(objet);
            ajoutReussi = true;
        }

        return ajoutReussi;
    }
    public Emplacement<T> chercheEmplacementStackable(T objet)
    {
        Emplacement<T> emplacement = null;
        boolean emplacementTrouver = false;

        for (int i = 0; i < this.inventaire.length && !emplacementTrouver ;i++)
        {
            Emplacement<T> emplacementAverifier = this.inventaire[i];

            if (emplacementAverifier.peuEncoreStacker() &&emplacementAverifier.estDeMemeClass(objet))
            {
                emplacement = emplacementAverifier;
                emplacementTrouver = true;
            }
        }

        return emplacement;
    }
    public Emplacement<T> chercheEmplacementVide()
    {
        Emplacement<T> emplacement = null;
        boolean emplacementTrouver = false;

        for (int i = 0; i < this.inventaire.length && !emplacementTrouver;i++)
        {
            Emplacement<T> emplacementAverifier = this.inventaire[i];

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




    public ArrayList<T> retourneObjets(int emplacement) {
        ArrayList<T> objets = new ArrayList<>();

        if (emplacement >= 0 && emplacement < this.inventaire.length && !this.inventaire[emplacement].estVide())
            objets.addAll(this.inventaire[emplacement].retourneToutLesObject());

        return objets;
    }


    public T retourneObjet(int emplacement)
    {
        T objet = null;

        if (emplacement >= 0 && emplacement < this.inventaire.length && !this.inventaire[emplacement].estVide())
            objet = this.inventaire[emplacement].retourneUnObjet();

        return objet;
    }


    public <U extends Objet> U trouveObjet(Class<U> typeObjet)
    {
        U objet = null;

        for (int i = 0; i < inventaire.length && objet == null; i++)
        {
            Emplacement<T> emplacement = inventaire[i];

            if (emplacement.estDuMemeType(typeObjet))
                objet = typeObjet.cast(emplacement.retourneUnObjet());

        }

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