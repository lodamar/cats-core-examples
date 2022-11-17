package it.bitrock.scalafordummies.monadtransformer

import cats.data.EitherT

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object MonadTransformer {
  sealed trait DomainError
  final case class Hamburger(style: String, meat: String, double: Boolean)

  def styleF:  Future[Either[DomainError, String]] = ???
  def doubleF: Future[Boolean]                     = ???
  def meatE(style: String): Either[DomainError, String] = ???

  for {
    style  <- styleF
    double <- doubleF
    meat   <- meatE(style)
  } yield Hamburger(style, meat, double)

  val result: EitherT[Future, DomainError, Hamburger] = for {
    style  <- EitherT(styleF)
    double <- EitherT.right(doubleF)
    meat   <- EitherT.fromEither[Future](meatE(style))
  } yield Hamburger(style, meat, double)

  val finalResult: Future[Either[DomainError, Hamburger]] = result.value
}
