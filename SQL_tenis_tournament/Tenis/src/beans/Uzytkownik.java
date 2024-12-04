package beans;

public class Uzytkownik {
	int idUzytkownika;
	String login;
	String haslo;
	String imie;
	String nazwisko;
	String uprawnienia;

	public Uzytkownik() {
	};

	public Uzytkownik(int idUzytkownika, String login, String haslo, String imie, String nazwisko, String uprawnienia) {
		super();
		this.idUzytkownika = idUzytkownika;
		this.login = login;
		this.haslo = haslo;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.uprawnienia = uprawnienia;
	}

	public int getIdUzytkownika() {
		return idUzytkownika;
	}

	public void setIdUzytkownika(int idUzytkownika) {
		this.idUzytkownika = idUzytkownika;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getUprawnienia() {
		return uprawnienia;
	}

	public void setUprawnienia(String uprawnienia) {
		this.uprawnienia = uprawnienia;
	}

}
