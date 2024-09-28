# README

This is an example calculator written in Kotlin for the JVM. It supports `*, /, +, -, (, )` on integers and floating point numbers. The real part of floating point numbers can either be separated by `.` or by `,`.

## Grammar

The parser implements the following grammar:

```
S   -> M S1
S1  -> + M S1 | - M S1 | ε
M   -> A M1
M1  -> * A M1 | / A M1 | ε
A   -> a A1 | - a A1 | (S)
A1  -> . a | ε
```

`a` represents an arbitrary integer number.  
