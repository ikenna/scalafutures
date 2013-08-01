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
