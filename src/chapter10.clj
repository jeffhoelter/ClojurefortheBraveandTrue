(ns chapter10)

(def sock-varieties
  #{"short" "tall" "brown" "red"})

(defn sock-count
  [sock-variety count]
  {:variety sock-variety
   :count count})

(defn generate-sock-gnome
  [name]
  {:name name
   :socks #{}})

(def sock-gnome (ref (generate-sock-gnome "Barumpharumph")))

(def dryer (ref {:name "LG 1337"
                 :socks (set (map #(sock-count % 2) sock-varieties))}))

(defn steal-sock
  [gnome dryer]
  (dosync
   (when-let [pair (some #(if (= (:count %) 2) %) (:socks @dryer))]
     (let [updated-count (sock-count (:variety pair) 1)]
       (alter gnome update-in [:socks] conj updated-count)
       (alter dryer update-in [:socks] disj pair)
       (alter dryer update-in [:socks] conj updated-count)))))

(steal-sock sock-gnome dryer)

(:socks @sock-gnome)


(defn similar-socks
  [target-sock sock-set]
  (filter #(= (:variety %) (:variety target-sock)) sock-set))

(similar-socks (first (:socks @sock-gnome)) (:socks @dryer))


(defn sleep-print-update
  [sleep-time thread-name update-fn]
  (fn [state]
    (Thread/sleep sleep-time)
    ;; (println (str thread-name ": " state))
    (update-fn state)))
(def counter (ref 0))
(future (dosync (commute counter (sleep-print-update 100 "Thread A" inc))))
(future (dosync (commute counter (sleep-print-update 150 "Thread B" inc))))


;; Exercises

;; 1. Create an atom with the initial value 0, use swap! to
;; increment it a couple of times, and then dereference it.

(def counter-atom (atom 0))
(swap! counter-atom inc)
(swap! counter-atom inc)
(swap! counter-atom inc)
@counter-atom


;; 2. Create a function that uses futures to parallelize the
;; task of download- ing random quotes from
;; http://www.braveclojure.com/random-quote using
;; (slurp "http://www.braveclojure.com/random-quote"). The
;; futures should update an atom that refers to a total word
;; count for all quotes. The function will take the number of
;; quotes to download as an argument and return the atom’s final
;; value. Keep in mind that you’ll need to ensure that all
;; futures have finished before returning the atom’s final
;; value. Here’s how you would call it and an example result:
;;                 (quote-word-count 5)
;;                 ; => {"ochre" 8, "smoothie" 2}

(slurp "http://www.braveclojure.com/random-quote")

(defn slurp-brave-into-frequencies-map
  []
  (frequencies (str/split (slurp "http://www.braveclojure.com/random-quote") #" ")))

(defn quote-word-count
  [count]
  (repeatedly count (slurp-brave-into-frequencies-map)))
(quote-word-count 5)





;; 3. Create representations of two characters in a game. The
;; first character has 15 hit points out of a total of 40. The
;; second character has a healing potion in his inventory.
;; Use refs and transactions to model the consumption of the
;; healing potion and the first character healing.
