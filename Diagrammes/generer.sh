#! /bin/bash

if [ $# -gt 0 ]; then
    fichiers=$@
else
    fichiers="*.pu"
fi

PLANTUMLJAR=plantuml-gplv2-1.2023.13.jar

for f in $fichiers; do
    echo "fichier PlantUML $f"
    java -jar $PLANTUMLJAR -tsvg -charset UTF-8 $f
    java -jar $PLANTUMLJAR -tpng -charset UTF-8 $f
done

exit 0
