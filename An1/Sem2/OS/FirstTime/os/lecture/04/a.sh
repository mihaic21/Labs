#!/bin/bash

if [ "$1" = "cat" ]; then
    echo mmm .. .cats ...
elif [ "$1" = "dog" ]; then
    echo woof
else
    if [ "$1" = "goose" ]; then
        echo any golden eggs
    else
        echo some other creature
    fi
fi