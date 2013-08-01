
# Creating Futures

* You can create a Future in 3 ways :

1. Using the scala.concurrent.future{} function

2. Using the scala.concurrent.Future() companion object

3. Creating a Promise, and referring to its associated Future.

These 3 methods are shown in the example below


* In the above examples, note that we need to define an ExecutionContext (line 10).
You can think of it as a Java ExecutorService or Threadpool - it is the thing that creates and manages the thread that the future runs in.