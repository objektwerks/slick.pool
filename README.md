Slick Pool
----------
>Pool domain model using Slick, H2 and Scala 3.

Scala 3 Bug
-----------
>See this PR ( https://github.com/slick/slick/pull/2760 ). The workaround is
>simple, just define ```def tupled = (apply _).tupled``` in the companion
>object of each case class and it will compile for all Scala versions.

Object Model
------------
* Pool(id, built, gallons, street, city, state, zip)
* Owner(id, poolId, since, first, last, email)
* Surface(id, poolId, installed, kind)
* Pump(id, poolId, installed, model)
* Timer(id, poolId, installed, model)
* Heater(id, poolId, installed, model)
* Lifecycle(id, poolId, created, active, pumpOn, pumpOff)
* Cleaning(id, poolId, on, deck, brush, vacuum, net, skimmerBasket, pumpBasket, pumpFilter)
* Measurement(id, poolId, on, temp, hardness, totalChlorine, bromine, freeChlorine, pH, alkalinity, cyanuricAcid)
* Additive(id, poolId, on, chemical, unit, amount)
* Supply(id, poolId, purchased, item, unit, amount, cost)
* Repair(id, poolId, on, repair, cost)

Relational Model
----------------
* Pool 1 ---> * Owner | Surface | Pump | Timer | Heater | Lifecycle | Cleaning | Measurement | Additive | Supply | Repair

Measurements
------------
1. total hardness 0 - 1000      ok = 250 - 500      ideal = 375
2. total chlorine 0 - 10        ok = 1 - 5          ideal = 3
3. total bromine 0 - 20         ok = 2 - 10         ideal = 5
4. free chlorine 0 - 10         ok = 1 - 5          ideal = 3
5. ph 6.2 - 8.4                 ok = 7.2 - 7.8      ideal = 7.5
6. total alkalinity 0 - 240     ok = 80 - 120       ideal = 100
7. cyanuric acid 0 - 300        ok = 30 - 100       ideal = 50
 
** Units of Measure - oz, gl, lb

Test
----
1. sbt clean test
