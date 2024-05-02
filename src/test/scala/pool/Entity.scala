package pool

import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalDateTime, LocalTime, ZoneOffset}

sealed trait Entity

object Entity:
  given Ordering[LocalTime] = Ordering.by(_.toSecondOfDay)
  given Ordering[LocalDate] = Ordering.by(_.toEpochDay)
  given Ordering[LocalDateTime] = Ordering.by(_.toEpochSecond(ZoneOffset.UTC))
  given Ordering[Pool] = Ordering.by(p => (p.zip, p.city))
  given Ordering[Owner] = Ordering.by(o => (o.since, o.last))
  given Ordering[Surface] = Ordering.by(_.installed)
  given Ordering[Pump] = Ordering.by(_.installed)
  given Ordering[Timer] = Ordering.by(_.installed)
  given Ordering[Heater] = Ordering.by(_.installed)
  given Ordering[Lifecycle] = Ordering.by(l => (l.active, l.created))
  given Ordering[Cleaning] = Ordering.by(_.on)
  given Ordering[Measurement] = Ordering.by(_.on)
  given Ordering[Additive] = Ordering.by(_.on)
  given Ordering[Supply] = Ordering.by(_.purchased)
  given Ordering[Repair] = Ordering.by(_.on)

  val dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
  val timeFormatter = DateTimeFormatter.ofPattern("hh:mm")

final case class Pool(id: Int = 0, 
                      built: String = LocalDate.now.toString, 
                      gallons: Int = 10000, 
                      street: String = "street", 
                      city: String = "city", 
                      state: String = "state", 
                      zip: Int = 12345) extends Entity

final case class Owner(id: Int = 0, 
                       poolId: Int, 
                       since: String = LocalDate.now.toString, 
                       first: String = "first", 
                       last: String = "last", 
                       email: String = "your@email.org") extends Entity

final case class Surface(id: Int = 0, 
                         poolId: Int, 
                         installed: String = LocalDate.now.toString, 
                         kind: String = "kind") extends Entity

final case class Pump(id: Int = 0, 
                      poolId: Int, 
                      installed: String = LocalDate.now.toString, 
                      model: String = "model") extends Entity

final case class Timer(id: Int = 0, 
                       poolId: Int, 
                       installed: String = LocalDate.now.toString, 
                       model: String = "model") extends Entity

final case class Heater(id: Int = 0, 
                        poolId: Int, 
                        installed: String = LocalDate.now.toString, 
                        model: String = "model") extends Entity

final case class Lifecycle(id: Int = 0, 
                           poolId: Int, 
                           created: String = LocalDate.now.toString, 
                           active: Boolean = true, 
                           pumpOn: String = LocalTime.of(9, 0).toString, 
                           pumpOff: String = LocalTime.of(17, 0).toString) extends Entity

final case class Cleaning(id: Int = 0, 
                          poolId: Int, 
                          on: String = LocalDate.now.toString, 
                          deck: Boolean = true, 
                          brush: Boolean = true, 
                          net: Boolean = true,
                          vacuum: Boolean = false, 
                          skimmerBasket: Boolean = true, 
                          pumpBasket: Boolean = false, 
                          pumpFilter: Boolean = false) extends Entity

final case class Measurement(id: Int = 0, 
                             poolId: Int, 
                             on: String = LocalDate.now.toString, 
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
                          on: String = LocalDate.now.toString, 
                          chemical: String = "cl", 
                          unit: String = "gl", 
                          amount: Double = 1.0) extends Entity

final case class Supply(id: Int = 0, 
                        poolId: Int, 
                        purchased: String = LocalDate.now.toString, 
                        item: String = "cl", 
                        unit: String = "gl", 
                        amount: Double = 1.0, 
                        cost: Double = 0.0) extends Entity

final case class Repair(id: Int = 0, 
                        poolId: Int, 
                        on: String = LocalDate.now.toString, 
                        item: String = "repair", 
                        cost: Double = 0.0) extends Entity