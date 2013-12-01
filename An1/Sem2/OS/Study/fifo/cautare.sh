#!/bin/bash
ps -c | grep $1 | awk '{print$1}' | wc | awk '{print $1}'
