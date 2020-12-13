package Bibman;

public class BibManagerArray {

	// private int n;
	BibEintrag Feld[];
	private int anzahlintraege;

	public BibManagerArray(int n) {
		// this.n = n;
		// ich k√∂nnte das wegschmeizen,da die Variable nie in meine klase benutz wird aber ich sollte auch mein Konstruktor aendern
		Feld = new BibEintrag[n];
	}

	public void hinzufuegen(BibEintrag j) {
		if (anzahlintraege < Feld.length) { // ich fuege einen Wert in meinem Feld step by step hin
			Feld[anzahlintraege] = j;  // ich fuege ein objekt der typ BibEintrag in meinem Feld
			anzahlintraege++;
		} else {
			System.out.println("bercksichtigen Sie bitte die lnge des Felder!");
		}
	}

	public void druckeAlleEintraege() {
		for (int i = 0; i < Feld.length; i++) {
			if (Feld[i] != null) { // aufpassen
				System.out.print("stelle " + i + " ");
				Feld[i].druckeEintrag(null);
			} else {
				System.out.print("stelle " + i + " ");
				System.out.println(Feld[i] + " ");
			}
		}
	}

	public void sucheNeustenEintrag() {
		BibEintrag max = Feld[0]; // ich setze das maximum in der stelle mit dem index 0
		for (int i = 1; i < Feld.length; i++) { // ich laufe in meinem felder ab dem stelle 1
			if (Feld[i] != null) { // ich vergleiche und tausche
				if (Feld[i].getJahr() > max.getJahr()) {
					max = Feld[i];

				}

			}

		}
		System.out.println("der neuenste Eintrag ist: ");
		max.druckeEintrag(System.out);
	}

	public void berechneErscheinungsjahr() {
		double durchschnitt = 0.0;
		double summejahr = 0.0;
		int zaehler = 0;
		for (int i = 0; i < Feld.length; i++) {
			if (Feld[i] != null) {
				summejahr += Feld[i].getJahr();
				zaehler++;

			}
		}
		if (zaehler != 0) {
			durchschnitt = summejahr / zaehler;
		} else {
			durchschnitt = 0.0;
		}
		System.out.println("durchschnittliche ErnscheinungsjahrJahr :" + durchschnitt);
	}

	public void druckeZitierschluessel(Primaerquelle tab[]) {
		System.out.println("zittierchlssel von arrays primaerquelle");
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] != null) {

				System.out.println(tab[i].erzeugZitierschl¸ssel());
			}
		}
	}
}
