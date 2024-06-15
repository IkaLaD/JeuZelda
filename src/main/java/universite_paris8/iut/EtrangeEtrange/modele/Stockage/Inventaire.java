package universite_paris8.iut.EtrangeEtrange.modele.Stockage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Conteneur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;

import java.util.ArrayList;

public class Inventaire<T extends Objet> implements Conteneur<T>
{
    private IntegerProperty taille;
    private Emplacement<T>[] inventaire;

    public Inventaire(int taille)
    {
        this.taille = new SimpleIntegerProperty(taille);
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
        return this.taille.get();
    }
    public IntegerProperty getTailleMaxProperty(){
        return this.taille;
    }

    public ArrayList<T> enleverObjet(int emplacement) {
        ArrayList<T> objets = new ArrayList<>();

        if (emplacement >= 0 && emplacement < this.inventaire.length && !this.inventaire[emplacement].estVide())
            objets.addAll(this.inventaire[emplacement].enleverToutLesObjets());

        return objets;
    }


    public T objetALemplacement(int emplacement){
        T objet = null;

        if (emplacement >= 0 && emplacement < this.inventaire.length && !this.inventaire[emplacement].estVide())
            objet = this.inventaire[emplacement].objetDansLemplacement();

        return objet;
    }


    public T retourneObjet(int emplacement)
    {
        T objet = null;

        if (emplacement >= 0 && emplacement < this.inventaire.length && !this.inventaire[emplacement].estVide())
            objet = this.inventaire[emplacement].enleveObjet();

        return objet;
    }


    public <U extends Objet> U trouveObjet(Class<U> typeObjet)
    {
        U objet = null;

        for (int i = 0; i < inventaire.length && objet == null; i++)
        {
            Emplacement<T> emplacement = inventaire[i];

            if (emplacement.estDuMemeType(typeObjet))
                objet = typeObjet.cast(emplacement.enleveObjet());

        }

        return objet;
    }

    public Emplacement<T> getEmplacement(int emplacement){
        return inventaire[emplacement];
    }

    public Emplacement<T>[] getInventaire(){return this.inventaire;}

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