(ns chapter5)


; 1. You used (comp :intelligence :attributes) to create a function that returns a
; character’s intelligence. Create a new function, attr, that you can call like
; (attr :intelligence) and that does the same thing.

(def character
               {:name "Smooches McCutes"
                :attributes {:intelligence 10
                             :strength 4
                             :dexterity 5}})

(def c-int (comp :intelligence :attributes))

(defn attr
  [attribute_function]
  (comp attribute_function :attributes))

; 2. Implement the comp function.

(defn my-comp
  [& function-list]
  (( (first function-list) (my-comp (rest function-list)))))


(defn my-comp1
  [& function-list]
  rest function-list)

(defn sum
([vals] (sum vals 0))
  ([vals accumulating-total]
     (if (empty? vals)
       accumulating-total
       (sum (rest vals) (+ (first vals) accumulating-total)))))


; 3. Implement the assoc-in function. Hint: use the assoc function and
; define its parameters as [m [k & ks] v].

;(defn my-assoc-in
;  [m [k & ks] v]
;  (loop [new-map m
;         k k
;         ks ks]
;    (if (empty? ks)
;      (assoc new-map k v)
;    (recur (k new-map) (first ks) (rest ks)))))


(defn my-assoc-in
  [m [k & ks] v]
    (if (empty? ks)
      (assoc m k v)
    (assoc m k (my-assoc-in (get m k) ks v))))


; (assoc character :attributes (assoc (:attributes character) :strength 12))

; (assoc-in character [:attributes1 :strength1] 12)
; (assoc character :attributes1 (assoc {} :strength1 12))

; (assoc-in character [:attributes1 :strength1 :arms] 12)
; (assoc character :attributes1 (assoc (:) :strength1 (assoc {} :arms 12)))

; (assoc-in character [:attributes :strength] 12)
; (assoc character :attributes1 (assoc (:) :strength 12)

; (def users [{:name "James" :age 26}  {:name "John" :age 43}])

;; update the age of the second (index 1) user 
;(my-assoc-in users [1 :age] 44)
;;=> [{:name "James", :age 26} {:name "John", :age 44}]

;; insert the password of the second (index 1) user
;(my-assoc-in users [1 :password] "nhoJ")
;;=> [{:name "James", :age 26} {:password "nhoJ", :name "John", :age 43}]

;; create a third (index 2) user
;; Also (assoc m 2 {...}) or (conj m {...})
;(my-assoc-in users [2] {:name "Jack" :age 19})  
;;=> [{:name "James", :age 26} {:name "John", :age 43} {:name "Jack", :age 19}]




;(defn sum
;  ([vals]
;     (sum vals 0))
;  ([vals accumulating-total]
;   (if (empty? vals)
;       accumulating-total
;       (recur (rest vals) (+ (first vals) accumulating-total)))))


; 4. Look up and use the update-in function.
; (doc update-in)
(update-in character [:attributes :strength] inc)
(update-in character [:attributes1 :strength1] empty?)


; 5. Implement update-in.

(defn my-update-in
  [m [k & ks] f & args]
    (println args)
    (println (empty? args))
    (if (empty? ks)
      (if (nil? args)
        (f (get m k))
        (f args (get m k)))
    (assoc m k (my-update-in (get m k) ks f args))))


