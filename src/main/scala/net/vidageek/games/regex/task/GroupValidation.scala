package net.vidageek.games.regex.task;

import java.util.regex.Matcher;

import net.vidageek.games.task.JudgedTask;

trait GroupValidation {

	def judge(challenge: String, matcher: Matcher): JudgedTask

}
