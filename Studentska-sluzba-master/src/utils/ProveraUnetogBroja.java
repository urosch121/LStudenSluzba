package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProveraUnetogBroja {

	public static int proveriUnetuOcenu (String text) 
	{
		Scanner unos = new Scanner(System.in);
		boolean krug = true;
		Integer broj = 0;
		do {
			try {
				System.out.print(text);
				broj = unos.nextInt();
				if (broj >=5 && broj<=10)
					krug = true;
				else
				{
					System.out.println("Uneli ste neodgovarajucu ocenu");
					krug = false;
				}
					
		} catch (InputMismatchException e) {
				System.err.println("Pogresno ste uneli podatak!");
				unos.next();
				krug = false;
			}
		} while (krug == false);
		return broj;
	}
	
	public static int proveriUnetiBroj (String text) {
		Scanner unos = new Scanner(System.in);
		boolean krug = true;
		Integer broj = 0;
		do {
			try {
				System.out.print(text);
				broj = unos.nextInt();
				krug = true;
		} catch (InputMismatchException e) {
				System.err.println("Pogresno ste uneli broj!");
				unos.next();
				krug = false;
			}
		} while (krug == false);
		return broj;
	}
	
}
