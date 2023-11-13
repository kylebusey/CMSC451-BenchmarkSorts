![Banner](Benchmark_Sorts-2.png)

# Benchmark Sorts

## About
This project was developed in the course CMSC 451, also known as *Algorithm Analysis* at University of Maryland Global Campus. This project was intended to take two sorting algorithms and compare the efficiency of the two with different data sets.

## Features
- Randomly generates datasets for two algorithms to sort based on data set sizes requested and runs 40 tests for each data set.
- Avoids data inconsistency issues by warming up the JVM prior to starting the sort tests.
- Creates a text file for both algorithms, recording the run time of each test as well as the data set size for each test. 
- The two text files created as used in the ReportProducer program which takes in the text files and produce graphs showing the runtime averages and overall differences.

