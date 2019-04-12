package studentskaSluzba;

import java.util.ArrayList;
import demo.Liste;
import utils.Fajlovi;
import utils.ProveraUnetogBroja;

public class Student implements Liste{
	
	private int brojIndexa;
	private int godinaUpisa;
	private String ime;
	private String prezime;
	private ArrayList<PolozenIspit> polozeniIspiti;
	private String putanjaFajlaStudent = "Fajlovi/Studenti/Studenti.csv";
	
	public Student () {}
	
	public Student(int brojIndexa, int godinaUpisa, String ime, String prezime) 
	{
		super();
		this.brojIndexa = brojIndexa;
		this.godinaUpisa = godinaUpisa;
		this.prezime = prezime;
		this.ime = ime;
		polozeniIspiti = new ArrayList<>();
	}
	
	public void listaStudentaSaOcenama ()
	{
		System.out.println("\nLista studenta sa ocenama: ");
		for (Student student : Liste.listaStudenta)
			System.out.println(student.toStringSaOcenama());
	}
	
	public void listaStudentaPoGodiniUpisa () 
	{
		boolean imaStudent = false;
		int unetaGodina = ProveraUnetogBroja.proveriUnetiBroj("Unesite godinu upisa: ");
		System.out.println("\nLista studenta po unetoj godini upisa: ");
		for (Student student : Liste.listaStudenta) 
			if (student.godinaUpisa == unetaGodina) 
			{
				System.out.println(student);
				imaStudent = true;
			}
		if (imaStudent == false)
			System.out.println("Za unetu godinu upisa ne postoji student!");
	}
	
	public void sveOceneOdredjenogStudenta () 
	{
		Liste.prikaziListu(listaStudenta);
		Student odredjeniStudent = (Student) Liste.pretragaPoID(new Student());
		if (odredjeniStudent != null) 
		{
			System.out.println("Ocene unetog studenta: ");
			for (PolozenIspit pi : odredjeniStudent.polozeniIspiti) 
				System.out.println(pi.predmet + " - " + pi.ocena);
		}
		else
			System.out.println("Ne postoji student sa unetim brojem indexa i godinom upisa!");
	}
	
	public void dodajPolozenIspit(Predmet predmet, int ocena) 
	{
		boolean ima = false;
		PolozenIspit pronadjenIspit = null;
		for (PolozenIspit polozenIspit : polozeniIspiti) 
		{
			if (polozenIspit.predmet.equals(predmet)) 
			{
				ima = true;
				pronadjenIspit = polozenIspit;
			}
		}
		if (ima == true)
		{
			if (pronadjenIspit.ocena < ocena)
				pronadjenIspit.ocena = ocena;
		}
		else
		{
			PolozenIspit pi = new PolozenIspit(predmet, ocena);
			this.polozeniIspiti.add(pi);
		}
	}
	
	public void dodajStudentuOceneIzSvihPredmeta () 
	{
		Liste.prikaziListu(listaStudenta);
		Student st = (Student) Liste.pretragaPoID(new Student());
		if (st == null)
			System.out.println("Student sa unetim indexom i godinom upisa ne postoji!");
		else
		{
		String stariCSV = st.csvStudent();
		for (Predmet predmet : Liste.listaPredmeta) 
		{
			int ocena = ProveraUnetogBroja.proveriUnetuOcenu("Unesi ocenu za izabranog studenta iz predmeta " + predmet.getNaziv() + ": ");
			st.dodajPolozenIspit(predmet, ocena);
		}
		String noviCSV = st.csvStudent();
		new Fajlovi().izmeniOceneUFajlu(st, stariCSV, noviCSV);
		}
	}
	
	public double prosecnaOcena () 
	{
		double prosek = 0.0;
		int trenutniZbir = 0;
		for (PolozenIspit polozenIspit : polozeniIspiti) 
			trenutniZbir += polozenIspit.ocena;
		if (trenutniZbir > 0)
			prosek = (double) trenutniZbir / polozeniIspiti.size();
		else 
			System.out.println("Ovaj student jos uvek nema unetu nijednu ocenu!");
		return prosek;
	}
	
	public double prosecnaOcenaProfesor (int id)
	{
		double prosek = 0.0;
		int trenutniZbir = 0;
		int brojIspita = 0;
		for (PolozenIspit polozenIspit : polozeniIspiti) 
		{
			if (polozenIspit.predmet.getProfesor().getId() == id)
			{
				trenutniZbir += polozenIspit.ocena;
				brojIspita++;
			}
		}
		if (trenutniZbir > 0)
			prosek = (double) trenutniZbir / brojIspita;
		else
			System.out.println("Ovaj student jos uvek nema unetu nijednu ocenu!");
		return prosek;
	}
	
	public String csvStudent() 
	{
		String zapisStudent = this.brojIndexa + "," + this.godinaUpisa + "," + this.ime + "," + this.prezime;
		String zapisStudentOcene = "";
		
		for (PolozenIspit pi : this.polozeniIspiti) 
			zapisStudentOcene += "," + pi.predmet.getId() + "," + pi.ocena;
		
		zapisStudent += zapisStudentOcene;
		return zapisStudent;
	}
	
	public String csvSviStudenti () 
	{
		String zapisSviProfesori = "";
		for (Student student : Liste.listaStudenta)
			zapisSviProfesori += student.csvStudent() + "\n";
		return zapisSviProfesori;
	}
	
	public String toStringSaOcenama () 
	{
		String ocene = "\nOcene: \n";
		for (int i = 0; i < polozeniIspiti.size(); i++)
			ocene += this.polozeniIspiti.get(i).predmet.getNaziv() + ": " + this.polozeniIspiti.get(i).ocena + "\n";
		return "Broj indexa: " + this.brojIndexa + ", godina upisa: " + this.godinaUpisa + ", ime: " + this.ime
				+ ", prezime: " + this.prezime + ocene;
	}
	
	@Override
	public String toString() {
		return "Broj indexa: " + this.brojIndexa + ", godina upisa: " + this.godinaUpisa + ", ime: " + this.ime
				+ ", prezime: " + this.prezime;
	}

	//GETERI I SETERI
	public int getGodinaUpisa() {return godinaUpisa;}
	public String getIme() {return ime;}
	public String getPrezime() {return prezime;}
	public ArrayList<PolozenIspit> getPolozeniIspiti() {return polozeniIspiti;}
	public int getBrojIndexa() {return brojIndexa;}
	public String getPutanjaFajlaStudent() {return putanjaFajlaStudent;}
	
}
