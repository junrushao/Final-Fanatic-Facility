#!/bin/bash

gcc input.c
time spim -sdata 16777216 -ldata 167772160 -f test.s > me.out
./a.out > std.out
gedit me.out std.out&
python removeExtraLines.py
diff me.out std.out
