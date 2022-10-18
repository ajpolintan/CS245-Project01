package project01;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
public class project01 {
	//Main Function
	public static void main(String[] args)  {
		System.out.println("Welcome to the Movie Wall!");
		//Create ArrayList of actors
		ArrayList<Actor> actors = new ArrayList<Actor>();
		
		//Reads the file and adds actors and characters
		buildArray(actors);
		
		//Quick sort function  
		sort(actors);
		
		//Asks for User Input for a specific actor and lists the movies the actor is from
		lookActor(actors);
		
	} //END OF MAIN
	
	 /**
     * Summary: Asks for User Input for a specific actor and lists the movies the actor is from
     * @param An ArrayList of actors
     * @return Nothing because it is void
     */
	public static void lookActor(ArrayList<Actor> actors) {
		
		//Ask for User Input
		Scanner sc = new Scanner(System.in);
		//Conditionals for user input loops
		boolean movieLoop = true;
		boolean continueLoop = true;
		boolean actorErrorLoop = true;
		boolean found = false;
		
		//Start Searching
		try {
			while (movieLoop) {
				//Starting Conditionals
				continueLoop = true;
				found = false;
				actorErrorLoop = true;
				while (actorErrorLoop) {
					//Ask for inputs
					System.out.println("Enter an Actor: ");
					try {
						//Get input
						String name = sc.nextLine().toUpperCase();
						//A binary search will be performed to get an actor and will be compared to the user input
						//If the user input is the same, then print the actor object
						if (name.equals(actors.get(binarySearchInsert(actors,name)).getActor())) {
							//Conditional changes
							actorErrorLoop = false;
							found = true;
							
							//Get index of the name
							int pos = actors.indexOf(actors.get(binarySearchInsert(actors,name)));
							
							//Reset to first duplicate of Actors, which is the first movie for every actor 
							while (actors.get(pos).getActor().equals(actors.get(binarySearchInsert(actors,name)).getActor())) {
								pos--;
							}
							
							//If it goes too far back
							pos++;
							
							//Print every movie of the actor
							while (actors.get(pos).getActor().equals(actors.get(binarySearchInsert(actors,name)).getActor())) {
								System.out.println(actors.get(pos));
								pos++;
							}
						} 
						//If a Actor is Not Found
						if (found == false) {
							//Recommend a similar actor similar to the name
							System.out.println("No actor found");
							System.out.println("Did you mean " + actors.get(binarySearchInsert(actors,name)).getActor());
							//Ask for input 
							//Y means print the movies of the recommended actor
							//No means it will asks the User if they want to quit the program or not
							String end = sc.nextLine().toUpperCase();
							if(end.equals("Y") || end.equals("YES")) {
									//Uses the previous method to print out actor names
									actorErrorLoop = false;
									//Get index of actor
									int pos = actors.indexOf(actors.get(binarySearchInsert(actors,name)));
									//Print actor movies
									while(actors.get(pos).getActor().equals(actors.get(binarySearchInsert(actors,name)).getActor())) {
										System.out.println(actors.get(pos));
										pos++;
									}
							//Move to the next conditionals	
							} else if(end.equals("N") || end.equals("NO")){
								actorErrorLoop = false;
							} else {
								System.out.println("Invalid arguments");
								actorErrorLoop = true;
							}
						}
					  //Exception Catcher
					} catch (IndexOutOfBoundsException e) {
						System.out.println();
					}
				}
				//Asks if the user wants to enter another actor or exit the program
				System.out.println("Press Y to continue or N to stop");
				
				
				while (continueLoop) {
					String end = sc.nextLine().toUpperCase();
					//Y means to ask the user for another actor they want to search for
					if(end.equals("Y") || end.equals("YES")) {
						movieLoop = true;
						continueLoop = false;
					//N means to exit the program
					} else if(end.equals("N") || end.equals("NO")){
						movieLoop = false;
						continueLoop = false;
						System.out.println("Thank you for using the Movie Wall!");
					//If the input is not Y or N
					} else {
						System.out.println("Invalid arguments");
						continueLoop = true;
					}
				}
			}
		//Error checker
		} catch (IllegalArgumentException e) {
			System.out.println("Error Occurance");
			e.printStackTrace();
		}
	}
	

	 /**
    * Summary: Reads the file and adds actors and characters
    * @param An ArrayList of actors
    * @return Nothing because it is void
    */
	public static void buildArray(ArrayList<Actor> arr) {
		try {
			//Scanning the file
			File file = new File("tmdb_5000_credits.csv");
			Scanner myReader = new Scanner(file,"UTF-8");
			//Skip Header Line
			myReader.nextLine();
			//Loop thru every line of CSV file
			while (myReader.hasNextLine() ) {
				//Get Data for each line
				String data = myReader.nextLine();
				//Split Data By Commas
				String[] splitNewData = data.split(",");

				//Loop thru the split data
				for(int i = 0; i < splitNewData.length;i++) {
					//Search for data in each line that equals character 
					if(splitNewData[i].contains("character")) {
						//Once a character or name is found, pass it to a String called adjust which will clean up the character String
						String adjust = splitNewData[i].replace("\"\"","");
						//Skip first space
						adjust = adjust.substring(1,adjust.length());
						
						//Add Character
						if (splitNewData[i].contains("character")) {
							//Split the data to be passed into an actor object
							String[] characters = adjust.split(": ");
							//Get the actor for the character which always comes after characters 4 times
							String name = splitNewData[i + 4].replace("\"\"","");
							//Remove extra space in beginning
							name = name.substring(1,name.length());
							//Split the data to be passed into an actor object
							String[] names = name.split(": ");
							//This simply fixed errors I was getting
							if(characters.length >= 2 && names.length >= 2) {
								//Add each actor into the ArrayList
								Actor actor = new Actor(names[1].toUpperCase(),characters[1],splitNewData[1]);
								arr.add(actor);
							}
						} 
					}
				}
			}
			//Close the reader
			myReader.close();
		//Error Checker
		} catch (FileNotFoundException e) {
			System.out.println("Error Occurance");
			e.printStackTrace();
		}
	}
	
	
	/**
     * partition function for quick sort *
     *
     * @param ArrayList of Type Actor to be sorted
     * @return nothing
     */
    public static <T extends Comparable<T>> void sort(ArrayList<Actor> actors) {
        quickSort(actors, 0, actors.size() - 1);
    }
    
    /**
     * partition function for quick sort *
     *
     * @param arr   array to be sorted
     * @param left  left boundary
     * @param right right boundary
     * @param ArrayList of type Actor
     * @return pivot index
     */
	public static <T extends Comparable<T>> int partition(ArrayList<Actor> arr, int left, int right) {
        Actor pivot = arr.get(left + right >> 1); // -> the element in the middle, not the pivot index
        //When comparing generic data from the array, use the compareTo() function instead of equality operators
        //compareTo return a boolean value, and boolean can also represent by -1, 0, 1
        
        while (left <= right) {
            //1. keep checking left element in the array if is less than pivot, update the left boundary
                if (arr.get(left).getActor().compareTo(pivot.getActor()) <= -1)  {
                	left++;
                }
            //2. keep checking pivot if is less than the right element in the array, update the right boundary
                else if (arr.get(right).getActor().compareTo(pivot.getActor()) >= 1) {
                	right--;
                }
            //3. check left with right -> what should be the if-condition?
            //then swap elements and update both boundary
                else {
                	swap(arr,left, right);
                	left++;
                	right--;
                }

        }
        return left;
    }
	   /**
     * Recursively quick sorting the array by randomPartitioning*
     *
     * @param unsorted unsorted array
     * @param left     left boundary
     * @param right    right boundary
     * @param ArrayList of type Actor
     */
    public static <T extends Comparable<T>> void quickSort(ArrayList<Actor> unsorted, int left, int right) {
        // Complete the quicksort logic here
        // pivot assignment should call the randomPartition helper function
        // figure out the new left and right boundary based on the pivot
    	
    	if(left < right) {
    		int p = randomPartition(unsorted,left,right);
    		quickSort(unsorted, left, p - 1);
    		quickSort(unsorted, p, right);
    	}

    }

    /**
     * random partitioning with Math.random() function to generate a random index *
     * *
     *
     * @param unsorted unsorted array
     * @param left     left pointer
     * @param right    right pointer
     * @param ArrayList of type Actor
     * @return perform sorting and return the index of pivot
     */
    public static <T extends Comparable<T>> int randomPartition(ArrayList<Actor> unsorted, int left, int right) {
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        swap(unsorted, randomIndex, right);
        return partition(unsorted, left, right);
    }

	 public static <T> void swap(ArrayList<Actor> unsorted, int i, int j) {
	        Actor temp = unsorted.get(i);
	        unsorted.set(i, unsorted.get(j));
	        unsorted.set(j, temp);

	 }
	 /**
	    * Summary: Performs a binary search on the actor ArrayList
	    * @param An ArrayList of actors and a String of the actor to search for 
	    * @return The index of where the actor/target is found
	 */
	public static int binarySearchInsert(ArrayList<Actor> arr, String target) {
	       	int min = 0;
	       	int max = arr.size() - 1;
	       	while (min <= max) {
	       		int mid = (min + max) /2;
	       		if(arr.get(mid).getActor().equals(target)) {
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
	     
	      }


}


