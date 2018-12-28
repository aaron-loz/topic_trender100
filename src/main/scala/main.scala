/*@Author: Aaron Lopez
**@date: 12/24/2018
**@build: Scala 2.12.8
**@ver: 0.0.001
**@Purpose: To create a basic Topic class with Ordering overloading, initializations of the priority queue,
**and to create a basic version of the processTopic. With examples and mostly using boilerplate functions.
**@TODO: link passera's UInt to this file for Topic Class
**@TODO: modify initializePriQu() to accomodate 2+ Topics
**@TODO: (!)modify processTopic to remove last element in priorityqueue when full.
**@TODO: (!!)create flatten function for Topic Class to allow no side effects in trendingTopic
**@TODO: Implement Time.scala to measure time.
*/
import collection.mutable.PriorityQueue
object TopicTrenderInit{
	
  val MAXTOPICLENGTH: Byte = 20//small for the sake of testing
  val NUMTOTALTOPICS: Byte = 10 
  val NUMTRENDINGTOPICS: Byte = 5
  val cacheReplacementStrategy = "LRU"//example name of a policy
  
  case class Topic(topic: String, usageFrequency: Long = 1)//!UF only creates a signed 64 bit, we need unsigned

  /*@pre: a Topic Class with the usageFrequency field
  **@post: overloads Ordering to accomodate Topic and compare through UsageFrequency field
  */
  object FreqOrdering extends Ordering[Topic] {
    def compare(a: Topic, b:Topic) = a.usageFrequency compare b.usageFrequency
  }

  /*@pre: Single Topic object to be passed for initialization
  **@post: returns priority queue containing object with overloading of Ordering
  */
  def initPriQu(a: Topic): PriorityQueue[Topic] = {
    return collection.mutable.PriorityQueue(a)(FreqOrdering)
  }

  /*@pre: 2+ Topic objects passed for initialization
  **@post: returns priority queue containing objects with overloading of Ordering
  */
  def initPriQu(a : Topic, b: Topic, c: Topic): PriorityQueue[Topic] = {//TODO: make it s.t. params can accomodate 2+ Topics. Look @ priorityqueue docs for example
    return collection.mutable.PriorityQueue(a,b,c)(FreqOrdering)
  }

  /*@pre: a unique Topic object, and an existing PriorityQueue
  **@post: Topic object added into PriorityQueue in order
  */
  def processTopic(a: Topic, topicPriorityQueue : PriorityQueue[Topic]) : PriorityQueue[Topic]={
    if (topicPriorityQueue.length <= NUMTOTALTOPICS){
			return (topicPriorityQueue +=(a: Topic))
    }else{
    	return (topicPriorityQueue +=(a: Topic))  
    }
    
  }

  /*@pre: passes priorityQueue
  **@post: prints list of Topics with hashtags and frequency, in sorted order.
  */
  def trendingTopics(topicPriorityQueue : PriorityQueue[Topic]) : Unit={	
    for(e<-topicPriorityQueue.clone.dequeueAll){
      println(s"${e.topic} ${e.usageFrequency}") //? return "{e.topic} {e.usageFrequency}\n" ?
    }
	}
  
  //!def rewrtrendingTopics(topicPriorityQueue: PriorityQueue[Topic]) = topicPriorityQueue.PriorityQueue.clone.dequeueall.mkString(//?.....flatten.mkString ?
    //"", "\n", "\n")
  
  /*Unit testing
  */
  def main(ars: Array[String]): Unit={
    val topicPriorityQueue = initPriQu(Topic("apples", 200), Topic("Fruit", 100), Topic("Axes", 100))

    println(topicPriorityQueue.clone.dequeueAll)//guarantees order, just printing doesn't

    processTopic(Topic("create_dogs", 120), topicPriorityQueue)
    
    println(topicPriorityQueue.clone.dequeueAll)
    trendingTopics(topicPriorityQueue)
    
    //!println(rewrtrendingTopics(topicPriorityQueue))
  }
 }