# CSC2001F-A5
CSC2001F 2023 Assignment 5
Instructions

The goal of this assignment is to programmatically compare the performance of Dijkstra's shortest paths algorithm (as per the course slides) with the theoretical performance bounds.
Dataset Generation and Basic Operation

Write an application to generate data, insert the data into a graph and measure the performance experimentally.  Use the attached code (from the textbook) as a starting point (or write everything from scratch or use standard code from elsewhere).

For a single test, you need to assume values for |V| (number of vertices) and |E| (number of edges), then generate a random dataset (in the same format as Graph1.txt).  Save the dataset to a file on disk (so the experiment can be validated later).  Then, load the data into a graph and run Dijkstra's algorithm to determine shortest paths.

For multiple tests, you will need to repeat these operations for different values of |V| and |E|,   You can assume the first vertex in each dataset is the source; or you can loop over all source vertices and take averages.

You should be able to invoke your application using "make run" or a command such as

java -cp bin GraphExperiment

Your program should take in no command-line parameters and use no interactive user input.
Instrumentation

Add additional code to the program to discretely count the number of times you do vertex-processing and edge-processing operations in the code.  This is called instrumentation.  There are 3 basic steps.

First, create a variable/object (e.g., vCount=0) somewhere in the code to track the counter; maybe use an instance variable in the data structure class.  Initialise it to 0 whenever a new set of measurements is about to be taken.

Secondly, wherever there is an operation you want to count, increment the counter (vCount++).  For example:

some loop over vertices: 

vCount++;   // instrumentation
...

Store all of these data points in an internal data structure where you can keep track of (V, E, vCount, eCount).

Finally, report the values to the screen or a file before the program terminates.
Experiment Parameters

Vary the size of V and E and measure the number of vertex-processing and edge-processing operations for different values of V and E.  Use at least 5 different values of V, such as {10, 20, 30, 40, 50}.  Then, for each value of V, use different values of E.  Since |E|<|V|2, for V=10, the E values must range between 0 and 100.  Since a very sparse graph is not realistic, maybe use E={20,35,50,65,80} as evenly-spaced increments that avoid sparse graphs and also avoid fully-connected graphs.

For vertex names, maybe use "NodeXXX" where the XXX is an integer.  When generating edges randomly, you must not generate edges that already exist and you must not generate edges where the source and destination are the same.  Maybe use an integer in the range 1-10 for edge costs.

For each pair of values of V and E, your program will then generate a dataset and measure performance.
