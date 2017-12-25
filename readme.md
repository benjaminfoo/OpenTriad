<img src="/docs/banner.png">  

# Welcome to OpenTriad
This project is a reimplementation of the great card game called "Triple Triad" originally developed by Squaresoft for the Playstation 1 title "Final Fantasy 8". 

## Architecture
The project is separated into the following subprojects:
 * engine - Contains the core components and mechanics for the actual game. 
 * terminalApp - Contains the terminal implementation (used for implementation purposes, but also for testing)
 * fxApp - Contains the JavaFX implementation (because why not?)
 * android - Contains the refactored android implementation

### Engine
The projects core is the 'engine'-submodule. It consists of vaarious interfaces and default implementations.
The engine itself isn't runnable (well the tests are) instead there are different implementations provided.

Model:
 * Card - A pojo which contains a name and four values (top, right, bottom, left).
 * Deck - Model which contains five cards.
 * Player - Simple POJO which contains a name.
 * Battlefield - Contains a two-dimensional array of cards and a "GameFinish"-flag.
 
Controller: 
 * BattlefieldController - Contains the actual game loop
 * PlayerController - Contains the Player-Entity and the MoveDelegate
 
View: 
 * No view, these are implemented in the corresponding submodules (terminalApp, fxApp, android)

Interfaces:
* CardChooser - Callbacks in order to choose cards before the actual game happens.
* BattlefieldDelegate - Callbacks for events during the game.
* MoveDelegate - Callbacks for (engine-events) in order to provide a runtime-independent player-turn. This is needed for telling the engine which card a player sets on the battlefield.

Implementations
* PreselectedCardChooser - Simply returns a list with five test cards.
* DefaultBattlefieldDelegate - Dummy implementation (for testing purposes) - doesnt actually do anything
* DefaultMoveDelegate - Also just a dummy implementation - mimics turns of a player and provides information about what card has been set on the battlefield.


# Screenshots
## Screenshots - Terminal 
<img src="/docs/ingame_term.png" width="275"> 

## Screenshots - Android
<img src="/docs/main.jpg" width="275">    <img src="/docs/ingame.jpg" width="275">    <img src="/docs/menu.jpg" width="275">

## Screenshots - JavaFX
 * Coming soon...
  
# Development 

## How to build & deploy
 * Clone this repository: git clone https://github.com/benjaminfoo/OpenTriad.git
 * Open Android Studio, Select "Open Project" and within the FileDialog, choose the build.gradle file at the project-root.
   * Let gradle / android studio install missing packages / API levels
 * Install project on emulator or mobile device
 * Thats it!

## How to add custom cards
 1. Add an entry to the file '/opentriad/engine/src/main/resources/cards.json' like 
 ```
    {
       "name": "MyCustomCard",
       "north": "1",
       "west": "3",
       "east": "3",
       "south": "7"
     }
 ```
 2. Within '/OpenTriad/app/src/main/assets/cards/', create an image (jpg!) if your custom card. The name must be the same as you've used in the json file (e.g. for this card, an image called "MyCustomCard.jpg" has to be saved.)
 3. Have fun playing with your custom card!
 
## Used Software / Libraries
 * Java
 * Gradle 
 * Gson
 * JUnit
 
## Note
Final Fantasy, Triple Triad, and all subject material are property of Square-Enix. I am not the owner / maker / author of the provided images / names or trademarks used within this project.
