package utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import demo.Liste;
import studentskaSluzba.Predmet;
import studentskaSluzba.Profesor;
import studentskaSluzba.Student;

public class Fajlovi implements Liste {

	public void upisiUFajl (String podatak, String putanjaFajla) throws IOException 
	{
		try 
		{ 
			BufferedWriter upisi = new BufferedWriter(new FileWriter(putanjaFajla, false));
			upisi.write(podatak);
			upisi.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Greska! Fajl nije pronadjen!");
		}
	}
	
	public void izmeniOceneUFajlu (Student student, String stariCSV, String noviCSV) 
	{
		try 
		{
			Path path = Paths.get(student.getPutanjaFajlaStudent());
			String fajlStudenti = new String (Files.readAllBytes(path));
			fajlStudenti = fajlStudenti.replace(stariCSV, noviCSV);
			Files.write(path, fajlStudenti.getBytes());
			
		}
		catch (IOException e)
		{
			System.err.println("Nije pronadjen fajl sa studentima!");
		}
	}
	
	public void izmeniPredmetUFajlu (Predmet predmet, String stariCSV, String noviCSV) //kada predmetu zamenimo profesora
	{
		try 
		{
			Path path = Paths.get(predmet.getPutanjaFajlaPredmet());
			String fajlPredmet = new String (Files.readAllBytes(path));
			fajlPredmet = fajlPredmet.replace(stariCSV, noviCSV);
			Files.write(path, fajlPredmet.getBytes());
		}
		catch (IOException e)
		{
			System.err.println("Nije pronadjen fajl sa predmetima!");
		}
	}
	
	//ovo  se poziva kada unesemo novog studenta, profesora, predmet
	public void upisiUFajlSamoNovObjekat (String podatak, String putanjaFajla) throws IOException 
	{
		try 
		{ 
			BufferedWriter upisi = new BufferedWriter(new FileWriter(putanjaFajla, true));
			upisi.write(podatak);
			upisi.newLine();
			upisi.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Greska! Fajl nije pronadjen!");
		}
	}
	
	public void ucitajCsvProfesor () 
	{
		Scanner in;
		String line;
		Profesor prof = new Profesor();
		try 
		{
			in = new Scanner(new FileReader(prof.getPutanjaFajlaProfesor()));
			
			while(in.hasNextLine()) 
			{
				line = in.nextLine();
				String [] nizLine = line.split(",");
				int id = Integer.parseInt(nizLine[0].trim());
				String ime = nizLine[1].trim();
				String prezime = nizLine[2].trim();
				Profesor p = new Profesor(id, ime, prezime);
				Liste.listaProfesora.add(p);
			}
			in.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Navedeni fajl ne postoji! ");
		}
	}
	
	
	public void ucitajCsvPredmet () 
	{
		Scanner in;
		String line;
		Predmet pred = new Predmet();
		try 
		{
			in = new Scanner(new FileReader(pred.getPutanjaFajlaPredmet()));
			
			while(in.hasNextLine()) 
			{
				line = in.nextLine();
				String [] nizLine = line.split(",");
				int id = Integer.parseInt(nizLine[0].trim());
				String naziv = nizLine[1].trim();
				int idProfesora = Integer.parseInt(nizLine[2].trim());
				Profesor profesor = (Profesor) Liste.pretragaPoID(new Profesor(), idProfesora);
				Predmet predmet = new Predmet(id, naziv, profesor);
				Liste.listaPredmeta.add(predmet);
			}
			in.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Navedeni fajl ne postoji! ");
		}
	}
	
	public void ucitajCsvStudenta () 
	{
		Scanner in;
		String line;
		Student stud = new Student();
		try 
		{
			in = new Scanner(new FileReader(stud.getPutanjaFajlaStudent()));
			
			while(in.hasNextLine()) 
			{
				line = in.nextLine();
				String [] nizLine = line.split(",");
				int brojIndexa = Integer.parseInt(nizLine[0].trim());
				int godinaUpisa = Integer.parseInt(nizLine[1].trim());
				String ime = nizLine[2].trim();
				String prezime = nizLine[3].trim();
				int brojOcena = (nizLine.length - 4) / 2;
				int [] idPredmetaNiz = new int [brojOcena];
				int [] ocenaNiz = new int [brojOcena];
				for (int i = 4, j = 0; i < nizLine.length; i++, j++) 
				{
					if (i%2 == 0) 
					{
						idPredmetaNiz[j] = Integer.parseInt(nizLine[i]);
						j--;
					}
					else
						ocenaNiz[j] = Integer.parseInt(nizLine[i]);
				}
				Student student = new Student(brojIndexa, godinaUpisa, ime, prezime);
				
				for (int i = 0; i < brojOcena; i++) 
				{
					Predmet predmet = (Predmet) Liste.pretragaPoID(new Predmet(), idPredmetaNiz[i]);
					student.dodajPolozenIspit(predmet, ocenaNiz[i]);
				}
				Liste.listaStudenta.add(student);
			}
			in.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Navedeni fajl ne postoji! ");
		}
	}
}
