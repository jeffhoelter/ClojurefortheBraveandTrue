(ns chapter7)


; 1. Use the list function, quoting, and read-string to create 
; a list that, when evaluated, prints your first name and your 
; favorite sci-fi movie.


; (eval (quote (list (read-string "Jeff") (read-string "Star-Wars"))))
(eval (list (read-string "print") "Jeff:" "Star Wars\n"))


; 2. Create an infix function that takes a list like 
; (1 + 3 * 4 - 5) and trans- forms it into the lists 
; that Clojure needs in order to correctly evaluate 
; the expression using operator precedence rules.

(let [infix (read-string "(1 + 3 * 4 - 5)")]
  (list (second infix) (first infix) (last infix)))
