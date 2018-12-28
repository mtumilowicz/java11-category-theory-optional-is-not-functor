# java11-optional-is-not-functor
_Reference_: https://bartoszmilewski.com/2015/01/20/functors/  
_Reference_: https://www.amazon.com/Category-Theory-Oxford-Logic-Guides/dp/0199237182

# preface
A functor `F : C → D`
between categories `C` and `D` is a mapping of objects to 
objects and arrows to arrows, in such a way that
1. `F(f : A → B) = F(f) : F(A) → F(B)`,
1. `F(1A) = 1F(A)`,
1. `F(g . f) = F(g) . F(f)`.

That is, `F` preserves:
* domains and codomains, 
* identity arrows, 
* and compostion.

# project description
* define functions and its composition
    ```
    Function<Integer, Integer> nullFunction = i -> null;
    Function<Integer, String> toString = i -> nonNull(i) ? String.valueOf(i) : "null";
    Function<Integer, String> composition = nullFunction.andThen(toString);
    ```
* java `Optional` does not follow composition rules
    ```
    assertNotEquals(Optional.of(1).map(composition), 
          Optional.of(1).map(nullFunction).map(toString));
    ```
* vavr `Option` follows composition rules
    ```
    assertEquals(Option.of(1).map(composition), 
            Option.of(1).map(nullFunction).map(toString));
    ```