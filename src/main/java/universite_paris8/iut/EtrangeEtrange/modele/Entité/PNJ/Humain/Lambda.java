package universite_paris8.iut.EtrangeEtrange.modele.Entité.PNJ.Humain;

import universite_paris8.iut.EtrangeEtrange.modele.Entité.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Entité.PNJ.PnjOffensif;
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
        Direction direction;

        switch (d)
        {
            case 1:
                direction = Direction.DROITE;
                setDirection(Direction.DROITE);
                break;
            case 2:
                direction = Direction.GAUCHE;
                setDirection(Direction.GAUCHE);
                break;
            case 3:
                direction = Direction.HAUT;
                setDirection(Direction.HAUT);
                break;
            default:
                direction = Direction.BAS;
                setDirection(Direction.BAS);
                break;
        }


        int rdm = (int) (Math.random() * 501) +1;

        if (rdm > 240 && rdm < 247)
            enlevePv(4);



        seDeplace(direction);


    }
}
