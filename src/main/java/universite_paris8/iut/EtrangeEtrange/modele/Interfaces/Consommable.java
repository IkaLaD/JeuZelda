package universite_paris8.iut.EtrangeEtrange.modele.Interfaces;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreConsomable.ParametreActionConsomable;

public interface Consommable extends Objet, Utilisable
{
    public abstract void consommer(ParametreActionConsomable param);

}
