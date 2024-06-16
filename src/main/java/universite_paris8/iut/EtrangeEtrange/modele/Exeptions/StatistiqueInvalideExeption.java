package universite_paris8.iut.EtrangeEtrange.modele.Exeptions;

public class StatistiqueInvalideExeption extends RuntimeException
{
    public StatistiqueInvalideExeption(String message)
    {
        super(message);
    }
}
