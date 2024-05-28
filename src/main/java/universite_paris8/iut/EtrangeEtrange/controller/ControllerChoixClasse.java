package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerChoixClasse implements Initializable
{


    @FXML
    private HBox hbox;

    @FXML
    private Label nomClasse;
    @FXML
    private Label descriptionClasse;


    @FXML
    private ProgressBar statPv;
    @FXML
    private ProgressBar statAttaque;
    @FXML
    private ProgressBar statDefense;
    @FXML
    private ProgressBar statAttaqueSpecial;
    @FXML
    private ProgressBar statDefenseSpecial;
    @FXML
    private ProgressBar statVitesse;



    private double pvPlusElevee;
    private double atkPlusElevee;
    private double defPlusElevee;
    private double atkSpePlusElevee;
    private double defSpePlusElevee;
    private double vitPlusElevee;






    private String[] nomGuerrier;

    private StringProperty nomActuelle;

    private int classActuelle;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        hbox.requestFocus();


        this.nomGuerrier = new String[2];
        this.nomGuerrier[0] = "Guerrier";
        this.nomGuerrier[1] = "Archer";
        this.classActuelle = 0;

        this.pvPlusElevee = ConstantesPersonnages.pvPlusHaut();
        this.atkPlusElevee = ConstantesPersonnages.attaquePlusHaute();
        this.defPlusElevee = ConstantesPersonnages.defensePlusHaute();
        this.atkSpePlusElevee = ConstantesPersonnages.attaqueSpecialPlusHaute();
        this.defSpePlusElevee = ConstantesPersonnages.attaqueSpecialPlusHaute();
        this.vitPlusElevee = ConstantesPersonnages.vitessePlusHaute();


        this.nomActuelle = new SimpleStringProperty(nomGuerrier[classActuelle]);

        initListenerClasse();
    }


    private void initListenerClasse()
    {
        this.nomActuelle.addListener(e ->
        {
            String guerrier = nomActuelle.get();

            if (guerrier.equals("Guerrier"))
            {
                changeValueProgressBar(ConstantesPersonnages.GUERRIER_PV,ConstantesPersonnages.GUERRIER_ATTAQUE,ConstantesPersonnages.GUERRIER_DEFENSE,ConstantesPersonnages.GUERRIER_ATTAQUE_SPECIAL,ConstantesPersonnages.GUERRIER_DEFENSE_SEPCIAL,ConstantesPersonnages.GUERRIER_VITESSE);

            }
            else if (guerrier.equals("Archer"))
            {
                changeValueProgressBar(ConstantesPersonnages.ARCHER_PV,ConstantesPersonnages.ARCHER_ATTAQUE,ConstantesPersonnages.ARCHER_DEFENSE,ConstantesPersonnages.ARCHER_ATTAQUE_SPECIAL,ConstantesPersonnages.ARCHER_DEFENSE_SEPCIAL,ConstantesPersonnages.ARCHER_VITESSE);
            }
        });
    }


    private void changeValueProgressBar(double pv,double atk,double def,double atkSpe,double defSpe,double vit)
    {
        this.statPv.setProgress(pv/pvPlusElevee);
        this.statAttaque.setProgress(atk/atkPlusElevee);
        this.statDefense.setProgress(def/defPlusElevee);
        this.statAttaqueSpecial.setProgress(atkSpe/atkSpePlusElevee);
        this.statDefenseSpecial.setProgress(defSpe/defSpePlusElevee);
        this.statVitesse.setProgress(vit/vitPlusElevee);
    }


    @FXML
    public void changeClasse(KeyEvent keyEvent)
    {
        System.out.println("f");

        if(keyEvent.getCode() == KeyCode.Q)
        {
            this.classActuelle = (this.classActuelle - 1) % this.nomGuerrier.length;
            this.nomActuelle.set(nomGuerrier[classActuelle]);
        }
        else if (keyEvent.getCode() == KeyCode.D)
        {
            this.classActuelle = (this.classActuelle + 1) % this.nomGuerrier.length;
            this.nomActuelle.set(nomGuerrier[classActuelle]);
        }
    }

    @FXML
    public void mouseClick(MouseEvent mouseEvent)
    {
        this.hbox.requestFocus();
    }
}
