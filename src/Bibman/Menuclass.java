package Bibman;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JOptionPane;

import UI.DialogUtil;

public class Menuclass {
	private final BibManager managering;

	File dateimenu;
	String dateiname;
	BibEintrag j = null;

	String titel, jahr;

	public Menuclass(BibManager managering) {
		this.managering = managering;
//		managering.addObserver(new Observer() {
//
//			public void update(Observable o, Object arg) {
//
//				try (FileWriter datei = new FileWriter("log.txt", true)) {
//					datei.write("[" + new Date() + "] Eintrag hinzugefuegt:" + arg + "\n");
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//
//			}
//
//		});
	}

	public void erzeugDatei() {
		boolean prfer = false;
		do {
			dateiname = JOptionPane.showInputDialog(null, "Geben Sie Bitte das Datei ein Name ein!");
			dateimenu = new File(dateiname);
			if (dateiname.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Sie haben leider nichts eingetippt!\n versuchen Sie es noch mal");
				prfer = true;

			} else {
				if (dateimenu.exists()) {

					int antwort = JOptionPane.showConfirmDialog(null, "Wollen Sie Wirklich das Datei überschreiben", "Datei überschreiben", JOptionPane.YES_NO_OPTION);
					if (antwort== JOptionPane.YES_OPTION) {
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
			managering.exportiereEintraegeAlsCsv(dateimenu);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fehlermeldung");
		}

	}

	public void Titel() {
		boolean Testor = false;
		do {

			titel = JOptionPane.showInputDialog(null, "Geben sie das Buch ein Titel ein:");
			if (titel.isEmpty()) {
				Testor = true;
				JOptionPane.showMessageDialog(null, "Sie haben kein Titel eingegeben");
			} else {
				Testor = false;
			}

		} while (Testor != false);
	}

	public void Jahr() {
		int jahres;
		boolean Testor = false;
		do {
			try {

				jahr = JOptionPane.showInputDialog(null, "Geben sie das Buch ein Jahr  Autor ein:");
				jahres = Integer.parseInt(jahr);
				Testor = false;

			}

			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Bitte gltiges Jahr eingeben!");
				Testor = true;
			}
		} while (Testor != false);

	}

	// die methode controller hilft beim hinzufuegen
	public void controller(BibEintrag j) {
		try {
			managering.hinzuefuegen(j);
		} catch (DoppelterBibEintragException e) {

			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public void MenuMethode() {

		Scanner tastatur = new Scanner(System.in);
		// int repeat = 0;
		int zahl;
		try {
			do {

				System.out.println("Bibliographie-Manager");
				System.out.println("1.Buch hinzufuegen \n" + "2.Artikel hinzufuegen\n" + "3.Webseite hinzufuegen\n"
						+ "4.Drucke Alle Eintraege\n" + "5.Suche neuensten Eintrag\n"
						+ "6.Berechne durchschnittliches Erscheinungsjahr\n" + "7.CSV-Export\n"
						+ "8.Wir wollen ein datei speichern\n" + "9.Wir wollen ein datei laden\n" + "10.Beeden\n"
						+ "Bitte Aktion whlen:");
				zahl = tastatur.nextInt();
				String vorname, nachname;

				switch (zahl) {
				case 1:
					System.out.println("Wir  wollen ein Buch hinzufgen!");
					vorname = JOptionPane.showInputDialog(null, "Geben sie der Vorname des Autor des Buch  ein:");
					nachname = JOptionPane.showInputDialog(null, "Geben sie der Nachname des Autor des Buch ein:");
					Titel();
					Jahr();
					int Jahr1 = Integer.parseInt(jahr);

					j = new Buch(vorname, nachname, titel, Jahr1, "No Starch Press", "(ISBN:9781593272814)");
					controller(j);

					break;
				case 2:
					System.out.println("Wir wollen ein Artikel hinzufuegen");
					vorname = JOptionPane.showInputDialog(null, "Geben sie der Vorname des Autor des Artikel  ein:");
					nachname = JOptionPane.showInputDialog(null, "Geben sie der Nachname des Autor des Artikel ein:");

					titel = JOptionPane.showInputDialog(null, "Geben sie das Artikel ein Titel ein:");
					jahr = JOptionPane.showInputDialog(null, "Geben sie das Artikel ein Jahr  Autor ein:");
					int Jahr2 = Integer.parseInt(jahr);

					j = new Artikel(vorname, nachname, titel, Jahr2, "In: Communications of the ACM", 12);
					controller(j);
					break;
				case 3:
					System.out.println("Wir  wollen eine Webseite hinzufuegen");

					vorname = JOptionPane.showInputDialog(null, "Geben sie der Vorname des Autor der Webseite  ein:");
					nachname = JOptionPane.showInputDialog(null, "Geben sie der Nachname des Autor der Webseite ein:");

					titel = JOptionPane.showInputDialog(null, "Geben sie die Webseite ein Titel ein:");
					jahr = JOptionPane.showInputDialog(null, "Geben sie die Webseite ein Jahr  Autor ein:");
					int Jahr3 = Integer.parseInt(jahr);

					j = new Webseite(vorname, nachname, titel, Jahr3,
							" URL:http://openbook.rheinwerk-verlag.de/javainsel/");
					controller(j);
					break;
				case 4:
					System.out.println("4.Drucke Alle Eintraege");
					managering.druckeAlleEintraege();
					break;
				case 5:
					System.out.println("5.Suche neunsten Eintrag");
					BibEintrag e = managering.sucheNeustenEintrag();
					if (e != null)
						e.druckeEintrag(System.out);
					break;
				case 6:
					System.out.println("6.Berechne durchschnittliches Erscheinungsjahr");
					managering.berechneErscheinungsjahr();
					break;
				case 7:
					System.out.println("CSV-Export");
					erzeugDatei();

					break;
				case 8:
					System.out.println("Wir wollen ein datei speichern");
					managering.speichern();
					// MenuMethode();
					break;
				case 9:
					System.out.println("wir wollen ein object laden");
					managering.Laden();
					// MenuMethode();
					break;
				case 10:
					System.out.println("Beenden");

					break;
				default:
					System.out.println("Geben Sie ein Zahl zwischen 1-10 ein!:\n");

					break;

				}
				// repeat = zahl;
			} while (zahl != 10);
		} catch (InputMismatchException e) {
			System.out.println("Geben Sie ein gltiges Zahl zwischen 1-7 ein!\n");
			MenuMethode();
		}
	}
}
