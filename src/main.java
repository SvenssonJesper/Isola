import gui.View;
import model.Model;

public class main {
	public static void main(String[] args) {
		Model state = new Model(10, 10, 2);
		new View(state);
	}
}
