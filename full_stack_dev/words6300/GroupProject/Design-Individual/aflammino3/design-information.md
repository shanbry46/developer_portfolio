1. When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings.

**The menu class has a &#39;navigate&#39; method that receives the user input from the gui and sets uses it to set the value &#39;selection&#39;, which calls another class**

1. When choosing to _adjust the game settings,_ the player (1) may choose for the game to end after a certain _maximum number of turns_ and (2) may adjust the _number_ of and the _letter points_ for each _letter_ available in the _pool of letters,_ starting with the default matching the [English Scrabble distribution](https://en.wikipedia.org/wiki/Scrabble_letter_distributions) (12 E&#39;s worth 1 point each, 4 D&#39;s worth 2 points each, etc).

**The number of turns is held in int maxTurns, the numbers and letters of points are held in an arrayList of pairs of chars and ints. Max turns are set when the setMaxTurns() function is called, which takes user input and sets the variable. The letters and their values are set by user input in the changeLetterSettings() function.**

1. When choosing to _play a word game,_ a player will:
  1. Be shown a &#39;board&#39; consisting of 4 letters drawn from the pool of available letters.

**The maxTurns and letters variables are initialized by calling getMaxTurns() and getLetters() from the settings class. The board is an arrayList of chars that is generated in the generateBoard() function which takes the letters arrayList as a parameter. The Letters used are randomly selected and removed from the arrayList.**

1.
  1. Be shown a &#39;rack&#39; of 7 letters also drawn from the pool of available letters.

**The rack is an arrayList of chars that is generated in the generateRack() function which takes the letters arrayList as a parameter. Letters used are randomly selected and removed from the arrayList.**

1.
  1. On each turn, up to the _maximum number of turns_ either_:_
    1. Enter a word made up of one or more letters from the player&#39;s rack of letters, and one from the board.  The word must contain only valid letters and may not be repeated within a single game, but does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly).

**Anytime user input is needed, the getInput() function is called, which returns a string of that input. The enterWord() function is called by passing it the current score, the available letters, the board, and the rack. It checks that this is a valid play, then calls related functions.**

or

1.
  1.
    1. Swap 1-7 letters from their rack with letters from the pool of letters.  This is the only time letters are returned to the pool during a game.

**For each letter the user wishes to swap (determined by user input obtained with the getInput() function), swapLetter() is called. The parameters for swapLetter() are the letters, the rack, and the position of the character on the rack to swap. The character in the position called is swapped in place with a random selection from the letters. Characters selected from letters are removed from the letters arrayList.**

1.
  1. After a word is played, that letter on the board will be replaced by a different random letter from the word that was just played.  Example:  If &#39;c&#39; is on the board, and &#39;j&#39;,&#39;a&#39;,&#39;k&#39;,&#39;e&#39;,&#39;t&#39;,&#39;s&#39; are part of the rack, then the player may enter &#39;jackets&#39; as a word, and the &#39;c&#39; will be randomly replaced by the &#39;j&#39;,&#39;a&#39;,&#39;k&#39;,&#39;e&#39;,&#39;t&#39;, or &#39;s&#39; for the next turn on the board.

**This is handled by the swapLetter() function as well, in the same way as above, with the exception that it is called only a single time by the enterWord() function and doesn&#39;t take new user input.**

1.
  1. After a word is played, the tiles used from the rack are replaced from the pool of letters.

**The fillRack() function is called by the enterWord() function which takes the rack and the letters as parameters. Until there are no more available chars in letters, or the rack has 7 chars, it randomly selects characters from letters, removes them from letters, and adds them to the rack.**

1.
  1. After a word is played, the player&#39;s score will increase by the total number of points for the letters in the word, including the letter used from the board. (So &#39;jackets&#39;, if using default values, would score 20 points.)

**The enter word function determines score by checking the value of each char used in the letters arrayList. The total is added to the existing score.**

1.
  1. If the _pool of letters_ is empty and the rack cannot be refilled, the player will score an additional 10 points.

**At the end of the enterWord function, if any of the conditions to end the game exist, the endGame() function is called. When the endGame() function is called it checks if there are less than 7 letters in the rack. If it there are, 10 points is added to the score.**

1.
  1. When the _maximum number of turns_ has been played, or the _pool of letters_ is empty and the rack cannot be refilled, the game will end, and the final score will be displayed before returning to the first menu.

**At the end of the enterWord function, if the rack is less than 7 chars, 10 points is added to the score. The endGame() function displays the score after calculating it, calls related functions, then sends the user back to the menu.**

1. A player may choose to leave a game in progress at any time.  Selecting to play a game from the menu should then return to the game in progress.

**If a player inputs that they would like to exit the game, the save() function is called. The save() function writes relevant info to a file, then returns to the menu. When a player enters a game, the checkSave() function is called- if a saved game exists checkSave() calls loadSave() and loads the relevant info into the variables.**  **Any time a function loads or modifies a file, that function returns true if successful or false if unsuccessful.**

1. When choosing to view statistics, the player may view (1) _game score statistics_, (2) _letter statistics_ or (3) _the word bank_.

**This will be handled entirely by the GUI.**

1. For game score statistics, the player will view the list of scores, in descending order by final game score, displaying:
  1. The final game score
  2. The number of turns in that game
  3. The average score per turn

The player may select any of the game scores to view the settings for that game&#39;s _maximum number of turns, letter distribution,_ and _letter points._

**This info is all written to a file at the end of a game when endGame() calls modifyStats(). The score and number of turns are passed in, the average score per turn is calculated within the function, and the letter settings are retrieved by calling getLetterSettings()**

1. For letter statistics, the player will view the list of letters, in ascending order by number of times played, displaying:
  1. The total number of times that letter has been played in a word
  2. The total number of times that letter has been traded back into the pool
  3. The percentage of times that the letter is used in a word, out of the total number of times it has been drawn

**The words used and letters remaining in the rack are saved when endGame() calls modifyWordsUsed(). These are retrieved with getWordsUsed(), and the displayed stats are calculated by the function**

1. For the word bank, the player will view the list of words used, starting from the most recently played, displaying:
  1.
    1. The word
    2. The number of times the word has been played

**The words used and letters remaining in the rack are saved when endGame() calls modifyWordsUsed(). These are retrieved with getWordsUsed(). The words and times used can be displayed by printing the saved pairs.**

1. The user interface must be intuitive and responsive.

**This will be handled entirely by the GUI.**

1. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.

**This shouldn&#39;t be a problem with the overall design.**

1. For simplicity, you may assume there is a _single system_ running the application.

**This design is made for a single system.**