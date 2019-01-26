package time
  /*@post: measures time of object by nanoseconds.
   */
class debugTime{
	def time[R](block: => R): R = {
		val t0 = System.nanoTime()
		val result = block    // call-by-name
		val t1 = System.nanoTime()
		println("Elapsed time: " + (t1 - t0) + "ns")
		result
	}
}