import gui.View;
import model.Model;

public class Main {
	public static void main(String[] args) {
		Model state = new Model(10, 10, 2);
		new View(state);
	}
}
