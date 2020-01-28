# Isola
This is a recreation of the game Isolation.
The nordic version is called Isola and was the version I played as a kid.
<img src="https://github.com/SvenssonJesper/Isola/blob/master/res/images/gameBox.png" alt="Isola (nordic version of Isolation)" width="250">

## What is Isola?
Isolation was created in the 1970s by a company named [Lakeside][Lside].
The version I played called [Isola][Isola] was released in 1972 by the company 
[Ravensburger Spieleverlag GmbH][GmbH].

### Rules
Isola is a two-player abstract strategy board game. It is played on an 8x6 board which is initially filled with tiles, except at the starting positions of the pieces. Both players have one piece; it is in the middle position of the row closest to his/her side of the board.

<img src="https://github.com/SvenssonJesper/Isola/blob/master/res/images/IsolaBoard.png" alt="Isola game board" width="250">


A move consists of two subsequent actions:

1. Moving one's piece to a neighboring (horizontally, vertically, or diagonally) position that contains a square but not the opponent's piece
2. Removing any tile with no piece on it (this means that starting positions can't be removed).

The player who cannot make any move loses the game.

### My modifications
I decided to recreate the game with the option to play against more players than one. 
Have in mind that this game is designed to be played by only two persons so the addition of more players 
will probably result in one of your friends (or you) getting bullied, sorry! 

---ToDo---

Write about all modifications

## How to run
1. Navigate to the ```run.sh``` script.
2. run it with the command ```./run.sh```.

This will compile the project as ```.Class``` files to the bin folder and then run it.

---ToDo---

Update to .jar file

[lside]: (https://www.lakeside.com/homels)
[GmbH]: (https://www.ravensburger.org/uk/start/index.html)
[Isola]: (https://boardgamegeek.com/boardgameversion/223480/nordic-first-edition)
