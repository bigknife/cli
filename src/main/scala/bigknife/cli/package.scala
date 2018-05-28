package bigknife

package object cli {

	/** Command line parser
	  * Given an initial value of A, and a CommandLineArgs,
	  * generate an optioanl value of A
	  */
	type CommandLineParser[A] = A => CommandLineArgs => Option[A]

	/** a program with an arg of A
	  */
	type Program[A] = A => Unit

}