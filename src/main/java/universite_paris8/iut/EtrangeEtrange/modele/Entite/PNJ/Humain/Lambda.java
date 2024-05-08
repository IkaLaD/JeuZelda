package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.PnjOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public class Lambda extends PnjOffensif {
    public Lambda(Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(100, 5, 50, 1, 20, 0.1, monde, x, y, direction, hitbox);
    }

    @Override
    protected double subitDegatPhysique(double attaque)
    {
        return getDefense().getDefenseActuelle() - attaque;
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecial) {
        return getDefenseSpecial().getDefenseSpecialActuelle() - attaqueSpecial;
    }

    @Override
    public void attaque() {

    }

    @Override
    public void action() {
        int d = (int) (Math.random()*4+1);
        switch (d)
        {
            case 1:
                setDirection(Direction.DROITE);
                break;
            case 2:
                setDirection(Direction.GAUCHE);
                break;
            case 3:
                setDirection(Direction.HAUT);
                break;
            case 4:
                setDirection(Direction.BAS);
                break;
        }

        int rdm = (int) (Math.random() * 501) +1;

        if (rdm > 400)
            enlevePv(4);

        seDeplace();
    }
}
