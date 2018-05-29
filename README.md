# CLI

scala command line programs skeleton.

[ ![Download from bintray](https://api.bintray.com/packages/bigknife/maven/cli/images/download.svg) ](https://bintray.com/bigknife/maven/cli/_latestVersion)

## Usage

### Args of program

First, we need create a type for our program args. to be simplified, we create a case
class like this:

```scala
case class Args()
```

ofcourse, you can add any properties to the Args.

### Some implicits

Then, a scopt parser implementation should be given as an implicit in which we can
build our `Args` from command lines. e.g. :

```scala
object Args {

  def default: Args = Args()

  trait Implicits {
    implicit object parser extends _root_.scopt.OptionParser[Args]("fssi") {
      println("This is output from _root_.scopt.OptionParser")
      head("fssi", "0.0.1")
    }
  }

  object implicits extends Implicits
}
```

And, the side-effect program function should also be given as an implicit in which the real program is runing. e.g. :

```scala
implicit val p: Program[Args] = { args =>
  println("Hello,world")
}
```

### Run program

Now, we can run our program, the `Main` class like this: 

```scala
import bigknife.cli.CommandLineProgram
import bigknife.cli.scopt._
import Args.implicits._

object Main {
  def main(args: Array[String]): Unit = {
    CommandLineProgram[Args](args, Args.default).run()
  }
}
```
