package ikenna.futuresnotes

import org.scalatest.FunSuite
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class How_does_a_Future_complete extends FunSuite {

  test("What happens if we dont want to wait for a future to complete?") {

    val aFuture: Future[Int] = future {
      Thread.sleep(3000) // lets wait for 3 seconds
      42
    }


    println("Has the future completed ? " + aFuture.isCompleted)

    // Say we now don't want to wait for 3 seconds. How do we complete the Future ?

  }


  test("A Promise is an object that has a Future internally _ It is a single assignment placeholder that can be used to complete its own internal Future") {

    val aPromise = Promise[Int]()

    val aFuture: Future[Int] = future {
      Thread.sleep(3000) // lets wait for 3 seconds
      val theResult = 42
      theResult
    }

    aPromise.completeWith(aFuture)  // Very roughly : make the completion value of aPromises internal Future the same as aFuture


    println("Has the promise completed ? " + aPromise.isCompleted)

    // Too impatient to wait. Lets complete it.
    println("Lets assign a value to the Promise")

    aPromise.success(43)

    println("Has the promise completed ? " + aPromise.isCompleted)
    println("Has the Future associated with the Promise completed ? " + aPromise.future.isCompleted)
    println("Whats the completed value of aPromise ? " + Await.result(aPromise.future, Duration("1 ms")))
    println("Has the original Future completed ? " + aFuture.isCompleted)
  }

}
