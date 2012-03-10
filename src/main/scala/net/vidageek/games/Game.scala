package net.vidageek.games

import java.util.Collection

import net.vidageek.games.task.TaskWithDescription

trait Game {

  def getSize: Int

  def task(index: Int): TaskWithDescription

  def getDescription: String

  def getTasks(): Collection[_ <: TaskWithDescription]

  def getName: String

  def hasNextTask(index: Int): Boolean

  def nextTask(index: Int): Int
}