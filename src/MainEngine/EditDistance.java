package MainEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class EditDistance {
	static int EditDistDP(String str1, String str2)
	{
	    int len1 = str1.length();
	    int len2 = str2.length();
	    
	    int [][]DP = new int[2][len1 + 1];
	 
	    for (int i = 0; i <= len1; i++)
	        DP[0][i] = i;
	
	    for (int i = 1; i <= len2; i++)
	    {
	       
	        for (int j = 0; j <= len1; j++)
	        {
	           
	            if (j == 0)
	                DP[i % 2][j] = i;
	 
	            else if (str1.charAt(j - 1) == str2.charAt(i - 1)) {
	                DP[i % 2][j] = DP[(i - 1) % 2][j - 1];
	            }

	            else {
	                DP[i % 2][j] = 1 + Math.min(DP[(i - 1) % 2][j],
	                                       Math.min(DP[i % 2][j - 1],
	                                           DP[(i - 1) % 2][j - 1]));
	            }
	        }
	    }
	 
	   return (DP[len2 % 2][len1]);
	}
	public static void main(String[] args) throws IOException {
}
	public static Set<String> suggestedWords(String pathTEXT, String search) throws IOException
	{
		ArrayList<String> tokens = new ArrayList<String>(); 
		tokens = findTokens(pathTEXT);
		ArrayList<String> suggestions= new ArrayList<String>();
		for(int i=0;i<tokens.size();i++)
		{
			int dr = EditDistDP(tokens.get(i), search);
			if(dr == 0) {
				break;
			}
			else if(dr==1) {
				
				if(!(tokens.get(i).contains(".") || tokens.get(i).contains(",") || tokens.get(i).contains(" ")))
				{
					suggestions.add(tokens.get(i));
				}
			}
			
		}
		Set<String> suggestedWords = new HashSet<>(suggestions);
		
		return suggestedWords;
	}
	public static ArrayList<String> findTokens(String pathTEXT) throws IOException
	{
		String txt = "";
		ArrayList<String> tokens = new ArrayList<String>();
		File fol= new File(pathTEXT);
		File[] listoffiles = fol.listFiles();
		String data = "";
		for(File f : listoffiles){
			//System.out.print(f.getName());
			BufferedReader br = new BufferedReader(new FileReader(f));   
			while((txt = br.readLine()) != null) 
		   		   data=data+txt;
		   	  StringTokenizer st = new StringTokenizer(data);  
		        while (st.hasMoreTokens())   
		      	  tokens.add(st.nextToken().toLowerCase());
			 br.close();		 
		}
		return tokens;
	}
}
