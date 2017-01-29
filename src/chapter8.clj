(ns chapter8)

(def order-details
               {:name "Mitchard Blimmons"
                  :email "mitchard.blimmonsgmail.com"})

(def order-details-validations
  {:name
   ["Please enter a name" not-empty]
   :email
   ["Please enter an email address" not-empty
    "Your email address doesn't look like an email address"
    #(or (empty? %) (re-seq #"@" %))]})

(defn error-messages-for
  "Return a seq of error messages"
  [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))

(defn validate
  "Returns a map with a vector of errors for each key"
  [to-validate validations]
  (reduce (fn [errors validation]
            (let [[fieldname validation-check-groups] validation
                  value (get to-validate fieldname)
                  error-messages (error-messages-for value validation-check-groups)]
              (if (empty? error-messages)
                errors
                (assoc errors fieldname error-messages))))
          {}
          validations))

; (if-valid order-details order-details-validations my-error-name
;             (println :success)
;             (println :failure my-error-name))


; 1. Write the macro when-valid so that it behaves similarly to when. 
; Here is an example of calling it:
                 ; (when-valid order-details order-details-validations
                 ;  (println "It's a success!")
                 ;  (println "It's a success2!"))
; When the data is valid, the println and render forms should be eval- uated, and when-valid should return nil if the data is invalid.

(defmacro when-valid
  "Handle validation more concisely"
  [to-validate validations & then-else]
  `(when (validate ~to-validate ~validations)
       ~@then-else))



; 2. You saw that and is implemented as a macro. Implement or as a macro.

(defmacro or1
        				"Evaluates exprs one at a time, from left to right. If a form
              				returns a logical true value, or returns that value and doesn't
              				evaluate any of the other expressions, otherwise it returns the
              				value of the last expression. (or) returns nil."
                ([] nil)
                ([x] x)
                ([x & next]
                `(let [or# ~x]
                   (if or# or# (or1 ~@next)))))


; 3. In Chapter 5 you created a series of functions (c-int, c-str, c-dex) to read an 
;    RPG character’s attributes. Write a macro that defines an arbi- trary number of 
;    attribute-retrieving functions using one macro call. Here’s how you would call it:
;     (defattrs c-int :intelligence
;               c-str :strength
;               c-dex :dexterity)

(defmacro defattrs
  [& attribute-function-pairs]
  (map #(println (first %) (second %)) (partition 2 (vec attribute-function-pairs))))

(defattrs c-int :intelligence c-str :strength c-dex :dexterity)

(macroexpand '(defattrs c-int :intelligence c-str :strength c-dex :dexterity))




