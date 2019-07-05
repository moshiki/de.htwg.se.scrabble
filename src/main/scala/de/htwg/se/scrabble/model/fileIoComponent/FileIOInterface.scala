package de.htwg.se.scrabble.model.fileIoComponent

import de.htwg.se.scrabble.controller.StateCacheInterface

trait FileIOInterface {

  def load: StateCacheInterface
  def save(states: StateCacheInterface)

}
