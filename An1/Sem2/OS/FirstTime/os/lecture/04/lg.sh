#!/bin/bash

for w in `find . -type f -exec file {} \;|grep ASCII|sed 's/:.*//'|xargs cat`; do
    len=`echo $w|wc -m`
    len=`expr $len - 1`
    echo $len $w
done