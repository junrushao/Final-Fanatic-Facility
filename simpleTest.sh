#!/bin/bash

gcc input.c
time spim -f test.s > me.out
./a.out > std.out
gedit me.out std.out&
diff me.out std.out
