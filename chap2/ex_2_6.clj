(ns sicp-in-clojure.chap1.ex-2-6
  (:use clojure.contrib.math))

(def zero (fn[f] (fn[x] x)))

((zero 1) 1)






