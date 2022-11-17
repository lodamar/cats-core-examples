package it.bitrock.scalafordummies.magic

import cats.implicits._
import cats.syntax._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

object Magic {
  final case class User(username: String)
  final case class Request(id: Option[String])

  def getFromIdWithDefault(id: String): Future[User]         = ???
  def getFromId(id:            String): Future[Option[User]] = ???

  val request:   Request              = Request("username".some)
  val mappedSeq: Future[Option[User]] = request.id.map(getFromIdWithDefault).sequence

  val a = List("").flatMap(List(_))
  val mappedTrav: Future[Option[User]] = request.id.traverse(getFromIdWithDefault)

  val r1: Future[Option[User]] = request.id.fold[Future[Option[User]]](Future.successful(None))(getFromId)
  val r2 = request.id.map(getFromId).getOrElse(Future.successful(None))

  val r3: Future[Option[User]] = request.id.map(getFromId).flatSequence
  val r4: Future[Option[User]] = request.id.flatTraverse(getFromId)

  val stringsToParse:             List[String]                                           = List("1", "2", "name")
  val parsed:                     List[Either[String, Int]]                              = stringsToParse.map(numberOrString)
  val (stringsPart, numbersPart): (List[Either[String, Int]], List[Either[String, Int]]) = parsed.partition(_.isRight)
  val flattenStrings:             List[String]                                           = stringsPart.flatMap(_.left.toOption)
  val flattenInts:                List[Int]                                              = numbersPart.flatMap(_.toOption)

  val (strings, ints): (List[String], List[Int]) = parsed.separate

  private def numberOrString(s: String): Either[String, Int] = Try(s.toInt).toOption.toRight(s)
}
