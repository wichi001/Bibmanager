package Bibman;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class Webseite extends BibEintrag implements CsvExportable, Serializable {
	private String url;
	private boolean isavailable;

	// public Webseite() {
	// }
	public Webseite(String Vorname, String Nachname, String titel, int jahr, String url) {
		super(Vorname, Nachname, titel, jahr);
		this.url = url;

	}

	public void druckeEintrag(OutputStream stream) {
		PrintStream print = new PrintStream(stream);
		print.println(rufen() + "," + getJahr() + url);

		// System.out.println(rufen() + "," + url);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Webseite other = (Webseite) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	// praktikum 7
	public String exportierAlsCsv() {
		String result = null;
		result = super.exportierAlsCsv() + url;
		return result;
	}

	// praktikum 10
	// public String aussehen() {
	// return aussehen10() + "url= " + url;
	// }

	public String toString() {
		return (rufen() + ", " + " Jahr= " + getJahr() + " ,Url =" + url);

	}

	public boolean isAvailable() {
		return isavailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isavailable = isAvailable;
	}
}
