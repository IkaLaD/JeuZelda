package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte;

import java.util.ArrayList;

public class GestionPrompt
{
    private Prompt prompt;

    public GestionPrompt(Prompt promptRacine)
    {
        this.prompt = promptRacine;
    }

    public void promptSuivant(String choix)
    {
        prompt = prompt.promptSuivant(choix);
    }

    public ArrayList<String> getChoixPossible()
    {
        return this.prompt.getChoixPossible();
    }

    public Prompt getPrompt(){ return this.prompt; }

    public void setPrompt(Prompt prompt){ this.prompt = prompt;}

}
