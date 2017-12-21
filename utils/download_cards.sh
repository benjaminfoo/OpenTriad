#!/bin/bash

#
# Credits to ttadvance.ca for providing the card-images (in red and blue!)
#

#create download location
mkdir cards/

# download every (blue) card from ttadvance
wget -nd -r -P cards/ -A jpg http://ttadvance.ca/viewFF8.php

# download every card again, but the red version
FILES=cards/*
for f in $FILES
do
  echo Downloading ${f##*/} ... 
  #echo "Downloading $f ..."
  wget -P cards/ http://ttadvance.ca/FF8/r${f##*/}
done

# download unwanted files
rm cards/robots.txt.tmp
