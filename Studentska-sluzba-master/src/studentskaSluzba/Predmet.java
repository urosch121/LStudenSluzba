package studentskaSluzba;

import java.io.IOException;
import java.util.Comparator;

import demo.Liste;
import utils.Fajlovi;
import utils.ProveraUnetogBroja;

public class Predmet implements Liste, Comparable<Predmet>{

	private int id;
	private String naziv;
	private Profesor profesor;
	private String putanjaFajlaPredmet = "Fajlovi/Predmeti/Predmeti.csv";
	
	public Predmet() {}
	
	public Predmet(int id, String naziv, Profesor profesor) 
	{
		super();
		this.id = id;
		this.naziv = naziv;
		this.profesor = profesor;
	}
	
	public void prikazOcenaPoPredmetu()
	{
		System.out.println("Ocene studenta za izabrani predmet: ");
		boolean nemaOceneIzPredmeta = true;
		for (Student student : Liste.listaStudenta)
			for (int i = 0; i < student.getPolozeniIspiti().size(); i++) 
				if (student.getPolozeniIspiti().get(i).predmet.equals(this)) 
				{
					System.out.println(student + " : " + student.getPolozeniIspiti().get(i).ocena);
					nemaOceneIzPredmeta = false;
				}
		if (nemaOceneIzPredmeta == true)
			System.out.println("Nema ocena iz izabranog predmeta!");
	}
	
	public void dodajSvimStudentimaOcenuIzPredmeta () 
	{
		for (Student student : Liste.listaStudenta)
		{
			int ocena = ProveraUnetogBroja.proveriUnetuOcenu("Unesi ocenu za studenta " + student + " : "); 
			student.dodajPolozenIspit(this, ocena);
		}
		
		try {
			new Fajlovi().upisiUFajl(new Student().csvSviStudenti(), new Student().getPutanjaFajlaStudent());
		} catch (IOException e) {
			System.out.println("Greska u radu sa fajlom studenti");
			e.printStackTrace();
		}
	}
	
	public double predmetProsecnaOcena () 
	{
		double prosekPredmet = 0.0;
		int brojNadjenihStudenta = 0;
		for (Student student : Liste.listaStudenta) 
			for (int i = 0; i < student.getPolozeniIspiti().size(); i++) {
				if (student.getPolozeniIspiti().get(i).predmet.equals(this)) 
				{
					prosekPredmet += (double) student.getPolozeniIspiti().get(i).ocena;
					brojNadjenihStudenta++;
				}
			}
		if (prosekPredmet > 0.0) 
		{
			prosekPredmet = (double) prosekPredmet / brojNadjenihStudenta;
			return prosekPredmet;
		}
		else
			return 0.0;
	}
	
	public String csvPredmet() 
	{
		String zapisPredmet = this.id + "," + this.naziv + "," + this.profesor.getId();
		return zapisPredmet;
	}
	
	public String csvSviPredmeti () 
	{
		String zapisSvipredmeti = "";
		for (Predmet predmet : Liste.listaPredmeta)
			zapisSvipredmeti += predmet.csvPredmet() + "\n";
		return zapisSvipredmeti;
	}
	
	public void promeniProfesora (Profesor profesor) {
		this.profesor = profesor;
	}
	
	public static Comparator<Predmet> sortPredmetNaziv = new Comparator<Predmet>() 
	{
		@Override
		public int compare(Predmet o1, Predmet o2) 
		{
			return o1.naziv.compareTo(o2.naziv);
		}
	};
	
	@Override
	public int compareTo(Predmet o) 
	{
		return this.naziv.compareTo(o.naziv);
	}

	@Override
	public String toString() {
		return "ID: " + this.id + ", naziv: " + this.naziv + ", profesor: " + this.profesor;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Predmet))
			return false;
		Predmet p = (Predmet) obj;
		if (this.id == p.id)
			return true;
		return false;
	}
	
	public int getId() {return id;}
	public String getNaziv() {return naziv;}
	public Profesor getProfesor() {return profesor;}
	public String getPutanjaFajlaPredmet() {return putanjaFajlaPredmet;}

	
}
