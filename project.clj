(defproject clojure-for-the-brave-and-true "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"] [org.clojure/tools.nrepl "0.2.12" :scope "test"]]
  :main ^:skip-aot chapter13
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
