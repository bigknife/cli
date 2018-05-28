package bigknife.cli

import org.scalatest.FunSuite

class CliSpec extends FunSuite {

  case class Arg(hostName: String = "localhost")

  test("An Cli Program should use implicit functions to run program") {

    implicit val p: Program[Arg] = { arg =>
      info("This is output by a program")
    }

    implicit val parser: CommandLineParser[Arg] = arg =>  line => {
      info(s"This is a input line: $line")
      Some(Arg())
    }

    CommandLineProgram[Arg](Array("--host-name localhost"), Arg()).run()
  }

  test("Only an scopt.OptionParser[A] needed to make a command line program") {
    implicit val p: Program[Arg] = { arg =>
      info("This is output by a program")
      assert(arg.hostName == "org.wexchain")
      ()
    }

    import bigknife.cli.scopt._

    implicit object parser extends _root_.scopt.OptionParser[Arg]("wexchain") {
      info("This is output from _root_.scopt.OptionParser")

      head("wexchain", "0.0.1-SNAPSHOT")
      opt[String]('h', "host-name")
        .text("host name")
        .action((x, c) => c.copy(hostName = x))
    }

    parser.usage.split("\n").foreach(info(_))

    CommandLineProgram[Arg](Array("--host-name","org.wexchain"), Arg()).run()

  }
}
