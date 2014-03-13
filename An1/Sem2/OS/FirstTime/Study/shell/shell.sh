#!/bin/bash
aici='ls | wc -l'
acolo='ls $1 | wc -l'
if [ "$aici" = "$acolo" ]
    then echo directoarele "pwd" si $1 coincid
    else echo directoarele "pwd" si $1 sunt diferite
fi
