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

  /**Output:
   * onComplete success: Hello World!
   */
}


