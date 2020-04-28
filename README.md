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
 - Fentre.java :
 - Panneau.java : 

### Dijkstra part
 - Matrice.java :
 - TblDijkstra.java :

### Metro part
 - TblStation.java : Prepare a 'Matrice' with all stations and travels between them.
 - Station.java : Class which represente a Station ( number, name ).
 - Travel.java : Class which represente a Travel ( Start Station, Stop Station, travel time ).
 - MetroLine.java : Class which reprensent a MetroLine ( Station list, Travel list, name ).
 - Metro.java : Class which represente global metro ( MetroLine table )

### Global part
 - Menu.java :
 - Main.java :
 - Makefile :

# Autor
CAMBRESY Florian => Graphic & Global part

LE DENMAT Mickael => Metro part

LAVALLIERE Adrien => Dijkstra part
