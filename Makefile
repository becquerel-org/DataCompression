SOURCE=tools/SimpleAnalysis.java tests/TestAnalysis.java util/FileReader.java
OBJECTS=$(SOURCE:.java=.class)

%.class: %.java $(SOURCE)
	javac -cp .. $<

all: $(OBJECTS)

clean:
	rm $(OBJECTS)

