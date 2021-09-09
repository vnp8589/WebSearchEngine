package MainEngine;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WebSearchEngine 
{
	private static String filepath = null;
	public static boolean flag = false;
	private static String pathTEXT = "C:\\Users\\vachh\\eclipse-workspace\\ACC Final Project\\WebPages\\Text\\";
	private static String userOption = "";
	static Indexer indexer = Indexer.getInstance();
	private WebSearchEngine() {
	    WebSearchEngine.filepath = pathTEXT;
	  }

	  private WebSearchEngine(String dir) {
	    if (dir.isEmpty()) {
	      WebSearchEngine.filepath = pathTEXT;
	    } else {
	      WebSearchEngine.filepath = dir;
	    }
	  }
	
	public static void main(String[] args) throws IOException, InterruptedException
	{
	
		if(flag == false) {
			System.out.println("***********Welcome to Findsly - A Search Engine***********");
			System.out.println("------------------------------------------------------------------------------");
			
			String url = null;
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter the url for which you want to perform Crawling :: ");
			url = sc.nextLine();
			System.out.println("------------------------------------------------------------------------------");
//			new Crawler().crawl(url);
			indexer.startIndexer();
			System.out.println("---------------------------------------------");
			System.out.println("You can also look for strings in the files...");
			System.out.println("---------------------------------------------");
			flag = true;
		}
	

	Scanner sc = new Scanner(System.in);
	String toSearch = "";
	
	Set<String> suggestedWords;
	String choice;
	

	System.out.print("Do you want to search?(yes/no) :: ");
	userOption = sc.next();
	System.out.println("---------------------------------------------");

	while(userOption.equalsIgnoreCase("yes")) {
		
		
		System.out.print("Enter the String You Want to Search :: ");

		toSearch = sc.next();
		System.out.println("---------------------------------------------");

		toSearch = toSearch.toLowerCase();
		
		suggestedWords = EditDistance.suggestedWords(pathTEXT, toSearch);


		for(String s:suggestedWords) 
		{
			toSearch = s;
			System.out.print(s + " , ");
		}

		if(suggestedWords.size() == 0)
		{
			break;
		}
		else {
			System.out.print("Did you mean ?(yes/no) :: ");
			choice = sc.next().toLowerCase();
			if(choice.equals("yes"))
			{
				Operation(toSearch, userOption);
				break;
			}
			else
			{
				continue;
			}
			
			
		}
	}
		Operation("", userOption);
	}
	  private void getFiles(File folder, Set<File> list) 
	  {
		    folder.setReadOnly();
		    File[] files = folder.listFiles();
		    for (int j = 0; j < files.length; j++) 
		    {
		      list.add(files[j]);
		      if (files[j].isDirectory())
		        getFiles(files[j], list);
		    }
	 }
	  
	 public static void Operation(String toSearch, String userOption) throws IOException, InterruptedException {
		 
		 Scanner sc = new Scanner(System.in);
		 WebSearchEngine mb = new WebSearchEngine();
		 if(userOption.equalsIgnoreCase("no")) {
		    	System.out.print("Would you like to find links?(yes/no) :: ");
				String reply = sc.next().toLowerCase();
				System.out.println("---------------------------------------------");

				if(reply.equals("yes")) {
					Regex.urlFinder(pathTEXT);
				}
				else {
					System.out.println("BYE !");
					System.exit(0);
				}
		    } else {
		    	 String folderToSearch = WebSearchEngine.filepath;

				    File folder = new File(folderToSearch);
				    Set<File> list = new HashSet<File>();
				    mb.getFiles(folder, list);

				    System.out.println();
				    System.out.println("Occurences | File Name");

				    for (File file : list)
				    {
				     BFS bSerch = new BFS(file);
				     bSerch.search(toSearch);
				    }
					System.out.println("---------------------------------------------");

				    BFS b = new BFS();
				    main(null);
		    }
	 }
}