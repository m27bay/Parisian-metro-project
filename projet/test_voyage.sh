#!/bin/sh

rm -f ../save.txt
touch ../save.txt
"save" > ../save.txt

for i in `seq 0 10`
do
  java -cp commons-lang3-3.9/commons-lang3-3.9.jar:. Main random
  cat ../UserTravel.txt >> ../save.txt
  echo "" >> ../save.txt
  echo "" >> ../save.txt
done

sublime ../save.txt
