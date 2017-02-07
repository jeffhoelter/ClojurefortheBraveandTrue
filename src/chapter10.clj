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

