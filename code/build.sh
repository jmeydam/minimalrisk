pwd
# /.../java/minimalrisk

export CLASSPATH=.:gson-2.8.5.jar
echo $CLASSPATH
# .:gson-2.8.5.jar

javac com/example/minimalrisk/GsonTemplateCountry.java
javac com/example/minimalrisk/GsonTemplateContinent.java
javac com/example/minimalrisk/GsonTemplateBidirectionalLink.java
javac com/example/minimalrisk/GsonTemplate.java

javac com/example/minimalrisk/GsonTest.java
java com/example/minimalrisk/GsonTest

javac com/example/minimalrisk/Node.java
javac com/example/minimalrisk/Edge.java
javac com/example/minimalrisk/DirectedGraph.java
javac com/example/minimalrisk/Graph.java
javac com/example/minimalrisk/Board.java
javac com/example/minimalrisk/MinimalRisk.java

