(ns sicp-in-clojure.chap1.ex-1-11
  (:use clojure.contrib.repl-utils))

;recursive solution
(defn ex111[n]
  (if (< n 3)
    n
    (+ (ex111 (- n 1)) 
       (* (ex111 (- n 2)) 2)
       (* (ex111 (- n 3)) 3))))

(ex111 3)
(map #(ex111 %) (range 0 10))
;(0 1 2 4 11 25 59 142 335 796)

;From "Programming Clojure" Chapter 5
(defn fibo[]
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1])))

(take 20 (fibo))

;super-slick iterative solution
(defn ex111-iter[]
  (map first (iterate
	      (fn [[a b c]]
		[b c (+ c (* 2 b) (* 3 a))])
	      [0 1 2])))

(take 10 (ex111-iter))
;(0 1 2 4 11 25 59 142 335 796)