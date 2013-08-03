
#Introduction to Scala Futures and Promises (4)

## Specifying Callbacks on Futures

A Future can either

* execute successfully. In this case the Future completes with a result wrapped by an object of type `scala.util.Success` (which is a subtype of `scala.util.Try`)

or

* throw an exception.  In this case the Future completes with a result wrapped by an object of type `scala.util.Failure` (also a subtype of `scala.util.Try`)


There are 3 callbacks you can specify on Futures and Promises

1. `onSuccess` : what should happen if Future completes successfully

2. `onFailure` : what should happen if Future completes with a Failure

3. `onComplete` : the success and failure behaviours (a combination of the above 2)

Here is an example below to demonstrate onSuccess and onFailure ( [or view the src file](https://github.com/ikenna/scalafutures/blob/master/main/test/ikenna/futuresnotes/OnSuccess_and_OnFailure_Callbacks.scala) )

```
package ikenna.futuresnotes

import scala.concurrent._
import java.util.concurrent.Executors

object OnSuccess_and_OnFailure_Callbacks {

  def main(args: Array[String]) {
    implicit val executionContext = ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())

    val aFuture:Future[String] =  future{"Hello World!"} // change this to 'future{throw new RuntimeException()}' later and see what happens

    aFuture onSuccess  {
      case result => println(s"onSuccess : $result")
    }

    aFuture onFailure   {
      case exception:Throwable => println(s"onFailure : $exception")
    }
  }
}

```

Note that in the above example we create our own ExecutionContext using Executors.newCachedThreadPool() (line 12),  instead of importing the ExecutionContext.Implicits.global as before. (See footnote 1)

The below example shows use of the OnComplete callback ( [or view the src file](https://github.com/ikenna/scalafutures/blob/master/main/test/ikenna/futuresnotes/OnComplete_Callback.scala) )
```
   package ikenna.futuresnotes

   import scala.concurrent._
   import scala.util.Failure
   import scala.util.Success
   import java.util.concurrent.Executors

   object OnComplete_Callback {


     def main(args: Array[String]) {
       implicit val executionContext = ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())

       val aFuture: Future[String] = future {"Hello World!"}

       aFuture onComplete {
         case Success(result) => println(s"onComplete success: $result")
         case Failure(result) => println(s"onComplete failure: $result")
       }
     }

   }



```

Callbacks are one big difference Scala Futures have from Java Futures. Lets look at this in the next section.

**[Next >>> Specifying Callbacks on futures](https://github.com/ikenna/scalafutures/blob/master/docs/4_Callbacks_on_futures.md)**



