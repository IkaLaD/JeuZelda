package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import java.util.ArrayList;
import java.util.Set;

public class Direction
{
    public static final Direction GAUCHE = new Direction(-1,0);
    public static final Direction DROITE = new Direction(1,0);
    public static final Direction HAUT = new Direction(0,-1);
    public static final Direction BAS = new Direction(0,1);


    private int x,y;
    private Direction(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }


    public final static Direction calculeDirection(Set<Direction> directions)
    {
        int x = 0;
        int y = 0;

        for (Direction direction : directions)
        {
            x += direction.getX();
            y += direction.getY();
        }

        return new Direction(x,y);
    }



    public static ArrayList<Direction> getDirections()
    {
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(GAUCHE);
        directions.add(DROITE);
        directions.add(HAUT);
        directions.add(BAS);

        return directions;
    }


}
