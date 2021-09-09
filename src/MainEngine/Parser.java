package MainEngine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Document;


public class Parser {

	private static List<File> webPageFilesList = new ArrayList<File>();

	public static List<File> getWebPageFilesList() {
		if (webPageFilesList.isEmpty()) {
			generateFilesList();
		}
		return webPageFilesList;
	}

	public static void generateFilesList() {
		try {
			File directory = new File("C:\\Users\\vachh\\eclipse-workspace\\ACC Final Project\\WebPages\\Text\\");
			File[] files = directory.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					webPageFilesList.add(file);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in adding file:" + e);
		}
	}

	

	public static void saveDoc(Document doc) {
		try {
			BufferedWriter html = new BufferedWriter(
					new FileWriter(Path.htmlDirectoryPath + doc.title().replace('/', '-') + ".html"));
			html.write(doc.toString());
			html.close();

			BufferedWriter txt = new BufferedWriter(
					new FileWriter(Path.txtDirectoryPath + doc.title().replace('/', '-') + ".txt"));
			txt.write(doc.body().text().toLowerCase());
			txt.close();

		} catch (Exception e) {
			
		}
	}
}
