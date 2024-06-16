package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FabriquePnj
{
    public static void fabriquePnj(Class<? extends Acteur> typePnj, int nombre, Monde monde, Position position)
    {
        try
        {
            Constructor<? extends Acteur> constructor = typePnj.getDeclaredConstructor(Monde.class,double.class,double.class,Direction.class);

            for (int i = 0; i < nombre; i++)
            {
                Acteur pnj = constructor.newInstance(monde,position.getX()+(Math.random()*2+0.5),position.getY()+(Math.random()*2+0.5),Direction.BAS);
                monde.ajoutActeur(pnj);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }





}
