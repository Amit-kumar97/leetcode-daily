#!/bin/bash

DATE=$(date +%F)
FOLDER="solutions/$DATE"

mkdir -p "$FOLDER"

if [ "$#" -lt 2 ]; then
  echo "❌ Please provide 2 solution files"
  echo "Usage: ./push_solution.sh Problem1.java Problem2.java"
  exit 1
fi

cp "$1" "$FOLDER/"
cp "$2" "$FOLDER/"

git add .
git commit -m "✅ LeetCode $DATE — 2 Java problems solved"
git push origin main

echo "🚀 Done! Solutions pushed for $DATE"