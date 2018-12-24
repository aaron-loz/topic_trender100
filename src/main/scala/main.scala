/*@Author: Aaron Lopez
**@date: 12/24/2018
**@build: Scala 2.12.8
**@ver: 0.0.001
**@Purpose: To create a basic Topic class with Ordering overloading, initializations of the priority queue,
**and to create a basic version of the processTopic. With examples and mostly using boilerplate functions.
**@TODO: create a type that will allow 64 bit unsigned positive numbers(if possible in Scala)
**modify initializePriQu() to accomodate initialize 2+ Topics
**normalize all variables to be closer to documentation constraints
**add basic trendingTopics() and all that entails
*/
import collection.mutable.PriorityQueue
object TopicTrenderInit{

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
  def processTopic(a: Topic, pri : PriorityQueue[Topic]) : PriorityQueue[Topic]={
    return (pri +=(a: Topic))
  }

  /*Unit testing
  */
  def main(ars: Array[String]): Unit={
    val twitList = initPriQu(Topic("apples", 200), Topic("Fruit", 100), Topic("Axes", 100))

    println(twitList.clone.dequeueAll)//guarantees order, just printing doesn't

    processTopic(Topic("create_dogs", 120), twitList)

    println(twitList.clone.dequeueAll)
  }
 }