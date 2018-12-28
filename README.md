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

# programming
`Optional` itself is not a type, it’s a type constructor. 
You have to give it a type argument, like `Integer` or `Boolean`, 
in order to turn it into a type. `Optional` without any 
argument represents a **function on types**. But can we 
turn `Optional` into a functor?

Type constructor `Optional` together with the function 
`map :: (a -> b) -> Optional a -> Optional b` should 
form a functor.

# project description
* define functions and its composition
    ```
    Function<Integer, Integer> nullFunction = i -> null;
    Function<Integer, String> toString = i -> nonNull(i) ? String.valueOf(i) : "null";
    Function<Integer, String> composition = nullFunction.andThen(toString);
    ```
* java `Optional` does **not follow** composition rules
    ```
    assertNotEquals(Optional.of(1).map(composition), 
          Optional.of(1).map(nullFunction).map(toString));
    ```
* java `Optional` **follows** composition rules when treated
as stream
    ```
    assertEquals(Optional.of(1).stream().map(composition).findAny(), 
            Optional.of(1).stream().map(nullFunction).map(toString).findAny());
    ```
* vavr `Option` **follows** composition rules
    ```
    assertEquals(Option.of(1).map(composition), 
            Option.of(1).map(nullFunction).map(toString));
    ```