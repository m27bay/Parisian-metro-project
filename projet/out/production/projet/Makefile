package1 = commons-lang3-3.9/commons-lang3-3.9.jar
program = Metro.java TblStations.java Matrice.java TblDijkstra.java
dataFile = ../metroL.txt

run : compile
	java -cp  $(package1):. Main $(dataFile)

compile : clean
	javac -cp $(package1) Main.java $(program)

clean :
	rm -f *.class
