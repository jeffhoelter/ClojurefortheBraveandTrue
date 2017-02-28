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

;; 3. Create your own protocol, and then extend it using extend-type and extend-protocol.

;; 4. Create a role-playing game that implements behavior using multiple dispatch.
