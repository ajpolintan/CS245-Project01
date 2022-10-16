package project01;

import java.util.ArrayList;

public class Actor {
	String actor;
	String character;
	String movie;
	ArrayList<String> movies;
	public Actor(String _actor, String _character,String _movie) {
		actor = _actor;
		character = _character;
		movie = _movie;
		ArrayList<String> movies;

	}
	
	public Actor(String _actor) {
		actor = _actor;
	}
	public String toString() {
		if(character != null) {
			return actor + " as " + character + " in " + movie;

		}
		return actor;
	}
	
	public String getActor() {
		return actor;
	}
	public ArrayList<String> getMovies(){
		return movies;
	}
	public boolean addMovie(String movied) {
		movies.add(movied);
		return true;
	}
	public void printMovies() {
		for(int i = 0; i < movies.size();i++) {
			System.out.println(movies.get(i));
		}
	}
}
