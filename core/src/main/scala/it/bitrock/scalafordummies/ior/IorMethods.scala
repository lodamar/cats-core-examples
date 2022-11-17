package it.bitrock.scalafordummies.ior

import cats.data.Ior
import cats.implicits.{catsSyntaxIorId, catsSyntaxTuple2Semigroupal, none}

object IorMethods {
  Ior.right("Value")
  Ior.left("Error")
  val both = Ior.both("Warning", "ValueWithWarning")

  999.rightIor
  "Error".leftIor

  both.fold(right => ???, left => ???, (left, right) => ???)

  both match {
    case Ior.Left(a) => ???
    case Ior.Right(b) => ???
    case Ior.Both(a, b) => ???
  }

  val fromOpts: Option[Ior[BigDecimal, Int]] = Ior.fromOptions(none[BigDecimal], Some(123))
}
