package crawlerPackage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg {
	
	/*
	 * USER_AGENT is a fake one to ensure we can request what we want
	 * Otherwise some sites display their pages thinking in terms of a mobile device
	 * Or they may display it knowing it's a robot so better to set it equal to...
	 * Mozilla FireFox
	 */
	private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private List<String> links = new LinkedList<String>(); //links that have been clicked
	private Document htmlDocument; //JSoup

	/*
	 * @param url
	 * 			-the URL to visit
	 * @return whether or not the crawl was successful
	 */
	public boolean crawl(String url) {
		try {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT); //access URL via USER_AGENT
			Document htmlDocument = connection.get(); //makes the connection
			this.htmlDocument = htmlDocument;
			if(connection.response().statusCode()==200) {
				System.out.println("Web page from " + url);
			}
			if(!connection.response().contentType().contains("text/html")) {
				System.out.println("Failure! Retrieved something other than html");
				return false;
			}
			Elements linksOnPage = htmlDocument.select("a[href]"); //links
			System.out.println("Found (" + linksOnPage.size() + ") links"); //amount of available links to follow
			for(Element link : linksOnPage) {
				this.links.add(link.absUrl("href"));
			}
			return true;
		}
		catch(IOException ioe) {
			System.out.println("Error" + ioe); //not successful HTTP request
			return false;
		}
	}
	
	/*
	 * performs search on body of HTML doc that is retrieved.
	 * Only called after a successful crawl
	 * 
	 * @param searchWord
	 * 					-the word you're looking for
	 * @return whether or not the word is found
	 */
	public boolean searchForWord(String searchWord) {
		
		//Defensive coding. This method only called after a successful crawl
		if(this.htmlDocument == null) {
			System.out.println("Error! Call crawl() before");
			return false;
		}
		System.out.println("Searching for word " + searchWord);
		String bodyText = this.htmlDocument.body().text();
		return bodyText.toLowerCase().contains(searchWord.toLowerCase()); //returns text containing word
		
	}
	
	public List<String> getLinks(){
		return this.links;
		
	}
}
