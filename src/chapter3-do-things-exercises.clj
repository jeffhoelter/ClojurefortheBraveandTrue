(ns chapter3)



;; 1. Use the str, vector, list, hash-map, and hash-set functions.

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
;; #{1 2 3 4 4} ;; duplicate key exception

