(ns sicp-in-clojure.chap2.ex-2-34
  (:use clojure.contrib.repl-utils))

(let [x (ref -1)]
  (defn incr1 []
    (dosync (ref-set x (inc @x)))))

(defn accumulate [op initial sequence]
  (if (empty? sequence) initial
      (op (first sequence)
	  (accumulate op initial (rest sequence)))))

(defn horner-eval[y coefficient-sequence]
  (accumulate (fn[this-coeff higher-terms]
		(+ (* this-coeff (Math/pow y (incr1)))
		   higher-terms))
	      0
	      (reverse coefficient-sequence)))


(def a (horner-eval 2 (list 1 3 0 5 0 1)))
a

