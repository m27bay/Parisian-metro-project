package1 = commons-lang3-3.9/commons-lang3-3.9.jar

tools = Metro.java MetroLine.java Travel.java Station.java Menu.java
dijkstra = Matrice.java TblDijkstra.java TblStations.java
graphics = Fenetre.java Panneau.java

program = $(tools) $(dijkstra) $(graphics)

run : compile
	java -cp  $(package1):. Main

run2 : compile
	java -cp  $(package1):. Main random

compile : clean
	javac -cp $(package1) Main.java $(program)

cleanFile :
	rm -f ../UserTravel.txt
	rm -f ../save.txt

clean : cleanFile
	rm -f *.class
