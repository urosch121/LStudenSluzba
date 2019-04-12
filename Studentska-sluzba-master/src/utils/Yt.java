package utils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Yt {
	
	public static void pustiPesmu ()
	{
		Desktop dt = Desktop.getDesktop();
		URI uri1;
		try {
			uri1 = new URI("https://www.youtube.com/watch?v=qNHedkVdzGQ");
			dt.browse(uri1);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
