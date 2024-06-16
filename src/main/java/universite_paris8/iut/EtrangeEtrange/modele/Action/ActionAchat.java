package universite_paris8.iut.EtrangeEtrange.modele.Action;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Marchand;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;

public class ActionAchat extends Action
{
    private Marchand marchand;
    private Objet objet;
    private Joueur joueur;
    private Marchander marchander;
    public ActionAchat(Marchand marchand, Objet objet, Joueur joueur, Marchander marchander)
    {
        this.joueur = joueur;
        this.objet = objet;
        this.marchand = marchand;
        this.marchander = marchander;
    }



    @Override
    public Prompt execute()
    {
        if (!joueur.getSac().estPlein())
        {
            if (joueur.getPiece() >= objet.prixAchat())
            {
                joueur.getSac().ajoutItem(objet);
                //joueur.enlevePiece(objet.prixAchat());
                //marchand.getSac().enleverObjet(objet);
            }
        }

        return this.marchander.execute();
    }
}