
#Introduction to Scala Futures and Promises
(Part 1)

## What is a Future ?

* A Future is a reference to the result of an asynchronous computation. In other words, a placeholder for a value that will be available at some point in future, as a result of some concurrent computation.
* When would you use a Future ? If you had several computations you would like to occur asynchronously or parallelize, you may want to ceate a Future to perform them.
* Note: The computation the Future will carry out will occur in another thread to the one that created the Future.


## What is a Promise

* A Promise is a single assignment reference that can be used to complete its associated Future.
* Creating a Promise creates its associated Future. Completing a Promise completes its associated Future.
* You can write to a Promise only once. But you can read its result as many times as you like.
* When would you use a Promise ? You can use a Promise to pass messages between Futures. If this is confusing, don't worry - we will explain it more later with an example


Next -> Creating a Future