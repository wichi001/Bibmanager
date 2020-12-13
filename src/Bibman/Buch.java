package Bibman;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class Buch extends BibEintrag implements Primaerquelle, CsvExportable, Serializable {
	private String verlag;
	private String isbn;
	private boolean isavailable;

	public Buch() {
	}

	public Buch(String Vorname, String Nachname, String titel, int jahr, String verlag, String isbn) {
		super(Vorname, Nachname, titel, jahr);
		this.verlag = verlag;
		this.isbn = isbn;
	}

	public void druckeEintrag(OutputStream stream) {
		PrintStream print = new PrintStream(stream);
		print.println(rufen() + verlag + "," + getJahr() + " " + isbn);

		// System.out.println(rufen() + verlag + "," + getJahr() + " " + isbn);
	}

	public String erzeugZitierschlüssel() {
		String loesung = getAutor().getVorname() + getAutor().getNachname() + getJahr();
		loesung = loesung.replaceAll(" ", "");
		return loesung;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((verlag == null) ? 0 : verlag.hashCode());
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
		Buch other = (Buch) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (verlag == null) {
			if (other.verlag != null)
				return false;
		} else if (!verlag.equals(other.verlag))
			return false;
		return true;
	}

	// praktikum 7
	public String exportierAlsCsv() {
		String result = null;
		result = super.exportierAlsCsv() + verlag + "," + isbn;
		return result;
	}

	// praaktikum 10
	// public String aussehen() {
	// return aussehen10() + "verlag= " + verlag + "," + "isbn= " + isbn;
	// }

	public String toString() {
		return (rufen() +"Verlag="+ verlag + ",   Jahr =" + getJahr() + "   Isbn =" + isbn);

	}

	public boolean isAvailable() {
		return isavailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isavailable = isAvailable;
	}
}
