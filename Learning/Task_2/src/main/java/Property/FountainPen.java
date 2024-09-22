package Property;

public class FountainPen implements Pen {
    private Ink ink;

    public FountainPen(Ink ink) {
        this.ink = ink;
    }

    @Override
    public void write() {
        System.out.println("Hi, I am writing with " + ink.color() + " ink from the brand " + ink.brand());
    }
}
