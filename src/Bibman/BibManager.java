package Bibman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;

import UI.DialogUtil;

public class BibManager extends Observable implements Serializable {
	private BibEintrag eintrag;
	private DialogUtil d;

	public BibManager() {

	}

	ArrayList<BibEintrag> Li = new ArrayList<>();
	HashMap<Autor, Integer> lmap = new HashMap<>();

	// Iterator<BibEintrag> it = Li.iterator();
	public void hinzuefuegen(BibEintrag j) throws DoppelterBibEintragException {

		if (Li.contains(j))
			throw new DoppelterBibEintragException(
					"Diese Eintrag ist schon vorhanden und kann leider nicht hinzugefügt werden!");

		Li.add(j);
		setChanged();
		notifyObservers(j);

		int anzEintraege;

		if (lmap.containsKey(j.getAutor())) {
			anzEintraege = lmap.get(j.getAutor()) + 1;
		} else {
			anzEintraege = 1;
		}
		lmap.put(j.getAutor(), anzEintraege);

	}

	public void druckeAlleEintraege() {

		/**
		 * jetzt möchte ich eine innere Klasse bauen
		 * 
		 * anders gesagt ich möchte die methode Comparator durch eine Anonyme klasse
		 * 
		 * bauen
		 * 
		 * 
		 * Collections.sort(Li, new Comparator<BibEintrag>() { public int
		 * compare(BibEintrag a, BibEintrag b) {
		 * 
		 * return a.getAutor().getNachname().compareTo(b.getAutor().getNachname()); }
		 * 
		 * });
		 **/
		Collections.sort(Li, new ComparatorName());
		for (BibEintrag eintrag : Li) {
			eintrag.druckeEintrag(System.out);
			// Iterator<BibEintrag> it = Li.iterator();
			// System.out.println("hiermit haben Sie eine Ansicht auf alle Einttraege");
			// while (it.hasNext()) {
			// it.next().druckeEintrag(System.out);
			//
			// }
		}
		// Iterator<Autor> itm = lmap.keySet().iterator();
		for (Autor i : lmap.keySet()) {
			System.out.println(i.getVorname() + " " + i.getNachname() + ": " + gibAnzahlEintraege(i) + "  Eintraege");
		}
	}

	// public BibEintrag sucheNeustenEintrag() {
	public BibEintrag sucheNeustenEintrag() {
		if (Li.isEmpty() && lmap.size() == 0) {
			// System.out.println("Sorry Ihre Bibliographie ist Leer" + "\n");
			d.showMessageDialog("sucheNeustenEintrag", "Sorry Ihre Bibliographie ist Leer");
			return null;
		}

		BibEintrag max = Li.get(0);
		for (int i = 1; i < Li.size(); i++) {

			if (Li.get(i).getJahr() > max.getJahr()) {
				max = Li.get(i);
			}

		}
		// System.out.println("der neunste Eintrag ist: ");
		// max.druckeEintrag(System.out);
		return max;
	}

	public double berechneErscheinungsjahr() {
		double Sum = 0.0;
		int zaehler = 0;
		double durchchnitt = 0.0;

		for (BibEintrag j : Li) {

			Sum += j.getJahr();
			zaehler++;

		}

		if (zaehler == 0) {
			durchchnitt = durchchnitt;
		} else {
			durchchnitt = Sum / zaehler;
		}
		// System.out.println("durchschnittliche ErnscheinungsjahrJahr :" +
		// durchchnitt);
		return durchchnitt;
	}

	public void druckeZitierschluessel(ArrayList<Primaerquelle> list) {
		System.out.println("zittierchlssel von arrays primaerquelle");
		for (Primaerquelle prim : list) {

			System.out.println(prim.erzeugZitierschlüssel());
		}

	}

	public int gibAnzahlEintraege(Autor autor) {
		int ergebnis = 0;
		// System.out.println("Der Autor:" + autor.getVorname() + autor.getNachname() +
		// " hat:");
		if (lmap.containsKey(autor)) {
			ergebnis = lmap.get(autor);
		} else {
			ergebnis = 0;
		}
		return ergebnis;

	}

	String Kopf = "ID,Vorname,Nachname,Titel,Jahr,Verlag,ISBN,Zeitschrift,Ausgabe,URL";

	public void exportiereEintraegeAlsCsvRaf(File datei) throws IOException {
		RandomAccessFile in = new RandomAccessFile(datei, "rw");
		{
			in.write((Kopf + "\r\n").getBytes(StandardCharsets.UTF_8));
			for (BibEintrag eintrag : Li) {

				in.write((eintrag.exportierAlsCsv() + "\n").getBytes(StandardCharsets.UTF_8));

			}
		}

	}

	public void exportiereEintraegeAlsCsv(File datei) throws IOException {
		try (OutputStream out = new FileOutputStream(datei)) {
			out.write((Kopf + "\r\n").getBytes(StandardCharsets.UTF_8));
			for (BibEintrag eintrag : Li) {

				out.write((eintrag.exportierAlsCsv() + "\n").getBytes(StandardCharsets.UTF_8));

			}
		}

	}

	// laden ist nichts andere als Deserialisierung
	public void Laden() {
		File FileLade = new File("dateiseria.ser");
		try (FileInputStream fis = new FileInputStream(FileLade); ObjectInputStream oos = new ObjectInputStream(fis)) {

			// objekt serialisieren

			Li = (ArrayList<BibEintrag>) oos.readObject();

			// jetzt kann ich die datei wieder in lmap wiederladen
			lmap = (HashMap<Autor, Integer>) oos.readObject();
			BibEintrag.setZaehler(Li.size() + 1);

		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("datei existiert nicht");
		}

	}

	// serialisierung ist anders gesagt speichern
	public void speichern() {

		File FileLade = new File("dateiseria.ser");
		try (FileOutputStream out = new FileOutputStream(FileLade);
				ObjectOutputStream ois = new ObjectOutputStream(out)) {

			ois.writeObject(Li);
			// ich habe diese code geschrieben um die daten von lmap zu speichern
			ois.writeObject(lmap);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// hier möchte ich alle Eintraege alphabetisch in eine innere klasse sortieren!

	public class ComparatorName implements Comparator<BibEintrag> {

		public int compare(BibEintrag a, BibEintrag b) {

			return a.getAutor().getNachname().compareTo(b.getAutor().getNachname());
		}

	}

	// praktikum 10

	public Iterator<BibEintrag> iterator() {
		return Li.iterator();
	}

}
