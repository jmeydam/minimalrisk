echo Working directory:
pwd
# /.../java/minimalrisk

export CLASSPATH=.:gson-2.8.5.jar
echo CLASSPATH:
echo $CLASSPATH
# .:gson-2.8.5.jar

echo Running Gson tests ...

java GsonTest

echo Done.

