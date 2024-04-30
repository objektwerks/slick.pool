package pool

import com.typesafe.config.ConfigFactory

import org.scalatest.funsuite.AnyFunSuite

class RepositoryTest extends AnyFunSuite:
  test("repository"):
    val config = ConfigFactory.load("test.conf")
    val repository = Repository(config)
    repository.createSchema()
    repository.dropSchema()
    repository.close()
