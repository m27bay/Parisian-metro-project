run: compile
	java Main metro.txt

compile:
	javac *.java

clean:
	rm -f *.class
	ls
