
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int minMinutes;
    private int maxMinutes;
	
	public MinutesFilter(int mini, int maxi) {
		minMinutes = mini;
		maxMinutes = maxi;
	}
	
	@Override
	public boolean satisfies(String id) {
	    int m  = MovieDatabase.getMinutes(id);
	    return (m >=minMinutes && m<=maxMinutes);
	}
}
