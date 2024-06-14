package universite_paris8.iut.EtrangeEtrange.TestJunit;


import org.junit.Test;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.GestionPrompt;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.Prompt;

public class PromptPnjTest
{
    private GestionPrompt gestionPrompt;

    private void initGestionPrompt()
    {

        Prompt root = new Prompt("Je suis la racine");
        Prompt enfant1 = new Prompt("Je suis l'enfant de la racine 1");

        root.ajoutPrompt(enfant1,null);

        gestionPrompt = new GestionPrompt(root);
    }


    @Test
    public void testLogiquePrompt()
    {
        initGestionPrompt();
        System.out.println(gestionPrompt.getPrompt().getTextePrompt());
        gestionPrompt.promptSuivant("");
        System.out.println(gestionPrompt.getPrompt().getTextePrompt());

    }
}
