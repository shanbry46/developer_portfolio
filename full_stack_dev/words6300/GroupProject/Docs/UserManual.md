# Words 6300 - Incredible Idioms

### Application Purpose
This application allows the user to practice scrable on their own. It allows them to change the letter pool to focus on their weakness and allows them to set a max number of turns to try and beat for extra points. While playing a game, the user cannot play the same word more than once and must create a word using only letters from there rack and 1 letter from the board. At any point in the game, they can use one of their turns to swap letters from their rack. 

### Step By Step Instructions
From the main screen, the user can choose to View Statistics, Update Settings, or Play a Game. 
#### Statistics 
1. Within Statistics, the user can click on Game Stats, Word Bank, or Letter Stats. 
2. The corresponding statistics will be displayed on the screen.
3. Within Game Stats, the user can click on any game and view the settings for that game. 
Note: The user my use the phones back button if they wish to navigate back to an earlier screen. 
#### Settings
###### Updated Max Turns
1. Use the number scroll bar on the top of the screen to select the max number of turns allowed per game. 
###### Update Letter Distribution
1. Use the letter scroll bar on the lower left side of the screen to choose the letter you would like to update. 
2. Use the number scroll bar next to the letter scroll bar to select the value you would like the letter to be worth.
3. Use the number scroll bar on the lower right side of the screen to select the number of the chosen letter you would like available in the letter pool. 
4. Click the Update Letter Settings button.

Note: The user my use the phones back button if they wish to navigate back to an earlier screen. 
#### Play Game
###### Play a Word
1. Select 1 or more letters from the rack and 1 letter from the board to create a word.
2. Click the Play Word button. 
It is against the rules to play the same word twice in one game. 
###### Swap Letters
1. Click on Swap Letters button
2. Select the number of letters you would like to swap. 

Note: The user should use the Exit Game button if they wish to save the data for the game they are on whether to resume the game or view the statistics later. If the phone's back button is used, data will not be saved. 
### Inputs
##### Statistics 
Statistics is designed to render information, it does not take input.
##### Settings
- **Max Turns**: The maximum number of turns a game should last. This defaults to 15.
- **Letter**: The letter you would like to update. The settings for each letter defaults to the standard English Scrabble distribution.
- **Value**: The number of points the chosen letter is worth.
- **Number Available**: The number of the chosen letter you would like available in the letter pool.
##### Play Game
- **Letters/Word**: A player will select letters from the board and rack to ultimately provide a word as input to the game. 
- **Number of letters to swap**: The player will select a number of letters from their rack they would like to swap. 


### Output
### Inputs
##### Statistics 
Within the pages of each of the three kinds of statistics, the statistics from previous games will be displayed. Within the game score statistics, the user will have the ability to click on a game and a new screen will be displayed showing the settings used in that game.
##### Settings
- The current selection for max turns will display on the screen and will be updated as the user updates the max turns setting. This will also update in the persistant storage. 
- No visible outputs are expected for letter settings. When the user updates the letter settings, they are updated in the persistant storage that the game uses to read the settings from.
##### Play Game
- When the user chooses to play a game, the game screen will render a board with 4 characters and a rack with 7 characters. 
- When a user selects to swap letters, a message will be displayed with the number of letters that were swapped and the rack will be updated with that number of new letters. 
- When a user clicks on a letter to include in the word they would like to play, the letter will change color and the word will appear at the top of the screen as they select letters. 
- When a user clicks Play Word, if the word is valid, the number of points they earned for the word will be displayed on the screen.The letters played from both the board and rack will be replaced.
- When a user clicks Play Word, if the word is invalid, an error message will be displayed on the screen.
- If a user clicks Reset, the board will be reset to it's default state. 
- If the game completes, the final score will be displayed. 
- If a user clicks Exit game and the game, they will be returned to the home screen. 



### Potential Causes of Error
##### Statistics 
Statistics is designed to render information. It does not take input so no potential errors are anticipated. 
##### Settings
The UI for settings is designed in a way that errors should not occur. Only expected values can be selected and they can never be left blank. 
##### Play Game
The UI for playing a word game is designed in a way to help you avoid errrors. The only potential error you should see while playing a game is that you have played a duplicate word. Each word is only allowed to be played a max of 1 time during a game. 

