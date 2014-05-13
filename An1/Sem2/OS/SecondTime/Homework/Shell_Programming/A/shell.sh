#!/bin/bash

while [ 1 ]; do
    echo "Insert word: "
    read WORD

    if [ $WORD == "exit" ]; then
        exit
    else
        for fileName in $*; do
            if [ $fileName != $0 ]; then
                sed "s/\<${WORD}\>//g" $fileName > newfile.txt
                mv newfile.txt $fileName
            fi
        done
    fi
done
