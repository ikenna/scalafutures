package ikenna.futuresnotes

import org.scalatest.FunSuite
import scala.concurrent._
import scala.concurrent.duration.Duration
import ExecutionContext.Implicits.global

class Accessing_the_result_of_a_future extends FunSuite {


  test("Accessing the result of a future via a callback ") {
    /**
     * Its important to note the computation of a Future occurs in a different Thread. This example helps illustrate that
     */
    val future4: Future[Int] = future {
        printf("Making the future's thread '%s' sleep \n", Thread.currentThread.getName)
        Thread.sleep(100)
        printf("Waking thread '%s' and returning 42 \n", Thread.currentThread.getName)
        42
    }

    // Defining call back
    future4 onSuccess {
      case result: Int => printf("Result was '%s' \n", result)
    }

    printf("Sleeping '%s' thread \n", Thread.currentThread.getName)
    Thread.sleep(200)


    /**
     * Output : (Order not guaranteed)
     * Making the future's thread 'ForkJoinPool-1-worker-1' sleep
     * Sleeping 'main' thread
     * Waking thread 'ForkJoinPool-1-worker-1' and returning 42
     * Result was '42'
     */
  }

  test("Access the result of a future with a blocking call") {
    val aFuture: Future[Int] = future { throw new RuntimeException(); Thread.sleep(100);  42 }

    aFuture onSuccess {
      case result: Int => result
    }


    val output:Int = Await.result(aFuture, Duration("200 millisecond"))

    // The above line blocks the main thread for 200 milliseconds waiting for the future to complete, then returns the result.
    // i.e You can't get to this line until the Future has completed.
    // Await.result will throw a TimeoutException if future does not complete in given time

    println(s"Output was $output")

    /**
     * Output :
     * Result was '42'
     */


    /**
     * Note that Await.result(aFuture, Duration("200 millisecond")) will return the *result* of the completed future (in this case, Int)
     * Await.ready(aFuture, Duration("200 millisecond")) will return the completed future (in this case Future(Int) )
     */
  }




}
