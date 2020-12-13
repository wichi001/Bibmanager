package UI;

import Bibman.BibEintrag;
import Bibman.BibManager;
import Bibman.DoppelterBibEintragException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class ErfassungView extends Stage {

	protected BibEintrag eintrag;
	private BibManager man;
	protected String Vorname;
	protected String Nachname;

	int jahres;

	String titelTyp, jahrTyp;
	boolean ok = false;

	// Textfield erzeugen
	protected TextField titel = new TextField("");
	protected TextField Autor = new TextField("");
	// protected TextField autorN = new TextField("");
	protected TextField jahr = new TextField();

	protected HBox hb = new HBox();
	protected GridPane gp = new GridPane();
	protected BorderPane bp = new BorderPane();

	// button erzeugen
	protected Button OK = new Button("OK");
	protected Button ABBRECHEN = new Button("Abbrechen");

	// konstruktor
	public ErfassungView(Stage primarystage, BibEintrag eintrag, BibManager mann) {
		this.initOwner(primarystage);
		this.initModality(Modality.WINDOW_MODAL);
		this.eintrag = eintrag;
		this.man = mann;

		Label lTitel = new Label("TiTel");
		Label lAutor = new Label(" Autor");
		// Label lAutorN = new Label("Nachname");
		Label lJahr = new Label("Jahr");

		gp.add(lTitel, 0, 0, 1, 4);
		gp.add(titel, 1, 0, 1, 4);
		gp.add(lAutor, 0, 3, 1, 4);
		gp.add(Autor, 1, 3, 1, 4);
		// gp.add(lAutorN, 0, 6, 1, 4);
		// gp.add(autorN, 1, 6, 1, 4);
		gp.add(lJahr, 0, 6, 1, 4);
		gp.add(jahr, 1, 6, 1, 4);

		hb.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(20));
		gp.setHgap(25);
		gp.setVgap(15);

		// sache im hbox positionieren
		hb.getChildren().addAll(OK, ABBRECHEN);
		// sache im borderPane positionieren

		bp.setCenter(gp);
		bp.setBottom(hb);

	} 

	protected void Titel() {
		titelTyp = titel.getText();
		if (ok == false) {
			if (titelTyp.length() == 0) {

				DialogUtil.showMessageDialog("Fehler im Titel", "Sie haben kein Titel eingegeben");
				ok = false;

			}
		}
	}

	protected void Jahr() {
		if (ok == false) {
			try {

				jahrTyp = jahr.getText();
				jahres = Integer.parseInt(jahrTyp);

			}

			catch (NumberFormatException e) {
				DialogUtil.showMessageDialog("Fehler im Jahr", "Bitte gültiges Jahr eingeben!");
				ok = false;
			}

		}

	}

	// diese Methodeprüft eine Doppelte Eintrag

	public void controller(BibEintrag j) {

		try {
			man.hinzuefuegen(eintrag);
		} catch (DoppelterBibEintragException | NullPointerException e) {

			DialogUtil.showMessageDialog("hinzufuegen", e.getMessage());

		}

	}

	public void showView() {

	}
}