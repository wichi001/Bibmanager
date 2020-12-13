package Bibman;

import java.io.Serializable;

public class Autor implements CsvExportable, Serializable {
	private String Vorname;
	private String Nachname;
	private boolean isvailable;

	public Autor() {
	}
	public Autor(String Vorname, String Nachname) {
		this.Vorname = Vorname;
		this.Nachname = Nachname;
	}

	public String getVorname() {
		return Vorname;
	}

	public void setVorname(String Vorname) {
		this.Vorname = Vorname;
	}

	public String getNachname() {
		return Nachname;
	}

	public void setNachname(String Nachname) {
		this.Nachname = Nachname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Nachname == null) ? 0 : Nachname.hashCode());
		result = prime * result + ((Vorname == null) ? 0 : Vorname.hashCode());
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
		Autor other = (Autor) obj;
		if (Nachname == null) {
			if (other.Nachname != null)
				return false;
		} else if (!Nachname.equals(other.Nachname))
			return false;
		if (Vorname == null) {
			if (other.Vorname != null)
				return false;
		} else if (!Vorname.equals(other.Vorname))
			return false;
		return true;
	}

	// praktikum 7
	public String exportierAlsCsv() {
		String result = null;
		result = getVorname() + "," + getNachname();
		return result;
	}
	// praktikum 10

//	public String aussehen() {
//		return Vorname + Nachname;
//	}

	public String toString() {
		return Vorname + " " + Nachname;
	}

	private boolean isAvailable() {
		return isvailable;
	}

	public void setIsavailable(boolean isAvailable) {
		this.isvailable = isAvailable;
	}
}
