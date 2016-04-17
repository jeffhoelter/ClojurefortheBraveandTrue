(ns chapter3)



; 1. Use the str, vector, list, hash-map, and hash-set functions.

(str "hello")
(str 123)
(str "hello" "hi")

(vector 1 2 3)
[2 2 5]

'(a b c)
(list 'a 'b 'd)
(list 1 2 3)

(hash-map)
{}
(hash-map :a 1 :b 3)
(keys (hash-map :a 1 :b 3))

(hash-set :a :b :c)
(hash-set 1 2 3 3 2 3 4 3 2 1)
#{1 2 3 4}
; #{1 2 3 4 4} ; duplicate key exception


; 2. Write a function that takes a number and adds 100 to it.

(defn add-100 [n] (+ n 100))

(chapter3/add-100 2)
; => 102

(chapter3/add-100 -1)
; => 99

(chapter3/add-100 1000)
; => 1100


; 3. Write a function, dec-maker, that works exactly like the
; function inc-maker except with subtraction:

(defn dec-maker
  "Create a custom decrementor"
  [dec-by]
  #(- % dec-by))

(def dec9 (dec-maker 9)) (dec9 10)
; => 1


; 4. Write a function, mapset, that works like map except the return value is a set:
; (mapset inc [1 1 2 2])
; => #{2 3}

(defn mapset
	[a rest]
	(set (map a rest)))


; 5. Create a function that’s similar to symmetrize-body-parts except that it has 
; to work with weird space aliens with radial symmetry. Instead of two eyes, 
; arms, legs, and so on, they have five.

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

