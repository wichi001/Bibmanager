package UI;

import Bibman.Autor;
import Bibman.BibManager;
import Bibman.Webseite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WebseiteErfassungView extends ErfassungView {

	// private Webseite webseite;
	private Autor autor;
	private DialogUtil d;

	TextField url = new TextField();

	// Bibman.Menuclass Menuclass = new Bibman.Menuclass(managering1);

	public WebseiteErfassungView(Stage primarystage, Webseite webseite, BibManager man) {
		super(primarystage, webseite, man);

		Label lurl = new Label("URL");

		gp.add(lurl, 0, 9, 1, 4);
		gp.add(url, 1, 9, 1, 4);
		// TODO Auto-generated constructor stub

		// sache im hbox positionieren

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
					} catch (NumberFormatException f) {
						DialogUtil.showMessageDialog("Problem Im Jahr ", "Bitte Jahr eingeben!");
						oki = false;
					}

					ok = oki;

				}
				if (ok != false) {

					eintrag = new Webseite(Vorname, Nachname, titel.getText(), f, url.getText());
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
		setTitle("Erfassung einer Webseite");
		setX(100);
		setY(485);
		showAndWait();
	}
}
