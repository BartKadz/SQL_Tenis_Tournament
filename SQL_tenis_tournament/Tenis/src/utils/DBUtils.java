package utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.*;
public class DBUtils {

	public static void DodajUzytkownika(Connection conn, Uzytkownik user) throws SQLException
	{
		String sql = "insert into uzytkownik values(?,?,?,?,?,?)";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setString(1,null);
    	pstm.setString(2, user.getLogin());
    	pstm.setString(3, user.getHaslo());
    	pstm.setString(4, user.getImie());
    	pstm.setString(5, user.getNazwisko());
    	pstm.setString(6, user.getUprawnienia());
    	int rs = pstm.executeUpdate();
	}
	public static Uzytkownik ZnajdzUzytkownika(Connection conn, String login, String haslo) throws SQLException
	{
		 String sql = "Select * from uzytkownik a " //
	                + " where a.login = ? and a.haslo= ?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, login);
	        pstm.setString(2, haslo);
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	            Uzytkownik user = new Uzytkownik();
	            user.setLogin(login);
	            user.setHaslo(haslo);
	            user.setImie(rs.getString("imie"));
	            user.setNazwisko(rs.getString("nazwisko"));
	            user.setUprawnienia(rs.getString("uprawnienia"));
	            user.setIdUzytkownika(rs.getInt("idUzytkownika"));

	            return user;
	        }
	        return null;
	    
	}
	public static Uzytkownik ZnajdzUzytkownika(Connection conn, int id) throws SQLException
	{
		 String sql = "Select * from uzytkownik a " //
	                + " where a.idUzytkownika=?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, id);
	        ResultSet rs = pstm.executeQuery();
	        if (rs.next()) {
	            Uzytkownik user = new Uzytkownik();
	            user.setIdUzytkownika(id);
	            user.setLogin(rs.getString("login"));
	            user.setHaslo(rs.getString("haslo"));
	            user.setImie(rs.getString("imie"));
	            user.setNazwisko(rs.getString("nazwisko"));
	            user.setUprawnienia(rs.getString("uprawnienia"));

	            return user;
	        }
	        return null;
	    
	}
	public static Uzytkownik ZnajdzUzytkownika(Connection conn, String login) throws SQLException
	{
		 String sql = "Select * from uzytkownik a " //
	                + " where a.login = ?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, login);
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	            Uzytkownik user = new Uzytkownik();
	            user.setLogin(login);
	            user.setHaslo(rs.getString("haslo"));
	            user.setImie(rs.getString("imie"));
	            user.setNazwisko(rs.getString("nazwisko"));
	            user.setUprawnienia(rs.getString("uprawnienia"));

	            return user;
	        }
	        return null;
	    
	}
	public static void aktualizujUzytkownika(Connection conn, Uzytkownik user) throws SQLException {
        String sql = "Update Uzytkownik set imie =? , nazwisko=? where idUzytkownika= ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getImie());
        pstm.setString(2, user.getNazwisko());
        pstm.setInt(3, user.getIdUzytkownika());
        
        pstm.executeUpdate();
    }
	public static List<Uzytkownik> listaUzytkownikow(Connection conn) throws SQLException 
	{
		 String sql = "Select * from Uzytkownik where uprawnienia not like 'admin';";
		 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        ResultSet rs = pstm.executeQuery();
	        List<Uzytkownik> list = new ArrayList<Uzytkownik>();
	        while (rs.next()) {
	        	int id = rs.getInt("idUzytkownika");
	        	String login = rs.getString("login");
	        	String haslo =  rs.getString("haslo");
	        	String imie = rs.getString("imie");
	        	String nazwisko =  rs.getString("nazwisko");
	        	String uprawnienia =  rs.getString("uprawnienia");
	        	Uzytkownik user = new Uzytkownik(id,login,haslo,imie,nazwisko,uprawnienia);
	            list.add(user);
	        }
	        return list;
	}
	public static void usunUzytkownika(Connection conn, String id) throws SQLException
	{
		String sql = "Delete from Uzytkownik where idUzytkownika=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		 
        pstm.setString(1, id);
 
        pstm.executeUpdate();
	}
	public static void dodajSpotkanie(Connection conn, Spotkanie spotkanie) throws SQLException
	{
		String sql = "insert into spotkanie values(?,?,?,?,?,?,?,?,?)";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setString(1,null);
    	pstm.setString(2, spotkanie.getSet1());
    	pstm.setString(3, spotkanie.getSet2());
    	pstm.setString(4, spotkanie.getSet3());
    	pstm.setString(5, spotkanie.getSet4());
    	pstm.setString(6, spotkanie.getSet5());
    	pstm.setInt(7, spotkanie.getIdTurnieju());
    	pstm.setInt(8, spotkanie.getIdZawodnika1());
    	pstm.setInt(9, spotkanie.getIdZawodnika2());
    	int rs = pstm.executeUpdate();
	}
	public static Spotkanie znajdzSpotkanie(Connection conn, int idSpotkania) throws SQLException
	{
		 String sql = "Select * from spotkanie a " //
	                + " where a.idSpotkanie = ?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, idSpotkania);
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	            Spotkanie spotkanie = new Spotkanie();
	            spotkanie.setIdSpotkania(rs.getInt("idSpotkanie"));
	            spotkanie.setSet1(rs.getString("Set1"));
	            spotkanie.setSet2(rs.getString("Set2"));
	            spotkanie.setSet3(rs.getString("Set3"));
	            spotkanie.setSet4(rs.getString("Set4"));
	            spotkanie.setSet5(rs.getString("Set5"));
	            spotkanie.setIdTurnieju(rs.getInt("idTurnieju"));
	            spotkanie.setIdZawodnika1(rs.getInt("idZawodnika1"));
	            spotkanie.setIdZawodnika2(rs.getInt("idZawodnika2"));
	            return spotkanie;
	        }
	        return null;
	    
	}
	public static void aktualizujSpotkanie(Connection conn, Spotkanie spotkanie) throws SQLException {
        String sql = "Update spotkanie set Set1=?,  Set2=?, Set3=?, Set4=?, Set5=?,idZawodnika1=?, idZawodnika2=? where idSpotkanie=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, spotkanie.getSet1());
        pstm.setString(2, spotkanie.getSet2());
        pstm.setString(3, spotkanie.getSet3());
        pstm.setString(4, spotkanie.getSet4());
        pstm.setString(5, spotkanie.getSet5());
        pstm.setInt(6, spotkanie.getIdZawodnika1());
        pstm.setInt(7, spotkanie.getIdZawodnika2());
        pstm.setInt(8, spotkanie.getIdSpotkania());
        pstm.executeUpdate();
    }
	public static List<Spotkanie> listaSpotkania(Connection conn,int id) throws SQLException 
	{
		 String sql = "Select * from spotkanie where idTurnieju=?";
		 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, id);
	        ResultSet rs = pstm.executeQuery();
	        List<Spotkanie> list = new ArrayList<Spotkanie>();
	        while (rs.next()) {
	        	Spotkanie spotkanie = new Spotkanie();
	            spotkanie.setIdSpotkania(rs.getInt("idSpotkanie"));
	            spotkanie.setSet1(rs.getString("Set1"));
	            spotkanie.setSet2(rs.getString("Set2"));
	            spotkanie.setSet3(rs.getString("Set3"));
	            spotkanie.setSet4(rs.getString("Set4"));
	            spotkanie.setSet5(rs.getString("Set5"));
	            spotkanie.setIdTurnieju(rs.getInt("idTurnieju"));
	            spotkanie.setIdZawodnika1(rs.getInt("idZawodnika1"));
	            spotkanie.setIdZawodnika2(rs.getInt("idZawodnika2"));;
	            list.add(spotkanie);
	        }
	        return list;
	}
	public static void usunSpotkanie (Connection conn, String id) throws SQLException
	{
		String sql = "Delete from spotkanie where idSpotkanie=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		 
        pstm.setString(1, id);
 
        pstm.executeUpdate();
	}
	public static void dodajZawodnika(Connection conn, Zawodnik zawodnik) throws SQLException
	{
		String sql = "insert into zawodnik values(?,?,?)";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setString(1,null);
    	pstm.setString(2,zawodnik.getImie());
    	pstm.setString(3,zawodnik.getNazwisko());
    	
    	int rs = pstm.executeUpdate();
	}
	public static Zawodnik znajdzZawodnika(Connection conn, int id) throws SQLException
	{
		 String sql = "Select * from zawodnik a " //
	                + " where a.idZawodnik = ?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, id);
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	            Zawodnik zawodnik = new Zawodnik();
	            zawodnik.setIdZawodnika(rs.getInt("idZawodnik"));
	            zawodnik.setImie(rs.getString("imie"));
	            zawodnik.setNazwisko(rs.getString("nazwisko"));
	            
	            return zawodnik;
	        }
	        return null;
	    
	}
	public static void aktualizujZawodnika(Connection conn, Zawodnik zawodnik) throws SQLException {
        String sql = "Update zawodnik set imie=?, nazwisko=? where idZawodnik=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setString(1,zawodnik.getImie());
    	pstm.setString(2,zawodnik.getNazwisko());
    	pstm.setInt(3,zawodnik.getIdZawodnika());
        pstm.executeUpdate();
    }
	public static List<Zawodnik> listaZawodnikow(Connection conn) throws SQLException 
	{
		 String sql = "Select * from zawodnik";
		 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        ResultSet rs = pstm.executeQuery();
	        List<Zawodnik> list = new ArrayList<Zawodnik>();
	        while (rs.next()) {
	        	int id = rs.getInt("idZawodnik");
	        	String imie = rs.getString("imie");
	        	String nazwisko = rs.getString("nazwisko");
	        	Zawodnik zawodnik = new Zawodnik(id,imie, nazwisko);
	            list.add(zawodnik);
	        }
	        return list;
	}
	public static void usunZawodnika (Connection conn, String id) throws SQLException
	{
		String sql = "Delete from zawodnik where idZawodnik=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		 
        pstm.setString(1, id);
 
        pstm.executeUpdate();
	}
	public static void dodajTurniej(Connection conn, Turniej turniej) throws SQLException
	{
		String sql = "insert into turniej values(?,?,?,?,?)";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setString(1,null);
    	pstm.setDate(2, new java.sql.Date(turniej.getData().getTime()));
    	pstm.setString(3,turniej.getMiejsce());
    	pstm.setString(4,turniej.getNawierzchnia());
    	pstm.setInt(5,turniej.getIdManagera());
    	
    	int rs = pstm.executeUpdate();
	}
	public static Turniej znajdzTurniej(Connection conn, int id) throws SQLException
	{
		 String sql = "Select * from turniej a " //
	                + " where a.idTurniej = ?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, id);
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	            Turniej turniej = new Turniej();
	            turniej.setIdTurnieju(id);
	            turniej.setData(rs.getDate("Data"));
	            turniej.setMiejsce(rs.getString("Miejsce"));
	            turniej.setNawierzchnia(rs.getString("Nawierzchnia"));
	            turniej.setIdManagera(rs.getInt("idManagera"));
	            return turniej;
	        }
	        return null;
	    
	}
	public static List<Turniej> listaTurniej(Connection conn, int id) throws SQLException
	{
		 String sql = "Select * from turniej a " //
	                + " where a.idManagera = ?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, id);
	        ResultSet rs = pstm.executeQuery();
	        List<Turniej> lista = new ArrayList<Turniej>();
	        while (rs.next()) {
	            Turniej turniej = new Turniej();
	            turniej.setIdTurnieju(rs.getInt("idTurniej"));
	            turniej.setData(rs.getDate("Data"));
	            turniej.setMiejsce(rs.getString("Miejsce"));
	            turniej.setNawierzchnia(rs.getString("Nawierzchnia"));
	            turniej.setIdManagera(id);
	            lista.add(turniej);
	        }
	        return lista;
	    
	}
	public static void aktualizujTurniej(Connection conn, Turniej turniej) throws SQLException {
        String sql = "Update Turniej set Data=?, Miejsce=?, Nawierzchnia=? where idTurniej=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setDate(1, new java.sql.Date(turniej.getData().getTime()));
    	pstm.setString(2,turniej.getMiejsce());
    	pstm.setString(3,turniej.getNawierzchnia());
    	pstm.setInt(4,turniej.getIdTurnieju());
        pstm.executeUpdate();
    }
	public static void aktualizujTurniej2(Connection conn, Turniej turniej) throws SQLException {
        String sql = "Update Turniej set Data=?, Miejsce=?, Nawierzchnia=?, idManagera=? where idTurniej=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setDate(1, new java.sql.Date(turniej.getData().getTime()));
    	pstm.setString(2,turniej.getMiejsce());
    	pstm.setString(3,turniej.getNawierzchnia());
    	pstm.setInt(4,turniej.getIdManagera());
    	pstm.setInt(5,turniej.getIdTurnieju());
        pstm.executeUpdate();
    }
	public static List<Turniej> listaTurniej(Connection conn) throws SQLException 
	{
		 String sql = "Select * from turniej";
		 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        ResultSet rs = pstm.executeQuery();
	        List<Turniej> list = new ArrayList<Turniej>();
	        while (rs.next()) {
	        	 Turniej turniej = new Turniej();
		            turniej.setIdTurnieju(rs.getInt("idTurniej"));
		            turniej.setData(rs.getDate("Data"));
		            turniej.setMiejsce(rs.getString("Miejsce"));
		            turniej.setNawierzchnia(rs.getString("Nawierzchnia"));
		            turniej.setIdManagera(rs.getInt("idManagera"));
		            list.add(turniej);
	        }
	        return list;
	}
	public static void usunTurniej (Connection conn, String id) throws SQLException
	{
		String sql = "Delete from Turniej where idTurniej=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		 
        pstm.setString(1, id);
 
        pstm.executeUpdate();
	}
	
}
