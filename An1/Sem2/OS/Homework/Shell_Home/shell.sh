#!/bin/bash
#It give a list of filenames and a folder. Print for each filename all
#subfolders of the folder, where the file is found. The subfolder names
#should be sorted in descending order of the file sizes (the file can
#have various sizes in different subfolders).

if [ "$1" == "-h" ]
    then
        echo "Usage: <folder_name> <filename> <filename>..."
        exit
fi

i=1
for arg in $@
do
    if [ $i != 1 ]
        then
            echo $arg":"
	        find $1 -name $arg -exec bash -c "ls -la {} | awk '{print \$5 \" \" \$9}'" \; | sort -n -r | sed "s/.*$1\/\(.*\)\/$arg/\1/g"
    fi
    i=$i+1
done
