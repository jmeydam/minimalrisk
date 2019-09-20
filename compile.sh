echo Working directory:
pwd

export CLASSPATH=./src:src/gson-2.8.5.jar
echo CLASSPATH:
echo $CLASSPATH

echo Compiling ...

javac src/com/example/minimalrisk/Node.java
javac src/com/example/minimalrisk/Edge.java
javac src/com/example/minimalrisk/DirectedGraph.java
javac src/com/example/minimalrisk/Graph.java
javac src/com/example/minimalrisk/Board.java
javac src/com/example/minimalrisk/MinimalRisk.java
javac src/com/example/minimalrisk/MinimalRiskTestdriveStateful.java
javac src/com/example/minimalrisk/GsonTemplateCountry.java
javac src/com/example/minimalrisk/GsonTemplateContinent.java
javac src/com/example/minimalrisk/GsonTemplateBidirectionalLink.java
javac src/com/example/minimalrisk/GsonTemplateCountryGraph.java
javac src/com/example/minimalrisk/GsonTemplateCountryList.java
javac src/GsonTest.java
javac src/MinimalRiskTestdriveStateless.java

echo Done.

