#!/bin/bash

#for p in $*; do
#    echo parameter $0 $p
#done

#for a in cat dog goose mouse; do
#    echo $a
#done

for f in *.sh; do
    echo $f
    file $f
done