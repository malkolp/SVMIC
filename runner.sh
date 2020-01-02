#!/usr/bin/env bash

files = "$@";

for i in files;
do
    java -jar scivmic.jar <<< "$i"
done