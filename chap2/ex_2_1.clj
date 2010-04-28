(ns sicp-in-clojure.chap1.ex-2-1
  (:use clojure.contrib.math))

(defn my-gcd[a b]
  (if (= b 0)
    a
    (recur b (mod a b))))

(defn normalize-rat [n d]
  (cond (and (< n 0) (< d 0)) (list (- n) (- d))
	(< d 0) (list (- n) (- d))
	:esle (list n d)))

(defn make-rat[n d]
  (let [g (my-gcd n d)]
    (normalize-rat (/ n g) (/ d g))))


