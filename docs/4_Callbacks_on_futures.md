
#Introduction to Scala Futures and Promises
(Part 4)

## Specifying Callbacks on Futures?

A future can either

1. execute without any problems . In this case the Future completes with a Success

or

2. throw an exception.  In this case the Future completes with a Failure

There are 3 callbacks you can specify on Futures and Promises

1. OnSuccess : what should happen if Future completes successfully

2. OnFailure : what should happen if Future completes with a Failure

3. OnComplete : the success and failure behaviours (a combination of the above 2)

```

package ikenna.futuresnotes

import org.scalatest.FunSuite
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.concurrent.duration.Duration


class Callbacks_you_can_specify_on_futures extends FunSuite{

  test("With onComplete, we can specify the Success and Failure cases") {

    val aFuture:Future[String] = future{"Hello World!"}

    aFuture onComplete {
      case Success(result) => println(s"onComplete : $result")
      case Failure(result) => println(s"onComplete : $result")
    }

    /**Note: Success and Failure are subtypes of class Try
      * A Try is a class used to denote an operation that ends either successfully with a value, or fails with an exception
     */

    Await.ready(aFuture, Duration("100 ms"))
  }


  test("Or we can specify separate callbacks for failure and success") {

    val aFuture:Future[String] =  future{"Hello World!"} // change this to 'future{throw new RuntimeException()}' later and see what happens

    aFuture onSuccess  {
      case result => println(s"onSuccess : $result")
    }

    aFuture onFailure   {
      case exception:Throwable => println(s"onFailure : $exception")
    }

    Await.ready(aFuture, Duration("100 ms"))
  }

}



```