package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public interface PNJ
{
    void action();
    Prompt prompt();

    public Position getPosition();
}
