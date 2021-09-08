Enum-_ish_ Type Inference
===========================
or **_Fake Enum_** type inference -- ensures the type safety of using sets of constants as true `enum` constructs.

[comment]: <> ([![Build Status]&#40;https://travis-ci.com/opprop/fenum-demo.svg?branch=master&#41;]&#40;https://travis-ci.com/opprop/fenum-demo&#41;)

### Background I - Fake Enum Checker for fake enumerations
The following is a digest from https://checkerframework.org/manual/#fenum-checker

The Fake Enum Checker gives the same safety guarantees as a true enumeration type or typedef, but retaining backward-compatibility with interfaces that use existing Java types. You can apply fenum annotations to any Java type, including all primitive types and also reference types, as the following example shows.
```java
@NavigationMode int NAVIGATION_MODE_STANDARD = 0;
@NavigationMode int NAVIGATION_MODE_LIST = 1;
@NavigationMode int NAVIGATION_MODE_TABS = 2;

@NavigationMode int getNavigationMode();

void setNavigationMode(@NavigationMode int mode);
```
The Fake Enum Checker can issue a compile-time warning if the programmer ever tries to call setNavigationMode with an int that is not a @NavigationMode int.


### Background II - Whole program type inference
[Checker Framework Inference](https://github.com/opprop/checker-framework-inference) is a general type inference framework, which save the effort of manually adding annotations before using a Fake Enum type checker.

This Fake Enum type inference is build upon `Checker Framework Inference`.


## Setup
run ./setup.sh

run ./run-fenum.sh [_java_source_file_]