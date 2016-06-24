(ns fwpd.core)

(def filename "suspects.csv")
(def output-filename "output-suspects.csv")


(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a CSV file into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
    (clojure.string/split string #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
        (reduce (fn [row-map [vamp-key value]]
                 (assoc row-map vamp-key (convert vamp-key value)))
         {}
         (map vector vamp-keys unmapped-row)))
    rows))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))


; Chapter 4 - Exercise 1
; Turn the result of your glitter filter into a list of names.

(defn glitter-filter-just-names
  [minimum-glitter records]
  (map :name (filter #(>= (:glitter-index %) minimum-glitter) records)))

; Chapter 4 - Exercise 2
; Write a function, append, which will append a new suspect to
; your list of suspects.

(defn append
  "Appends a new suspect into the given list of suspects"
  [suspects new-suspect-name new-suspect-glitter-index]
  (cons {:name new-suspect-name :glitter-index new-suspect-glitter-index} suspects))

; Chapter 4 - Exercise 3
; Write a function, validate, which will check that :name and
; :glitter-index are present when you append. The validate
; function should accept two arguments: a map of keywords to
; validating functions, similar to conversions, and the record
; to be validated.

(def valid-vampire-keys (:name :glitter-index))

(defn validate
  [valid-keys record]
  (= (keys record) valid-keys))


; Chapter 4 - Exercise 4
; Write a function that will take your list of maps and convert it
; back to a CSV string. You’ll need to use the clojure.string/join function.

(defn write-maps-to-file
  [filename maps-to-write]
  (spit filename (clojure.string/join "\n" (map #(clojure.string/join "," %) (map vals maps-to-write)))))


