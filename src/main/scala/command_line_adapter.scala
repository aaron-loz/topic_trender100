/*@Author: Aaron Lopez
**@date: 12/24/2018
**@build: Scala 2.12.8
**@ver: 0.0.001
**@Purpose: To demo current topic_trender_100 features
*/
import time._
import topicPriQu._
import topic._

object cmd_lin_adapter{
  
  /*Unit testing
   */
  def main(ars: Array[String]): Unit={
    val topic_trender = new TopicTrenderInit()
    val debug = new debugTime()
    val topicPriorityQueue =  topic_trender.initPriQu()
      
    println("\n\t The Command-Line Adapter\n\nEnter a command") 
    //TODO: Create a param for initialize priqueue that is empty.
    //TODO: write out commands for adding topics and printing and exiting.
    var input = scala.io.StdIn.readLine().toLowerCase.trim
    while (input != "exit" && input !="zz"){
      println(s"user inputted: $input")
      //TODO: helper function that trims everything and returns array.    
      if(input.startsWith("topic ")|| input.startsWith("processtopic ")|| input.startsWith("pt ")){
        input = input.stripPrefix("topic").trim 
        input = input.stripPrefix("processtopic").trim
        input = input.stripPrefix("pt").trim
        println(s"after first trim: $input")
        val strs = input.split(" ")
        //TODO:streamline cases.
        if(strs.length == 2){
          try{
            val freq = strs(1).toInt          
            //TODO:write out what to do with str(0) and str(1)
            println(s"""two found, both are ${strs(0)}: $freq
            |\nAdding to priority Queue...""".stripMargin)
            //TODO: add case if strs(1) is a negative. 
            if(freq <1){
              println("Number is not positive")
            }else{
              topic_trender.processTopic(strs(0), freq, topicPriorityQueue)
            }
          }catch{
            case e: NumberFormatException => println("Invalid 2nd param, not a number.")
          }
       }else if(strs.length == 1 && !(strs(0) matches ("^[0-9]*|\\s|-[0-9]*$"))){
          println(s"one param, ${strs(0)}.\n\nAdding to priorityQueue...")
          topic_trender.processTopic(strs(0), topicPriorityQueue) 
        }else if(strs.length >2 || strs.length<1){
          println("Invalid number of arguments.")
        }else{
          println("Invalid parameters")
        }
      }else if(input matches "trending.$"){
        //TODO: work on getting trending
        println("Grabbing trending topics...")
      }
      input = scala.io.StdIn.readLine().toLowerCase.trim
    }
  }
}
