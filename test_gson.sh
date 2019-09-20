echo Working directory:
pwd

export CLASSPATH=./src:src/gson-2.8.5.jar
echo CLASSPATH:
echo $CLASSPATH

echo Running Gson tests ...

java GsonTest

echo Done.

