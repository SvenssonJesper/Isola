import gui.View;
import model.Model;

public class Isola {
	public static void main(String[] args) {
		Model state = new Model(8, 8, 4);
		new View(state);
	}
}
