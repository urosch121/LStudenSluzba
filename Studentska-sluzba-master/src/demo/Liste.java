package demo;

import java.util.ArrayList;
import studentskaSluzba.Predmet;
import studentskaSluzba.Profesor;
import studentskaSluzba.Student;
import utils.ProveraUnetogBroja;

public interface Liste {
	
	public ArrayList<Profesor> listaProfesora = new ArrayList<>();
	public ArrayList<Predmet> listaPredmeta = new ArrayList<>();
	public ArrayList<Student> listaStudenta = new ArrayList<>();
	
	public static void prikaziListu (ArrayList<?> lista)
	{
		for (Object obj : lista) 
			System.out.println(obj);
	}
	
	public static void sortirajPoId ()
	{
		
	}
	
//	public static Object pretragaPoID (Object o) 
//	{
//		if (o instanceof Predmet)
//		{
//			int id = ProveraUnetogBroja.proveriUnetiBroj("Unesite ID predmeta: ");
//			for (Predmet predmet : listaPredmeta)
//				if (predmet.getId() == id)
//					return predmet;
//			return null;
//		}
//		else if (o instanceof Profesor)
//		{
//			int id = ProveraUnetogBroja.proveriUnetiBroj("Unesite ID profesora: ");
//			for (Profesor profesor : listaProfesora)
//				if (profesor.getId() == id)
//					return profesor;
//			return null;
//		}
//		else if (o instanceof Student)
//		{
//			int brIndexa = ProveraUnetogBroja.proveriUnetiBroj("Unesite broj indexa: ");
//			int godinaUpisa = ProveraUnetogBroja.proveriUnetiBroj("Unesite godinu upisa: ");
//			for (Student student : listaStudenta)
//				if (student.getBrojIndexa() == brIndexa && student.getGodinaUpisa() == godinaUpisa)
//					return student;
//			return null;
//		}
//		return null;
//	}
	
	public static Object pretragaPoID (Object o, int... cifra) //koristi se kod citanja csv fajla
	{
		if (o instanceof Profesor)
		{
			int id = 0;
			if (cifra.length == 0)
			{
				id = ProveraUnetogBroja.proveriUnetiBroj("Unesite ID predmeta: ");
			}
			else
				id = cifra[0];
			for (Profesor profesor : listaProfesora)
				if (profesor.getId() == id)
					return profesor;
		}
		else if (o instanceof Predmet)
		{
			int id = 0;
			if (cifra.length == 0)
			{
				id = ProveraUnetogBroja.proveriUnetiBroj("Unesite ID predmeta: ");
			}
			else
				id = cifra[0];
			for (Predmet predmet : listaPredmeta)
				if (predmet.getId() == id)
					return predmet;
		}
		else if (o instanceof Student)
		{
			int brIndexa = 0;
			int godinaUpisa = 0;
			if (cifra.length == 0)
			{
				brIndexa = ProveraUnetogBroja.proveriUnetiBroj("Unesite broj indexa: ");
				godinaUpisa = ProveraUnetogBroja.proveriUnetiBroj("Unesite godinu upisa: ");
			}
			else
			{
				brIndexa = cifra[0];
				godinaUpisa = cifra[1];
			}
			
			for (Student student : listaStudenta)
				if (student.getBrojIndexa() == brIndexa && student.getGodinaUpisa() == godinaUpisa)
					return student;
		}
		return null;
	}
	
}
