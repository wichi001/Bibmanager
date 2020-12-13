package Bibman;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class Artikel extends BibEintrag implements Primaerquelle, CsvExportable, Serializable {
	private String zeitschrift;
	private int ausgabe;
	private boolean isavailable;

	public Artikel() {

	}

	public Artikel(String Vorname, String Nachname, String titel, int jahr, String zeitschrift, int ausgabe) {
		super(Vorname, Nachname, titel, jahr);
		this.zeitschrift = zeitschrift;
		this.ausgabe = ausgabe;
	}

	public void druckeEintrag(OutputStream stream) {
		PrintStream print = new PrintStream(stream);
		print.println(rufen() + zeitschrift + " " + "( Ausgabe" + ausgabe + ")" + "," + getJahr());

		// System.out.println(rufen() + zeitschrift + " " + "( Ausgabe" + ausgabe + ")"
		// + "," + getJahr());
	}

	public String erzeugZitierschlüssel() {
		String loesung = getAutor().getVorname() + getAutor().getNachname() + getJahr() + "-" + ausgabe;
		loesung = loesung.replaceAll(" ", "");
		return loesung;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ausgabe;
		result = prime * result + ((zeitschrift == null) ? 0 : zeitschrift.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artikel other = (Artikel) obj;
		if (ausgabe != other.ausgabe)
			return false;
		if (zeitschrift == null) {
			if (other.zeitschrift != null)
				return false;
		} else if (!zeitschrift.equals(other.zeitschrift))
			return false;
		return true;
	}

	// praktikum 7
	public String exportierAlsCsv() {
		String result = null;
		result = super.exportierAlsCsv() + zeitschrift + "," + ausgabe + ",";
		return result;
	}

	// praktikum 10
	// public String aussehen() {
	// return aussehen10() + "zeitschrift= " + zeitschrift + "," + "ausgabe= " +
	// ausgabe;
	// }

	public String toString() {
		return rufen() + "  Zeitschrift =" + zeitschrift + " " + " Ausgabe =" + ausgabe + ",   Jahr =" + getJahr();

	}

	public boolean isAvailable() {
		return isavailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isavailable = isAvailable;
	}
}
