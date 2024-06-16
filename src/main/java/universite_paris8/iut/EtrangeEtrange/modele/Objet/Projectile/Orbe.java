package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.BFS;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Orbe extends Projectile implements Utilisable, Rechargeable
{

    private static final double PV = ConstanteObjet.PV_ORBE;
    private static final double DEGAT_PHYSIQUE = ConstanteObjet.DEGAT_PHYSIQUE_ORBE;
    private static final double DEGAT_SPECIAL = ConstanteObjet.DEGAT_SPECIAL_ORBE;
    private static final double VITESSE = ConstanteObjet.VITESSE_ORBE;
    private static final Hitbox HITBOX = ConstanteObjet.HITBOX_ORBE;
    private static final int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_ORBE;
    private static final int STACK_MAX  = ConstanteObjet.STACK_MAX_ORBE;
    private static final int NOMBRE_UTILISATION = ConstanteObjet.NOMBRE_UTLISATION_ORBE;
    private static final long DELAIE = ConstanteObjet.DELAIE_CHERCHE_POSITION_ORBE;

    private final BFS bfs;
    private Position positionAsuivre;
    private int nombreUtilisationRestant;
    private long derniereApelle;
    private Acteur acteurAsuivre;

    public Orbe()
    {
        super(PV,VITESSE,HITBOX);
        this.positionAsuivre = null;
        this.nombreUtilisationRestant = NOMBRE_UTILISATION;
        this.derniereApelle = 0;
        this.bfs = new BFS();
    }

    @Override
    public void utilise(Entite entite)
    {
        if (nombreUtilisationRestant > 0)
        {
            setMonde(entite.getMonde());
            setNewPosition(entite.getPosition().getX(), entite.getPosition().getY());

            this.acteurAsuivre =  monde.chercheEnemie();

            if (acteurAsuivre != null)
            {
                this.bfs.chercherChemin(monde, getPosition(),acteurAsuivre.getPosition());

                entite.getMonde().ajoutActeur(this);
                this.positionAsuivre = this.bfs.prochainePosition();
                this.nombreUtilisationRestant--;
                this.monde.ajoutRechargeable(this);
            }
        }
    }

    @Override
    public void unTour()
    {
        long apelle = System.currentTimeMillis();


        if (apelle - derniereApelle >= delaie())
            cooldown();




        if (positionAsuivre != null)
        {
            double deltaX = positionAsuivre.getX() - getPosition().getX();
            double deltaY = positionAsuivre.getY() - getPosition().getY();

            if (Math.abs(deltaX) > Math.abs(deltaY))
                setDirection(deltaX > 0 ? Direction.DROITE : Direction.GAUCHE);
            else
                setDirection(deltaY > 0 ? Direction.BAS : Direction.HAUT);


            setSeDeplace(true);

            if (peutSeDeplacer())
                seDeplace(1);
            else
                enleveToutPv();

            if (positionAtteinte(positionAsuivre))
                this.positionAsuivre = this.bfs.prochainePosition();

        }

    }

    private boolean positionAtteinte(Position position)
    {
        return  this.position != null
                && Math.abs(getPosition().getX() - position.getX()) < 0.1
                && Math.abs(getPosition().getY() - position.getY()) < 0.1;
    }

    @Override
    public String typeActeur() { return "fleche"; }
    @Override
    public boolean estUnEnemie() { return false; }
    @Override
    public double degatPhysique() { return DEGAT_PHYSIQUE; }
    @Override
    public double degatSpecial() { return DEGAT_SPECIAL; }
    @Override
    public String getNom() {return "fleche";}
    @Override
    public int stackMax() { return STACK_MAX; }
    @Override
    public double durabilitee() { return nombreUtilisationRestant; }
    @Override
    public int prixAchat() { return PRIX_ACHAT; }

    @Override
    public long delaie() {
        return DELAIE;
    }

    @Override
    public boolean cooldown()
    {
        this.derniereApelle = System.currentTimeMillis();
        this.bfs.chercherChemin(monde, getPosition(), acteurAsuivre.getPosition());
        this.positionAsuivre = bfs.prochainePosition();

        return true;
    }
}
