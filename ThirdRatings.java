
/**
 * Write a description of ThirdRatings here.
 * 
 * @author PARUL
 * @version 24.5.2020
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.io.FileReader;
public class ThirdRatings {
    
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    
    public ArrayList<Rater> getRaters()
    {
        return myRaters;
    }
    
    public ThirdRatings(String ratingsfile)
    {
        FirstRatings temp = new FirstRatings();
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

    public int getRaterSize()
    {
        return myRaters.size();
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
       ArrayList<Rating> avgRating = new ArrayList<Rating>();
       
       ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter()); 
       for(String mov_id  : movies){
           double avg_rating = getAverageByID(mov_id, minimalRaters);
           Rating rat = new Rating(mov_id, avg_rating);
           if(avg_rating != 0) {avgRating.add(rat);
        }
           
       }
        
       return avgRating;   
     
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
    {
        ArrayList<Rating> r = new ArrayList<Rating>();
        ArrayList<String>m = MovieDatabase.filterBy(filterCriteria);
        for(String mov_id  : m){
           double avg_rating = getAverageByID(mov_id, minimalRaters);
           Rating rat = new Rating(mov_id, avg_rating);
           if(avg_rating != 0) {r.add(rat);}
    }
        return r;
    }
    
}
