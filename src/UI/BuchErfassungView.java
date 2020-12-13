package UI;

import Bibman.Autor;
import Bibman.BibEintrag;
import Bibman.BibManager;
import Bibman.Buch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BuchErfassungView extends ErfassungView {
	private Autor autor;
	

	// Label
	TextField verlag = new TextField("Verlag");
	TextField isbn = new TextField("ISBN");

	// Bibman.Menuclass Menuclass = new Bibman.Menuclass(managering1);

	// konstruktor
	public BuchErfassungView(Stage primarystage, Buch buch, BibManager man) {
		super(primarystage, buch, man);
		// TODO Auto-generated constructor stub

		Label lab4 = new Label("Verlag");
		Label lab5 = new Label("ISBN");

		// containers mit controllers ausfüllen

		gp.add(lab4, 0, 9, 1, 4);
		gp.add(verlag, 1, 9, 1, 4);
		gp.add(lab5, 0, 12, 1, 4);
		gp.add(isbn, 1, 12, 1, 4);
		Scene scene = new Scene(bp, 400, 340);
		setScene(scene);

	}

	// hbox positionieren
	public void showView() {
		// button action setting
		OK.setOnAction(new EventHandler<ActionEvent>() {
			boolean ok = false;
			int f;

			public void handle(ActionEvent actionEvent) {
				boolean oki = true;
				Titel();
				if (ok != true) {

					try {
						if (titelTyp.isEmpty()) {
							oki = false;
						}
						f = Integer.parseInt(jahr.getText());
						String vn = Autor.getText();
						String[] felder = vn.split(" ");
						Vorname = felder[0];
						Nachname = felder[1];
					} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
						DialogUtil.showMessageDialog("Autor", "Geben Sie der Autor ein Vorname und Nachname");
						oki = false;
					} catch (NumberFormatException a) {
						DialogUtil.showMessageDialog("Problem Im Jahr ", "Bitte Jahr eingeben!");
						oki = false;
					}

					ok = oki;

				}
				if (ok != false) {
					eintrag = new Buch(Vorname, Nachname, titelTyp, f, verlag.getText(), isbn.getText());
					controller(eintrag);
					close();
				}
			}
		});
		ABBRECHEN.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {
				close();
			}
		});
		// Scene
		setTitle("Erfassung eines Buchs");
		setX(505);
		setY(100);
		showAndWait();
	}
}