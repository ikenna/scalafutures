package ikenna.futuresnotes

import org.scalatest.FunSuite
import scala.concurrent._
import scala.concurrent.duration.Duration
import ExecutionContext.Implicits.global


class Composition_applying_functions_to_the_result_of_futures extends FunSuite {

  /** We can compose Futures - pipeline, or chain, operations that will occur on the result of the completed Future
   *
   */

  test("You can map a function on the result of a future. The result will be wrapped in a Future for you"){
    val future1 = future {42}

    val result:Future[String] = future1.map(x => x.toString  + " is the answer to everything")

    println("Result of map is: " + Await.result(result, Duration("10 ms")))

    /** Output :
      *  Result of map is: 42 is the answer to everything
      */
  }

  test("You can apply functions to futures via flatMap. This allows you specify the Future returned"){
    val future1 = future {42}

    val result:Future[String] = future1.flatMap(x => future{" A random unexpected answer "})

    println("Result of flatMap is: " + Await.result(result, Duration("10 ms")))

    /** Output :
      *  Result of flatMap is:  A random unexpected answer
      */
  }


  test("Or you can do a transform in which you specify functions for the Success and Failure exception conditions"){
    val future1 = future {42}

    val result:Future[String] = future1.transform((x => x.toString), (y => new RuntimeException(y)))

    println("Result of transform is: " + Await.result(result, Duration("10 ms")))

    /** Output :
      *  Result of transform is: 42
      */
  }

  test("You can use the foreach method on the Future for side-effects"){
    val future1 = future {42}

    future1.foreach(x => println("Result of foreach is: " + x.toString))

    Await.ready(future1, Duration("10 ms"))

    /** Output :
      *  Result of foreach is: 42
      */
  }

  test("You can also specify a future to fallback to if the first one fails"){
    val future1 = future {throw new RuntimeException("Oh dear! I failed")}
    val future2 = future {42}

    val result = future1.fallbackTo(future2)

    val value = Await.result(result, Duration("1 second"))

    println("Result of fallback is: " + value)
  }

  test("And also a recovery function if the future fails with a given exception "){
    val future1 = future {throw new RuntimeException("Oh dear! I failed")}

    val result = future1.recover{case e:RuntimeException => "It failed with a RuntimeException"}

    val value = Await.result(result, Duration("1 second"))

    println("Result of fallback is: " + value)

    /** Output
     * Result of fallback is: It failed with a RuntimeException
     */
  }

  test("Want to add the result of 2 futures together? Use the zip method which gives you a tuple of the results"){
    val future1 = future {41}
    val future2 = future {42}

    val result = future1.zip(future2)

    val value = Await.result(result, Duration("1 second"))

    println("Result of zip is: " + value)

    /** Output:
     * Result of zip is: (41,42)
     */
  }

  test("You can turn a list of futures to a future of results"){
    val future1 = future {Thread.sleep(1000); 41}
    val future2 = future {Thread.sleep(2000); 42}
    val future3 = future {Thread.sleep(700); 43}

    val futures:List[Future[Int]] = List(future1, future2, future3)

    val result:Future[List[Int]] = Future.sequence(futures)

    val value = Await.result(result, Duration("3 second"))  // waits for all the futures to complete

    println("Result of sequence is: " + value)

    /** Output:
      * Result of zip is: List(41, 42, 43)
      */
  }

  test("What if you wanted to find the first future that completes ?"){
    val future1 = future {Thread.sleep(1000); 41}
    val future2 = future {Thread.sleep(2000); 42}
    val future3 = future {Thread.sleep(700); 43}

    val futures:List[Future[Int]] = List(future1, future2, future3)

    val result:Future[Int] = Future.firstCompletedOf(futures)

    val value = Await.result(result, Duration("3 second"))

    println("Result of firstCompleted is: " + value)

    /** Output:
      * Result of firstCompleted is: 43
      */
  }
}
