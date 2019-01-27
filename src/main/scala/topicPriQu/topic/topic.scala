/**@TODO: create unsigned Int. Passera's is deprecated.
*/
package topic

case class Topic(topic: String, usageFrequency: Long = 1)//!UF only creates a signed 64 bit, we need unsigned
