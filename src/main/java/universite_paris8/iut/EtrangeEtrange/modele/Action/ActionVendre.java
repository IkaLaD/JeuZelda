package universite_paris8.iut.EtrangeEtrange.modele.Action;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable.Marchand;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Emplacement;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

public class ActionVendre extends Action
{
    private Marchand marchand;

    public ActionVendre(Marchand marchand)
    {
        this.marchand = marchand;
    }

    @Override
    public Prompt execute()
    {
        Prompt racine = new Prompt("Voici ce que j'ai",null);

        for (Emplacement<Objet> objets : marchand.getMarchandise().getInventaire())
        {
            ArrayList<Objet> obs = objets.enleverToutLesObjets();

            for (Objet objet : obs)
            {
                racine.ajoutPrompt(new Prompt("Ta fais une bonne affaire !",new ActionAchat(marchand,objet,marchand.getMonde().getJoueur(),this)),objet.getNom() +"     ["+objet.prixAchat()+"]");
                System.out.println("nv objet");
            }
        }



        return racine;
    }
}
