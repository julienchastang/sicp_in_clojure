(ns sicp-in-clojure.chap2.ex-2-40
  (:use clojure.contrib.repl-utils)
  (:require [sicp-in-clojure.chap2.ex-1-22 :as ex22])
  (:require [sicp-in-clojure.chap2.ex-2-33 :as ex33]))

(defn accumulate [op initial sequence]
  (if (empty? sequence) initial
      (op (first sequence)
	  (accumulate op initial (rest sequence)))))

(defn prime? [n]
  (:result (ex22/prime? n)))

(defn flatmap [proc seq]
  (accumulate ex33/append nil (map proc seq)))

(defn prime-sum? [pair]
  (prime? (+ (first pair)
	       (last pair))))

(defn make-pair-sum [pair]
  (list (first pair) (last pair)
	(+ (first pair) (last pair))))

(defn unique-pairs [n]
  (flatmap (fn [i]
	     (map (fn [j] (list i j))
		  (range 1  i)))
	   (range 1 (+ n 1) )))

(defn prime-sum-pairs [n]
  (map make-pair-sum
       (filter prime-sum? (unique-pairs n))))

