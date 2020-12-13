package UI;

import Bibman.Artikel;
import Bibman.Autor;
import Bibman.BibManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ArtikelErfassungView extends ErfassungView {

	private Autor autor;
	private DialogUtil d;

	// Label
	TextField zeitschrift = new TextField("Zeitschrift");
	TextField ausgabe = new TextField("Ausgabe");

	// Bibman.Menuclass Menuclass = new Bibman.Menuclass(managering1);

	// Label,TextField,

	public ArtikelErfassungView(Stage primarystage, Artikel artikel, BibManager man) {
		super(primarystage, artikel, man);

		// TODO Auto-generated constructor stub
		Label lab4 = new Label("Zeitschrift");
		Label lab5 = new Label("Ausgabe");

		// containers mit controllers ausfüllen

		gp.add(lab4, 0, 9, 1, 4);
		gp.add(zeitschrift, 1, 9, 1, 4);
		gp.add(lab5, 0, 12, 1, 4);
		gp.add(ausgabe, 1, 12, 1, 4);
		Scene scene = new Scene(bp, 400, 340);
		setScene(scene);
	}

	// textField

	// hbox positionieren
	public void showView() {
//		int f;
		OK.setOnAction(new EventHandler<ActionEvent>() {
			boolean ok = false;
			int f;
			int ag;

			public void handle(ActionEvent actionEvent) {
				boolean oki = true;
				Titel();
				if (ok != true) {

					try {
						if (titelTyp.isEmpty()) {
							oki = false;
						}
						f = Integer.parseInt(jahr.getText());
						ag = Integer.parseInt(ausgabe.getText());
						String vn = Autor.getText();
						String[] felder = vn.split(" ");
						Vorname = felder[0];
						Nachname = felder[1];
					} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
						DialogUtil.showMessageDialog("Autor", "Geben Sie der Autor ein Vorname und Nachname");
						oki = false;}
					 catch (NumberFormatException f) {
						DialogUtil.showMessageDialog("Problem Im Jahr Oder Ausgabe ",
								"Bitte Jahr eingeben! und sorgen Sie sich bitte,dass die Ausgabe auch ein Zahl sein soll!");
						oki = false;
					}

					ok = oki;

				}
				if (ok != false) {
					eintrag = new Artikel(Vorname, Nachname, titelTyp, f, zeitschrift.getText(), ag);
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

		setTitle("Erfassung eines Artikel");
		setX(100);
		setY(100);
		showAndWait();
	}
}
