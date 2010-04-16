(ns sicp-in-clojure.chap1.ex-1-41
  (:use clojure.contrib.repl-utils))

(defn doub[f]
  (fn[x] (apply f [(apply f [x])])))

; add 2
((doub inc) 1)

(((doub (doub doub)) inc) 5) ; 21

; Booooorrrrriiiinnnggg!


