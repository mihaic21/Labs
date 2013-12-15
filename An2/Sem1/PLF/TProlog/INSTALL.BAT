echo off
if "%1" == "" goto parerror
if "%2" == "" goto parerror
if "%2" == "b:" goto mkflpy
if "%2" == "B:" goto mkflpy
if "%4" == "" goto chkdisk1
goto parerror
:mkflpy
if "%3" == "y" goto mkflpyy
if "%3" == "Y" goto mkflpyy
if "%3" == "n" goto mkflpyn
if "%3" == "N" goto mkflpyn
goto parerror
:mkflpyy
installf a: b: y
:mkflpyn
installf a: b: n
:chkdisk1
if exist %1EXAMPLES.ARC goto mkhdisk
echo Please insert the Turbo Prolog disk labelled INSTALLATION/README
echo in drive %1
pause
gotochkdisk1
:mkhdisk
echo Making directories...
md %2
md %2\ANSWERS
md %2\BGI
md %2\EXAMPLES
md %2\PIE
md %2\PROGRAMS
md %2\REFEXAMP
copy %1\INSTALLH.BAT %2
copy %1\UNPACK.COM %2
cd %2
INSTALLH %1 %2 CHAIN
:parerror
cls
echo            Turbo Prolog version 2.0 Installation Utility.
echo 
echo 
echo 
echo 
echo 
echo 
echo            BEFORE YOU INSTALL PLEASE READ THE README!!!
echo 
echo 
echo 
echo 
echo 
echo 
echo 
echo 
echo 
echo 
echo 
echo Press Ctrl-Break to end or
pause
cls
echo            Turbo Prolog version 2.0 Installation Utility.
echo 
echo 
echo 
echo 
echo 
echo 
echo            CAUTION -- HARD DISK USERS installation must be
echo                       initiated from the hard disk DRIVE
echo 
echo 
echo 
echo 
echo 
echo 
echo 
echo 
echo 
pause
cls
echo            Turbo Prolog version 2.0 Installation Utility
echo 
echo 
echo 
echo                             hard disk
echo                             ---------
echo 
echo      To install Turbo Prolog, enter the following command at the DOS
echo prompt (if your floppy is not in drive A, just substitute your drive
echo wherever you see A:).  Make sure that the hard disk is the DEFAULT
echo DRIVE.  You CANNOT install Turbo Prolog 2.0 if the default
echo drive is A: or B:!
echo 
echo At the DOS prompt for your HARD DISK enter:
echo 
echo      A:INSTALL A: C:\PROLOG
echo 
echo 
echo 
echo 
echo 
echo 
pause
cls
echo                         floppy disk users
echo                         -----------------
echo 
echo 
echo If you're using a two-floppy drive system, you have a choice of copying
echo the reference examples (REFEXAMP.ARC) or not.  If you DO wish to copy
echo them, use this command:
echo 
echo      INSTALL A: B: y
echo 
echo If you DO NOT wish to copy them, then use the following command:
echo 
echo      INSTALL A: B: n
echo 
echo 
echo 
echo 
echo 
echo 
pause
cls
echo                   hard disk and floppy disk users
echo                   -------------------------------
echo 
echo 
echo      The first install parameter (A:) specifies the floppy disk drive
echo that contains your master disk.  The second parameter (C:\PROLOG or
echo B:) specifies the full path name of the drive (and subdirectory if
echo you're a hard disk user) where you want to install Turbo Prolog (unless
echo it conflicts with another directory, we suggest you use \PROLOG).  You
echo should NOT add a trailing "\" to these parameters.
echo 
echo      For two floppy drive users, the third argument (y, or n) specifies
echo whether you want the reference examples to be copied to your disks.
echo The installation program will create the following directories on your
echo hard disk (assuming you are installing in the \PROLOG directory):
echo 
echo   C:\PROLOG            Main Turbo Prolog directory, compiler, utilities
echo   C:\PROLOG\ANSWERS    Answers to exercises in the tutorial
echo   C:\PROLOG\BGI        Borland Graphics Interface programs and utilities
echo   C:\PROLOG\EXAMPLES   Example programs from the Prolog tutorial
echo   C:\PROLOG\PIE        Prolog Inference Engine
echo   C:\PROLOG\PROGRAMS   Various demonstration programs
echo   C:\PROLOG\REFEXAMP   Examples of predicate usage from reference guide
echo  
pause 
cls 
echo      For two-floppy drive users, the installation program will create 
echo the same directories only on different disks. (Except the BGI and 
echo PROGRAMS directories;  the files in these directories are simply 
echo placed in the root directory of the disk they are on). 
echo  
echo      After creating these directories, the program will copy your
echo files from your master disks onto your hard disk (or your floppy disks).
echo because Turbo Prolog comes on more than one disk, during this process 
echo you will be instructed to change disks before continuing.  Just follow 
echo the directions and you will be on your way.  If you have any problems 
echo just check the README file.  It explains in detail, how to use the 
echo install programs and exactly what they do. 
echo  
pause 
cls 
echo      Once again, the following command will install Turbo Prolog 2.0 
echo on your hard disk (make sure your hard disk is the DEFAULT DRIVE!!). 
echo  
echo     A:INSTALL A: C:\PROLOG 
echo  
echo And this next command will install Turbo Prolog on your floppy disks: 
echo  
echo     INSTALL A: B: y   (if you want to copy the reference examples)
echo  
echo     INSTALL A: B: n   (if you don't want the reference examples) 
echo  
echo Where A: designates the floppy drive with your master diskette and 
echo C:\PROLOG or B: is the fill path name of the destination drive and  
echo subdirectory.
:quit
