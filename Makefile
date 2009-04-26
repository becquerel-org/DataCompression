SOURCE=tools/SimpleAnalysis.java tests/TestAnalysis.java util/FileReader.java
OBJECTS=$(SOURCE:.java=.class)

%.class: %.java $(SOURCE)
	javac -cp .. $<

all: $(OBJECTS)

.PHONY : doc
doc: 
	javadoc -private  -d doc/ -classpath ..  -subpackages DataCompression.tools DataCompression.tests Datacompression.util DataCompression

.PHONY : clean
clean:
	rm $(OBJECTS)
	rm -r doc/*

