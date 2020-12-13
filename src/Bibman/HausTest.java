
package Bibman;

import java.util.ArrayList;

public class HausTest {

	public static void main(String[] args) throws DoppelterBibEintragException {

		BibManager manager = new BibManager();

		 Buch Eintrag1 = new Buch("Conrad", "Barski", "Land of Lisp2 ", 2010, "No Starch Press",
		 "(ISBN:9781593272814)");
		//
		// Buch Eintrag2 = new Buch("Conrad", "Barski", "Land of Lisp2 ", 20170, "No
		// Starch Press",
		// "(ISBN:9781593272814)");
		//
		// Artikel Eintrag3 = new Artikel("Donald", "E. Knuth", "Computer programming as
		// an art", 1974,
		// "In: Communications of the ACM", 12);
		// Artikel Eintrag4 = new Artikel("Donald ", "E. Knuth", "Computer programming
		// as an art 2", 2019,
		// "In: Communications of the ACM", 12);
		//
		// Webseite Eintrag5 = new Webseite("Christian", "Ullenbloom", "Java ist auch
		// eine Insel A ", 2016,
		// " URL:http://openbook.rheinwerk-verlag.de/javainsel/");
		//
		// Webseite Eintrag6 = new Webseite("Christian", "Ullenbloom", "Java ist auch
		// eine Insel B ", 2025,
		// " URL:http://openbook.rheinwerk-verlag.de/javainsel/");
		// Buch Eintrag7 = new Buch("Michel ", "Djifack", "Enfant noir ", 2018, "No
		// Starch Press", "(ISBN:9781593272814)");
		// Buch Eintrag8 = new Buch("Conrad", "Barski", "Lander ", 2013, "No Starch
		// Press", "(ISBN:9781593272814)");
		// Webseite Eintrag9 = new Webseite("Michel ", "Djifack", "PaketSent.com", 2018,
		// " URL:http://www.Paketsent.com/");
		 ArrayList<Primaerquelle> l = new ArrayList<>();
		 l.add(Eintrag1);
		// l.add(Eintrag2);
		// l.add(Eintrag4);
		// l.add(Eintrag7);
		//
		 manager.hinzuefuegen(Eintrag1);
		// manager.hinzuefuegen(Eintrag2);
		// manager.hinzuefuegen(Eintrag3);
		// manager.hinzuefuegen(Eintrag4);
		// manager.hinzuefuegen(Eintrag5);
		// manager.hinzuefuegen(Eintrag6);
		// manager.hinzuefuegen(Eintrag7);
		// manager.hinzuefuegen(Eintrag8);
		// manager.hinzuefuegen(Eintrag9);

		// manager.druckeAlleEintraege();
		// manager.sucheNeustenEintrag();
		// manager.berechneErscheinungsjahr();
		// manager.druckeZitierschluessel(l);
		//
		// System.out.println(manager.gibAnzahlEintraege(new Autor("Christian", "
		// Ullenbloom")));
		// System.out.println(manager.gibAnzahlEintraege(new Autor("Conrad", "
		// Barski")));
		// System.out.println(manager.gibAnzahlEintraege(new Autor("David", "
		// Barski")));
		// System.out.println(manager.gibAnzahlEintraege(new Autor("Michel ",
		// "Djifack")));
		/*
		 * Wenn Sie die Menu Option testen wollen, dann müssen Sie bitte die zeile 10
		 * (zehn)- bis 39 (neununddreißig) kommentieren und die Zeile 45-bis 46 die
		 * Kommentare Symbol wegmachen.
		 */
		// System.out.println("solution visuelle:"
		// manager.exportiereEintraegeAlsCsv(datei));

		// File datei = new File("datei.txt");
		// try {
		// manager.exportiereEintraegeAlsCsv(datei);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
//		// }
		Menuclass m = new Menuclass(manager);
		m.MenuMethode();

	}
}
