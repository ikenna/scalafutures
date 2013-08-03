
#Introduction to Scala Futures and Promises (2)


## How are Futures created ?

In 3 ways :

1. Using the `scala.concurrent.future{}` function

2. Using the `scala.concurrent.Future()` companion object

3. Creating a Promise, and referring to its associated Future.

These 3 methods are shown in the example below

```
   package ikenna.futuresnotes

   import scala.concurrent._
   import scala.concurrent.duration.Duration

   /** You need to define an ExecutionContext to create futures.
     * You can think of an ExecutionContext as something which creates and manages the threads the Future will run in,
     * similar to a java.util.concurrent.Executor */

   import ExecutionContext.Implicits.global
   import org.scalatest.FunSuite

   class How_to_create_futures extends FunSuite {

     test("Creating a future that returns a String using scala.concurrent.future (The usual way to do it)") {
       val future1 = future {
         "My Future"
       } // Create a Future but don't do anything with it
       assert(future1.isInstanceOf[Future[String]])
     }

     test("Creating a future that returns an Int using scala.concurrent.Future companion object") {
       val future2 = Future(42)
       assert(future2.isInstanceOf[Future[Int]])
     }

     test("Creating a future from a Promise ") {
       val promise = Promise[Boolean]() // A Promise is an object we can use to complete its associated Future. We will talk more on this later.
       val future3 = promise.future
       assert(future3.isInstanceOf[Future[Boolean]])
     }

   }

```

In the above example, note that we need to define an ExecutionContext (line 10). We can't create a Future without this.

Also note that we just create a Future, we don't do anything with it. So how do we get the result of a Future ? This leads us to the next section -

**[Next >>> Accessing the result of a Future](https://github.com/ikenna/scalafutures/blob/master/docs/3_Accessing_The_Result_Of_A_Future_By_Blocking.md)**

