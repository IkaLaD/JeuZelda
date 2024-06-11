package universite_paris8.iut.EtrangeEtrange.modele.Exeptions;

public class PositionInvalideExeption extends RuntimeException
{
    public PositionInvalideExeption(String message)
    {
        super(message);
    }
}
