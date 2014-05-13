#!/bin/bash

while  [ "$1" ]; do
    file=$1
    shift
    size=$1
    shift
    
    realSize=$(ls -l | grep "$file" | awk '{print $5}')

    if [ $realSize -eq $size ]; then
        echo "You were right! The file $file has the size $size"
    else
        echo "You were wrong! The file $file has the size $realSize, not $size"
    fi
done
