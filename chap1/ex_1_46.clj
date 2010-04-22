(ns sicp-in-clojure.chap1.ex-1-46
  (:use clojure.contrib.repl-utils))


; I solved this exercise through partial functions.
; I am not sure this is what Abelson & Sussman
; had in mind.

(defn average[coll]
    (let [cnt (count coll)]
          (reduce + (map #(/ % cnt) coll))))

(defn abs[x] (Math/abs x))

(defn square[x] (* x x))

(defn iterative-improve[good-enough? improve first-guess]
  (loop [g first-guess]
    (if (good-enough? g) g
	(recur (improve g)))))

(defn good-enough?[x guess]
  (< (abs (- (square guess) x)) 0.001))

(defn partial-good-enough[x] (partial good-enough? x)) 

(defn partial-improve[x]
  (partial (fn[x y] (average [y (/ x y)])) x))

(defn sqrt[x] (iterative-improve (partial-good-enough x)  (partial-improve x) 1.0))

(sqrt 100)
