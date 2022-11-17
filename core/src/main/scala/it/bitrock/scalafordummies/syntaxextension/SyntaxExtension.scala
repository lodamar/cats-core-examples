package it.bitrock.scalafordummies.syntaxextension

import cats.implicits.{ catsSyntaxEitherId, catsSyntaxOptionId, toBifunctorOps }

object SyntaxExtension {
  val or     = Some(1)
  val orCats = 1.some
  def returnThis[T](t: T): Option[T] = Option(t)

  val r     = Option(2).fold(or)(returnThis(_))
  val rCats = Option(2).fold(orCats)(returnThis)

  val or     = Some(1)
  val orCats = 1.some

  val right     = Right(1)
  val right2    = Right[String, Int](1)
  val rightCats = 1.asRight[String]
  def returnThisEither[T](t: T): Either[String, T] = Right(t)

  val r     = Option(2).fold(right)(returnThisEither(_))
  val r2    = Option(2).fold(right2)(returnThisEither(_))
  val rCats = Option(2).fold(rightCats)(returnThisEither)

  final case class WrappedError(error: String)
  val left:       Either[String, Int]       = "Error".asLeft[Int]
  val leftMapped: Either[WrappedError, Int] = left.leftMap(WrappedError)
  val bimapped:   Either[Int, String]       = left.bimap(_.length, _.toString)
}
