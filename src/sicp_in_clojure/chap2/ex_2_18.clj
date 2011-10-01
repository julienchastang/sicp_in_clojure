(ns sicp-in-clojure.chap2.ex-2-18
  (:use clojure.contrib.math))

(defn my-reverse[c]
  (loop [col c l '()]
    (if (= (count col) 0)
      l
      (recur (rest col) (cons (first col) l)))))

(my-reverse (list 1 4 9 16 25))
