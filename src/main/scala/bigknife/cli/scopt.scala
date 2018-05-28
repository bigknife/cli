package bigknife.cli

object scopt {
  implicit def commandLineParser[A](implicit parser: _root_.scopt.OptionParser[A]):
      CommandLineParser[A] =  (a: A) => (args) => parser.parse(args.value, a)
}