package futures

import scala.concurrent.{Promise, Await, Future, future}
import scala.concurrent.duration.{FiniteDuration, Duration, TimeUnit}
import java.util.concurrent.TimeUnit
import scala.util.Success

/* Execution contexts - Creates and manages the threads run the Future will run in. For example,
creating a Java ExecutorService to which the Runnable for the Future task will be submitted.
(An ExecutorService distributes tasks to worker threads in a thread pool)
 You need to define an ExecutionContext to create futures. */
import scala.concurrent.ExecutionContext.Implicits.global

class CreatingAFuture {

  def createAFuture(): Future[Unit] = {

   val result:Future[Unit] = future {
      println("Long running computation started")
      Thread.sleep(3000)
      println("Long running computation stopped")
    }
    result
  }

}


object CreatingAFuture extends App{
  val result:Future[Unit] = new CreatingAFuture().createAFuture()
 Await.ready(result, Duration(5, TimeUnit.SECONDS))
}


object CreatingAPromise extends App{
     val p = Promise[String]()
  println("Is completed " + p.isCompleted)
  p.success("something")
  p.success("something else")
  println("Is completed " + p.isCompleted)
}

object CreatingAPromise2 extends App{
  val p = Promise[String]()
  println("Is completed " + p.isCompleted)
  p.tryComplete(Success("something"))
  println("Promise is completed ? " + p.isCompleted)
  println("Future is completed ? " + p.future.isCompleted)

}