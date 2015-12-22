package im.tox.sbt

import java.io.{File, PrintWriter}

import im.tox.sbt.ConfigurePlugin.NativeCompiler
import org.apache.commons.io.FilenameUtils
import sbt._

import scala.util.control.NonFatal

/**
 * TODO(iphydf): Write comments.
 */
object Configure {

  object nullLog extends ProcessLogger {
    override def info(s: => String): Unit = ()
    override def error(s: => String): Unit = ()
    override def buffer[T](f: => T): T = f
  }

  private def writeSource(sourceFile: File, includes: Seq[String], code: String): Unit = {
    val out = new PrintWriter(sourceFile)
    try {
      includes.foreach(include => out.println(s"#include <$include>"))
      out.println(code)
      out.println("int main () { return 0; }")
    } finally {
      out.close()
    }
  }

  private def tryCompileCode(log: Logger, compiler: NativeCompiler, includes: Seq[String], code: String, flags: String*): Seq[String] = {
    val sourceFile = File.createTempFile("conftest", "." + compiler.suffix)
    val outputFile = file(FilenameUtils.removeExtension(sourceFile.getPath))
    try {
      writeSource(sourceFile, includes, code)
      val command = Seq(compiler.program, "-o", outputFile.getPath, sourceFile.getPath) ++ flags
      log.debug(command.mkString(" "))
      command !! log
      flags
    } catch {
      case NonFatal(_) => Nil
    } finally {
      outputFile.delete()
      sourceFile.delete()
    }
  }

  def tryCompileIncludes(log: Logger, compiler: NativeCompiler, extraFlags: Seq[String], includes: Seq[String], code: String, flags: String*): Seq[String] = {
    tryCompileCode(log, compiler, includes, code, flags ++ extraFlags: _*) match {
      case Nil => Nil
      case _   => flags
    }
  }

  def tryCompile(log: Logger, compiler: NativeCompiler, flags: Seq[String]*): Seq[String] = {
    flags.find { flags =>
      tryCompileCode(log, compiler, Nil, "", flags: _*).nonEmpty
    }.getOrElse(Nil)
  }

}
