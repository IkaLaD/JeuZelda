package universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie;

public class PieceOr extends Piece{

    public PieceOr(){
        super(1);
    }

    @Override
    public String getNom() {
        return "pieceor";
    }

    @Override
    public int stackMax() {
        return 64;
    }
}
