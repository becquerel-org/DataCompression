SOURCE=tools/SimpleAnalysis.java tests/TestAnalysis.java util/FileReader.java tools/Entropy.java tests/TestEntropy.java tests/TestEntropyCSV.java tools/MTF.java tests/TestMTFEncode.java tests/TestMTFDecode.java tools/Shannon.java tools/Huffman.java tests/TestShannonEncode.java tests/TestShannonDecode.java
OBJECTS=$(SOURCE:.java=.class)

%.class: %.java
	javac -cp .. $<

all: $(OBJECTS)

.PHONY : doc
doc: 
	javadoc -private  -d doc/ -classpath ..  -subpackages DataCompression.tools DataCompression.tests DataCompression.util DataCompression

.PHONY : clean
clean:
	rm $(OBJECTS)
	rm -r doc/*

