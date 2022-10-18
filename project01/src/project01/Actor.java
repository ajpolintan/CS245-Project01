package project01;

import java.util.ArrayList;
//THE ACTOR CLASS
public class Actor {

	//Actor class is a class that consists of a actor String, character String, and movie String
	String actor;
	String character;
	String movie;
	//The Constructor
	public Actor(String _actor, String _character,String _movie) {
		actor = _actor;
		character = _character;
		movie = _movie;

	}
	//Alternate Constructor which constructs a crew member if necessary 
	public Actor(String _actor) {
		actor = _actor;
	}
	
	//How an actor is printed
	public String toString() {
		if(character != null) {
			return actor + " as " + character + " in " + movie;

		}
		return actor;
	}
	
	//Getter method
	public String getActor() {
		return actor;
	}

}
