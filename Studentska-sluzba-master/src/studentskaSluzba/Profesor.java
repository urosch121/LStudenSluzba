package studentskaSluzba;

import java.util.Comparator;

import demo.Liste;

public class Profesor implements Liste {

	private int id;
	private String ime;
	private String prezime;
	private String putanjaFajlaProfesor = "Fajlovi/Profesori/Profesori.csv";
	
	public Profesor() {}
	
	public Profesor(int id, String ime, String prezime) 
	{
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
	}
		
	public String csvProfesor() 
	{
		String zapisProfesor = this.id + "," + this.ime + "," + this.prezime;
		return zapisProfesor;
	}
	
	public String csvSviProfesori () 
	{
		String zapisSviProfesori = "";
		for (Profesor profesor : Liste.listaProfesora)
			zapisSviProfesori += profesor.csvProfesor() + "\n";
		return zapisSviProfesori;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id + ", ime: " + this.ime + ", prezime: " + this.prezime;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Profesor))
			return false;
		
		Profesor p = (Profesor) obj;
		if (this.id == p.id)
			return true;
		return false;
	}
	
	public static Comparator<Profesor> profesorSortIme = new Comparator<Profesor>() 
	{
		@Override
		public int compare(Profesor o1, Profesor o2) {
			
			return o1.getIme().compareTo(o2.getIme());
		}
	};
	
	public static Comparator<Profesor> profesorSortPrezime = new Comparator<Profesor>() 
	{
		@Override
		public int compare(Profesor o1, Profesor o2) 
		{
			return o1.getPrezime().compareTo(o2.getPrezime());
		}
	};
	
	//GETTERI I SETTERI
	public int getId() {return id;}
	public String getIme() {return ime;}
	public String getPrezime() {return prezime;}
	public String getPutanjaFajlaProfesor() {return putanjaFajlaProfesor;}
	
	
}
