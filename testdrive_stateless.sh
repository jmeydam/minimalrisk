echo Working directory:
pwd
# /.../java/minimalrisk

export CLASSPATH=./src:src/gson-2.8.5.jar
echo CLASSPATH:
echo $CLASSPATH
# .:gson-2.8.5.jar

echo Running simulation ...

java MinimalRiskTestdriveStateless

echo Done.

