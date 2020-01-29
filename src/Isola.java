import gui.View;
import model.Model;

public class Isola {
	public static void main(String[] args) {
		Model state = new Model(7, 7, 4);
		new View(state);
	}
}
