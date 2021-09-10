package MainEngine;


import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.File;
import java.io.IOException;



public class Indexer {
	private static final Logger logger = Logger.getLogger(Indexer.class.getName());

	int id = 0;

	private static Indexer indexer;

	private Indexer() {
	}

	public static Indexer getInstance() {
		if (indexer == null) {
			indexer = new Indexer();
		}
		return indexer;
	}

	public void startIndexer() throws IOException {


		int count = 0;
		logger.log(Level.INFO, "Indexing Started");
		System.out.println("---------------------------------------------------------------------------------------------");

		System.out.println("Now all the documents in the directory will be indexed and displayed in the sorted form...");
		System.out.println("---------------------------------------------------------------------------------------------");


		for (File file : Parser.getWebPageFilesList()) {
			System.out.println(
					"No :: [" + (++count) + "/" + Parser.getWebPageFilesList().size() + "] " + file.getName());
		}
		logger.log(Level.INFO, "Indexing Completed");

	}

}
