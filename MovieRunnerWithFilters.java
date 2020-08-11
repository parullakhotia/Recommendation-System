
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {

    public void printAverageRatings(int minrater)
    {
        ThirdRatings sec = new ThirdRatings(); 
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("movie database size "+ MovieDatabase.size());
        System.out.println("rater size "+ sec.getRaterSize());
        
        ArrayList<Rating> sorted = sec.getAverageRatings(minrater);
        System.out.println(minrater+" or more ratings "+sorted.size());
        Collections.sort(sorted);
        for(Rating item : sorted){
            System.out.println(item.getValue()+" "+ MovieDatabase.getTitle(item.getItem()));
            
        }
    }
    
    public void printAverageRatingsByYear(int year, int minimal_raters)
    {
        Filter f = new YearAfterFilter(year);
        ThirdRatings t = new ThirdRatings(); 
        ArrayList<Rating> r = t.getAverageRatingsByFilter(minimal_raters, f);
        System.out.println("no. of movies after year: "+r.size());
        Collections.sort(r);
        for(Rating item : r){
            System.out.println(item.getValue()+" "+MovieDatabase.getYear(item.getItem())+" "+ MovieDatabase.getTitle(item.getItem()));
            
        }
    }
    
    public void printAverageRatingsByGenre(String genre, int minimal_raters)
    {
        Filter f = new GenreFilter(genre);
        ThirdRatings t = new ThirdRatings(); 
        ArrayList<Rating> r = t.getAverageRatingsByFilter(minimal_raters, f);
        System.out.println("no. of movies of a genre: "+r.size());
        Collections.sort(r);
        for(Rating item : r){
            System.out.println(item.getValue()+"  "+ MovieDatabase.getTitle(item.getItem()));
            System.out.println("      Genres: "+MovieDatabase.getGenres(item.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(int min_minutes, int max_minutes, int minimal_raters)
    {
        Filter f = new MinutesFilter(min_minutes,max_minutes);
        ThirdRatings t = new ThirdRatings(); 
        ArrayList<Rating> r = t.getAverageRatingsByFilter(minimal_raters, f);
        System.out.println("no. of movies in given range of minutes: "+r.size());
        Collections.sort(r);
        for(Rating item : r){
            System.out.println(item.getValue()+" Time: "+MovieDatabase.getMinutes(item.getItem())+" "+ MovieDatabase.getTitle(item.getItem()));
            
        }
    }
    
    public void printAverageRatingsByDirectors(String director, int minimal_raters)
    {
        Filter f = new DirectorsFilter(director);
        ThirdRatings t = new ThirdRatings(); 
        ArrayList<Rating> r = t.getAverageRatingsByFilter(minimal_raters, f);
        System.out.println("no. of movies by a director: "+r.size());
        Collections.sort(r);
        for(Rating item : r){
            System.out.println(item.getValue()+" "+ MovieDatabase.getTitle(item.getItem()));
            System.out.println("      by "+MovieDatabase.getDirector(item.getItem()));
            
        }
    }
    
    
    
    
    
    public void printAverageRatingsByYearAfterAndGenre(String genre, int year, int minimal_raters)
    {
        Filter g = new GenreFilter(genre);
        Filter y = new YearAfterFilter(year);
        AllFilters f = new AllFilters();
        f.addFilter(g);
        f.addFilter(y);
        ThirdRatings t = new ThirdRatings(); 
        ArrayList<Rating> r = t.getAverageRatingsByFilter(minimal_raters, f);
        System.out.println("no. of movies in given genre and year "+r.size());
        Collections.sort(r);
        for(Rating item : r){
            //System.out.println(item.getValue()+" "+MovieDatabase.getYear(item.getItem())+" "+ MovieDatabase.getTitle(item.getItem()));
            //System.out.println("      genres: "+MovieDatabase.getGenres(item.getItem()));
            
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(String directors, int min_minute,int max_minute, int minimal_raters)
    {
        Filter d = new DirectorsFilter(directors);
        Filter m = new MinutesFilter(min_minute, max_minute);
        AllFilters f = new AllFilters();
        f.addFilter(d);
        f.addFilter(m);
        ThirdRatings t = new ThirdRatings(); 
        ArrayList<Rating> r = t.getAverageRatingsByFilter(minimal_raters, f);
        System.out.println("no. of movies in given time range & by given directors: "+r.size());
        Collections.sort(r);
        for(Rating item : r){
            System.out.println(item.getValue()+" Time: "+MovieDatabase.getMinutes(item.getItem())+" "+ MovieDatabase.getTitle(item.getItem()));
            System.out.println("      by "+MovieDatabase.getDirector(item.getItem()));
            
        }
    }
    
}
