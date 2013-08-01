package ikenna.futuresnotes

import org.scalatest.FunSuite
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}


class For_comprehensions_and_Futures extends FunSuite{

  test("Scala For Comprehensions work like Java For loops but are much richer"){
    val result =
      for (i <- 1 to 3; j <- 1 to 3; if i !=  j; c = i - j) // 2 generators, 1 guard, 1 definition
      yield {                                               // yield returns a sequence containing all the results
      println(s"i = $i, j = $j , c = $c")
      (i, j, c)
    }

    println(s"result = $result")

    /**
     * Output
     * i = 1, j = 2 , c = -1
     * i = 1, j = 3 , c = -2
     * i = 2, j = 1 , c = 1
     * i = 2, j = 3 , c = -1
     * i = 3, j = 1 , c = 2
     * i = 3, j = 2 , c = 1
     * result = Vector((1,2,-1), (1,3,-2), (2,1,1), (2,3,-1), (3,1,2), (3,2,1))
     */
  }


  test("Futures work with Scala For Comprehensions"){
    val future1 = future {42}
    val future2 = future {44}

    val result:Future[Int] =
     for {
       value1 <- future1
       value2 <- future2
     } yield { value2 - value1 }    // yield here returns a Future

    result onSuccess {
      case output => println("The result is : " + output)
    }

    Await.ready(result, Duration("1 second"))
    /** Output :
     *  The result is : 2
     */
  }


  test("What if the first future fails ?"){
    val future1:Future[Int] = future {throw new RuntimeException("I am failing!")}
    val future2:Future[Int] = future {44}

    val result:Future[Int] =
      for {
        value1 <- future1
        value2 <- future2
      } yield { value2 - value1 }

    result onComplete  {
      case Success(output) => println("The result is : " + output)
      case Failure(output) => println("The result is : " + output)
    }

    Await.ready(result, Duration("1 second"))
    /** Output :
      * The result is : java.lang.RuntimeException: I am failing!
      */
  }


}
