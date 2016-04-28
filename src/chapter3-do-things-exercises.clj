(ns chapter3)



; 1. Use the str, vector, list, hash-map, and hash-set functions.

(str "hello world")
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

(def asym-hobbit-body-parts [{:name "head" :size 3}
                            {:name "part1-eye" :size 1}
                            {:name "part1-ear" :size 1}
                            {:name "mouth" :size 1}
                            {:name "nose" :size 1}
                            {:name "neck" :size 2}
                            {:name "part1-shoulder" :size 3}
                            {:name "part1-upper-arm" :size 3}
                            {:name "chest" :size 10}
                            {:name "back" :size 10}
                            {:name "part1-forearm" :size 3}
                            {:name "abdomen" :size 6}
                            {:name "part1-kidney" :size 1}
                            {:name "part1-hand" :size 2}
                            {:name "part1-knee" :size 2}
                            {:name "part1-thigh" :size 4}
                            {:name "part1-lower-leg" :size 3}
                            {:name "part1-achilles" :size 1}
                            {:name "part1-foot" :size 2}])

(defn matching-part
  [part part-number]
  (let [part-name (clojure.string/replace (:name part) #"^part1-" (str "part" part-number "-"))]
    {:name part-name
     :size (:size part)}))

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
                     (set [part (matching-part part 2) (matching-part part 3) (matching-part part 4) (matching-part part 5)])))))))

(defn symmetrize-body-parts-nums
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts number]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (vec (map chapter3/matching-part (repeat part) (range 2 (inc number))))])))))))



