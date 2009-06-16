#SOURCE=tools/SimpleAnalysis.java tests/TestAnalysis.java util/FileReader.java tools/Entropy.java tests/TestEntropy.java tests/TestEntropyCSV.java tools/MTF.java tests/TestMTFEncode.java tests/TestMTFDecode.java tools/Shannon.java tools/Huffman.java tests/TestShannonEncode.java tests/TestShannonDecode.java tools/BurrowsWheeler.java tests/TestBurrowsWheelerEncode.java tests/TestBurrowsWheelerDecode.java tools/LZ77.java tests/TestLZ77Encode.java tests/TestLZ77Decode.java

SOURCETOOLS=$(wildcard tools/*.java)
SOURCEUTIL=$(wildcard util/*.java)
SOURCETEST=$(wildcard tests/*.java)
SOURCETASKS=$(wildcard tasks/*.java)


SOURCE=$(SOURCETOOLS) $(SOURCEUTIL) $(SOURCETEST) $(SOURCETASKS)

OBJECTS=$(SOURCE:.java=.class)

%.class: %.java
	javac -cp .. $<

all: $(OBJECTS)

.PHONY : doc
doc: 
	javadoc -private  -d doc/ -classpath ..  -subpackages DataCompression.tools DataCompression.tests DataCompression.util DataCompression

.PHONY : clean
clean:
	rm -f $(OBJECTS)
	rm -rf doc/*

