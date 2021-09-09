package MainEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BFS implements WordSearch
{

  private  String fileName;
  private File file;
  private int count =0;
 public BFS()
 {
	}

  public BFS(File file) throws IOException {
    this.file =  file;
    this.fileName = file.getName();
    this.count = 0;
  }

  public void searchBruteForce(String toSearch) throws IOException {

      FileInputStream fstream = new FileInputStream(file);
      BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
      String readLine = "";
      while ((readLine = in.readLine()) != null) {
        String[] words = readLine.split("\\W");
        for (String text : words) {
          if (text.equalsIgnoreCase(toSearch)) {
            count++;
          }
        }
      }
      in.close();
  }

  public String getFile() {
    return fileName;
  }

  public int getCount(String word) {
    return count;
  }

  public void search(String toSearch) throws IOException 
  {
    searchBruteForce(toSearch);
    if (getCount(toSearch) != 0) {
    	System.out.println();
        System.out.println(getCount(toSearch) + " | " + getFile()); 
    } 
  }
}



