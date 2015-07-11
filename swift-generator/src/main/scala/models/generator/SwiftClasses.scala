package models.generator

import com.bryzek.apidoc.generator.v0.models.InvocationForm
import com.bryzek.apidoc.spec.v0.models.{Union, Model, Enum}
import lib.generator.{GeneratorUtil, CodeGenerator}
import lib.Text._

object SwiftClasses extends CodeGenerator {
  /**
   * Invokes the code generators, returning either a list of errors
   * or the result of the code generation.
   */
  override def invoke(form: InvocationForm): Either[Seq[String], String] = {

  }

  def generateCode(form: InvocationForm): String = {
    // enums, unions, models...
  }

  def generateEnum(enum: Enum): String = {
    val comments = enum.description.map{ desc =>
      SwiftUtil.textToComment(desc) + "\n"
    }
    val fields = enum.values.map{
      "  case + " + _.name
    }.mkString("\n")
    s"${comments}enum ${enum.name} {\n${fields}\n}"
  }

  def generateUnionAsProtocol(union: Union): String = {
    val comments = union.description.map{ desc =>
      SwiftUtil.textToComment(desc) + "\n"
    }.getOrElse("")
    s"${comments}protocol ${union.name} {}"
  }

  def generateModelAsClass(model: Model): String = {
    // comments
    val commentsSection = model.description.map{ desc =>
      SwiftUtil.textToComment(desc) + "\n"
    }.getOrElse("")
    val classDef = s"class ${model.name} "
  }
}

object SwiftUtil {
  def textToComment(text: Seq[String]): String = {
    "/*\n * " + text.mkString("\n * ") + "\n */"
  }

  def textToComment(text: String): String = {
    if ( text.trim.isEmpty ) {
      ""
    } else {
      textToComment(GeneratorUtil.splitIntoLines(text))
    }
  }
}