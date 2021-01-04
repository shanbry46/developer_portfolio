## Requirements
1.	When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings.    
**This will be handled by 3 buttons on the UI and is represented in the diagram by the (1) Word Game class, (2) Statistics class, and (3) Settings Class**  
2.	When choosing to adjust the game settings, the player (1) may choose for the game to end after a certain maximum number of turns and (2) may adjust the number of and the letter points for each letter available in the pool of letters, starting with the default matching the English Scrabble distribution (12 E’s worth 1 point each, 4 D’s worth 2 points each, etc).  
**This is represented in the settings class with 2 attributes: maxTurns and letterPool. Max turns defaults to -1 which represents that the player has not yet configured a max number of turns and the game will be played until the user runs out of letters. The letterPool is an array of letters. Each letter object has a value and number available.**
3.	When choosing to play a word game, a player will:  
    a.	Be shown a ‘board’ consisting of 4 letters drawn from the pool of available letters.    
    **Represented by the viewBoard method and board character array in the Active Game class**  
    b.	Be shown a ‘rack’ of 7 letters also drawn from the pool of available letters.   
    **Represented by the viewRack method and rackOfLetters character array in the Active Game class.**   
    c.	On each turn, up to the maximum number of turns either:  
        i.	Enter a word made up of one or more letters from the player’s rack of letters, and one from the board.  The word must contain only valid letters and may not be repeated within a single game, but does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly).  
        Or  
        **This is represented by the playWord method where the selectedLetter and currentWord attributes are used. The playWord method called the verifyWord method which uses the attributes board, rackOfLetters, and previousWords[] to verify that the current word meets the criteria.**   
        ii.	Swap 1-7 letters from their rack with letters from the pool of letters.  This is the only time letters are returned to the pool during a game.  
        **This is represented by the swapLetters method in Active Game that takes the number of letters the player wants to swap and randomly swaps that number of letters from the rack with letters from the remainingLetters char array.**  
    d.	After a word is played, that letter on the board will be replaced by a different random letter from the word that was just played.  Example:  If ‘c’ is on the board, and ‘j’,’a’,’k’,’e’,’t’,’s’ are part of the rack, then the player may enter ‘jackets’ as a word, and the ‘c’ will be randomly replaced by the ‘j’,’a’,’k’,’e’,’t’, or ’s’ for the next turn on the board.  
    **The playWord method will call the updateBoard method which uses the currentWord and selectedLetter attributes to update the board with a new letter from the remaining letters in the word.**  
    e.	After a word is played, the tiles used from the rack are replaced from the pool of letters.    
    **After the updateBoardMethod is called, the refillRack method is called. The refillRack method uses the currentWord and selectedLetter attributes to replace the used letters from the rack with letters from the remainingLetters char array.**  
    f.	After a word is played, the player’s score will increase by the total number of points for the letters in the word, including the letter used from the board. (So ‘jackets’, if using default values, would score 20 points.)  
    **The updateScore method is used to calculate the points based on the current word and letterPool attributes and then updates the scores array attribute. **  
    g.	If the pool of letters is empty and the rack cannot be refilled, the player will score an additional 10 points.  
    **The refillRack and swapLetters methods will both call the completeGame method with a parameter value of true if the rack cannot be refilled. The completeGame method will add the attribute bonusPoints to the final score if this is true.**  
    h.	When the maximum number of turns has been played, or the pool of letters is empty and the rack cannot be refilled, the game will end, and the final score will be displayed before returning to the first menu.  
    **The completeGame method will display the final score. Which is a derived attribute in the Word Game class. It will be called from the playWord or swapLetters methods if at the end of the turn the gameMaxTurns = turnsPlayed. It will be called with a value of false since maxTurns were reached instead of the remainingLetters running out.**  
4.	A player may choose to leave a game in progress at any time.  Selecting to play a game from the menu should then return to the game in progress.  
**If there is an instance of Active Game, the player will be taken to that game. Otherwise a new instance will be created.**  
5.	When choosing to view statistics, the player may view (1) game score statistics, (2) letter statistics or (3) the word bank.  
**Within the statistics there are 3 classes representing the subtypes of statistics: GameStatistics, LetterStatistics, and WordBank.**  
6.	For game score statistics, the player will view the list of scores, in descending order by final game score, displaying:  
    a.	The final game score  
    b.	The number of turns in that game  
    c.	The average score per turn  
  The player may select any of the game scores to view the settings for that game’s maximum number of turns, letter distribution, and letter points.  
**The GameStatistics class has an array of completedGames. The completedGames class has a derived averageScore attribute and inherits the totalScore and turnsPlayed attributes from WordGame.**  
7.	For letter statistics, the player will view the list of letters, in ascending order by number of times played, displaying:  
    a.	The total number of times that letter has been played in a word  
    b.	The total number of times that letter has been traded back into the pool  
    c.	The percentage of times that the letter is used in a word, out of the total number of times it has been drawn  
**The Statistics class has an associated class called LetterPool. LetterPool is a collection of Letters. Each Letter object has the letter it represents, the value of the letter, the number of the letter that is part of the pool, the number of times the letter has been played, the number of times it has been traded, and the percent it has been used.**  
8.	For the word bank, the player will view the list of words used, starting from the most recently played, displaying:  
    a.	The word  
    b.	The number of times the word has been played  
**The Statistics class has an associated class called WordBank. WordBank is a collection of Words. Each Word object has the word, a useCount and lastModDateTime. The last modified datetime is used to display the words and their use count in order of most recently played.**  
9.	The user interface must be intuitive and responsive.  
**This does not need to be in the diagram because it will be handled as part of the GUI implementation.**  
10.	The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.  
**This does not need to be represented in the diagram because non-functional requirements are represented by classes, attributes, and methods.**  
11.	For simplicity, you may assume there is a single system running the application.  
**This does not need to be represented in the diagram because since it is a single system, we do not need to represent multiple systems talking to each other. The diagram is the system.**  

