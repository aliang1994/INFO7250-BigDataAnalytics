#!/bin/sh

FILEPATH="/Users/aliceliang/CS/7250/HW2/NYSE/NYSE_daily_prices_*.csv"
for f in $FILEPATH;
    do
        echo $f;
        mongoimport --db homework2 --collection nyse --type csv --headerline --file $f;
    done