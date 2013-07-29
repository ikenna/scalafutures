import scala.actors.Futures._
import scala.actors.Future

object MyAopp {

  def main(args: Array[String]) = {
    println("About to do something...")

    future {
      Thread.sleep(500)
      println("Slow thing finished 1")
    }

    future {
      Thread.sleep(700)
      println("Slow thing finished 2")
    }


    println("I don't wait")

    Thread.sleep(5000)

  }
}


object App2 {
  def main(args: Array[String]) = {

    var results = List[Future[Int]]()
    for (i <- 1 to 10) {
      println("Sending " + i + "...")
      val f = future {
        println("Processing " + i + "...")
        Thread.sleep(500)
        println("Processed " + i)
        i
      }
      println("Sent " + i)
      results = results ::: List(f)
    }

    results.foreach(future =>
      println("result: " + future()))
  }
}