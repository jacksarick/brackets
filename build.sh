#!/bin/sh
alias addmod="cp build/libs/brackets-1.0.jar /Users/sarick/Library/Application\ Support/minecraft/mods/Brackets.jar && echo moved"
./gradlew build && addmod