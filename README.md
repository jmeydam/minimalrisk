# minimalrisk

Minimal implementation of the game [Risk](https://en.wikipedia.org/wiki/Risk_(game)).

The moves are random possible moves and are not optimized in any way.

The MinimalRisk class provides an API consisting exclusively of static methods that accept 
as their first parameter the current state of the board in form of a JSON string and that return one of the following: 

* A JSON string representing the next state of the board (example: json/country_graph_example.json)
* A JSON string representing a list of countries (example: json/country_list_example.json)
* A boolean

As all methods are static and no object state is maintained between subsequent method calls this class
can serve as the basis for a simple stateless web API. 

The implementation of the graph model and breadth-first search are based on Python code presented in 
[MITx - 6.00.2x](https://www.edx.org/course/6-00-2x-introduction-to-computational-thinking-and-data-science-3).


## Test Drive

```
./compile.sh
./test_gson.sh
./testdrive_stateless.sh
```

