package utils;

import java.io.IOException;
import java.util.Scanner;
import demo.Liste;
import studentskaSluzba.Predmet;
import studentskaSluzba.Profesor;
import studentskaSluzba.Student;

public class Citac implements Liste{
	
	//************************************************************//
	public void unesiProfesora (Scanner unos) 
	{
		int id;
		String ime;
		String prezime;
		boolean postojiProfesor = false;
		
		do 
		{
			postojiProfesor = false;
			id = ProveraUnetogBroja.proveriUnetiBroj("Unesite ID profesora: ");
			for (Profesor profesor : Liste.listaProfesora)
				if (profesor.getId() == id) 
				{
					System.out.println("Vec postoji profesora sa ovim ID brojem. Unesite drugi ID!");
					postojiProfesor = true;
				}
		}
		while (postojiProfesor == true);
		
		System.out.print("Unesite ime profesora: " );
		ime = unos.nextLine();
		System.out.print("Unesite prezime profesora: ");
		prezime = unos.nextLine();
		
		Profesor pr = new Profesor(id, ime, prezime);
		Liste.listaProfesora.add(pr);
		System.out.println("Profesor sa " + pr + " je uspesno dodat u listu profesora!");
		String csvProfesor = pr.csvProfesor();
		try {
			new Fajlovi().upisiUFajlSamoNovObjekat(csvProfesor, pr.getPutanjaFajlaProfesor());
		} catch (IOException e) {
			System.out.println("Fajl sa profesorima nije nadjen!");
			e.printStackTrace();
		}
	}
	
	//************************************************************//
	public void unesiStudenta (Scanner unos) 
	{
		int brojIndexa;
		int godinaUpisa;
		String ime;
		String prezime;
		boolean postojiStudent = false;
		
		do 
		{
			postojiStudent = false;
			brojIndexa = ProveraUnetogBroja.proveriUnetiBroj("Unesite broj indexa studenta: ");
			godinaUpisa = ProveraUnetogBroja.proveriUnetiBroj("Unesite godinu upisa: ");
			for (Student student : Liste.listaStudenta)
				if (student.getBrojIndexa() == brojIndexa && student.getGodinaUpisa() == godinaUpisa) 
				{
					System.out.println("Vec postoji student sa ovim indexom. Unesite drugi index!");
					postojiStudent = true;
				}
		}
		while (postojiStudent == true);
		
		System.out.print("Unesite ime studenta: " );
		ime = unos.nextLine();
		System.out.print("Unesite prezime studenta: ");
		prezime = unos.nextLine();
		
		Student st = new Student(brojIndexa, godinaUpisa, ime, prezime);
		Liste.listaStudenta.add(st);
		System.out.println("Student sa " + st + " je uspesno dodat u listu studenta!");
		String csvStudent = st.csvStudent();
		
		try {
			new Fajlovi().upisiUFajlSamoNovObjekat(csvStudent, st.getPutanjaFajlaStudent());
		} catch (IOException e) {
			System.out.println("Fajl sa studentima nije nadjen!");
			e.printStackTrace();
		}
	}
	
	//************************************************************//
	public void unesiPredmet (Scanner unos)
	{
		int id;
		String naziv;
		
		boolean postojiPredmet = false;
		do
		{
			Liste.prikaziListu(listaPredmeta);
			postojiPredmet = false;
			id = ProveraUnetogBroja.proveriUnetiBroj("Unesite ID novog predmeta: ");
			for (Predmet predmet : Liste.listaPredmeta)
			{
				if (predmet.getId() == id)
				{
					System.out.println("Vec postoji predmet sa ovim ID brojem. Unesite drugi ID!");
					postojiPredmet = true;
				}
			}
		}
		while (postojiPredmet == true);
		
		System.out.print("Unesite naziv predmeta: ");
		naziv = unos.nextLine();
		
		if (Liste.listaProfesora.size() > 0)
		{
			boolean postojiProfesor = false;
			do 
			{
				postojiProfesor = false;
				Liste.prikaziListu(listaPredmeta);
				Profesor nadjeniProfesor = (Profesor) Liste.pretragaPoID(new Profesor());
				if (nadjeniProfesor != null) 
				{
					Predmet predmet = new Predmet(id, naziv, nadjeniProfesor);
					Liste.listaPredmeta.add(predmet);
					System.out.println("Predmet sa " + predmet + " je uspesno dodat u listu predmeta! ");
					postojiProfesor = true;
					String csvPredmet = predmet.csvPredmet();
					try {
						new Fajlovi().upisiUFajlSamoNovObjekat(csvPredmet, predmet.getPutanjaFajlaPredmet());
					} catch (IOException e) {
						System.out.println("Fajl sa predmetima nije nadjen!");
						e.printStackTrace();
					}
				}
				else
					System.out.println("Ne postoji profesor sa unetim ID!");
			}
			while (postojiProfesor == false);
		}
		else 
			System.out.println("Jos uvek nema profesora u bazi podataka. Morate prvo uneti profesora!");
	}
	//************************************************************//
	public void unesiPolozenIspit (Scanner unos)
	{
		Student student = new Student();
		int ocena;
		Liste.prikaziListu(listaStudenta);
		Student st = (Student) Liste.pretragaPoID(student);
		if (st != null)
		{
			boolean krug = false;
			int redniBrojPredmeta = 0;
			int odlukaPredmet = 0;
			do
			{
				krug = false;
				Liste.prikaziListu(listaPredmeta);
				odlukaPredmet = ProveraUnetogBroja.proveriUnetiBroj("Unesite ID predmeta za koji zelite da unesete ocenu: ");
				
				for (int i = 0; i < Liste.listaPredmeta.size(); i++)
				{
					if (Liste.listaPredmeta.get(i).getId() == odlukaPredmet) 
					{
						redniBrojPredmeta = i;
						krug = true;
					}
				}
			}
			while (krug == false);
			ocena = ProveraUnetogBroja.proveriUnetuOcenu("Unesite ocenu: ");
			String stariCSV = st.csvStudent();
			st.dodajPolozenIspit(Liste.listaPredmeta.get(redniBrojPredmeta), ocena);
			String noviCSV = st.csvStudent();
			new Fajlovi().izmeniOceneUFajlu(st, stariCSV, noviCSV);
		}
		else
			System.out.println("Student sa unetim indexom ne postoji!");
	}
}
