### Deliverable 1 - Team 75 - Angela Deters, Adam Flammino, Shannon Bryant

#### Design 1 - Angela Deters
##### ![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Design-Individual/adeters3/Angela_Deters_Assignment_5_Design.png)

#### Pros
##### 1) Simplistic design with good handling of past game statistics and settings given her Active Game and Completed game classes.
##### 2) Covered every piece of the requirements.
##### 3) We liked the setup of the Statistics classes including: the superclass Statistics and the three subclasses, Game Statistics, Word Bank, and Letter Statistics.
##### 4) The relationship between the Letter Pool and Letter object were defined correctly as an aggregation.
##### 5) The diagram displayed a correct implementation for how to handle the refill letters and swap letters methods by using an array that was generated after the user had set their preferences for the game.


#### Cons
##### 1) There could be a simpler way to manage the Letter pool class.
##### 2) The relationship between Word Bank and Word was incorrect, calling Word as the superclass.

#### Design 2 - Adam Flammino
##### ![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Design-Individual/aflammino3/Adam_Flammino_Assignment_5_Design.png)


#### Pros
##### 1) Easy to understand given the four classes used to handle the game.
##### 2) The statistics were stored in a flat file rather than an object to reference.
##### 3) The controller had every method to run the game in one class.

##### Cons
##### 1) The diagram didn't have classes for the Statistics and Letter Pool which made the diagram difficult to read. Given that they are two separate entities with attributes, it makes sense to have these represented as their own objects.
##### 2) Many of the classes that the other diagrams had were handled by data structures in the game class.  This would have been much more difficult to implement given that the data structures would have to be updated frequently for changes.
##### 3) The menu class is not necessary as the UI does not need to be represented in UML.

#### Design 3 - Shannon Bryant
##### ![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Design-Individual/sbryant46/Shannon_Bryant_Assignment_5_Design.png)

#### Pros
##### 1) The diagram included the Random utility class.
##### 2) The idea to use Letter objects that make up the Letter Pool was a unique idea.  This would be a clean approach to handling the "bag" of letters.
##### 3) The Statistics classes were well managed and broken up by Letter, Game, and Word Statistics.
##### 4) The concept of physically moving the Letter objects outside of the Letter Pool to the Rack and then to the Board was a good idea for handling the movement of the letters from the Letter Pool to the wordbank.

#### Cons
##### 1) Request did not need to be outlined for every relationship.
##### 2) The player would essentially be the main class, not it's own class.
##### 3) The game is single player and the diagram denotes 2 to 4 players.
##### 4) There doesn't need to be a direct relationship for every class.  For example, since Player already had a relationship with Word Game and Word Game already had a relationship with Game Statistics, Player does not also need a defined relationship with Game Statistics.
##### 5) The scoring was not as intuitive.

#### Team Design
##### ![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Images/TeamDesign.png)

#### Commonalities

##### 1) Every diagram included a central Word Game class and Settings class to handle game creation and game settings.
##### 2) Shannon and Angela's diagrams included a superclass for the Statistics and subclasses for the types of Statistics.
##### 3) Adam and Angela's approach to handling the Letter Pool, Rack, and Board was similar by holding the the values in data structures.
##### 4) Shannon and Angela's diagrams both had a Letter Pool and Letter object to help instantiate the pool of letters.

#### Differences

##### 1) Each diagram handled an active game differently.  Shannon instatiated the active game via the Word Game, Board and Rack classes and Adam instatiated an active game via the Game class.  We ultimately went with an interface called WordGame to instantiate the game.
##### 2) Adam handled the Statistics differently from our Team Design by generating a text file to hold the statistics and referencing the text file.
##### 3) Adam's diagram did not explicity call out Settings or the Letter Pool as their own classes and handled them as data structures.

#### Design Justifications

##### As a team, we represented the WordGame class as an interface with two classes that implement WordGame including: ActiveGame and CompletedGame.  Additionally, we have a 1 to 1 relationship defined with GameSettings to manage the default and or user set preferences for that game.  The GameSettings class will generate a LetterPool with either the default or user preferences.  The LetterPool has a 0 to many aggregate relationship with the Letter class where the value and numAvailable attributes are set.  The ActiveGame class also has a 1 to 1 relationship with the LetterPool class to generate a character type array called remainingLetters of the count of letters available.  For example, if the numAvailable attribute for a Letter is 4 then the remainingLetters array will contain 4 'a' characters.  This will allow us to use the remainingLetters data structures as a "bag" of letters. 
##### Our handling of the board and rack will occur in the ActiveGame class.  Both of these values are character arrays and will pull or push letters from the remainingLetters array.  For the words played, there is a class called WordStatistics with a 0 to many aggregate relationship with a Word class.  The WordStatistics class will be used to hold all of the words played from the ActiveGame class and to hold occurences of that word in the Word class.
##### To draft statistics there is a parent class called Statistics that has three subclasses including: GameScoreStatistics, LetterStatistics, and WordStatistics.  The GameScoreStatistics, there is a 1 to 1 relationship with the CompletedGame class, for the LetterStatistics class, there is a 1 to 1 relationship with the LetterPool class, and the WordStatistics class, has a 1 to many relationship with the ActiveGame class because the WordStatistics class will hold information for multiple active games.  Each relationship allows the program to aggregate statistics for the game.  Finally, there is a utility class called Random that is part of the standard Java library and will be used to randomly select letters from the "bag" of remainingLetters.

#### Summary

##### Since we all approached the word game design differently, it was interesting hearing how each team member approached a certain problem.  For example, Adam decided to store the statistics in a text file, Angela and I chose to store this information in classes.  From each individiual design we took information and baked it into our final design, such as the "bag" of letter concept, where we have a data structure of letters that has the total count of letters available. A few design lessons learned include: learning the difference between a composition and aggregation relationship, understanding how an interface class would act as the primary class, and understanding cardinality/modality and how it applied to our class relationships.  Overall, our team had efficient meetings, good discussion, and worked well together.    
