echo Working directory:
pwd

export CLASSPATH=./src:src/gson-2.8.5.jar
echo CLASSPATH:
echo $CLASSPATH

echo Generating documentation ...

javadoc -d doc com.example.minimalrisk

echo Done.

