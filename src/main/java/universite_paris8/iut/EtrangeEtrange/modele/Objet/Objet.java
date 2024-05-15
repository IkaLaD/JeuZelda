package universite_paris8.iut.EtrangeEtrange.modele.Objet;

public abstract class Objet {
    private static int ID = 1;
    private int objetID;


    public Objet()
    {
        this.objetID = ID++;
    }

    public abstract String getNom();
    public abstract int stackMax();

    public int getObjetID() {
        return this.objetID;
    }
}
