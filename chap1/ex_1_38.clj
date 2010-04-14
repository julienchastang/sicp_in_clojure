(ns sicp-in-clojure.chap1.ex-1-38
  (:use clojure.contrib.repl-utils))

(defn cont-frac-iter [n d k]
  (loop [sum 0 count k]
    (if (neg? count) sum
	(recur (/ (n count)
		  (+ (d count) sum))
	       (dec count))))) 

(defn euler-seq[i]
  (if (= (mod i 3) 1) ( + 2 (/ (* 2 (- i 1)) 3))
      1))

(map #(euler-seq %) (range 0 20))

(def e  ( + 2.0 (cont-frac-iter (fn[i] 1) euler-seq  20)))

e   ;2.718281828459045
