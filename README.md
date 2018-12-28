# java11-optional-is-not-functor
_Reference_: https://bartoszmilewski.com/2015/01/20/functors/

# preface

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