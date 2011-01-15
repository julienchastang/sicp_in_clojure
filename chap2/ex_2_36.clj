(ns sicp-in-clojure.chap1.ex-2-36
  (:use clojure.contrib.repl-utils))


(defn accumulate [op initial sequence]
  (if (empty? sequence) initial
      (op (first sequence)
	  (accumulate op initial (rest sequence)))))

(defn foo [sequence]
  (if (empty? sequence) nil
      (first sequence
	     (foo (rest sequence)))))

