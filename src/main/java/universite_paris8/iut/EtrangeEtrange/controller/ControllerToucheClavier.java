package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerToucheClavier implements Initializable {
    public TextArea Haut;
    public TextArea Bas;
    public TextArea Gauche;
    public TextArea Droite;
    public TextArea UtiliserObjet;
    public TextArea Courrir;
    public TextArea Inventaire;
    public Button boutton;
    public TextArea menuHaut;
    public TextArea menuBas;
    public TextArea menuGauche;
    public TextArea menuDroite;
    public TextArea selectionner;
    public TextArea Sort2;
    public TextArea Sort3;
    public TextArea ParlerPnj;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setText();
    }

    public KeyCode getKeyCode(TextArea textArea, KeyCode keyCode){
        if(textArea.getText().length()!=1) {
            return keyCode;
        }
        return KeyCode.getKeyCode(textArea.getText());
    }

    public void setText(){
        Haut.setText(ConstantesClavier.deplacementHaut.getChar());
        Bas.setText(ConstantesClavier.deplacementBas.getChar());
        Gauche.setText(ConstantesClavier.deplacementGauche.getChar());
        Droite.setText(ConstantesClavier.deplacementDroite.getChar());
        UtiliserObjet.setText(ConstantesClavier.attaquer.getChar());
        Courrir.setText(ConstantesClavier.courrir.getChar());
        Inventaire.setText(ConstantesClavier.inventaire.getChar());
        menuBas.setText(ConstantesClavier.menuDeplacementBas.getChar());
        menuHaut.setText(ConstantesClavier.deplacementHaut.getChar());
        menuDroite.setText(ConstantesClavier.deplacementDroite.getChar());
        menuGauche.setText(ConstantesClavier.deplacementGauche.getChar());
        selectionner.setText(ConstantesClavier.menuSelectionner.getChar());
        Sort2.setText(ConstantesClavier.sort2.getChar());
        Sort3.setText(ConstantesClavier.sort3.getChar());
        ParlerPnj.setText(ConstantesClavier.parlerPnj.getChar());
    }


    public void bouttonClick(MouseEvent mouseEvent) {
        ArrayList<TextArea> textAreas = new ArrayList<>();
        textAreas.add(Haut);
        textAreas.add(Bas);
        textAreas.add(Gauche);
        textAreas.add(Droite);
        textAreas.add(Inventaire);
        textAreas.add(UtiliserObjet);
        textAreas.add(Courrir);
        textAreas.add(Sort2);
        textAreas.add(Sort3);
        textAreas.add(ParlerPnj);
        for(int i = 0 ; i < textAreas.size() ; i++){
            for(int j = 1 + i ; j < textAreas.size() ; j++){
                if(textAreas.get(i).getText().equals(textAreas.get(j).getText())) {
                    setText();
                    return;
                }
            }
        }
        ConstantesClavier.setCourrir(getKeyCode(Courrir, ConstantesClavier.courrir));
        ConstantesClavier.setDeplacementBas(getKeyCode(Bas, ConstantesClavier.deplacementBas));
        ConstantesClavier.setDeplacementGauche(getKeyCode(Gauche, ConstantesClavier.deplacementGauche));
        ConstantesClavier.setInventaire(getKeyCode(Inventaire, ConstantesClavier.inventaire));
        ConstantesClavier.setDeplacementDroite(getKeyCode(Droite, ConstantesClavier.deplacementDroite));
        ConstantesClavier.setDeplacementHaut(getKeyCode(Haut, ConstantesClavier.deplacementHaut));
        ConstantesClavier.setAttaquer(getKeyCode(UtiliserObjet, ConstantesClavier.attaquer));
        ConstantesClavier.setSort2(getKeyCode(Sort2, ConstantesClavier.sort2));
        ConstantesClavier.setSort3(getKeyCode(Sort3, ConstantesClavier.sort3));
        ConstantesClavier.setParlerPnj(getKeyCode(ParlerPnj, ConstantesClavier.parlerPnj));


        textAreas.clear();
        textAreas.add(menuDroite);
        textAreas.add(menuGauche);
        textAreas.add(menuHaut);
        textAreas.add(menuBas);
        textAreas.add(selectionner);
        for(int i = 0 ; i < textAreas.size() ; i++){
            for(int j = 1 + i ; j < textAreas.size() ; j++){
                if(textAreas.get(i).getText().equals(textAreas.get(j).getText())) {
                    setText();
                    return;
                }
            }
        }
        ConstantesClavier.setMenuDeplacementDroite(getKeyCode(menuDroite, ConstantesClavier.menuDeplacementDroite));
        ConstantesClavier.setMenuDeplacementGauche(getKeyCode(menuGauche, ConstantesClavier.menuDeplacementGauche));
        ConstantesClavier.setMenuDeplacementHaut(getKeyCode(menuHaut, ConstantesClavier.menuDeplacementHaut));
        ConstantesClavier.setMenuDeplacementBas(getKeyCode(menuBas, ConstantesClavier.menuDeplacementBas));
        ConstantesClavier.setMenuSelectionner(getKeyCode(selectionner, ConstantesClavier.menuSelectionner));

        setText();

    }
}
