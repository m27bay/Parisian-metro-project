#!/bin/sh

file="../save.txt"
rm -f $file
touch $file

if [ $# -eq 0 ]
then
  echo "Usage : number_test"
else

  #
  if [ $1 -ge 0 ]
  then

    #
    for i in `seq 0 $1`
    do
      java -cp commons-lang3-3.9/commons-lang3-3.9.jar:. Main random
      cat ../UserTravel.txt >> $file
      echo "" >> $file
      echo "" >> $file
    done
    sublime $file

  #
  else
    echo "Usage : number_test ( <= 0 )"

  fi
fi
