package vggames.shared

import java.security.MessageDigest

object Hash {

  def apply(string : String) = BigInt(MessageDigest.getInstance("MD5").digest(string.getBytes)).toString(16).replace("-", "$")

}