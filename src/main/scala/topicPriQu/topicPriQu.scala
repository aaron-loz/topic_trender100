/*@Author: Aaron Lopez
**@date: 12/24/2018
**@build: Scala 2.12.8
**@ver: 0.0.001
**@Purpose: To create a basic Topic class with Ordering overloading, initializations of the priority queue,
**and to create a basic version of the processTopic. With examples and mostly using boilerplate functions.
**@TODO: Method to check if topic is already in priorityQueue
**@TODO: modify trendingTopics() with optional var for dequeueing first how many topics.
**@TODO: Create own priorityqueue to sort Topics by Highest to lowest usageFrequency and point to
***last element in queue for removal. (like a Priority Double-Ended Queue)
**@TODO: Create a helper function that will handle full processTopics to streamline.
*/
package topicPriQu
import collection.mutable.PriorityQueue
import topic._

class TopicTrenderInit{
	
  val MAXTOPICLENGTH: Byte = 20//small for the sake of testing
  val NUMTOTALTOPICS: Byte = 3
  val NUMTRENDINGTOPICS: Byte = 5
  val cacheReplacementStrategy = "LRU"//example name of a policy
  
  /*@pre: a Topic Class with the usageFrequency field
  **@post: overloads Ordering to accomodate ascending Topic UsageFrequency field
  */
  object FreqOrdering extends Ordering[Topic] {
    def compare(a: Topic, b:Topic) = -(a.usageFrequency compare b.usageFrequency)
  }
 
  /*@post: creates an empty PriorityQueue for usage.
   */
  def initPriQu(): PriorityQueue[Topic]={
    PriorityQueue()(FreqOrdering)
  }
  /*@pre: Single Topic object to be passed for initialization
  **@post: returns priority queue containing object with overloading of Ordering
  */
  def initPriQu(a: Topic): PriorityQueue[Topic]= {
    PriorityQueue(a)(FreqOrdering)
  }
  
  /*@pre: 2+ Topic objects passed for initialization(work in progress)
   *@post: returns priority queue containing objects with overloading of Ordering
   */
  def initPriQu(a : Topic, b: Topic, c: Topic): PriorityQueue[Topic]= {
    PriorityQueue(a,b,c)(FreqOrdering)
  }

  /*@pre: a Topic and a vector of topics, presumable
   *@post: returns a priorityqueue with all those elements with overloading of Ordering
   */
  def initPriQu(a: Topic, b: IndexedSeq[Topic]): Unit= {
    PriorityQueue(a)(FreqOrdering)
  }
  
  /*@pre: a string, and an existing PriorityQueue
   * @post: string is turned into a Topic, and added into PriorityQueue in order
   */
  def processTopic(a: String, topicPriorityQueue : PriorityQueue[Topic]): Unit= {
    if (topicPriorityQueue.length < NUMTOTALTOPICS){
      topicPriorityQueue += (Topic(a): Topic)
    }else{
      topicPriorityQueue.dequeue
      topicPriorityQueue +=(Topic(a): Topic)
    }
  }
  def processTopic(a: String, b: Int, topicPriorityQueue : PriorityQueue[Topic]): Unit= {
    if (topicPriorityQueue.length < NUMTOTALTOPICS){
      topicPriorityQueue += (Topic(a, b): Topic)
    }else{
      topicPriorityQueue.dequeue
      topicPriorityQueue +=(Topic(a, b): Topic)
    }
  }
  /*@pre: a unique Topic object, and an existing PriorityQueue
   *@post: Topic object added into PriorityQueue in order
   */
  def processTopic(a: Topic, topicPriorityQueue : PriorityQueue[Topic]): PriorityQueue[Topic]={
    if (topicPriorityQueue.length < NUMTOTALTOPICS){
			return (topicPriorityQueue +=(a: Topic))
    }else{
      topicPriorityQueue.dequeue
      return(topicPriorityQueue +=(a:Topic))
    }
  }

  /*@pre: passes priorityQueue
   *@post: prints list of Topics with hashtags and frequency, in sorted order.
   P*/
  def trendingTopics(topicPriorityQueue : PriorityQueue[Topic]): Unit={	
    for(e<-topicPriorityQueue.clone.dequeueAll){
      println(s"${e.topic} ${e.usageFrequency}") //? return "{e.topic} {e.usageFrequency}\n" ?
    }
	}
 }
