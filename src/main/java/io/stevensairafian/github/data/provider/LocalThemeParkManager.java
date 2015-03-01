package io.stevensairafian.github.data.provider;

import io.stevensairafian.github.data.ThemePark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import io.stevensairafian.github.data.*;

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
	                    //hours are in format 10am - 6pm
	                    //int openingHours  = Integer.parseInt(hours.substring(0, 4).replaceAll("\\D", ""));
	                    //int closingHours = Integer.parseInt(hours.substring(hours.length() - 4, hours.length()).replace("\\D", ""));
	                    System.out.println("hours : " + hours);

	                    Set<Attraction> attractions = new HashSet<Attraction>();

	                    // get all table cells (the only tables on the page only hold elements of concern
	                    Elements td = doc.select("td");

	                    /*
	                     * The site is set up such that td's are next to each other in form "attraction" "wait time"
	                     * We may assume that it will always start with an atraction name, followed by a wait time. thus
	                     * we may create attractions by grabbing the 0th element, setting it's wiat time to element 1
	                     * and then grabbing element 2, setting its wait time to element 3 so on and so forth
	                     */
	                    Element link;
	                    for (int i = 0; i < td.size(); i++) {
	                    	link = td.get(i);
	                        System.out.println("text : " + link.text());
	                        Attraction a = new Attraction();
	                        a.setName(link.text());
	                        //get wait time (the next element. Note pre-increment is used
	                        link = td.get(++i);
	                        a.setWaitTime(link.text());
	                    }

	                    // save to the file
	                    JSON.writeValue(ResourceResolver.getThemeParkFile(),
	                            attractions);

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
	            parks = JSON.readValue(ResourceResolver.getThemeParkFile(),
	                    new TypeReference<Set<Attraction>>() {});
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
