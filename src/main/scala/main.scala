/*@Author: Aaron Lopez
**@date: 12/24/2018
**@build: Scala 2.12.8
**@ver: 0.0.001
**@Purpose: To create a basic Topic class with Ordering overloading, initializations of the priority queue,
**and to create a basic version of the processTopic. With examples and mostly using boilerplate functions.
**@TODO: create unsigned Int. Passera's is deprecated.
**@TODO: modify initializePriQu() to accomodate 2+ Topics
**@TODO: Create own priorityqueue to sort Topics by Highest to lowest usageFrequency and point to
**last element in queue for removal. (like a Priority Double-Ended Queue)
*/
import collection.mutable.PriorityQueue
object TopicTrenderInit{
	
  val MAXTOPICLENGTH: Byte = 20//small for the sake of testing
  val NUMTOTALTOPICS: Byte = 3
  val NUMTRENDINGTOPICS: Byte = 5
  val cacheReplacementStrategy = "LRU"//example name of a policy
  
  case class Topic(topic: String, usageFrequency: Long = 1)//!UF only creates a signed 64 bit, we need unsigned
  /*@post: measures time of object by nanoseconds.
   */
  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
  }

  /*@pre: a Topic Class with the usageFrequency field
  **@post: overloads Ordering to accomodate ascending Topic UsageFrequency field
  */
  object FreqOrdering extends Ordering[Topic] {
    def compare(a: Topic, b:Topic) = -(a.usageFrequency compare b.usageFrequency)
  }
 
  /*@pre: Single Topic object to be passed for initialization
  **@post: returns priority queue containing object with overloading of Ordering
  */
  def initPriQu(a: Topic): PriorityQueue[Topic] = {
    return PriorityQueue(a)(FreqOrdering)
  }
  /*@pre: 2+ Topic objects passed for initialization
   *@post: returns priority queue containing objects with overloading of Ordering
   */
  def initPriQu(a : Topic, b: Topic, c: Topic): PriorityQueue[Topic] = {
    return PriorityQueue(a,b,c)(FreqOrdering)
  }

  /*@pre: a Topic and a vector of topics, presumable
   *@post: returns a priorityqueue with all those elements with overloading of Ordering
   */
  def initPriQu(a: Topic, b: IndexedSeq[Topic]): PriorityQueue[Topic] = {
    b foreach println
    return PriorityQueue(a)(FreqOrdering)//,b.iterator)(FreqOrdering)
  }
  /*@pre: a unique Topic object, and an existing PriorityQueue
   *@post: Topic object added into PriorityQueue in order
   */
  def processTopic(a: Topic, topicPriorityQueue : PriorityQueue[Topic]) : PriorityQueue[Topic]={
    if (topicPriorityQueue.length < NUMTOTALTOPICS){
			return (topicPriorityQueue +=(a: Topic))
    }else{
      topicPriorityQueue.dequeue
      return(topicPriorityQueue +=(a:Topic))
    }
  }

  /*@pre: passes priorityQueue
   *@post: prints list of Topics with hashtags and frequency, in sorted order.
   */
  def trendingTopics(topicPriorityQueue : PriorityQueue[Topic]) : Unit={	
    for(e<-topicPriorityQueue.clone.dequeueAll){
      println(s"${e.topic} ${e.usageFrequency}") //? return "{e.topic} {e.usageFrequency}\n" ?
    }
	}
  
  /*Unit testing
   */
  def main(ars: Array[String]): Unit={
    val topicPriorityQueue = initPriQu(Topic("apples", 200), Topic("Fruit", 100), Topic("Axes", 100))

    println(topicPriorityQueue.clone.dequeueAll)//guarantees order, just printing doesn't

    processTopic(Topic("create_dogs", 120), topicPriorityQueue)
    
    println(topicPriorityQueue.clone.dequeueAll)
    trendingTopics(topicPriorityQueue)
    
  }
 }
