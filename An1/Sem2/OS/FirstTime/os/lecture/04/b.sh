#!/bin/bash

echo $0
echo $*

while [ -n "$1" ]; do
    echo parameter $0 $1
    shift
done