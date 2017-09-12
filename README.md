# KREATIZE-code-interview

_This repository features a solver for simple binpacking problems done in approx. 1h30 as a task to join KREATIZE for a summer internship_
_______

### The binpacking solver

  - [The task](#the-task)
  - [The classes used](#the-classes-used)
  - [How to run it](#how-to-use-it)

#### The task
The task is clearly discribed in _KREATIZE Technical Task - Bin Packing Problem.pdf_

This solver contains three classes in _src/binpacking_.

#### The classes used

__SPACE__ - represents the space contained within a bound box. Has the following methods : 
  - a constructor
  - three getters (`getX()`, `getY()`, `getZ()`)
  - `fit(int[])` : tests if a given volume can fit into the space
  - `bestFit(int[])` : finds the best rotation for a given volume (to maximise the number of this volume that can be fitted in the space)
			
__PROBLEM__ - represents an optimization problem, given the space contained within the machine bound box and the dimensions of the part. Has the following methods : 
  - a constructor
  - two getters (`getS()`, `getPart()`)
  - `solve()` : solve the problem
			
__PARSER__ - reads the data file, and translates it into a list of Problem. Has the following methods : 
   - a constructor
   - `solve()` : solve the problems of the instance

### HOW TO USE IT  

binpacking/Parser.java feaures a main in which you can launch the solver for istances stored in a .csv file. 
A .csv file is provided as an example : data.csv

```java
	public static void main(String[] args) {
		Parser p = new Parser("data.csv");
		p.solve();
	}
```