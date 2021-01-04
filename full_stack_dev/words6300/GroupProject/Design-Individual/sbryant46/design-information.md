## Assignment 5 Design Information
##### Shannon M. Bryant
##### sbryant46
##### 9/21/19

##### 1. When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings.

###### To realize this requirement, I added a Player class with methods that instatiate the Word Game, Rack, and Game Statistics classes. The Word Game class has two dependent classes for Board and Settings.  The Settings class contains methods for updating the settings.

##### 2. When choosing to adjust the game settings, the player (1) may choose for the game to end after a certain maximum number of turns and (2) may adjust the number of and the letter points for each letter available in the pool of letters, starting with the default matching the English Scrabble distribution (12 E’s worth 1 point each, 4 D’s worth 2 points each, etc).

###### To realize this requirement, I added methods to the Settings class for the user to chose the maximum number of turns, adjust letter values, and adjust letter counts.  The information that is set in the Settings class is used to submit two Hash Map's to the Letter Pool class from the Word Game class that will generate an Array of Letter type ojects.  The Array will serve as a literal pool of letters, for example, if the user defines 4 A's, then the Array will contain 4 Letter objects where the character is 'A'.  This will allow us to remove Letter objects from the Pool to the Rack and onto the Board.

##### 3. When choosing to play a word game, a player will:  Be shown a ‘board’ consisting of 4 letters drawn from the pool of available letters.  Be shown a ‘rack’ of 7 letters also drawn from the pool of available letters.

###### To realize this requirement, I added a method to the Board class that will leverage the Letter Pool classes method to get and remove letters from the Letter Pool with an Integer input that will be set to 4.  Additionally, I added a private method to the Player class that gets a Rack object and added a method to refill the letters based on the number of remaining letters up to 7.  This method also calls the get and remove random letters Letter Pool class method.  

##### 4. On each turn, up to the maximum number of turns either:  Enter a word made up of one or more letters from the player’s rack of letters, and one from the board.  The word must contain only valid letters and may not be repeated within a single game, but does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly).  or Swap 1-7 letters from their rack with letters from the pool of letters.  This is the only time letters are returned to the pool during a game.

###### To realize this requirement, I added a method to the Board class to submit the Letter objects and another method to evaluate the word.  The Board class is associated with the Game Statistics class, which has two subclasses including: Letter and Word Statistics.  There is a method in the Word Statistics class that will return a Hash Map of each unique word entered and the time of occurence so we can evalute the letter for uniquness.  For the second piece of this requirement, I added a method to the Rack class to swap letters and a method insid the Letter Pool class to return Letter type objects to the pool.

##### After a word is played, that letter on the board will be replaced by a different random letter from the word that was just played.  Example:  If ‘c’ is on the board, and ‘j’,’a’,’k’,’e’,’t’,’s’ are part of the rack, then the player may enter ‘jackets’ as a word, and the ‘c’ will be randomly replaced by the ‘j’,’a’,’k’,’e’,’t’, or ’s’ for the next turn on the board.

###### To realize this requirement, I added a replace letter method to the board class that will take the letter objects on the board that form a word minus the letter(s) used and submit them to a method in the Letter Pool class.  Since there is a relationship with the Letter Pool class, we call that class to instatiate the get and remove random letters class.  Finally, the set letters method in the Board class can set the letter object into the two dimensional array representing the board value.


##### After a word is played, the tiles used from the rack are replaced from the pool of letters.

###### To realize this requiremnt, there will be a series of functions that run when a word is submitted to trigger this action.  The Board class contains methods to submit a word, have that word evaluated, and then since there is a relationship between the Board and the Rack the Board can instatiate the refill rack method in the Rack object. 

##### After a word is played, the player’s score will increase by the total number of points for the letters in the word, including the letter used from the board. (So ‘jackets’, if using default values, would score 20 points.)

###### To realize this requirement, there is a Game Statistics class that is associated with the Board class.  Once the word has been evaluated using a method in the Board class, an additional method will be called to calculate and set the score. This method will ultimantely call a method in the Game Statistics class, which will locate the player by their number and increase the score value by the points earned using the set player score method.

##### If the pool of letters is empty and the rack cannot be refilled, the player will score an additional 10 points.

###### To realize this requirement, I added a method to the Board class that will update that player's score by 10 points.  This method will be called when the refill letters method in the Rack class returns null. 

##### When the maximum number of turns has been played, or the pool of letters is empty and the rack cannot be refilled, the game will end, and the final score will be displayed before returning to the first menu.

###### To realize this requirement, there is a method in the Board class called terminate.  There will be evaluation in the background every time a turn occurs to iterate the turn value in the Game Statistics class and compare it to the maximum turn value in the Settings class.  If refill letters returns null in the Rack class or if the current turn value is greater than the maximum turn then the terminate function in the Board class will be called, which will then call the stop game function in the Word Game class.  This will set the status of the game to 'Stopped.'

##### A player may choose to leave a game in progress at any time.  Selecting to play a game from the menu should then return to the game in progress.

###### To realize this requirement, there is a method in the Player class called leave game that will call a method in the Board class to set the game state.

##### When choosing to view statistics, the player may view (1) game score statistics, (2) letter statistics or (3) the word bank.

###### To realize this requiremnt, I added a parent class Game Statistics with two subclasses Letter and Word Statistics.  Additionally, there are methods in the Player class to get each type of statistic.

##### For game score statistics, the player will view the list of scores, in descending order by final game score, displaying: The final game score The number of turns in that game The average score per turn

###### To realize this requirement, I have built a relationship between the Player and the Game Statistics class and have added a method to get the score, the number of turns, and the average score per turn.

##### The player may select any of the game scores to view the settings for that game’s maximum number of turns, letter distribution, and letter points.

###### To realize this requirement, there is a relationship between the Game Settings class and Game Statistics class.  Any of the get methods can be called in the Game Settings class when the score is viewed in the Game Settings class. 

##### For letter statistics, the player will view the list of letters, in ascending order by number of times played, displaying:The total number of times that letter has been played in a word The total number of times that letter has been traded back into the poolThe percentage of times that the letter is used in a word, out of the total number of times it has been drawn

###### To realize this requirement, there is a Hash Map of statisticis in the Letter Statistics class.  This Hash Map has an object which will be used to store the value mentioned above when actions take place to change the statistics.

##### For the word bank, the player will view the list of words used, starting from the most recently played, displaying: The word The number of times the word has been played

###### To realize this requirement, I have added a word statistics Hash Map in the Word Statistics class that when rendered uses the word as the key and the value as the number of occurences.  