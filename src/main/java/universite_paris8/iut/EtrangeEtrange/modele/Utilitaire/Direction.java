package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

public enum Direction
{
    GAUCHE(-1,0),
    DROITE(1,0),
    HAUT(0,-1),
    BAS(0,1);


    int x,y;
    private Direction(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
