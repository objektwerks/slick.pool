package pool

import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalDateTime, LocalTime, ZoneOffset}

sealed trait Entity extends Product with Serializable

object Entity {
  implicit def localTimeOrdering: Ordering[LocalTime] = Ordering.by(_.toSecondOfDay)
  implicit def localDateOrdering: Ordering[LocalDate] = Ordering.by(_.toEpochDay)
  implicit def localDateTimeOrdering: Ordering[LocalDateTime] = Ordering.by(_.toEpochSecond(ZoneOffset.UTC))
  implicit def poolOrdering: Ordering[Pool] = Ordering.by(p => (p.zip, p.city))
  implicit def ownerOrdering: Ordering[Owner] = Ordering.by(o => (o.since, o.last))
  implicit def surfaceOrdering: Ordering[Surface] = Ordering.by(_.installed)
  implicit def pumpOrdering: Ordering[Pump] = Ordering.by(_.installed)
  implicit def timerOrdering: Ordering[Timer] = Ordering.by(_.installed)
  implicit def heaterOrdering: Ordering[Heater] = Ordering.by(_.installed)
  implicit def lifecycleOrdering: Ordering[Lifecycle] = Ordering.by(l => (l.active, l.created))
  implicit def cleaningOrdering: Ordering[Cleaning] = Ordering.by(_.on)
  implicit def measurementOrdering: Ordering[Measurement] = Ordering.by(_.on)
  implicit def additiveOrdering: Ordering[Additive] = Ordering.by(_.on)
  implicit def supplyOrdering: Ordering[Supply] = Ordering.by(_.purchased)
  implicit def repairOrdering: Ordering[Repair] = Ordering.by(_.on)

  val dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
  val timeFormatter = DateTimeFormatter.ofPattern("hh:mm")
}

final case class Pool(id: Int = 0, 
                      built: LocalDate = LocalDate.now, 
                      gallons: Int = 10000, 
                      street: String = "street", 
                      city: String = "city", 
                      state: String = "state", 
                      zip: Int = 12345) extends Entity

final case class Owner(id: Int = 0, 
                       poolId: Int, 
                       since: LocalDate = LocalDate.now, 
                       first: String = "first", 
                       last: String = "last", 
                       email: String = "your@email.org") extends Entity

final case class Surface(id: Int = 0, 
                         poolId: Int, 
                         installed: LocalDate = LocalDate.now, 
                         kind: String = "kind") extends Entity

final case class Pump(id: Int = 0, 
                      poolId: Int, 
                      installed: LocalDate = LocalDate.now, 
                      model: String = "model") extends Entity

final case class Timer(id: Int = 0, 
                       poolId: Int, 
                       installed: LocalDate = LocalDate.now, 
                       model: String = "model") extends Entity

final case class Heater(id: Int = 0, 
                        poolId: Int, 
                        installed: LocalDate = LocalDate.now, 
                        model: String = "model") extends Entity

final case class Lifecycle(id: Int = 0, 
                           poolId: Int, 
                           created: LocalDate = LocalDate.now, 
                           active: Boolean = true, 
                           pumpOn: LocalTime = LocalTime.of(9, 0), 
                           pumpOff: LocalTime = LocalTime.of(17, 0)) extends Entity

final case class Cleaning(id: Int = 0, 
                          poolId: Int, 
                          on: LocalDate = LocalDate.now, 
                          deck: Boolean = true, 
                          brush: Boolean = true, 
                          net: Boolean = true,
                          vacuum: Boolean = false, 
                          skimmerBasket: Boolean = true, 
                          pumpBasket: Boolean = false, 
                          pumpFilter: Boolean = false) extends Entity

final case class Measurement(id: Int = 0, 
                             poolId: Int, 
                             on: LocalDate = LocalDate.now, 
                             temp: Double = 75.0, 
                             hardness: Double = 375.0, 
                             totalChlorine: Double = 3,
                             bromine: Double = 5.0, 
                             freeChlorine: Double = 3.0, 
                             pH: Double = 7.5, 
                             alkalinity: Double = 100.0, 
                             cyanuricAcid: Double = 50.0) extends Entity

final case class Additive(id: Int = 0, 
                          poolId: Int, 
                          on: LocalDate = LocalDate.now, 
                          chemical: String = "cl", 
                          unit: String = "gl", 
                          amount: Double = 1.0) extends Entity

final case class Supply(id: Int = 0, 
                        poolId: Int, 
                        purchased: LocalDate = LocalDate.now, 
                        item: String = "cl", 
                        unit: String = "gl", 
                        amount: Double = 1.0, 
                        cost: Double = 0.0) extends Entity

final case class Repair(id: Int = 0, 
                        poolId: Int, 
                        on: LocalDate = LocalDate.now, 
                        item: String = "repair", 
                        cost: Double = 0.0) extends Entity