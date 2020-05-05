package = commons-lang3-3.9/commons-lang3-3.9.jar

tools = Metro.java MetroLine.java Travel.java Station.java Menu.java
dijkstra = Matrice.java TblDijkstra.java TblStations.java
graphics = Fenetre.java Panneau.java
other = Main.java Makefile

program = $(tools) $(dijkstra) $(graphics)

run : compile
	java -cp  $(package):. Main

run2 : compile
	java -cp  $(package):. Main random

compile : clean
	javac -cp $(package) Main.java $(program)

cleanFile :
	rm -f ../UserTravel.txt
	rm -f ../save.txt

clean : cleanFile
	rm -f *.class
