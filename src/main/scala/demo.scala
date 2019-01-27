/*@Author: Aaron Lopez
**@date: 12/24/2018
**@build: Scala 2.12.8
**@ver: 0.0.001
**@Purpose: To demo current topic_trender_100 features
*/
import time._
import topicPriQu._
import topic._

object Demo{
  
  /*Unit testing
   */
  def main(ars: Array[String]): Unit={
	val demo = new TopicTrenderInit()
	val demoT = new debugTime()
	var list = demoT.time{
		val topicPriorityQueue =  demo.initPriQu(Topic("apples", 200), Topic("Fruit", 100), Topic("Axes", 100))

		println(topicPriorityQueue.clone.dequeueAll)//guarantees order, just printing doesn't

		demo.processTopic(Topic("create_dogs", 120), topicPriorityQueue)
		
		println(topicPriorityQueue.clone.dequeueAll)
		demo.trendingTopics(topicPriorityQueue)
		}
	println(list)
	}
 }
