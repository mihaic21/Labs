#!/bin/bash

k=0
while [ $k -lt 10 ]; do
    if [ `expr $k % 2` -eq 0 ]; then
        k=`expr $k + 1`
        continue
    fi
    echo $k
    k=`expr $k + 1`
done
