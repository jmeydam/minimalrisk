echo working directory:
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
javac com/example/minimalrisk/MinimalRiskTestdriveStateful.java
javac com/example/minimalrisk/GsonTemplateCountry.java
javac com/example/minimalrisk/GsonTemplateContinent.java
javac com/example/minimalrisk/GsonTemplateBidirectionalLink.java
javac com/example/minimalrisk/GsonTemplateCountryGraph.java
javac com/example/minimalrisk/GsonTemplateCountryList.java
javac GsonTest.java
javac MinimalRiskTestdriveStateless.java

echo Done.

