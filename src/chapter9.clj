(ns chapter9)

; 1. Write a function that takes a string as an argument and searches for 
; it on Bing and Google using the slurp function. Your function should 
; return the HTML of the first page returned by the search.

(defn offline-slurp
  [search-engine search-term]
    (str search-engine search-term))

(def bing-and-google-search-engines ["http://www.bing.com/search?q=" "https://www.google.com/#q="])


(defn search-bing-and-google
  [search-term]
  (let [search-promise (promise)]
    (doseq [search-engine bing-and-google-search-engines]
      (future (deliver search-promise (slurp (str search-engine search-term)))))
    (str @search-promise)))

; (search-bing-and-google "water")


; 2. Update your function so it takes a second argument consisting of 
; the search engines to use.

(defn search-with-search-engines
  [search-term search-engines]
  (let [search-promise (promise)]
    (doseq [search-engine search-engines]
      (future (deliver search-promise (slurp (str search-engine search-term)))))
    (str @search-promise)))

; (search-with-search-engines "water" bing-and-google-search-engines)


; 3. Create a new function that takes a search term and search engines 
; as arguments, and returns a vector of the URLs from the first page of 
; search results from each search engine.

(defn search-for-urls
  [search-term search-engines]
  (let [search-promise (promise)]
    (doseq [search-engine search-engines]
      (future (deliver search-promise (slurp (str search-engine search-term)))))
    (take 10 (re-seq #"(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"
        (str @search-promise)))))

; (search-for-urls "water" bing-and-google-search-engines)
