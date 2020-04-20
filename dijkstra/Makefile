package1 = commons-lang3-3.9/commons-lang3-3.9.jar

run : compile
	java -cp  $(package1):. Main_dikjstra

compile : clean
	javac -cp $(package1) *.java

clean :
	rm -f *.class
