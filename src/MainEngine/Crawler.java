package MainEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Crawler {
	private static final Logger logger = Logger.getLogger(Crawler.class.getName());


	private HashMap<String,Integer> pagesCrawledList = new HashMap<String,Integer>();
	private List<Document> webPageList = new ArrayList<Document>();

	public List<Document> getWebPagesList() {
		return webPageList;
	}

	public void setWebPageList(List<Document> webPageList) {
		this.webPageList = webPageList;
	}

	private int maxDepth = 2;

	public void crawl(String url) {
		
		logger.log(Level.INFO, "Crawling Started...");

		startCrawler(url, 0);
		logger.log(Level.INFO, "Crawling Ended...");
		System.out.println("----------------------------------------------------------------------------");


//		}
	}

	
	public void startCrawler(String url, int depth) {
		if (depth <= maxDepth) {
			try {
				Document document = Jsoup.connect(url).get();
				Parser.saveDoc(document);
				webPageList.add(document);
				depth++;
				if (depth < maxDepth) {
					 // Parse the HTML to extract links to other URLs
					Elements links = document.select("a[href]");
					for (Element page : links) {
						if (shouldCrawlUrl(page.attr("abs:href"))) {
							System.out.println(page.attr("abs:href"));
							startCrawler(page.attr("abs:href"), depth);
							pagesCrawledList.put(page.attr("abs:href"),depth);
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Error fetching url - " + url);
			}
		}
	}


	private boolean shouldCrawlUrl(String nextUrl) {
		if (this.pagesCrawledList.containsKey(nextUrl)) {
			return false;
		}
		if (nextUrl.startsWith("javascript:")) {
			return false;
		}
		if (nextUrl.contains("mailto:")) {
			return false;
		}
		if (nextUrl.contains("#") || nextUrl.contains("?")) {
			return false;
		}
		if (nextUrl.endsWith(".swf")) {
			return false;
		}
		if (nextUrl.endsWith(".txt")) {
			return false;
		}
		if (nextUrl.endsWith(".pdf")) {
			return false;
		}
		if (nextUrl.endsWith(".png")) {
			return false;
		}
		if (nextUrl.endsWith(".gif")) {
			return false;
		}
		if (nextUrl.endsWith(".jpg")) {
			return false;
		}
		if (nextUrl.endsWith(".jpeg")) {
			return false;
		}
		return true;
	}
}