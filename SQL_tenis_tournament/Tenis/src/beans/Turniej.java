package beans;

import java.util.Date;

public class Turniej {
	private int idTurnieju;
	private Date data;
	private String Miejsce;
	private String Nawierzchnia;
	private int idManagera;
	private String Manager;
	private int czy_moj;

	public Turniej(int idTurnieju, Date data, String miejsce, String nawierzchnia, int idManagera) {
		super();
		this.idTurnieju = idTurnieju;
		this.data = data;
		Miejsce = miejsce;
		Nawierzchnia = nawierzchnia;
		this.idManagera = idManagera;
	}

	public Turniej() {
		// TODO Auto-generated constructor stub
	}

	public int getIdTurnieju() {
		return idTurnieju;
	}

	public void setIdTurnieju(int idTurnieju) {
		this.idTurnieju = idTurnieju;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMiejsce() {
		return Miejsce;
	}

	public void setMiejsce(String miejsce) {
		Miejsce = miejsce;
	}

	public String getNawierzchnia() {
		return Nawierzchnia;
	}

	public void setNawierzchnia(String nawierzchnia) {
		Nawierzchnia = nawierzchnia;
	}

	public int getIdManagera() {
		return idManagera;
	}

	public void setIdManagera(int idManagera) {
		this.idManagera = idManagera;
	}

	public String getManager() {
		return Manager;
	}

	public void setManager(String manager) {
		Manager = manager;
	}

	public int getCzy_moj() {
		return czy_moj;
	}

	public void setCzy_moj(int czy_moj) {
		this.czy_moj = czy_moj;
	}
	

}
