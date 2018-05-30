Deliberate Q
==============
<a href="https://travis-ci.org/davidmoten/DeliberateQ"><img src="https://travis-ci.org/davidmoten/DeliberateQ.svg"/></a><br/>
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.davidmoten/DeliberateQ/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.davidmoten/DeliberateQ)<br/>
[![codecov](https://codecov.io/gh/davidmoten/DeliberateQ/branch/master/graph/badge.svg)](https://codecov.io/gh/davidmoten/DeliberateQ)

Deliberate Q is a visualisation tool for Q Methodology analysis. It is a java swing application with these features:

* Principal Components Analysis
* Centroid Method
* Factor rotations manually or using standard algorithms (Varimax, Orthomax etc)
* Rotation graphs
* Animated intersubjective correlation graphs for visualisation of multi stage data
* Venn Diagram views for factor interpretation

<img src="docs/images/forq.png?raw"/>

Mathematics libraries
---------------------
T distribution routines from apache [commons-math](http://commons.apache.org/proper/commons-math/) are used in the calculation of factor scores.

[Jama](http://math.nist.gov/javanumerics/jama/) is used for eigenvalue decomposition in Principal Components Analysis.
