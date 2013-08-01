scalafutures
============


An Introduction to Scala Futures
-------------

* A Future a reference to the result of an asynchronous computation. In other words, a placeholder for a value that will be available at some point in future, as a result of some concurrent computation.

Key points in this tutorial:

1. [How to create Futures](https://github.com/ikenna/scalafutures/blob/master/main/test/ikenna/futuresnotes/How_to_create_futures.scala)
2. [How to define Callbacks on Futures (non-blocking result retrieval)] (https://github.com/ikenna/scalafutures/blob/master/main/test/ikenna/futuresnotes/Callbacks_you_can_specify_on_futures.scala)
3. [How Futures work with For-Comprehensions](https://github.com/ikenna/scalafutures/blob/master/main/test/ikenna/futuresnotes/For_comprehensions_and_Futures.scala)
4. [Accessing the result Scala Futures using Await (blocking result retrieval). Specifying Duration](https://github.com/ikenna/scalafutures/blob/master/main/test/ikenna/futuresnotes/Accessing_the_result_of_a_future.scala)
5. [Composing /Applying functions to the result of Futures](https://github.com/ikenna/scalafutures/blob/master/main/test/ikenna/futuresnotes/Composition_applying_functions_to_the_result_of_futures.scala)
6. [Specifying ordered side-effects for the Success or Failure of Futures]  (https://github.com/ikenna/scalafutures/blob/master/main/test/ikenna/futuresnotes/Ordering_futures_and_partial_functions.scala)
7. [Completing a Future using Promises](https://github.com/ikenna/scalafutures/blob/master/main/test/ikenna/futuresnotes/How_does_a_Future_complete.scala)


References
-------------

[The Neophyte’s Guide to Scala Part 8: Welcome to the Future by Daniel Westheide](http://danielwestheide.com/blog/2013/01/09/the-neophytes-guide-to-scala-part-8-welcome-to-the-future.html)

[Futures and Promises" by Philipp Haller et al](http://docs.scala-lang.org/overviews/core/futures.html)

[Multithreading - Asynchronous IO in Scala with futures - Stack Over Flow (see Heather Millers answer to the question)](http://stackoverflow.com/questions/13097754/asynchronous-io-in-scala-with-futures)