#!/bin/sh
 

# print to screen
echo "Hello, world!!"

# The variable $1 contains the first argument passed to the shell scripT
echo "Hello, world $FIRST_ARG !!"
FIRST_ARG="$1"   

# When assigning literal strings (rather than variables containing strings) to a variable, 
# you must surround any spaces with quotation marks
STRING1="THIS IS A STRING!!"
echo $STRING1