package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Monstre;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Orbe;

public class PatternLanceOrbe extends Pattern
{
    @Override
    public void effectue(NPEs npe) {
        Orbe orbe = new Orbe();
        orbe.utiliseePar(npe);
    }
}
