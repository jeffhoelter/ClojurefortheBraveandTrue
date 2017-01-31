(ns chapter9)

; 1. Write a function that takes a string as an argument and searches for 
; it on Bing and Google using the slurp function. Your function should 
; return the HTML of the first page returned by the search.

(def gimli-headshots ["serious.jpg" "fun.jpg" "playful.jpg"])
(defn email-user
  [email-address]
  (println "Sending headshot notification to" email-address))
(defn upload-document
  "Needs to be implemented"
  [headshot]
  true)
(let [notify (delay (email-user "and-my-axe@gmail.com"))] (doseq [headshot gimli-headshots]
(future (upload-document headshot) (force notify))))



(def search-engines ["https://google.com" "https://bing.com"])

(defn search-bing-and-google
  [search-term]
  (let [search-promise (promise)]
    (doseq [search-engine search-engines]
      (future (deliver search-promise (slurp search-engine search-term))))
    (@search-promise)))


; 2. Update your function so it takes a second argument consisting of 
; the search engines to use.


; 3. Create a new function that takes a search term and search engines 
; as arguments, and returns a vector of the URLs from the first page of 
; search results from each search engine.
