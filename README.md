# CS245-Project01
Summary:
This function will read a CSV file of Movies with their cast and Actors. I will scan the file using a Scanner method and create an Actor object for each character 
that is found. Once I have read the file, I will sort my actor objects alphabetically by their actor name using quicksort. In order to search for each actor, I will use binary search.

How to Use:
1) Enter a actor name
2) If actor is not found, enter Y to accept actor reccomendation or N to refuse the reccomendation
3) If the actor is found, print the movie wall. Enter Y to continue to enter another actor name or N to exit the program


Bugs:
-Some special characters are read weirdly
-Some actors have a digit name (Although this is a very small amount. Around 900 actors have this problem but the majority (Around 99000 actors) other actors do not have this issue) 
