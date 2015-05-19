A Castrated Compiler for C
===========================

## Grammar Design

### Various Support in Type System
I designed the grammar with *declarator*, *directDeclarator* and *abstractDeclarator*, *directAbstractDeclarator*,
in order to cope with various types, such as function pointers or function that returning function pointers.

For analysing types well,
I created a class [***TypeAnalyser***](https://bitbucket.org/yzgysjr/compiler2015/src/5a58dcbb59e0934d2a3fab7ff5fecffe606071cb/src/Compiler2015/Parser/TypeAnalyser.java?at=master) in order to recover a type from a syntax tree that represents some type.
Also, another class [***StructBuilder***](https://bitbucket.org/yzgysjr/compiler2015/src/5a58dcbb59e0934d2a3fab7ff5fecffe606071cb/src/Compiler2015/Parser/StructBuilder.java?at=master) that deals with interesting scopes in struct / union of C is set up.

My program could deal with complex types shown below:

    struct A {
        int x, y;
        struct B {
            int i, j;
        } b;
        struct A *next;
    };
    
    /* a function that returns a pointer to function */
    int (*func(int flag, int (*f)(), int (*g)()))() {
        if (flag) return f;
        else return g;
    }

    int main() {
        struct A a;
        /* the follow types are distinctly different */
        int a0[10][20]; /* two-dimensional array */
        int (*a1)[20]; /* a pointer to array */
        int *a2[20];
        /* an array of pointers */
        int **a3;
        /* pointer to pointer */
        /* pointer to a function */
        int (*f)(), (*h)();
        /* function declaration, not a variable */
        int (*g(int ***e[10]))();
        /* complex type casting is also supported */
        f = (int (*)())(0x12345678);
        f = func(1, f, main); /* f */
        h = func(0, f, main); /* main */
        /* 0 1 */
        printf("%d %d\n", f == main, h == main);
    }

Meanwhile, during development, I found two bugs of ANTLR-4.5.

### ANTLR Bug \#1
ANTLR 4 declared itself handling left-recursion well, in fact it does not.
When passing parameters down through left-recursive, I found the ANTLR generator reports java.lang.NullPointerException.
It is the generator throws, not thrown during runtime. Thus it is sufficient to conclude that it is a bug of ANTLR itself.

### ANTLR Bug \#2
Predicate matching in ANTLR 4 is somewhat broken. Consider the code fragment below.

    struct A {};
    typedef struct A A;
    int main() {
        sizeof(struct A); // success
        sizeof(A); // fail
    }

How could I deal with this problem? I forcefully wrote an ambiguous grammar that detects this situation.

## Symbol Table Design
Symbol table and compilation-time environment takes an important part in the compiler.
It plays a great role as a global table.

- To be completed

## Abstract Syntax Tree Design and Semantic Checking
I designed abstract syntax tree as intermediate representation tree (IR tree), which is the first IR in my compiler.

- To be completed

## Intermediate Representation
I used a control flow graph (CFG) as my intermediate representation (IR), which benefits single static assignment a lot.

### Vertices in Control Flow Graph

The internal part of a vertex contains linear sequence of instructions that has to be executed in order.
Thus, my IR is a kind of nested IR which uses both graph and linear sequence.

Each vertex of the CFG has at most two outgoing edges: *unconditionalNext* and *branchIfFalse*.

A CFG vertex can have none outgoing edge iff it is the end vertex of the function.

If a CFG vertex has only one outgoing edge, it must be the *unconditionalNext* edge.

If a CFG vertex has two outgoing edges, 
it first check the last result of its internal linear IR sequence.
If it is non-zero, the program will choose *unconditionalNext* edge.
Otherwise, the program takes *branchIfFalse* edge.

## Refined and Detailed Intermediate Representation
First I delete all unreachable states in IR and then reduce unnecessary states. This is called the raw-refine of IR.

Next, I will make my IR detailed.
I should make my IR easy and correct for optimization.

I should avoid the condition that when I called a function, 
a global variable might changed,
I should load them back.
Also, local arrays / variables might changed as well.
In order to avoid all of these annoying conditions,
I use a brute force method: read them again.

When entering a function, I should load global variables into my virtual register,
split them from external uses, in order for my optimization.

What kinds of global variables I should not load? 
It's easy to see that struct / union / array pointers should not.

Before calling a function,
I should write back the global variables I modified.

After calling,
I should first fetch return variables,
then read global variables,
and next update local variables that might be changed.

All structs / unions are allocated in heap.

## Single Static Assignment


