package beans;

public class Ranking {

	private int idZawodnika;
	private String zawodnik;
	private int wygrane;
	private int przegrane;
	private int wygrane_sety;
	private int przegrane_sety;
	private int wygrane_gemy;
	private int przegrane_gemy;
	private double stosunek_gemow;

	public Ranking(String zawodnik, int wygrane, int przegrane, int wygrane_sety, int przegrane_sety, int wygrane_gemy,
			int przegrane_gemy, double stosunek_gemow) {
		super();
		this.zawodnik = zawodnik;
		this.wygrane = wygrane;
		this.przegrane = przegrane;
		this.wygrane_sety = wygrane_sety;
		this.przegrane_sety = przegrane_sety;
		this.wygrane_gemy = wygrane_gemy;
		this.przegrane_gemy = przegrane_gemy;
		this.stosunek_gemow = stosunek_gemow;
	}

	public Ranking() {
		// TODO Auto-generated constructor stub
	}

	public String getZawodnik() {
		return zawodnik;
	}

	public void setZawodnik(String zawodnik) {
		this.zawodnik = zawodnik;
	}

	public int getWygrane() {
		return wygrane;
	}

	public void setWygrane(int wygrane) {
		this.wygrane = wygrane;
	}

	public int getPrzegrane() {
		return przegrane;
	}

	public void setPrzegrane(int przegrane) {
		this.przegrane = przegrane;
	}

	public int getWygrane_sety() {
		return wygrane_sety;
	}

	public void setWygrane_sety(int wygrane_sety) {
		this.wygrane_sety = wygrane_sety;
	}

	public int getPrzegrane_sety() {
		return przegrane_sety;
	}

	public void setPrzegrane_sety(int przegrane_sety) {
		this.przegrane_sety = przegrane_sety;
	}

	public int getWygrane_gemy() {
		return wygrane_gemy;
	}

	public void setWygrane_gemy(int wygrane_gemy) {
		this.wygrane_gemy = wygrane_gemy;
	}

	public int getPrzegrane_gemy() {
		return przegrane_gemy;
	}

	public void setPrzegrane_gemy(int przegrane_gemy) {
		this.przegrane_gemy = przegrane_gemy;
	}

	public double getStosunek_gemow() {
		return stosunek_gemow;
	}

	public void setStosunek_gemow(double stosunek_gemow) {
		this.stosunek_gemow = stosunek_gemow;
	}

	public int getIdZawodnika() {
		return idZawodnika;
	}

	public void setIdZawodnika(int idZawodnika) {
		this.idZawodnika = idZawodnika;
	}
	

}
