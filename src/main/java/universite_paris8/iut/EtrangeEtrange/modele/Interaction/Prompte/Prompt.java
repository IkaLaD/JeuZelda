package universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte;

import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action.Action;

import java.util.ArrayList;

public class Prompt
{
    private String textePrompt;
    private Action action;


    private ArrayList<String> choixPossible;
    private ArrayList<Prompt> prompts;

    public Prompt(String prompt,Action action)
    {
        this.textePrompt = prompt;
        this.prompts = new ArrayList<>();
        this.choixPossible = new ArrayList<>();
        this.action = action;
    }

    public String getTextePrompt(){ return this.textePrompt;}
    public void ajoutPrompt(Prompt prompt,String reponse)
    {
        this.prompts.add(prompt);
        this.choixPossible.add(reponse);
    }

    public Prompt promptSuivant(String reponse)
    {
        Prompt prompt = null;

        if (!(prompts == null) && !prompts.isEmpty())
        {
            if (reponse == null || reponse.isEmpty())
            {
                prompt = prompts.get(0);
            }
            else
            {
                for (int i = 0;i<choixPossible.size() && prompt == null ;i++)
                    if (reponse.equalsIgnoreCase(choixPossible.get(i)))
                        prompt = prompts.get(i);
            }
        }

        return prompt;
    }

    public void setAction(Action action)
    {
        this.action = action;
    }

    public Action getAction(){ return this.action;}

    public ArrayList<String> getChoixPossible() {return this.choixPossible;}


    public String toString()
    {
        return textePrompt;
    }

    public ArrayList<String> getC()
    {
        return this.choixPossible;
    }
}
