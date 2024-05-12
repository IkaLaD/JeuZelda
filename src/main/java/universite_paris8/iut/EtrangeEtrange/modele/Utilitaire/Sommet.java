package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

import java.util.ArrayList;

public class Sommet
{
    private int x,y;
    public Sommet(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    public int getY() {
        return y;
    }

    public int getX()
    {
        return x;
    }
}

