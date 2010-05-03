(ns sicp-in-clojure.chap1.ex-2-4
  (:use clojure.contrib.math))

(defn my-cons[x y]
    (fn[m] (m x y)))

(defn my-car[z]
    (z (fn[p q] p)))

(defn my-cdr[z]
    (z (fn[p q] q)))


;substitution model
((fn[m] (m x y)) (fn[p q] q))

((fn[p q] q) x y)

(fn[x y] y)
