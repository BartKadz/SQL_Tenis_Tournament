package beans;

public class Spotkanie {
	private int idSpotkania;
	private String set1;
	private String set2;
	private String set3;
	private String set4;
	private String set5;
	private int idTurnieju;
	private int idZawodnika1;
	private int idZawodnika2;
	private String nazwaTurnieju;
	private String zawodnik1;
	private String zawodnik2;

	public Spotkanie(int idSpotkania, String set1, String set2, String set3, String set4, String set5, int idTurnieju,
			int idZawodnika1, int idZawodnika2) {
		super();
		this.idSpotkania = idSpotkania;
		this.set1 = set1;
		this.set2 = set2;
		this.set3 = set3;
		this.set4 = set4;
		this.set5 = set5;
		this.idTurnieju = idTurnieju;
		this.idZawodnika1 = idZawodnika1;
		this.idZawodnika2 = idZawodnika2;
	}

	public Spotkanie() {
		// TODO Auto-generated constructor stub
	}

	public int getIdSpotkania() {
		return idSpotkania;
	}

	public void setIdSpotkania(int idSpotkania) {
		this.idSpotkania = idSpotkania;
	}

	public String getSet1() {
		return set1;
	}

	public void setSet1(String set1) {
		this.set1 = set1;
	}

	public String getSet2() {
		return set2;
	}

	public void setSet2(String set2) {
		this.set2 = set2;
	}

	public String getSet3() {
		return set3;
	}

	public void setSet3(String set3) {
		this.set3 = set3;
	}

	public String getSet4() {
		return set4;
	}

	public void setSet4(String set4) {
		this.set4 = set4;
	}

	public String getSet5() {
		return set5;
	}

	public void setSet5(String set5) {
		this.set5 = set5;
	}

	public int getIdTurnieju() {
		return idTurnieju;
	}

	public void setIdTurnieju(int idTurnieju) {
		this.idTurnieju = idTurnieju;
	}

	public int getIdZawodnika1() {
		return idZawodnika1;
	}

	public void setIdZawodnika1(int idZawodnika1) {
		this.idZawodnika1 = idZawodnika1;
	}

	public int getIdZawodnika2() {
		return idZawodnika2;
	}

	public void setIdZawodnika2(int idZawodnika2) {
		this.idZawodnika2 = idZawodnika2;
	}

	public String getNazwaTurnieju() {
		return nazwaTurnieju;
	}

	public void setNazwaTurnieju(String nazwaTurnieju) {
		this.nazwaTurnieju = nazwaTurnieju;
	}

	public String getZawodnik1() {
		return zawodnik1;
	}

	public void setZawodnik1(String zawodnik1) {
		this.zawodnik1 = zawodnik1;
	}

	public String getZawodnik2() {
		return zawodnik2;
	}

	public void setZawodnik2(String zawodnik2) {
		this.zawodnik2 = zawodnik2;
	}

}
