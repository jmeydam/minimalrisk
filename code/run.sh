echo Working directory:
pwd
# /.../java/minimalrisk

export CLASSPATH=.:gson-2.8.5.jar
echo CLASSPATH:
echo $CLASSPATH
# .:gson-2.8.5.jar

echo Running simulation ...

java com/example/minimalrisk/MinimalRiskTest

echon Done.

