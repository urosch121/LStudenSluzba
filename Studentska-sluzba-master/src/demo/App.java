package demo;

import java.util.Scanner;
import utils.Fajlovi;
import utils.Yt;

public class App {
	
	public static void main(String[] args) {
		
		boolean pocetniMeni = true;
		System.out.println("Dobro dosli u studentsku sluzbu!!");
		System.out.println("==============================");
		Menu m = new Menu();
		Fajlovi f = new Fajlovi();
				
		//Ucitavanje iz fajlova!!!
		f.ucitajCsvProfesor();
		f.ucitajCsvPredmet();
		f.ucitajCsvStudenta();
		
		//Collections.sort(Liste.listaProfesora, Profesor.profesorSortIme);
		
//		Liste.listaProfesora.stream().sorted(Profesor.profesorSortIme.thenComparing(Profesor.profesorSortPrezime))
//			.forEach(Profesor -> System.out.println(Profesor));
		do
		{
			System.out.println("");
			System.out.println("1) Rad sa profesorima!");
			System.out.println("2) Rad sa studentima!");
			System.out.println("3) Rad sa predmetima! ");
			System.out.println("4) Rad sa ocenama!");
			System.out.println("0) Izlaz iz programa!");
			System.out.println("");
			System.out.print("Izaberite opciju: ");
			
			Scanner unos = new Scanner(System.in);
			String odluka = unos.next();
			
			if (odluka.equals("1"))
				m.opcija1();
			else if (odluka.equals("2"))
				m.opcija2();
			else if (odluka.equals("3"))
				m.opcija3();
			else if (odluka.equals("4"))
				m.opcija4();
			else if (odluka.equals("0")) 
			{
				//Upisivanje u fajl. Svaki put ispisuje od 0!
//				try {
//					f.upisiUFajl(prof.csvSviProfesori(), prof.getPutanjaFajlaProfesor());
//					f.upisiUFajl(pred.csvSviPredmeti(), pred.getPutanjaFajlaPredmet());
//					f.upisiUFajl(stud.csvSviStudenti(), stud.getPutanjaFajlaStudent());
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				Yt.pustiPesmu();
				pocetniMeni = false;
			}
			else
				System.out.println("Uneli ste pogresnu opciju! ");
		}
		while (pocetniMeni == true);
		System.out.println("Izasli ste iz programa!");
	}

}
