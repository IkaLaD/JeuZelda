package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Exeptions.PositionInvalideExeption;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

public class Position {
    private DoubleProperty x;
    private DoubleProperty y;

    public Position(double x, double y)
    {
        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();

        setX(x);
        setY(y);
    }

    public double getX() {
        return x.get();
    }

    public double getY() {
        return y.get();
    }

    public void setX(double x)
    {
        if (x < 0 || x > Monde.getSizeMondeLargeur())
            throw new PositionInvalideExeption("x hors map");

        this.x.set(x);
    }

    public void setY(double y)
    {
        if (y < 0 || y > Monde.getSizeMondeHauteur())
            throw new PositionInvalideExeption("y hors map");

        this.y.set(y);
    }

    public DoubleProperty getXProperty() {
        return x;
    }

    public DoubleProperty getYProperty() {
        return y;
    }

    public double distance(Position other) {
        double dx = this.getX() - other.getX();
        double dy = this.getY() - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString()
    {
        return x.get() + " - "+y.get();
    }
}
