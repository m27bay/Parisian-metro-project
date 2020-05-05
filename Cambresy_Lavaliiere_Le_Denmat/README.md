# Metro project in Java
Backup directory
L2 S4 IN403 Algo des graphes

# Desciption
You can find here the metro project in java
Implementation of dijkstra algorithm with the parisian metro

## Contains
 - README.md
 - metroL.txt : data file with metro information
 - UserTravel.txt : data file with user travel information
 - projet/ : with all implementations

### Projet/ contains
 - commons-lang3-3.9/ : extern library used
 - out/ : with all .class
 - picture/ : with picture used
 
### Graphic part
 - Fentre.java : Create a window in java.
 - Panneau.java : Concern everythings that will happend inside the window.

### Dijkstra part
 - Matrice.java : Tools to manipulate matrix
 - TblDijkstra.java : Class which create an array with the value of time between stations. These
   values are set for the shortest time between two stations requested.

### Metro part
 - TblStation.java : Prepare a 'Matrice' with all stations and travels between them.
 - Station.java : Class which represente a Station ( number, name ).
 - Travel.java : Class which represente a Travel ( Start Station, Stop Station, travel time ).
 - MetroLine.java : Class which reprensent a MetroLine ( Station list, Travel list, name ).
 - Metro.java : Class which represente global metro ( MetroLine table )

### Global part
 - Menu.java : This is the menu, where the user can make choices about the travel.
 - Main.java : This is the main class of the program.
 - Makefile : This is where are all the commands are to compile, execute or delete class of the program.

# Autor
CAMBRESY Florian => Graphic & Global part

LE DENMAT Mickael => Metro part

LAVALLIERE Adrien => Dijkstra part
