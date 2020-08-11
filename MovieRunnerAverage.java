
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {

    public void printAverageRatings()
    {
        SecondRatings sec = new SecondRatings(); 
        System.out.println("movie size "+ sec.getMovieSize());
        System.out.println("rater size "+ sec.getRaterSize());
        
        ArrayList<Rating> sorted = sec.getAverageRatings(11);
        System.out.println("50 or more ratings "+sorted.size());
        Collections.sort(sorted);
        for(Rating item : sorted){
            //System.out.println(item.getValue()+" "+ sec.getTitle(item.getItem()));
            
        }
    }
    
    public void getAverageRatingOneMovie()
    {
        SecondRatings s = new SecondRatings();
        ArrayList<Rating> AvgRatings =s.getAverageRatings(11);
        
        String m_id="";
        boolean flag = false;
        String name = "Elysium";
        //White House Down   Elysium  The Purge  Riddick
        for(Movie it: s.getMovies()){
            if(it.getTitle().equals(name))
            {
                m_id = it.getID();
                flag= true;
            }
        }
        if(flag == true){
            for(Rating r: AvgRatings){
                if(r.getItem().equals(m_id)){
                    System.out.println(name+ " "+ r.getValue());
                }
            }
    }
    }
}
