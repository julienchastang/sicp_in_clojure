(ns sicp-in-clojure.chap1.ex-2-5
  (:use clojure.contrib.math))

(defn my-cons[a b] (* (Math/pow 2 a) (Math/pow 3 b)))

(my-cons 5 7)

(defn my-car[n]
  (loop [my-n n cnt 0]
    (if (= (mod my-n 2) 0)
      (recur (/ my-n 2) (inc cnt))
      cnt)))

(defn my-cdr[n]
  (loop [my-n n cnt 0]
    (if (= (mod my-n 3) 0)
      (recur (/ my-n 3) (inc cnt))
      cnt)))

(my-car (my-cons 5 7)) ; 5

(my-cdr (my-cons 5 7)) ; 7




