#Introduction to Scala Futures and Promises (1)


## What is a Future ?

* A Future is a reference to the result of an asynchronous computation [1]. Its an object that keeps track of a concurrent computation, and can provide the result of the computation when it is complete [2].

* When would you use a Future ? If you had several computations you would like to run in parallel, and access their results  at a later point you would use futures. Using futures can improve performance, as the computations can run concurrently.

* Note: The Future is asynchronous because its computation is running in a different thread.


## What is a Promise ?

* A Scala Promise is a single assignment reference that can be used to complete its associated Future. [1]

* Creating a Promise creates its associated Future. Completing a Promise completes its associated Future.

* You can write to a Promise only once. But you can read its result as many times as you like.

* When would you use a Promise ?

   * Whenever you create a Future **f** in Scala, internally you are actually creating a Promise *p* whose future is **f**. **P** is the used to complete **f** internally. [3]

   * You can use a Promise to pass messages between Futures [1] . If this is confusing, don't worry - we will explain it more later with an example .


**[Next >>> Creating a Future](https://github.com/ikenna/scalafutures/blob/master/docs/2_Creating_Futures.md)**


### References

1.  [Futures and Promises by Philipp Haller et al](http://docs.scala-lang.org/overviews/core/futures.html)
2.  Pattern-Oriented Software Architecture: A Pattern Language for Distributed Computing, 4th Volume by  Frank Buschmann  et al
3.  [ scala / src / library / scala / concurrent / impl / Future.scala ](https://github.com/scala/scala/blob/master/src/library/scala/concurrent/impl/Future.scala)
