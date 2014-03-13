#!/bin/bash
#de cate ori e folosit procesul dat ca argument
ps -c | grep $1 | awk '{print$1}' | wc | awk '{print $1}'
