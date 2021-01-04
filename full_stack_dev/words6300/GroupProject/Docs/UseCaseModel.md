# Use Case Model

**Author**: Team 75

## 1 Use Case Diagram

![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Images/Incredible%20Idioms%20-%20Deliverable%202%20(1).png)

## 2 Use Case Descriptions

#### 1) Update Settings
**Requirements:** The requirement for the update settings use case is to update the letter count/value and the maximum turns.  
**Pre-Conditions:** The application must be started on the users device.  
**Post-Conditions:** The settings must be correct after submission of user preferences.  
**Scenarios:**
- The user opts to update the letter count.  This will allow the user to select how many of each letter they wish to be available in the pool of letters.
- The user opts to update the maximum turns.  This will allow the user to select how many turns they want to be available for that game.
- The user opts to update the letter value. This will allow the user to update how much each letter is worth when scoring submitted words.

#### 2) Play Word
**Requirements:** This requirement allows the player to submit a word for review and submission for points.  
**Pre-Conditions:** The application must have an active game running and the user must have letters on their rack to submit a word.  
**Post-Conditions:** The system must evaluate the word for validity and if valid calculate the points for the word and count it towards the score.  
**Scenarios:**
- The user will submit a word from their rack of available letters.  The system will calculate and score the word score total for that word.

#### 3) Swap Letters
**Requirements:** This requirement allows the player to use one of their turns to swap one or more letters from their rack out with those available in the letter pool.  
**Pre-Conditions:** The application must have an active game running and the user must have letters available to swap on their rack.  
**Post-Conditions:** The rack must have one or more swapped letters on it depending on how many the user selected to swap.  The system must update quantities available for each letter when the swap occurs.  
**Scenarios:**
- The user will opt to swap one or more letters.  They will select a number of letters to swap and the system will randomly pull that amount of letters from the rack and replace them with letters from the bag.

#### 4) View Statistics
**Requirements:** This requirement allows the player to view the game statistics, letter statistics, and word statistics.  
**Pre-Conditions:** The application must have an active game or completed game(s).  
**Post-Conditions:** The player has viewed one or more groups of statistics.  
**Scenarios:**
- The user will opt to view the game statistics and the system will present the player with the current game's statistics including: maximum turns for a specified game, letter distribution, letter points, average score per turn, turns in a specified game, and game final scores.  
- The user will opt to view the word statistics and the system will present them with the words that were played and the number of times the word was played.
- The user will opt to view the letter statistics and the system will present them with the total number of times that letter has been player in a word and the number of times that letter has been traded into the pool.

