# use MINGW64 or other LINUX shell, in this directory, input: bash count_lines.sh
find ../ "(" -name "*.java" -or -name "pom.xml" -or -name "*.yml" -or -name "*.application" ")" -print | xargs wc -l