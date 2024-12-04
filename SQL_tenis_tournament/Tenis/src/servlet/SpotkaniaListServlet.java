package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Ranking;
import beans.Spotkanie;
import beans.Turniej;
import beans.Uzytkownik;
import beans.Zawodnik;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/games" })
public class SpotkaniaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SpotkaniaListServlet() {
		super();
	}

	@SuppressWarnings("unused")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errorString = null;
		Connection conn = MyUtils.getStoredConnection(request);
		String code = (String) request.getParameter("code");
		List<Spotkanie> spotkania = null;
		Turniej turniej = null;
		List<Zawodnik> zawodnicy = null;
		int kod = 0;
		try {
			kod = Integer.valueOf(code);
			spotkania = DBUtils.listaSpotkania(conn, kod);
			turniej = DBUtils.znajdzTurniej(conn, kod);
			zawodnicy= DBUtils.listaZawodnikow(conn);
			for (int i = 0; i < spotkania.size(); i++) {
				for (int j = 0; j < zawodnicy.size(); j++) {
					if (spotkania.get(i).getIdZawodnika1() == zawodnicy.get(j).getIdZawodnika()) {
						spotkania.get(i)
								.setZawodnik1(zawodnicy.get(j).getImie() + " " + zawodnicy.get(j).getNazwisko());
					}
					if (spotkania.get(i).getIdZawodnika2() == zawodnicy.get(j).getIdZawodnika()) {
						spotkania.get(i)
								.setZawodnik2(zawodnicy.get(j).getImie() + " " + zawodnicy.get(j).getNazwisko());
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		List<Ranking> rankingi = new ArrayList<Ranking>();
		int wygrane = 0;
		int przegrane = 0;
		int wygrane_gemy;
		int przegrane_gemy = 0;
		int wygrane_sety = 0;
		int przegrane_sety = 0;
		double stosunek = 0.0;
		if(zawodnicy!=null)
		{
		for (Zawodnik zawodnik : zawodnicy) {
			for (Spotkanie spotkanie : spotkania) {
				if (zawodnik.getIdZawodnika() == spotkanie.getIdZawodnika1()
						|| zawodnik.getIdZawodnika() == spotkanie.getIdZawodnika2()) {
					boolean exist = false;
					for (Ranking rank : rankingi) {
						if (rank.getIdZawodnika() == zawodnik.getIdZawodnika()) {
							exist = true;
						}
					}
					if (!exist) {
						Ranking ranking = new Ranking();
						ranking.setIdZawodnika(zawodnik.getIdZawodnika());
						ranking.setZawodnik(zawodnik.getImie() + " " + zawodnik.getNazwisko());
						rankingi.add(ranking);
					}
				}
			}
		}
		for (Ranking ranking : rankingi) {
			wygrane = 0;
			przegrane = 0;
			wygrane_gemy = 0;
			przegrane_gemy = 0;
			wygrane_sety = 0;
			przegrane_sety = 0;
			stosunek = 0.0;
			for (Spotkanie spotkanie : spotkania) {
				if (ranking.getIdZawodnika() == spotkanie.getIdZawodnika1()) {
					ArrayList<List<String>> sety = new ArrayList<List<String>>();
					int wygrane_sety_meczu = 0;
					int pregrane_sety_meczu = 0;
					if (spotkanie.getSet1() != null) {
						String[] set1 = spotkanie.getSet1().split(":");
						List<String> al = Arrays.asList(set1);
						sety.add(al);
					}
					if (spotkanie.getSet2() != null) {
						String[] set1 = spotkanie.getSet2().split(":");
						List<String> al = Arrays.asList(set1);
						sety.add(al);
					}
					if (spotkanie.getSet3() != null) {
						String[] set1 = spotkanie.getSet3().split(":");
						List<String> al = Arrays.asList(set1);
						sety.add(al);
					}
					if (!spotkanie.getSet4().equals("")) {
						String[] set1 = spotkanie.getSet4().split(":");
						List<String> al = Arrays.asList(set1);
						sety.add(al);
					}
					if (!spotkanie.getSet5().equals("")) {
						String[] set1 = spotkanie.getSet5().split(":");
						List<String> al = Arrays.asList(set1);
						sety.add(al);
					}
					for (int i = 0; i < sety.size(); i++) {
						wygrane_gemy += Integer.valueOf(sety.get(i).get(0));
						przegrane_gemy += Integer.valueOf(sety.get(i).get(1));
						if (Integer.valueOf(sety.get(i).get(0)) > Integer.valueOf(sety.get(i).get(1))) {
							wygrane_sety++;
							wygrane_sety_meczu++;
						} else {
							przegrane_sety++;
							pregrane_sety_meczu++;
						}
					}
					if (wygrane_sety_meczu > pregrane_sety_meczu) {
						wygrane++;
					} else {
						przegrane++;
					}

				} else if (ranking.getIdZawodnika() == spotkanie.getIdZawodnika2()) {
					ArrayList<List<String>> sety = new ArrayList<List<String>>();
					int wygrane_sety_meczu = 0;
					int pregrane_sety_meczu = 0;
					if (spotkanie.getSet1() != null) {
						String[] set1 = spotkanie.getSet1().split(":");
						List<String> al = Arrays.asList(set1);
						sety.add(al);
					}
					if (spotkanie.getSet2() != null) {
						String[] set1 = spotkanie.getSet2().split(":");
						List<String> al = Arrays.asList(set1);
						sety.add(al);
					}
					if (spotkanie.getSet3() != null) {
						String[] set1 = spotkanie.getSet3().split(":");
						List<String> al = Arrays.asList(set1);
						sety.add(al);
					}
					if (!spotkanie.getSet4().equals("")) {
						String[] set1 = spotkanie.getSet4().split(":");
						List<String> al = Arrays.asList(set1);
						sety.add(al);
					}
					if (!spotkanie.getSet5().equals("")) {
						String[] set1 = spotkanie.getSet5().split(":");
						List<String> al = Arrays.asList(set1);
						sety.add(al);
					}
					for (int i = 0; i < sety.size(); i++) {
						wygrane_gemy += Integer.valueOf(sety.get(i).get(1));
						przegrane_gemy += Integer.valueOf(sety.get(i).get(0));
						if (Integer.valueOf(sety.get(i).get(0)) < Integer.valueOf(sety.get(i).get(1))) {
							wygrane_sety++;
							wygrane_sety_meczu++;
						} else {
							przegrane_sety++;
							pregrane_sety_meczu++;
						}
					}
					if (wygrane_sety_meczu > pregrane_sety_meczu) {
						wygrane++;
					} else {
						przegrane++;
					}
				}
			}
		
			stosunek = Double.valueOf(wygrane_gemy) /Double.valueOf(przegrane_gemy);
			double roundOff = Math.round(stosunek * 100.0) / 100.0;
			ranking.setPrzegrane(przegrane);
			ranking.setWygrane(wygrane);
			ranking.setWygrane_gemy(wygrane_gemy);
			ranking.setPrzegrane_gemy(przegrane_gemy);
			ranking.setPrzegrane_sety(przegrane_sety);
			ranking.setWygrane_sety(wygrane_sety);
			ranking.setStosunek_gemow(roundOff);
		}
		}
		 Collections.sort(rankingi, new Comparator<Ranking>(){
	            public int compare(Ranking e1, Ranking e2){
	                return Integer.valueOf(e2.getWygrane()).compareTo(Integer.valueOf(e1.getWygrane()));
	            }
	        });
		HttpSession session = request.getSession();
		Uzytkownik user = MyUtils.getLoginedUser(session);
		int admin = 0;
		if (user != null) {
			if (user.getUprawnienia().equals("admin")) {
				admin = 1;
			} else if (user.getIdUzytkownika() == turniej.getIdManagera()) {
				admin = 1;
			}
		}

		request.setAttribute("errorString", errorString);
		request.setAttribute("spotkania", spotkania);
		request.setAttribute("rankingi", rankingi);
		request.setAttribute("kod", kod);
		request.setAttribute("admin", admin);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/SpotkaniaListView.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
