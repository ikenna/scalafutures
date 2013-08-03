package ikenna.futuresnotes

import scala.concurrent._
import scala.util.Failure
import scala.util.Success
import java.util.concurrent.ScheduledThreadPoolExecutor

object OnComplete_Callback {


  def main(args: Array[String]) {
    implicit val executionContext = ExecutionContext.fromExecutorService(new ScheduledThreadPoolExecutor(2))

    val aFuture: Future[String] = future { "Hello World!" }

    aFuture onComplete {
      case Success(result) => println(s"onComplete : $result")
      case Failure(result) => println(s"onComplete : $result")
    }

    /** Note: Success and Failure are subtypes of class Try
      * A Try is a class used to denote an operation that ends either successfully with a value, or fails with an exception
      */
  }

}


