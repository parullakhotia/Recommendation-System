
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//import java.util.*;
public class GenreFilter implements Filter{
    
    private String mygenre;
    public GenreFilter(String genre)
    {
        mygenre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
	String [] gen = MovieDatabase.getGenres(id).split("\\s*,\\s*");
	for(String s: gen){
	    if(s.equals(mygenre)) return true;
	}
	return false;
    }
    
    
	/*public ArrayList<String> satisfies(String id) {
		String [] gen = MovieDatabase.getGenres(id).split("\\s*,\\s*");
		ArrayList<String> genList = new ArrayList<String>(Arrays.asList(gen));
		return genList;
		
	}*/


}
