package com.sample.spark

import java.io.PrintWriter
import java.io.File

object GenerateData {
  def fileGenerator(n: Int, path: String) {
    val pw = new PrintWriter(new File(path))
    for (x <- 1 to n) {
      pw.write(String.valueOf(x.toDouble))
      pw.println()
    }
    pw.close
    println("File created")
  }

  def main(args: Array[String]) {
    val N = 50000
    val path = args(0)
    fileGenerator(N, path);
  }
}