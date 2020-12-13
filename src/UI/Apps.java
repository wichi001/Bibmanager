package UI;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import Bibman.BibEintrag;
import Bibman.BibManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Apps extends Application {
	// eine Liste erzeugen und notwendige Attribute für die kommenden Methoden
	// erzeugen
	private DialogUtil d;

	protected BibManager managering1;

	File dateimenu;
	String dateiname;
	ListView<BibEintrag> listView = new ListView<>(); 

	// main methode class Apps
	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage primaryStage) throws Exception {
		managering1 = new BibManager();

		primaryStage.show();

		// praktikum 12
		MenuBar mb = new MenuBar();

		Menu menu1 = new Menu("Datei");

		Menu menu2 = new Menu("Eintrag");
		Menu menu3 = new Menu("Anzeige");

		mb.getMenus().addAll(menu1, menu2, menu3);

		MenuItem mi1 = new MenuItem("Laden");
		MenuItem mi2 = new MenuItem("Speichern");
		MenuItem mi3 = new MenuItem("CSV-Export");
		MenuItem mi4 = new MenuItem("Beenden");
		MenuItem mi5 = new MenuItem("Buch hinzufügen");
		MenuItem mi6 = new MenuItem("Artikel hinzufügen");
		MenuItem mi7 = new MenuItem("Webseite hinzufügen");
		MenuItem mi8 = new MenuItem("Berechne durchschnittliches Erscheinungsjahr");
		MenuItem mi9 = new MenuItem("Suche neunsten Eintrag");

		menu1.getItems().addAll(mi1, mi2, mi3, mi4);
		menu2.getItems().addAll(mi5, mi6, mi7);
		menu3.getItems().addAll(mi8, mi9);

		// separator zuordnen
		menu1.getItems().add(new SeparatorMenuItem());
		menu2.getItems().add(new SeparatorMenuItem());
		menu3.getItems().add(new SeparatorMenuItem());

		// menu Methoden

		// Methode Laden
		mi1.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent actionEvent) {
				managering1.Laden();
				listView.getItems().clear();
				Iterator<BibEintrag> it = managering1.iterator();
				while (it.hasNext()) {
					listView.getItems().add(it.next());
				}
			}
		});

		// Methode Speichern
		mi2.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent actionEvent) {
				managering1.speichern();
			}
		});

		// Methode CSV-Export
		mi3.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent actionEvent) {

				boolean prfer = false;
				do {
					dateiname = d.showInputDialog("CSV-Export", "Geben Sie Bitte das Datei ein Name ein!");
					dateimenu = new File(dateiname);
					if (dateiname.isEmpty()) {
						d.showMessageDialog("CSV-Export",
								"Sie haben leider nichts eingetippt!\n versuchen Sie es noch mal");
						prfer = true;

					} else {
						if (dateimenu.exists()) {

							boolean antwort = d.showConfirmDialog("CSV-Export",
									"Wollen Sie wirklich das Datei berschreiben ?");
							if (antwort == true) {
								prfer = false;

							} else {
								prfer = true;
							}
						} else {
							prfer = false;
						}

					}

				} while (prfer != false);
				try {
					managering1.exportiereEintraegeAlsCsv(dateimenu);
				} catch (IOException e) {
					d.showMessageDialog("CSV-Export", "Fehlermeldung");
				}

			}

		});

		// methode beenden
		mi4.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent actionEvent) {
				System.exit(0);
			}
		});
		// methode Buch hinzufügen
		mi5.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent actionEvent) {
				BuchErfassungView buch = new BuchErfassungView(primaryStage, null, managering1);
				buch.showView();
				listView.getItems().clear();
				Iterator<BibEintrag> it = managering1.iterator();
				while (it.hasNext()) {
					listView.getItems().add(it.next());
				}
			}
		});
		// metthode Artikel hinzufügen
		mi6.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent actionEvent) {
				ArtikelErfassungView art = new ArtikelErfassungView(primaryStage, null, managering1);
				art.showView();
				listView.getItems().clear();
				Iterator<BibEintrag> it = managering1.iterator();
				while (it.hasNext()) {
					listView.getItems().add(it.next());
				}
			}
		});
		// methode Webseite hinzufügen
		mi7.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent actionEvent) {
				WebseiteErfassungView web = new WebseiteErfassungView(primaryStage, null, managering1);

				web.showView();
				listView.getItems().clear();
				Iterator<BibEintrag> it = managering1.iterator();
				while (it.hasNext()) {
					listView.getItems().add(it.next());
				}

			}
		});
		// methode Berechne durchschnittliches Jahre
		mi8.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent actionEvent) {
				double e = managering1.berechneErscheinungsjahr();
				d.showMessageDialog("Berechne durchschnittliches Jahre", "Die durchschnittliches Jahr ist:" + e);
			}
		});
		// methode suche neunsten Eintrag
		mi9.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent actionEvent) {
				BibEintrag e = managering1.sucheNeustenEintrag();
				if (e != null)
					// d.showMessageDialog("Suche neunsten Eintrag", " Ihre Bibliographie ist
					// leer");

					d.showMessageDialog("Neunster Eintrag", "Der neunste Eintrag ist:" + e);
			}
		});

		// menu in BorderPane zuordnen

		BorderPane bp2 = new BorderPane();
		bp2.setTop(mb);
		bp2.setCenter(listView);

		Scene scene2 = new Scene(bp2, 1200, 700);
		primaryStage.setScene(scene2);
		primaryStage.setTitle("BibManager");

	}

}
