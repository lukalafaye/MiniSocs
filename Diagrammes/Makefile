.SUFFIXES: # Delete the default suffixes

PLANTUMLJAR = ./plantuml-gplv2-1.2023.13.jar
PLANTUML = $(wildcard *.pu)
CONVERT_PLANTUML = $(patsubst %.pu,%.svg,$(PLANTUML)) \
		   $(patsubst %.pu,%.png,$(PLANTUML))
CLEAN_PLANTUML   =   $(patsubst %.pu,%.svg,$(PLANTUML)) \
		     $(patsubst %.pu,%.png,$(PLANTUML))

all: $(CONVERT_PLANTUML)

cleanall: clean
	$(VOIRCOMM)rm -f $(CLEAN_PLANTUML)

clean:
	$(VOIRCOMM)rm -f *.bak *~

%.svg: %.pu
	$(VOIRCOMM)java -jar $(PLANTUMLJAR) -quiet -tsvg -charset UTF-8 $<

%.png: %.pu
	$(VOIRCOMM)java -jar $(PLANTUMLJAR) -quiet -tpng -charset UTF-8 $<
