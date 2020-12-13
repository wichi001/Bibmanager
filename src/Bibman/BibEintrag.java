package Bibman;

import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public abstract class BibEintrag implements Comparable<BibEintrag>, CsvExportable, Serializable {
	private final int id;
	private String titel;
	private int jahr;
	private static int zaehler = 1;
	private Autor autor; // beziehung zwischen die klasse BibEintrag und Autor
	private boolean isavailable;

	public BibEintrag() {
		this.id = zaehler;
		zaehler++;
	}

	public BibEintrag(String Vorname, String Nachname, String titel, int jahr) {
		/*
		 * ich wollte auch den Vorname und Nachname auf die Konsole miteinanader
		 * eingeben,deswegen habe ich die getMethode() von Nachname und Vorname in Meine
		 * Klasse Autor geschrieben
		 */
		/*
		 * ich habe auch ein neues Objekt autor in die Klasse BibEintrag gegründet, um
		 * die Fehler Java.Nullpoinhterexception zu vermeinden
		 */
		autor = new Autor(Vorname, Nachname);
		this.titel = titel;
		this.jahr = jahr;
		this.id = zaehler;
		zaehler++;
	}

	public int berechneAlter() {
		int ergebnis = 0;
		LocalDate currentDate = LocalDate.now();
		ergebnis = currentDate.getYear() - jahr;
		return ergebnis;
	}

	public String rufen() {
		return "id = " + id + " " + "Vorname =" + autor.getVorname() + "  Nachname =" + autor.getNachname()
				+ ":   Titel =" + titel + ",";
	}

	public int getJahr() {
		return jahr;
	}

	public String getTitel() {
		return titel;
	}

	public Autor getAutor() {
		return autor;
	}

	public void schein() {
		System.out.println(getTitel() + "wurde vor  " + berechneAlter() + "  Jahren verffentlicht.");
	}

	public abstract void druckeEintrag(OutputStream stream);

	public int compareTo(BibEintrag j) {
		if (this.jahr > j.getJahr()) // ich vergleiche das datum von mein Objekt mit dem Datum der objekt in meine
										// Liste
			return -1; // -1 bedeutet ich setze dan erstmal die groeße und dann die kleine. anders
						// gesagt von oben nach unten.
		else if (this.jahr < j.getJahr())
			return 1;
		else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + jahr;
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BibEintrag other = (BibEintrag) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (jahr != other.jahr)
			return false;
		if (titel == null) {
			if (other.titel != null)
				return false;
		} else if (!titel.equals(other.titel))
			return false;
		return true;
	}

	public String exportierAlsCsv() {
		String result = null;
		result = id + "," + autor.getVorname() + "," + autor.getNachname() + "," + titel + "," + jahr + ",";
		return result;
	}
	// ich rufe einmal die set Methode von id

	public static int getZaehler() {
		return zaehler;
	}

	public static void setZaehler(int zaehler) {
		BibEintrag.zaehler = zaehler;
	}

	// praktikum 10
	// public String aussehen10() {
	// return "id= " + id + ", " + "autor= " + autor.getVorname() + " " +
	// autor.getNachname() + "," + "titel= "
	// + titel + " Jahr = " + jahr + ",";
	// }

	// public abstract String aussehen();

	public abstract String toString();

	public boolean isAvailable() {
		return isavailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isavailable = isAvailable;
	}
}
