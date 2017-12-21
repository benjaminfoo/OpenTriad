# OpenTriad

Welcome to Open Triad! This project is a reimplementation of the fabulous card game called "Triple Triad" originally developed by Square for the Playstation 1 title "Final Fantasy 8". 

OpenTriad is enterily build upon the android-platform - no 3rd-party libraries are required to build, deploy and run the project.

This project started out as a one-man weekend project so don't except a complete game, its just a proof-of-concept but I'd love to see a complete Triple Triad clone in the future.

If you're interested in contributing to this project you should take a look into CONTRIBUTOR-README file.
Contributors, pull requests or mere ideas are welcome - happy hacking!

## Note 
Final Fantasy, Triple Triad, and all subject material including card images and names are property of Square-Enix.
I am not the owner / maker / author of the provided images / names or trademarks used within this project.

## Screenshots
<img src="/docs/main.jpg" width="275">    <img src="/docs/ingame.jpg" width="275">    <img src="/docs/menu.jpg" width="275">

## Features
 - Basic TripleTriad reimplementation
 - Userprofile-System
 - Score-System
 
# How to build & deploy
 * Clone this repository: git clone https://github.com/benjaminfoo/OpenTriad.git
 * Open Android Studio, Select "Open Project" and within the FileDialog, choose the build.gradle file at the project-root.
   * Let gradle / android studio install missing packages / API levels
 * Install project on emulator or mobile device
 * Thats it!
 
# How to add custom cards
 1. Add an entry to the file '/OpenTriad/app/src/main/assets/cards.json' like 
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
 
## TODO
- Implement deck management (we don't need real decks, just selecting 5 'active' cards should be enough)
 - Implement the rest of the cards and a solution to switch between background colors (red = enemy card, blue = player card)
 - Implement animations / mimic UI of the original game
 - Implement original ruleset (like same, open, etc..)
 - Replace ripped content with open source / free to use content
 - Audio FX / Music
 - Implement multiplayer (bluetooth, wlan, maybe over internet?)

### Happy hacking!

## Note
Images are provided by: 
http://ttadvance.ca/

Values for these cards are provided by:
http://www.nslists.com/finfa8tt.htm