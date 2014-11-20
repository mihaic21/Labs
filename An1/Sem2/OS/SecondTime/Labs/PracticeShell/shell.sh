#!/bin/bash

#read words until one appears 3 times

list1=()
list2=()

while [ 1 ]; do

    echo Insert word
    read word

    app1=$(echo $list2 | grep -o "\<${word}\>" | wc -w)
    if [ $app1 -gt 0 ]; then
        exit
    else
        app2=$(echo $list1 | grep -o "\<${word}\>" | wc -w)
        if [ $app2 -gt 0 ]; then
            list2+=$word
            list2+=" "
        else
            list1+=$word
            list1+=" "
        fi
    fi
done
