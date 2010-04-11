(ns sicp-in-clojure.chap1.ex-1-30
  (:use clojure.contrib.math)
  (:use clojure.contrib.repl-utils))

(defn sum-iter[term a next b]
  (loop[cnt a sum 0]
    (if (> cnt b) sum
	(recur (next cnt) (+ sum (term cnt))))))

(defn sum-integer[a b]
  (sum-iter identity a inc b))