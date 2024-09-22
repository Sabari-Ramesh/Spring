package Property;


public class Writer {
private Pen pen;
	
	public void write() {
		pen.write();
	}

	public Pen getPen() {
		return pen;
	}

	public Writer(Pen pen) {
		this.pen=pen;
	}
}
