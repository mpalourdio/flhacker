#!/bin/bash

sh_path=$(realpath $0)
bin_path=$(dirname $sh_path)
echo Working in $bin_path

rm -f $bin_path/*.so
rm -f $bin_path/*.zip
rm -f $bin_path/flhacker

cp $bin_path/../target/*.so $bin_path
cp $bin_path/../target/flhacker $bin_path

zip -j $bin_path/flhacker.zip $bin_path/*.so $bin_path/flhacker

rm -f $bin_path/*.so
rm -f $bin_path/flhacker

git add $bin_path/flhacker.zip
