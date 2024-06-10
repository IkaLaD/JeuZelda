package universite_paris8.iut.EtrangeEtrange.modele.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;


import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

public abstract class Humanoide extends EntiteOffensif
{
    protected Objet objetMainGauche;
    protected Objet objetMainDroite;
    protected Sac sac;


    public Humanoide(Monde monde, double x, double y, Direction direction,double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse,Hitbox hitbox,Sac sac, Objet objetMainGauche, Objet objetMainDroite) {
        super(monde, x, y, direction, pv, attaque, defense, attaqueSpecial, defenseSpecial, vitesse, hitbox);
        this.sac = sac;
        this.objetMainGauche = objetMainGauche;
        this.objetMainDroite = objetMainDroite;
    }

    @Override
    protected double subitDegatPhysique(double attaque,double forceEntite) {
        return (attaque * forceEntite) / (getDefense() - (attaque/6));
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecial,double forceEntite) {
        return (attaqueSpecial * forceEntite) / (getDefense() - (attaqueSpecial/6));
    }

    public Sac getSac()
    {
        return this.sac;
    }
    public Objet retournerObjetMainDroite()
    {
        Objet objet = this.objetMainDroite;
        this.objetMainDroite = null;
        return objet;
    }
    public void setObjetMainGauche(Objet objet){
        this.objetMainGauche = objet;
    }

    public Objet retournerObjetMainGauche(){
        Objet objet = this.objetMainGauche;
        this.objetMainGauche=null;
        return objet;
    }
    public Objet getObjetMainDroite(){
        return this.objetMainDroite;
    }
    public Objet getObjetMainGauche(){
        return this.objetMainGauche;
    }

    public void setObjetMainDroite(Objet objet){
        this.objetMainDroite = objet;
    }
    public void ramasserObjet() {
        ArrayList<DropAuSol> dropAuSols = getMonde().getDropAuSol();

        for(int i = 0 ; i < dropAuSols.size() ; i++){
            Position position1 = dropAuSols.get(i).getPosition();
            if(Math.abs(getPosition().getX()+getDirection().getX()-position1.getX())<1)
                if(Math.abs(getPosition().getY()+getDirection().getY()-position1.getY())<1) {
                    if (sac.ajoutItem(dropAuSols.get(i).getObjet())) {
                        getMonde().enleverDropAuSol(dropAuSols.get(i));
                        i++;
                    }
                }
        }
    }
}
