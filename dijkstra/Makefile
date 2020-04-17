package1 = commons-lang3-3.9/commons-lang3-3.9.jar

run : compile
	java -cp  $(package1):. Main_dikjstra

run_metro : compile
	java -cp  $(package1):. Main_metro

compile : clean
	mkdir out
	javac -cp $(package1) *.java

clean :
	rm -f *.class
	rm -f  out/*
	rm -df out
