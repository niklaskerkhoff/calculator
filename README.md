# README

This is an example calculator written in Kotlin for the JVM. It supports `*, /, +, -, (, )` on integers and floating
point numbers.

## Usage

- Enter the calculation you want to perform in the command line.
- The decimal separator can either be `.` or `,`.
- Whitespace characters are currently not supported.

## Examples

```
> 1+1
  = 2.0
> 2+2*2
  = 6.0
> (2+2)*2
  = 8.0
> -2.5--3.7
  = 1.2
```

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
