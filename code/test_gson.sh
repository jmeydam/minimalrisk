echo Working directory:
pwd
# /.../java/minimalrisk

export CLASSPATH=.:gson-2.8.5.jar
echo CLASSPATH:
echo $CLASSPATH
# .:gson-2.8.5.jar

echo Running Gson tests ...

echo In package com/example/minimalrisk:
java com/example/minimalrisk/GsonTest
echo In working directory:
java GsonTest

echo Done.

