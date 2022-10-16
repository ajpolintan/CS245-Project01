package project01;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
public class project01 {
	//TESTING
	public static void main(String[] args)  {
		System.out.println("Welcome to the Movie Wall!");
		ArrayList<Actor> actors = new ArrayList<Actor>();
		buildArray(actors);
		
		System.out.println("Loading...");
		insertionSort(actors);
		//Scanning Function
		lookActor(actors);
		
	} //END OF MAIN
	
	public static void lookActor(ArrayList<Actor> actors) {
		
		Scanner sc = new Scanner(System.in);
		boolean movieLoop = true;
		boolean continueLoop = true;
		boolean actorErrorLoop = true;
		boolean found = false;
		try {
			while (movieLoop) {
				continueLoop = true;
				found = false;
				actorErrorLoop = true;
				while (actorErrorLoop) {
					System.out.println("Enter an Actor: ");
					try {
						String name = sc.nextLine().toUpperCase();
						if (name.equals(actors.get(binarySearchInsert(actors,name)).getActor())) {
							/*
							for(int i = 0; i < actors.size();i++) {
								if(actors.get(i).getActor().equals(name)) {
									found = true;
									System.out.println(actors.get(i));
									actorErrorLoop = false;

								}	
							}
							*/

							actorErrorLoop = false;
							found = true;
							int pos = actors.indexOf(actors.get(binarySearchInsert(actors,name)));
							
							//Reset to first duplicate
							while(actors.get(pos).getActor().equals(actors.get(binarySearchInsert(actors,name)).getActor())) {
								pos--;
							}
							
							//If it goes too far back
							pos++;
							
							//Print duplicates
							while(actors.get(pos).getActor().equals(actors.get(binarySearchInsert(actors,name)).getActor())) {
								System.out.println(actors.get(pos));
								pos++;
							}
						} 
					
						//System.out.println(binarySearchInsert(actors,name));
						if (found == false) {
							System.out.println("No actor found") ;
							System.out.println("Did you mean " + actors.get(binarySearchInsert(actors,name)).getActor());
							String end = sc.nextLine().toUpperCase();
							if(end.equals("Y") || end.equals("YES")) {
								//BINARY SEARCH
									actorErrorLoop = false;
									int pos = actors.indexOf(actors.get(binarySearchInsert(actors,name)));
									while(actors.get(pos).getActor().equals(actors.get(binarySearchInsert(actors,name)).getActor())) {
										System.out.println(actors.get(pos));
										pos++;
									}
								
							} else if(end.equals("N") || end.equals("NO")){
								actorErrorLoop = false;
							} else {
								System.out.println("Invalid arguments");
								actorErrorLoop = true;
							}
						}
					} catch (IndexOutOfBoundsException e) {
						System.out.println();
					}
				}
			
				System.out.println("Press Y to continue or N to stop");
				
				
				while(continueLoop) {
					String end = sc.nextLine().toUpperCase();
					if(end.equals("Y") || end.equals("YES")) {
						movieLoop = true;
						continueLoop = false;
					} else if(end.equals("N") || end.equals("NO")){
						movieLoop = false;
						continueLoop = false;
						System.out.println("Thank you for using the Movie Wall!");
					} else {
						System.out.println("Invalid arguments");
						continueLoop = true;
					}
				}
			}
		
		} catch (IllegalArgumentException e) {
			System.out.println("Error Occurance");
			e.printStackTrace();
		}
	}
	public static void buildArray(ArrayList<Actor> arr) {
		try {
			
			File file = new File("tmdb_5000_credits.csv");
			Scanner myReader = new Scanner(file,"UTF-8");
			int count = 0;
			int index = 0;
			int commaCount = 0;
			myReader.nextLine();
			while (myReader.hasNextLine()) {
				boolean comma = true;
				String data = myReader.nextLine();
				String[] splitnewData = data.split(",");

				
				for(int i = 0; i < splitnewData.length;i++) {
					if(splitnewData[i].contains("character") || splitnewData[i].contains("name")) {
					//	System.out.println(splitnewData[i]);
						String adjust = splitnewData[i].replace("\"\"","");
						//Skip first space
						adjust = adjust.substring(1,adjust.length());

						//Remove } in crew
						if(adjust.contains("}")) {
							adjust = adjust.substring(0,adjust.length() - 1);
						}
						//Remove }] in crew
						if(adjust.contains("}]")) {
							adjust = adjust.substring(0,adjust.length() - 2);
						}
						
						//If the character is the first string
						//ADDS CHARACTER 
						if (splitnewData[i].contains("character")) {
							String[] characters = adjust.split(": ");
							//System.out.println(splitnewData[i + 4]);
							//Name value
							String name = splitnewData[i + 4].replace("\"\"","");
							//Remove extra space in beginning
							name = name.substring(1,name.length());

							String[] names = name.split(": ");
							//If the index is not greater
							if(characters.length >= 2 && names.length >= 2) {
								Actor actor = new Actor(names[1].toUpperCase(),characters[1],splitnewData[1]);
							    //System.out.println(actor);
								//If the actors array contains an actor that 
								
								
								/*
								 * If actor is not found, add the actor
								 * else search for actor, add movie to actor								 * 
								 */
								
							//	boolean isAdded = false;
							//	for(int j = 0; i < actors.size();i++) {
							//		if(actors.get(i).getActor().equals(name)) {
							//			actors.get(i).getMovies().add(splitnewData[1]);
							//			isAdded = true;
							//		}
							//	}
								//If the actor is not added yet, add the actor 
							//	if(isAdded == false) {
									arr.add(actor);
							//	}
							}
						//If it is just a name, aka crew members
						} else {
							String[] characters = adjust.split(": ");
							Actor actor = new Actor(characters[1]);

						}
 					
						//System.out.println(adjust);

					}
				}
			
				//System.out.println(castData);
				//System.out.println(newData);
				//for(int i = 0; i < splitnewData.length; i++) {
				//	System.out.println(splitnewData[i]);
				//}
				
				//System.out.println(splitnewData);
				count++;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error Occurance");
			e.printStackTrace();
		}
	}
	public static void insertionSort(ArrayList<Actor> arr) {
		for (int i = 1; i < arr.size(); i++) {
			Actor temp = arr.get(i);
			int j = i - 1;
			while (j >= 0 && arr.get(j).getActor().compareTo(temp.getActor()) > 0) {
				arr.set(j+1,arr.get(j));
				j--;
			}
			arr.set(j+1, temp);
		}
	}
	
	public static void mergeSort(ArrayList<Actor> arr) {
		if (arr.size() > 1) {
			ArrayList<Actor> left = new ArrayList<Actor>();
			ArrayList<Actor> right = new ArrayList<Actor>();
			left = get_left(arr);
			right = get_right(arr);
			
			mergeSort(left);
			mergeSort(right);
			merge(left,right,arr);
		}
	}
	
	public static ArrayList<Actor> get_left(ArrayList<Actor> arr) {
		
		int size = arr.size()/2;
		for (int i = 0; i < size; i++) {
			arr.add(arr.get(i));
		}
		return arr;
	}
	

	public static ArrayList<Actor> get_right(ArrayList<Actor> arr) {
		int size = arr.size() / 2 + 1;
		for (int i = size; i < arr.size();i++) {
			arr.add(arr.get(i));
		}
		return arr;
	}
	
	public static void merge(ArrayList<Actor> bot,ArrayList<Actor> top,ArrayList<Actor> target) {
		int li = 0;
		int ri = 0;
		int ti = 0;
		while (li < bot.size() && ri < top.size()) {
				if(bot.get(li).getActor().compareTo(top.get(ri).getActor()) > 0); {   
					target.set(ti++,top.get(ri++));
				}
			    if (bot.get(li).getActor().compareTo(top.get(ri).getActor()) > 0) {
					target.set(ti++,top.get(ri++));

			    }

			   
		}
		 while(li < bot.size()) {
		     target.set(ti++,bot.get(li++));
		 }
		 while(ri < top.size()) {
		     target.set(ti++,top.get(ri++));
		 }
	}
	public static int binarySearchInsert(ArrayList<Actor> arr, String target) {
	       	int min = 0;
	       	int max = arr.size() - 1;
	       	while (min <= max) {
	       		int mid = (min + max) /2;
	       		if(arr.get(mid).getActor().equals(target)) {
	       			//return arr.get(mid).getActor();
	       			return mid;
	       		}
	       		if(arr.get(mid).getActor().compareTo(target) <= 0) {
	       			min = mid + 1;
	       		}
	       		else {
	       			max = mid - 1;
	       		}
	       	}
	       	return min;
	      //   return arr.get(min).getActor();
	         
	      }


}


