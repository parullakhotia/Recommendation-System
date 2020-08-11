
/**
 *  to process the movie and ratings data and to answer questions about them. 
 * 
 * @author Parul 
 * @version 21.5.20
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.io.FileReader;
import java.io.IOException;
public class FirstRatings {
    
    //private static final String [] FILE_HEADER_MAPPING = {"id","title","year","country","genre","director","minutes","poster"};
     
    //MOVIE attributes
    /*private static final String MOVIE_ID = "id";
    private static final String MOVIE_TITLE = "title";
    private static final String MOVIE_YEAR = "year";
    private static final String MOVIE_COUNTRY = "country"; 
    private static final String MOVIE_GENRE = "genre";
    private static final String MOVIE_DIRECTOR = "director";
    private static final String MOVIE_MINUTES = "minutes";
    private static final String MOVIE_POSTER = "poster";*/
    
    public ArrayList<Movie> loadMovies(String filename)
    {
       // return ArrayList<movie>MovieData;
       
       /*FileReader fileReader = null;
         
       CSVParser csvFileParser = null;
         
        //Create the CSVFormat object with the header mapping
       CSVFormat csvFileFormat = CSVFormat.EXCEL.withHeader(FILE_HEADER_MAPPING);*/
      
       try
       {
           ArrayList<Movie> movie = new ArrayList<Movie>();
           /*fileReader = new FileReader(filename);
           
           //initialize CSVParser object
           csvFileParser = new CSVParser(fileReader, csvFileFormat);
           
           //Get a list of CSV file records
           List csvRecords = csvFileParser.getRecords();
           CSVRecord record = csvFileParser.getRecords().get(0);*/
           
           
           FileResource fr = new FileResource(filename);
           
           //Read the CSV file records starting from the second record to skip the header
           for (CSVRecord record : fr.getCSVParser()) {
                    //CSVRecord record = csvRecords.get(i);
                    //Create a new MOVIE object and fill his data
                    
                    Movie movieData = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"),record.get("director"),record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));
                    movie.add(movieData);  
                    //System.out.println(movieData.getPoster());
           }
           
           /*for (int i=0; i<movie.size(); i++) {
                System.out.println(movie.get(i).toString());
            }*/
            return movie;
            
      
        }  
       catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } 
        return null;
    }
    public void testLoadMovies()
    {
        /**call load movie on ratedmovies_short.csv
        *store the result in an ArrayList local variable
        *Print the number of movies, and print each movie
        *If you run your program on the file ratedmoviesfull.csv,
        *you should see there are 3143 movies in the file.
        */
       String filename = "data/ratedmoviesfull.csv";
       ArrayList<Movie> datafile = loadMovies(filename);
       
       //for director with max movies
       Map<String, Integer> frequencyMap = new HashMap<>();
       int count_comedy_movie = 0;
       int longer150 = 0;
       for (int i=0; i<datafile.size(); i++) {
                //System.out.println(datafile.get(i).toString());
                String [] gen = datafile.get(i).getGenres().split("\\s*,\\s*"); // s* for whitepaces
                //Arrays.toString(gen);
                
                for(String j: gen)
                {
                   // System.out.println(gen[i]);
                   
                    if(j.equals("Comedy"))
                    {
                        /*System.out.println("Comedy genre : ");
                        System.out.println(datafile.get(i).toString());*/
                        count_comedy_movie++;
                    }
                }
               
                
                int mins = datafile.get(i).getMinutes();
                if(mins>150)
                {
                    /*System.out.println(">150 minutes movie : ");
                    System.out.println(datafile.get(i).toString());*/
                    longer150++;
                }
                
                String [] direct = datafile.get(i).getDirector().split("\\s*,\\s*");
                for(String d : direct)
                {
                    Integer count = frequencyMap.get(d);
                    if(count == null) count = 0;
                    frequencyMap.put(d, count+1);
                }
                
                                           
                //System.out.println(datafile.get(i).toString());
       }
       
       String popular_director = frequencyMap.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
       Integer her_films = frequencyMap.get(popular_director);
       System.out.println(her_films);
       System.out.println(datafile.size());
       //System.out.println("max movies directed by director :");
       for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) 
       {
           //System.out.println(entry.getKey() + ": " + entry.getValue());
           
       }
    }
    
    public ArrayList<Rater> loadRaters(String filename)
    {
        /**should process every record from the CSV file whose name is filename,
         * a file of raters and their ratings, 
         *and return an ArrayList of 
         *type Rater with all the rater data from the file.
         
         */
        
        try
       {
           Map<String, Rater> s = new HashMap<>();
           //Set<Rater> s = new HashSet<Rater>();
           /*fileReader = new FileReader(filename);
           
           //initialize CSVParser object
           csvFileParser = new CSVParser(fileReader, csvFileFormat);
           
           //Get a list of CSV file records
           List csvRecords = csvFileParser.getRecords();
           CSVRecord record = csvFileParser.getRecords().get(0);*/
           
           
           FileResource fr = new FileResource(filename);
           
           //Read the CSV file records starting from the second record to skip the header
           for (CSVRecord record : fr.getCSVParser()) {
                    //CSVRecord record = csvRecords.get(i);
                    //Create a new MOVIE object and fill his data
                    Rater raterdata = new EfficientRater(record.get("rater_id"));
                    s.put(record.get("rater_id"),raterdata);
                    
                    //rater.add(rateData);  
                    //System.out.println(movieData.getPoster());
           }
           
           
           for (CSVRecord record : fr.getCSVParser()) {
                    //CSVRecord record = csvRecords.get(i);
                    //Create a new MOVIE object and fill his data
                    
                    String raterid = record.get("rater_id");
                    Rater temp = s.get(raterid);
                    temp.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                    s.replace(raterid, temp);
                    //rater.add(rateData);  
                    //System.out.println(movieData.getPoster());
           }
           /*for (int i=0; i<movie.size(); i++) {
                System.out.println(movie.get(i).toString());
            }*/
            
           Collection<Rater> values = s.values(); 
           ArrayList<Rater> rater = new ArrayList<Rater>(values);
           return rater;
            
      
        }  
       catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } 
        return null;
        
        
    }
    public void testLoadRaters()
    {
        /**Call the method loadRaters on the file ratings_short.csv and 
         * store the result in a local ArrayList variable
         *  Print number of raters.for each rater,print rater’s ID 
         *  and the number of ratings they did on one line, 
         *  followed by each rating (both the movie ID and the rating given) 
         *  on a separate line.
         *  Add code to find the number of ratings 
         *  for a particular rater you specify in your code.
         *  Add code to find the maximum number of ratings by any rater.
         *  Add code to find the number of ratings a particular movie has. 
         *  Add code to determine how many 
         *  different movies have been rated by all these raters.
         */
        
       String filename = "data/ratings.csv";
       ArrayList<Rater> datafile = loadRaters(filename);
       System.out.println(datafile.size());
       int max_rate = 0;
       String most_active_rater ="1";
       for (int i=0; i<datafile.size(); i++) 
       {
                Rater temp = datafile.get(i);
                //System.out.println(i);
                //System.out.println(temp.getID()+ " "+ temp.numRatings());
                if(temp.numRatings() > max_rate) {
                    max_rate = temp.numRatings();
                    most_active_rater = temp.getID();
                }
                //System.out.println(temp.getItemsRated());
                
                for(String j : temp.getItemsRated())
                {
                    
                    //System.out.println(temp.getRating(j));
                }
                //System.out.println(" ");
                
                if(temp.getID().equals("193")) {System.out.println(temp.numRatings());}
                
     
       }
       
       System.out.println("Raters with max ratings: ");
        for (int i=0; i<datafile.size(); i++) 
       {
           Rater temp = datafile.get(i);
           if(temp.numRatings() == max_rate)
           {
               //System.out.println(temp.getID()+ " "+ temp.numRatings());
           }
       }
       System.out.println("max rate "+ most_active_rater);
       System.out.println(" ");
       System.out.println("Movies with their number of ratings: ");
       Map<String, Integer> frequencyMap = new HashMap<>();
       
       FileResource fr = new FileResource(filename);
           
           //Read the CSV file records starting from the second record to skip the header
       for (CSVRecord record : fr.getCSVParser()) {

                    String mov_id =  record.get("movie_id");
                    Integer count = frequencyMap.get(mov_id);
                    if(count == null) {count = 0;}
                    frequencyMap.put(mov_id, count+1);
                    
       }
       //printing for given movie id
       String m = "1798709";
       System.out.println(frequencyMap.get(m));  
       
       //total number of distinct movies
       System.out.println("total number of distinct movies: "+frequencyMap.size());  
       
    }

}
