all:
	cd src/;javac com/img2txt/Main.java com/img2txt/Gui.java
	cd src/; jar cvfm ../img2txt.jar manifest.txt assets/ com/img2txt/*.class
run:
	java -jar img2txt.jar
