import gui.View;
import model.Model;

public class Isola {
	public static void main(String[] args) {
		Model state = new Model(5, 5, 2);
		new View(state);
	}
}
