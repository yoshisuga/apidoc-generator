package models.generator

import org.joda.time.format.ISODateTimeFormat.dateTimeParser
import play.api.libs.json.{JsString, Json, JsValue}
import lib.Text._

sealed trait SwiftDataType {
  def apidocType: String

  def name: String

  def valueFromString(value: String): String = valueFromJson(Json.parse(value))

  def valueFromJson(json: JsValue): String = {
    throw new UnsupportedOperationException(s"Unable to create default value for type $name")
  }
}

object SwiftValueTypes {
  case object Boolean extends SwiftDataType {
    override val apidocType = "boolean"
    override val name = "Bool"

    override def valueFromJson(json: JsValue): String = json.as[scala.Boolean].toString
  }

  case object Double extends SwiftDataType {
    override val apidocType = "double"
    override val name = "Double"

    override def valueFromJson(json: JsValue): String = json.as[scala.Double].toString
  }

  case object Integer extends SwiftDataType {
    override val apidocType = "integer"
    override val name = "Int"

    override def valueFromJson(json: JsValue): String = json.as[scala.Int].toString
  }

  case object Long extends SwiftDataType {
    override val apidocType = "long"
    override val name = "Int"

    override def valueFromJson(json: JsValue): String = json.as[scala.Long].toString
  }

  case object DateIso8601 extends SwiftDataType {
    override val apidocType = "date-iso8601"
    override val name = "NSDate"

    override def valueFromString(value: String): String = {
      val dt = dateTimeParser.parseLocalDate(JsString(value).as[String])
      val x = Seq(
        "{ () -> NSDate! in"
        "let df = NSDateFormatter".indent(2),
        "df.dateFormat = \"yyyy-M-d\"".indent(2),
        s"return df.dateFromString(\"${dt.getYear}-${dt.getMonthOfYear}-${dt.getDayOfMonth}".indent(2),
        "}()"
      )
      x.mkString("\n")
    }
  }

  case object DateTimeIso8601 extends SwiftDataType {
    override val apidocType = "date-time-iso-8601"
    override val name = "NSDate"

    override def valueFromString(value: String): String = {
      val dateTimeString = JsString(value).as[String]
      val x = Seq(
        "{ () -> NSDate! in"
      "let df = NSDateFormatter".indent(2),
      "df.dateFormat = \"yyyy-M-d H:m:ssZZZ\"".indent(2),
      s"return df.dateFromString(\"${dateTimeString}\")".indent(2),
      "}()"
      )
      x.mkString("\n")
    }
  }

  case object Decimal extends SwiftDataType {
    override val apidocType = "decimal"
    override val name = "NSDecimalNumber"

    override def valueFromString(value: String): String = {
      "NSDecimalNumber(string:\"${value}\")"
    }
  }

  case object Object extends SwiftDataType {
    override val apidocType = "object"
    override val name = "[String : String]"
  }

  case object String extends SwiftDataType {
    override val apidocType = "string"
    override val name = "String"
    override def valueFromString(value: String) = s""""${JsString(value).as[String]}""""
  }

  case object Uuid extends SwiftDataType {
    override val apidocType = "uuid"
    override val name = ""
  }
}
