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


