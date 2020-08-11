
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//import java.util.*;
public class DirectorsFilter implements Filter{
    private String [] myDirector;
    
    public DirectorsFilter(String director)
    {
        myDirector = director.split("\\s*,\\s*");
    }
    
    @Override
    public boolean satisfies(String id) {
	String [] gen = MovieDatabase.getDirector(id).split("\\s*,\\s*");
	for(String d: myDirector){
        	for(String s: gen){
        	    if(s.equals(d)) return true;
        	}      
        }
	return false;
    }
}
