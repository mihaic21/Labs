#!/bin/bash

file=$1
word=$2

grep "\<${word}\>" $file | wc -l
