package generator

import com.bryzek.apidoc.generator.v0.models.File
import org.scalatest.{FunSpec, ShouldMatchers}

class ServiceFileNamesSpec extends FunSpec with ShouldMatchers {

  describe("ServiceFileNames.toFile") {

    def toFile(
      languages: String,
      version: String = "0.0.1"
    ): File = {
      ServiceFileNames.toFile(
        namespace = "com.bryzek.apidoc",
        organizationKey = "bryzek",
        applicationKey = "apidoc",
        version = version,
        suffix = "Client",
        contents = "test",
        languages = Some(languages)
      )
    }

    it("ruby is underscored") {
      val file = toFile("ruby")
      file.name should be("bryzek_apidoc_v0_client.rb")
      file.dir should be(Some("com/bryzek/apidoc"))
    }

    it("scala is camelcased") {
      val file = toFile("scala")
      file.name should be("BryzekApidocV0Client.scala")
      file.dir should be(Some("com/bryzek/apidoc"))
    }

  }
  
  it("getSuffix for known languages") {
    ServiceFileNames.toLanguages("ruby").map(_.extension) should be(Seq("rb"))
    ServiceFileNames.toLanguages("ruby,scala").map(_.extension) should be(Seq("rb", "scala"))
    ServiceFileNames.toLanguages(" RUBY , SCALA ").map(_.extension) should be(Seq("rb", "scala"))
    ServiceFileNames.toLanguages("java").map(_.extension) should be(Seq("java"))
    ServiceFileNames.toLanguages("javascript").map(_.extension) should be(Seq("js"))
    ServiceFileNames.toLanguages("go").map(_.extension) should be(Seq("go"))
  }

  it("getSuffix for all known languages") {
    ServiceFileNames.Language.All.foreach { l =>
      ServiceFileNames.toLanguages(l.name).map(_.extension) should be(Seq(l.extension))
    }
  }

  it("getSuffix for unknown languages") {
    ServiceFileNames.toLanguages("") should be(Nil)
    ServiceFileNames.toLanguages("foo") should be(Nil)
    ServiceFileNames.toLanguages("foo, bar") should be(Nil)
  }

}
