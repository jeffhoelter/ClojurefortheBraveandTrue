(ns chapter13)


;; 1. Extend the full-moon-behavior multimethod to add behavior for your own kind of were-creature.

(defmulti full-moon-behavior (fn [were-creature] (:were-type were-creature)))

(defmethod full-moon-behavior :wolf
  [were-creature]
  (str (:name were-creature) " will howl and murder"))

(defmethod full-moon-behavior :simmons
  [were-creature]
  (str (:name were-creature) " will encourage people to sweat to the oldies"))

(full-moon-behavior {:were-type :wolf
                     :name "Rachel from next door"})

(full-moon-behavior {:were-type :simmons
                     :name "Andy the baker"})


(defmethod full-moon-behavior :angry-toddler
  [were-creature]
  (str (:name were-creature) " will cry and whine"))

(full-moon-behavior {:were-type :angry-toddler
                     :name "Althea"})

;; 2. Create a WereSimmons record type, and then extend the WereCreature protocol.


(defprotocol WereCreature
  (full-moon-behavior [x]))
(defrecord WereWolf [name title] WereCreature
            (full-moon-behavior [x]
              (str name " will howl and murder")))
(defrecord WereSimmons [name title] WereCreature
            (full-moon-behavior [x]
              (str name " has wild and crazy hair")))


;; 3. Create your own protocol, and then extend it using extend-type and extend-protocol.

(defprotocol ApplianceProtocol
  (process [x]))

(extend-type java.lang.Object
  ApplianceProtocol
  (process
    ([x] "meh")
    ([x y] (str "meh about " y))))


(extend-protocol ApplianceProtocol
  java.lang.String
  (process
    ([x] "meh2")
    ([x y] (str "meh2 about " y))))


;; 4. Create a role-playing game that implements behavior using multiple dispatch.

(defmulti cast-spell (fn [mage victim] (:mage-type mage)))

(defmethod cast-spell :summoner
  [mage victim]
  (str "Summoner mage " (:name mage) " casts a spell on " (:name victim)))

(defmethod cast-spell :white-mage
  [mage victim]
  (str "White mage " (:name mage) " casts a spell on " (:name victim)))

(cast-spell {:mage-type :summoner
             :name "Archdeacon Gotham"}
            {:name "Poor Victim"})

(cast-spell {:mage-type :white-mage
             :name "Lord Voldemort"}
            {:name "Harry Potter"})
