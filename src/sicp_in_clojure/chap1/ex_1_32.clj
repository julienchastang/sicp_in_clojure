(ns sicp-in-clojure.chap1.ex-1-32
  (:use clojure.contrib.math)
  (:use clojure.contrib.repl-utils))

(defn accumulate-iter [combiner null-value term a next b]
  (loop[cnt a sum null-value]
    (if (> cnt b) sum
	(recur (apply next [cnt])
	       (apply combiner [sum (apply term [cnt])])))))

(defn factorial[a b]
  (accumulate-iter * 1 identity a inc b))

(factorial 1 5)

; part b

(defn accumulate-recurse [combiner null-value term a next b]
  (if (> a b)
    null-value
    (combiner (term a)
       (accumulate-recurse combiner null-value term (next a) next b))))

(defn factorial-2[a b]
  (accumulate-recurse * 1 identity a inc b))

(factorial-2 1 5)
