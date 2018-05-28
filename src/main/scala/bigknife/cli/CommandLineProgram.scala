package bigknife.cli

class CommandLineProgram[A](args: Array[String], a: A)
  (implicit p: Program[A], parser: CommandLineParser[A]) {
  def run(): Unit = 
  	parser(a)(CommandLineArgs(args)) match {
  	  case Some(a) => p(a)
  	  case None => ()
  	}
}

object CommandLineProgram {
  def apply[A](args: Array[String], a: A)
    (implicit p: Program[A], parser: CommandLineParser[A]) = {
      new CommandLineProgram[A](args, a)
  }
}