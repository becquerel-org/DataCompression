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
	@rm -f util/*.class
	@rm -f tools/*.class
	@rm -f tests/*.class
	@rm -f tasks/*.class
	@rm -f $(OBJECTS)
	@rm -rf doc/*

