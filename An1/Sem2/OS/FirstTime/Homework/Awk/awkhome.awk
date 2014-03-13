#Display the number of the lines whose length is at least 10 characters,
#from each file given as argument. Also, display the content of those
#lines, excluding the first 10 characters from each line. After each file
#is analyzed, display the name of the file and the number of lines
#displayed.

BEGIN{
    count=0;
    perfilecount=0;
}

{
    if (FNR != 1){
        lastfilename = FILENAME;
    }
    if (FNR == 1 && NR !=1){
        print "File: " lastfilename;
        print "Number of lines: " perfilecount;
        perfilecount = 0;
    }
    if (length($0) > 10){
        count++;
        perfilecount++;
        print ($0);
    }
}

END{
    print "File: " lastfilename;
    print "Number of lines: " perfilecount;

    print "Total number of lines with more than 10 chars: " count;
}
