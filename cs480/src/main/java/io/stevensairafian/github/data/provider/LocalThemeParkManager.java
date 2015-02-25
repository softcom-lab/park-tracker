package io.stevensairafian.github.data.provider;

import io.stevensairafian.github.data.ThemePark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.stevensairafian.github.util.ResourceResolver;

public class LocalThemeParkManager implements ThemeParkManager{

	    private static final ObjectMapper JSON = new ObjectMapper();

	    public LocalThemeParkManager() {
	        ScheduledExecutorService scheduler =
	                Executors.newScheduledThreadPool(1);
	        scheduler.scheduleAtFixedRate(new Runnable() {
	            @Override
	            public void run() {
	                Document doc;
	                try {
	                    // need http protocol
	                    doc = Jsoup.connect("http://m.universalstudioshollywood.com/waittimes").get();

	                    // get page title
	                    String hours = doc.select("a").first().text();
	                    System.out.println("hours : " + hours);

	                    Set<Movie> titles = new HashSet<Movie>();

	                    // get all links
	                    boolean isPriceReady  = false;
	                    Elements links = doc.select("h2.a-size-medium.s-inline.s-access-title.a-text-normal");
	                    List<String> imgUrls = new ArrayList<String>();
	                    Elements imgs = doc.select("img");
	                    for (Element img : imgs) {
	                        if (img.attr("alt").equals("Product Details")) {

	                            imgUrls.add(img.absUrl("src"));
	                        }
	                    }

	                    int index = 0;
	                    for (Element link : links) {
	                        System.out.println("text : " + link.text());
	                        Movie m = new Movie();
	                        m.setName(link.text());
	                        m.setImgUrl(imgUrls.get(index));
	                        m.setProvider("Amazon Instance Video");
	                        // set the price
	                        Random randomGenerator = new Random();
	                        m.setPrice(randomGenerator.nextInt(10) + 1);

	                        titles.add(m);
	                        index++;
	                    }

	                    // save to the file
	                    JSON.writeValue(ResourceResolver.getThemeParkFile(),
	                            titles);

	                } catch (IOException e) {
	                    e.printStackTrace();
	                }

	            }}, 0, 10, TimeUnit.SECONDS);
	    }


		@Override
		public List<ThemePark> searchParks(String keyword) {
			 // load the movies from the file
	        Set<ThemePark> parks = new HashSet<ThemePark>();
	        try {
	            parks = JSON.readValue(ResourceResolver.,
	                    new TypeReference<Set<Movie>>() {});
	            System.out.println(parks);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        // search
	        List<ThemePark> res = new ArrayList<ThemePark>();
	        for(ThemePark m : parks) {
	            System.out.println(m.getName() + " " + keyword);
	            if (m.getName().toLowerCase().contains(keyword.toLowerCase())) {
	                res.add(m);
	            }
	        }

	        return res;
		}
	
}
