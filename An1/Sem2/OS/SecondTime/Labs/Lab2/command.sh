#!/bin/bash
grep "^aa" passwd | awk -F: '{print $5}' | sed "s/r/-/g"
