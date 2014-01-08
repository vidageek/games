package vggames

package object regex {
  implicit def from(matchingTargets : Seq[String]) : MatcherTargets = new MatcherTargets(matchingTargets.toList)
  implicit def from(matchingTarget : String) : MatcherTargets = new MatcherTargets(List(matchingTarget))
}