package demo;

import java.util.Scanner;

import studentskaSluzba.Predmet;
import studentskaSluzba.Profesor;
import studentskaSluzba.Student;
import utils.Citac;
import utils.Fajlovi;
import utils.ProveraUnetogBroja;
import demo.Liste;

public class Menu implements Liste{

	Citac citacC = new Citac();
	Profesor profesorP = new Profesor();
	Predmet predmetP = new Predmet();
	Student studentS = new Student();

	public void opcija1() {
		boolean pomocniMeni1 = true;
		do {
			System.out.println("Menu - Rad sa profesorima");
			System.out.println("1) Lista profesora \n2) Unos novog profesora \n0) Povratak na predhodni meni\n");
			int odluka1 = ProveraUnetogBroja.proveriUnetiBroj("Izaberi opciju: ");

			if (odluka1 == 1)
			{
				Liste.listaProfesora.sort(Profesor.profesorSortIme.thenComparing(Profesor.profesorSortPrezime));
				//listaProfesora.stream().forEach(Profesor -> System.out.println(Profesor));
				listaProfesora.forEach(Profesor -> System.out.println(Profesor));
				//Liste.prikaziListu(listaProfesora);
			}
			else if (odluka1 == 2)
				citacC.unesiProfesora(new Scanner(System.in));
			else if (odluka1 == 0)
				pomocniMeni1 = false;
			else
				System.out.println("Uneli ste pogresnu opciju! Unesite ponovo!");
		} while (pomocniMeni1 == true);
	}

	public void opcija2() {
		boolean pomocniMeni2 = true;
		do {
			System.out.println("Menu - Rad sa studentima");
			System.out.println("1) Lista studenta \n2) Lista studenta sa ocenama \n3) Sve ocene odredjenog studenta \n"
					+ "4) Prosecna ocena za studenta \n5) Prosecna ocena za studenta kod odredjenog profesora \n6) Lista studenta po godini upisa \n"
					+ "7) Unos novog studenta \n0) Povratak na predhodni meni\n");
			int odluka2 = ProveraUnetogBroja.proveriUnetiBroj("Izaberi opciju: ");

			if (odluka2 == 1)
				Liste.prikaziListu(listaStudenta);
			else if (odluka2 == 2)
				studentS.listaStudentaSaOcenama();
			else if (odluka2 == 3)
				studentS.sveOceneOdredjenogStudenta();
			else if (odluka2 == 4) 
			{
				Liste.prikaziListu(listaStudenta);
				Student unetStudent = (Student) Liste.pretragaPoID(studentS);
				if (unetStudent == null)
					System.out.println("Student sa unetim brojem indexa i godinom upisa ne postoji!");
				else 
					System.out.println(unetStudent.prosecnaOcena());
			} 
			else if (odluka2 == 5) {
				Liste.prikaziListu(listaStudenta);
				Student unetStudent = (Student) Liste.pretragaPoID(studentS);
				if (unetStudent == null)
					System.out.println("Student sa unetim brojem indexa i godinom upisa ne postoji!");
				else {
					Liste.prikaziListu(listaProfesora);
					Profesor profesor = (Profesor) Liste.pretragaPoID(profesorP);
					if (profesor == null)
						System.out.println("Profesor sa unetim ID ne postoji!");
					else 
						System.out.println(unetStudent.prosecnaOcenaProfesor(profesor.getId()));
				}
			}
			else if (odluka2 == 6)
				studentS.listaStudentaPoGodiniUpisa();
			else if (odluka2 == 7)
				citacC.unesiStudenta(new Scanner(System.in));

			else if (odluka2 == 0)
				pomocniMeni2 = false;
			else
				System.out.println("Uneli ste pogresnu opciju! Unesite ponovo!");
		} while (pomocniMeni2 == true);
	}

	public void opcija3() {
		boolean pomocniMeni3 = true;
		do {
			System.out.println("Menu - Rad sa predmetima");
			System.out.println("1) Lista predmeta \n2) Prikaz ocena iz predmeta \n3) Prosecna ocena iz predmeta \n4) Unos novog predmeta "
					+ "\n5) Promeni predmetu profesora \n0) Povratak na predhodni meni\n");
			int odluka3 = ProveraUnetogBroja.proveriUnetiBroj("Izaberi opciju: ");

			if (odluka3 == 1)
			{
				//Collections.sort(Liste.listaPredmeta); //ovako se poziva kada se implementira comparable i override metoda compare to
				//Collections.sort(Liste.listaPredmeta, Predmet.sortPredmetNaziv); //ovako se poziva kada se napravi staticka metoda comparator
				
				Liste.listaPredmeta.sort(Predmet.sortPredmetNaziv);// ovako se poziva kada se napravi staticka metoda comparator
				Liste.prikaziListu(listaPredmeta);
			}
			else if (odluka3 == 2) 
			{
				Liste.prikaziListu(listaPredmeta);
				Predmet izabranPredmet = (Predmet) Liste.pretragaPoID(predmetP);
				if (izabranPredmet == null)
					System.out.println("Ne postoji predmet sa unetim ID!");
				else
					izabranPredmet.prikazOcenaPoPredmetu();
			} 
			else if (odluka3 == 3)
			{
				double predmetProsek = 0.0;
				Liste.prikaziListu(listaPredmeta);
				Predmet izabranPredmet = (Predmet) Liste.pretragaPoID(predmetP);
				if (izabranPredmet == null)
					System.out.println("Ne postoji predmet sa unetim ID!");
				else
				{
					predmetProsek = izabranPredmet.predmetProsecnaOcena();
					if (predmetProsek > 0.0)
						System.out.println("Prosecna ocena za izabrani predmet je: " + predmetProsek);
					else
						System.out.println("Nema ocena iz izabranog predmeta!");
				}
			}
				
			else if (odluka3 == 4)
				citacC.unesiPredmet(new Scanner(System.in));
			else if (odluka3 == 5)
			{
				Liste.prikaziListu(listaPredmeta);
				Predmet izabraniPredmet = (Predmet) Liste.pretragaPoID(predmetP);
				if (izabraniPredmet == null)
					System.out.println("Ne postoji predmet sa unetim ID!");
				else
				{
					Liste.prikaziListu(listaProfesora);
					Profesor izabraniProfesor = (Profesor) Liste.pretragaPoID(profesorP);
					if (izabraniProfesor == null)
						System.out.println("Ne postoji profesor sa unetim ID!");
					else
					{
						String CSVstari = izabraniPredmet.csvPredmet();
						izabraniPredmet.promeniProfesora(izabraniProfesor);
						String CSVnovi = izabraniPredmet.csvPredmet();
						System.out.println(CSVstari);
						System.out.println(CSVnovi);
						new Fajlovi().izmeniPredmetUFajlu(izabraniPredmet, CSVstari, CSVnovi);
					}
				}
			}
			else if (odluka3 == 0)
				pomocniMeni3 = false;
			
			else
				System.out.println("Uneli ste pogresnu opciju! Unesite ponovo!");

		} while (pomocniMeni3 == true);
	}

	public void opcija4() 
	{
		boolean pomocniMeni4 = true;
		do {
			System.out.println("Menu - Rad sa ocenama");
			System.out.println("1) Dodaj ocenu studentu \n2) Dodaj svim studentima ocenu iz odredjenog predmeta \n"
					+ "3) Dodaj studentu ocene iz svih predmeta \n0) Povratak na predhodni meni\n");
			int odluka4 = ProveraUnetogBroja.proveriUnetiBroj("Izaberi opciju: ");
			
			if (odluka4 == 1) 
				citacC.unesiPolozenIspit(new Scanner(System.in));
			
				
			else if (odluka4 == 2) 
			{
				Liste.prikaziListu(listaPredmeta);
				Predmet predmet = (Predmet) Liste.pretragaPoID(predmetP);
				if (predmet == null)
					System.out.println("Ne postoji predmet sa unetim ID!");
				else
					predmet.dodajSvimStudentimaOcenuIzPredmeta();
			}
			else if (odluka4 == 3) 
				studentS.dodajStudentuOceneIzSvihPredmeta();
			else if (odluka4 == 0)
				pomocniMeni4 = false;
			else
				System.out.println("Uneli ste pogresnu opciju! Unesite ponovo!");
		} while (pomocniMeni4 == true);
	}
}
