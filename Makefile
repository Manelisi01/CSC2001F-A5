# dijkstra algorithm program makefile
# Manelisi Luthuli
# 05 May 2023

JAVAC=/usr/bin/javac



.PHONY: clean dataset run
.SUFFIXES: .java .class

DATA=data
SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR) -cp $(BINDIR) $<


CLASSES=GFGRandomGraph Edge Vertex Path \
	GraphException Graph GraphExperiment\

default: $(CLASSES:%=$(BINDIR)/%.class)

clean:
	rm $(BINDIR)/*.class

cleandata:
	rm $(DATA)/*		

run: $(CLASSES:%=$(BINDIR)/%.class)
	java -cp bin GraphExperiment
