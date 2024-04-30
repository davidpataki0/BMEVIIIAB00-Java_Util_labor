package lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.io.*;
import java.util.HashMap;

public class BeerList implements Comparable<Beer>{
	
	private static HashMap<String, Command> commands;
    private static HashMap<String, Comparator<Beer>> comps;

    static {
        comps = new HashMap<>();
        comps.put("name" , (Comparator.comparing(Beer::getName)));
        comps.put("style" , (Comparator.comparing(Beer::getStyle)));
        comps.put("strength" , (Comparator.comparing(Beer::getStrength)));
    }

    static {
        commands = new HashMap<>();
        commands.put("exit", command -> System.exit(0));
        commands.put("add" , BeerList::add);
        commands.put("list", BeerList::list);
        commands.put("save" , command -> {
            try {
                save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        commands.put("load" , command -> {
            try{
                load();
            } catch (FileNotFoundException e){
                System.out.println("Unable to find saved beers");
            } catch (IOException e){
                e.printStackTrace();
            }
        });
        commands.put("search" , BeerList::search);
        commands.put("find" , BeerList::find);
        commands.put("delete" , BeerList::delete);

    }
	
	public static ArrayList<Beer> List = new ArrayList<Beer>();
	
    private static File storageDir = new File(System.getProperty("user.dir"));
    private static String storageName = "beers.txt";
	
	protected static void add(String[] cmd) {
		Beer beer = new Beer(cmd);
		if(cmd.length > 3 && beer != null) List.add(beer);
	}
	
	protected static void list(String[] cmd) {
		ArrayList<Beer> temp = new ArrayList<Beer>();
		Comparator<Beer> cmp = comps.get("name");
		
		for(Beer beer : List) {
			temp.add(beer);
		}
		
		if(cmd.length == 2) {
			if(comps.containsKey(cmd[1])) {
				if(comps.get(cmd[1]) == null) {
					System.out.println("Unimplemented!");
					return;
				}
			}
			cmp = comps.get(cmd[1]);
			Collections.sort(temp,cmp);
			
			/*
			if(cmd[1].equals("name"))
				temp.sort(
						(b1,b2) -> b1.getName().compareTo(b2.getName()));
			else if(cmd[1].equals("style"))
				temp.sort(
						(b1,b2) -> b1.getStyle().compareTo(b2.getStyle()));
		
			else if(cmd[1].equals("strength"))
				temp.sort(
						(b1,b2) -> Double.compare(b1.getStrength(), b2.getStrength()));
			*/
			
		}
		for(int i = 0; i < temp.size(); i++)
			System.out.println(temp.get(i) + " ");
		
	}
	
	protected static void save() throws IOException {
		try {
			String wd = System.getProperty("user.dir");
			File f = new File(wd, storageName);
			
			if(!f.exists())
				f.createNewFile();
			else {
				FileOutputStream FileOut = new FileOutputStream(f);
				ObjectOutputStream ObjectOut = new ObjectOutputStream(FileOut);
				ObjectOut.writeObject(List);
				ObjectOut.close();
				FileOut.close();
				System.out.println("Successfully saved beers!");
			}
		} catch (IOException e) {
			System.out.println("Unable to save beers!");
		}

	}
	
	protected static void load() throws IOException {
		
		File source = null;
		
        for (File file: storageDir.listFiles()) {
            if (file.getName().equals(storageName))
                source = file;
        }
        if (source == null)
            throw new FileNotFoundException();
		
        try{
            FileInputStream fileIn = new FileInputStream(source);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            ArrayList<Beer> list = (ArrayList<Beer>) objectIn.readObject();
            List = list;
            objectIn.close();
            fileIn.close();
            System.out.println("Successfully loaded beers!");
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Unable to load beers!");
        }
    }
	
    protected static void delete(String[] cmd){
    	if(cmd.length < 2) {
    		System.out.println("Invalid operation!");
    		return;
    	}
        Iterator<Beer> iterator = List.iterator();
        while (iterator.hasNext()){
           Beer beer = iterator.next();
           if (beer.name.equals(cmd[1])){
               iterator.remove();
           }
        }
    }

    protected static void find(String[] cmd){
        String regex = ".*" + cmd[2] + ".*";
        for (Beer beer: List) {
            switch (cmd[1]){
                case "name":
                    if (beer.name.matches(regex)){
                        System.out.println(beer);
                    }
                    break;
                case "style":
                    if (beer.style.matches(regex)){
                        System.out.println(beer);
                    }
                    break;
                case "strength":
                    Double strength;

                    strength = Double.parseDouble(cmd[2]);

                    if (beer.strength == strength){
                        System.out.println(beer);
                    }
                    break;
                case "weaker":
                	
                    strength = Double.parseDouble(cmd[2]);
                    
                    if (beer.strength <= strength){
                        System.out.println(beer);
                    }
                    break;
            }
        }
    }

    protected static void search(String[] cmd){
        for (Beer beer: List) {
            if (beer.name.equals(cmd[1])){
                System.out.println(beer);
            }
        }
    }
    
    public static void processCommands(String[] cmd) throws IOException {
    	if(commands.containsKey(cmd[0])) {
    		if(commands.get(cmd[0]) == null) {
    			System.out.println("Unimplemented!");
    			return;
    		}
    		commands.get(cmd[0]).execute(cmd);
    	}
    }

	@Override
	public int compareTo(Beer o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
