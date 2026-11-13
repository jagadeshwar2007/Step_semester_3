abstract class ArtPiece {
    private static int counter = 0;
    private final String pieceId;

    public ArtPiece() {
        counter++;
        this.pieceId = "ART-" + counter;
    }

    public abstract String describe();

    String getPieceId() {
        return pieceId;
    }
}

class Painting extends ArtPiece {
    private String title;

    public Painting(String title) {
        this.title = title;
    }

    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {
    private String title;

    public Sculpture(String title) {
        this.title = title;
    }

    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}

public class Week7AssignmentQuestion2 {
    public static void main(String[] args) {
        Painting p = new Painting("Sunset Fields");
        Sculpture s = new Sculpture("The Thinker II");

        System.out.println(p.describe() + " | " + p.getPieceId());
        System.out.println(s.describe() + " | " + s.getPieceId());
    }
}
