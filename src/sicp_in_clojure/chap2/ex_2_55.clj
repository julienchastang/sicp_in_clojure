(ns sicp-in-clojure.chap1.ex-2-55)

(def a ''abracadabra)

(def b (first a))

(def c (rest a))

;; a   is a seq of which the first item is a quote

(class a )