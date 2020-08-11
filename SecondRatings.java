
/**
 * Write a description of SecondRatings here.
 * 
 * @author PARUL LAKHOTIA
 * @version 23.05.2020
 */

import edu.duke.*;
import java.util.*;

import org.apache.commons.csv.*;
import java.io.FileReader;
public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public ArrayList<Movie> getMovies()
    {
        return myMovies;
    }
    
    public ArrayList<Rater> getRaters()
    {
        return myRaters;
    }
    
    public SecondRatings(String moviefile, String ratingsfile)
    {
        FirstRatings temp = new FirstRatings();
        myMovies = temp.loadMovies(moviefile);
        myRaters = temp.loadRaters(ratingsfile);
    }
    
    private double getAverageByID(String id, int minimalRaters)
    {
        int count = 0;
        double average = 0.0;
        for(Rater r: myRaters){
            if(r.hasRating(id)){
                    count++;
                    average += r.getRating(id);               
            }
            
        }
        if(count >= minimalRaters) return average/count;
        else return 0.0;
    }
    
    public int getMovieSize()
    {
        return myMovies.size();
    }
    public int getRaterSize()
    {
        return myRaters.size();
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        
        
       ArrayList<Rating> avgRating = new ArrayList<Rating>();
       FileResource fr = new FileResource("data/ratings.csv");
           
       HashSet<String> visited = new HashSet<String>();
       for (CSVRecord record : fr.getCSVParser()) {
           String mov_id =  record.get("movie_id");
           double avg_rating =0.0;
           if(visited.contains(mov_id) == false){
               visited.add(mov_id);
               avg_rating = getAverageByID(mov_id, minimalRaters);
               Rating rat = new Rating(mov_id, avg_rating);
               if(avg_rating != 0) {avgRating.add(rat);}
           }
                    
                  
                    
       }
        
       return avgRating;   
     
    }
    
    public String getTitle(String id)
    {
        for(Movie m : myMovies){
            if(m.getID().equals(id)){
                return m.getTitle();
            }
        }
        return "Not Found";
    }
    
    
}