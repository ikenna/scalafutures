package ikenna.futuresnotes

import scala.concurrent._
import ExecutionContext.Implicits.global
import org.scalatest.FunSuite
import scala.util.{Failure, Success}
import scala.concurrent.duration.Duration


class Ordering_futures_and_partial_functions extends FunSuite {

  test("You can use -andThen- to chain futures to PartialFunctions that have side effects. At the end you still get back your original Future"){

    val future1:Future[Int] = future {println("Do something first");42 }

    val output =
      future1
      .andThen{case Success(y) =>println("Do something second") }
      .andThen{case Success(y) =>println("Do something third"); case Failure(y) =>println("Or dear! I failed")  }

    println("Result is: " + Await.result(output, Duration("1 second")))

    /** Output:
     * Do something first
     * Do something second
     * Do something third
     * Result is: 42
     */
  }

}
