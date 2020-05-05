package = commons-lang3-3.9/commons-lang3-3.9.jar

tools = Metro.java MetroLine.java Travel.java Station.java Menu.java
dijkstra = Matrice.java TblDijkstra.java TblStations.java
graphics = Fenetre.java Panneau.java
other = Main.java Makefile

dir = ../Parisian_metro_project
tar = ../Le_Denmat_Cambresy_Lavalliere
rapport = ../Compte_rendu_LE_DENMAT_CAMBRESY_LAVALLIERE.pdf

program = $(tools) $(dijkstra) $(graphics)

run : compile
	java -cp  $(package):. Main

run2 : compile
	java -cp  $(package):. Main random

compile : clean
	javac -cp $(package) Main.java $(program)


cleanArchive:
	rm -f $(tar).tar

	rm -f $(dir)/*
	rm -df $(dir)

archive: cleanArchive
	mkdir $(dir)
	cp -u  ../metroL.txt ../README.md $(rapport) $(program) $(other) $(dir)/

	tar -cvf $(tar).tar $(dir)/


cleanFile :
	rm -f ../UserTravel.txt
	rm -f ../save.txt

clean : cleanFile
	rm -f *.class
