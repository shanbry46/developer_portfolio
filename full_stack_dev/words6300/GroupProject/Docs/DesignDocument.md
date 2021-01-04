# Design Document

**Author**: Team 75

## 1 Design Considerations

### 1.1 Assumptions

* Each install of the application will be played by only *one* player. 
* Bonus points for emptying the letter pool before reaching the maximum number of turns will be included in the average score per turn. 
* The setting will default to the english scrabble distribution for letter values and distribution and will be used unless changed by the user before the start of a game. 
* If the player views statistics while in the middle of a game, the active game will not be included in the statistics game score statitics, but will be included in letter and word statistics. 

### 1.2 Constraints

This application is being built for a mobile device. This means the application has to function easily on a small screen, take up the smallest amount of memory on the users device as possible, and take into account all other limitations mobile devices impose. 


### 1.3 System Environment

This system will operate on a mobile device that uses the android operating system. Our application will require "API 23: Android 6.0 (Marshmallow)” as the minimum sdk.

## 2 Architectural Design

### 2.1 Component Diagram

The component diagram is not necessary for this application. It only has one component, the word game. The word game has multiple functions, playing the game, viewing statistics and updating settings; however, they are all part of the word game componet. The settings and statistics can't function without the ability to play the game. 

### 2.2 Deployment Diagram
A deployment diagram is not technically necessary for this implmentation since our application will not be deployed to the app store. This is a simple deployment diagram that could be used if we did deploy the app. 

![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Images/DeploymentDiagram.png)

## 3 Low-Level Design

### 3.1 Class Diagram

![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Images/design-team.png)

### 3.2 Other Diagrams

No other diagrams are needed at this time.

## 4 User Interface Design
### Home Screen
![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Images/homeMockUp.png)
### Play Game
![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Images/playWordMockUp.png)
### Settings
![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Images/settingMockUp.png)
![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Images/maxTurnsMockUp.png)
![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Images/letterPoolMockUp.png)
### Statistics 
![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Images/statsMockUp.png)
![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Images/gameStatsMockUp.png)
![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Images/letterStatsMockUp.png)
![](https://github.gatech.edu/gt-omscs-se-2019fall/6300Fall19Team75/blob/master/GroupProject/Images/wordStatsMockUp.png)

<!--stackedit_data:
eyJoaXN0b3J5IjpbMTYyODQ5NTM1MF19
-->
