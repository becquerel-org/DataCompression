SOURCETOOLS=$(wildcard tools/*.java)
SOURCEUTIL=$(wildcard util/*.java)
SOURCETEST=$(wildcard tests/*.java)

SOURCE=$(SOURCETOOLS) $(SOURCEUTIL) $(SOURCETEST)

OBJECTS=$(SOURCE:.java=.class)

%.class: %.java
	javac -cp .. $<

all: $(OBJECTS)

.PHONY : doc
doc: 
	javadoc -private  -d doc/ -classpath ..  -subpackages DataCompression.tools DataCompression.tests DataCompression.util DataCompression

.PHONY : clean
clean:
	@rm -f $(OBJECTS)
	@rm -f tools/*.class
	@rm -f tests/*.class
	@rm -f util/*.class
	@rm -f tasks/*.class
	@rm -rf doc/*

