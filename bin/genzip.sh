#!/bin/bash

rm -f bin/*.so
rm -f bin/*.zip
rm -f bin/flhacker

cp target/*.so bin
cp target/flhacker bin

zip -j bin/flhacker.zip bin/*.so bin/flhacker

rm -f bin/*.so
rm -f bin/flhacker

git add bin/flhacker.zip
