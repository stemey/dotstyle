#!/bin/bash

echo "start"

#rm -r ../target/

mkdir -p ../target/example
mkdir -p ../target/tmp


for var in ../example/*
do
	regex='\.\./example/(.*)\.dot$'
	if [[ $var =~ $regex ]]; then
		echo "transform $var"	
		out=${BASH_REMATCH[1]}
	 	dot -Tsvg -o  ../target/example/$out.raw.svg  $var
		java -jar ../target/dotstyle-jar-with-dependencies.jar $var > ../target/tmp/$out.dot
		echo "render $var"	
	 	dot -Tsvg -o ../target/example/$out.svg	 ../target/tmp/$out.dot
	 fi;
done

echo "done"
