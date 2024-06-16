package universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable.Marchand;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;

public class ActionAchat extends Action
{
    private Marchand marchand;
    private Objet objet;
    private Joueur joueur;
    private ActionVendre marchander;
    public ActionAchat(Marchand marchand, Objet objet, Joueur joueur, ActionVendre marchander)
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
                int resteAPayer = objet.prixAchat();

                for(int i = 0 ; i < joueur.getSac().getTailleMax() && resteAPayer!=0 ; i++){
                    if(joueur.getSac().getEmplacement(i).nomObjet().equals("pieceor")) {
                        int quantite = joueur.getSac().getEmplacement(i).quantiteObjet();
                        for(int j = 0 ; j <  quantite &&  resteAPayer!=0; j++){
                            joueur.getSac().getEmplacement(i).enleveObjet();
                            resteAPayer--;
                        }
                    }
                }

            }
        }

        return this.marchander.execute();
    }
}
