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
