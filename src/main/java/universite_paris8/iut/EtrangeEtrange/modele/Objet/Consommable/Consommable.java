package universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreConsomable.ParametreActionConsomable;

public abstract class Consommable extends Objet implements Utilisable
{
    public abstract void consommer(ParametreActionConsomable param);

}
