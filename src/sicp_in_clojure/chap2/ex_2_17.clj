(ns sicp-in-clojure.chap2.ex-2-17
  (:use clojure.contrib.math))


(defn last-pair[c]
  (if (< (count c) 2)
    c
    (recur (rest c))))

(last-pair (list 23 72 149 34))

