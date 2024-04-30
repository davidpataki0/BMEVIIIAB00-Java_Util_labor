package lab4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//Beer Borsodi = new Beer("Borsodi", "Kommersz", 4.5);
		//Beer Zipfer = new Beer("Zipfer", "Kedvenc", 5.0);
		
		//System.out.println(Borsodi.toString() + Zipfer.toString());
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while((line = reader.readLine()) != null)
		{
			
			
			String[] cmdline = line.split(" ");
			BeerList.processCommands(cmdline);
			
			/*
			if("exit".equals(cmdline[0]))
				System.exit(0);
			
			else if("add".equals(cmdline[0]))
				BeerList.add(cmdline);
			
			else if ("list".equals(cmdline[0]))
				BeerList.list(cmdline);
			
			else if ("save".equals(cmdline[0]))
				BeerList.save();
			
			else if ("load".equals(cmdline[0]))
				BeerList.load();
			
			else if ("search".equals(cmdline[0]))
				BeerList.search(cmdline[1]);
			
			else if ("find".equals(cmdline[0]))
				BeerList.find(cmdline);
			
			else if ("delete".equals(cmdline[0]))
				BeerList.delete(cmdline);
			*/
			
		}
		reader.close();
	}
}
