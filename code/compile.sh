echo Working directory:
pwd
# /.../java/minimalrisk

export CLASSPATH=.:gson-2.8.5.jar
echo CLASSPATH:
echo $CLASSPATH
# .:gson-2.8.5.jar

echo Compiling ...

javac com/example/minimalrisk/Node.java
javac com/example/minimalrisk/Edge.java
javac com/example/minimalrisk/DirectedGraph.java
javac com/example/minimalrisk/Graph.java
javac com/example/minimalrisk/Board.java
javac com/example/minimalrisk/MinimalRisk.java
javac com/example/minimalrisk/MinimalRiskTest.java
javac com/example/minimalrisk/GsonTemplateCountry.java
javac com/example/minimalrisk/GsonTemplateContinent.java
javac com/example/minimalrisk/GsonTemplateBidirectionalLink.java
javac com/example/minimalrisk/GsonTemplate.java
javac com/example/minimalrisk/GsonTest.java
javac GsonTest.java

echo Done.
