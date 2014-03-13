#!/bin/bash

echo $0
echo ${0##*/}

while true; do
    read v
    if [ "$v" = "quit" ]; then
        break
    else
        echo got command $v
    fi
done