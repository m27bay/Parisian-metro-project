package1 = "commons-lang3-3.9/commons-lang3-3.9.jar"

run : compile
	java -cp  $(package1):. main_dikjstra Matrice tbl_dikjstra

compile:
	javac -cp $(package1) main_dikjstra.java Matrice.java tbl_dikjstra.java

clean :
	rm -f *.class
