(ns sicp-in-clojure.chap2.ex-2-41
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

(defn prime-sum? [triple]
  (prime? (+ (first triple)
	     (second triple)
	     (last triple))))

(defn make-triple-sum [triple]
  (list (first triple)
	(second triple)
	(last triple)
	(+ (first triple)
	   (second triple)
	   (last triple))))

(defn unique-triples [n]
  (flatmap (fn [i]
	     (flatmap 
	      (fn [j]
		(map
		 (fn [k] (list i j k))
		 (range 1 j)))
	      (range 1 i)))
	   (range 1 (inc n))))

(defn prime-sum-triples [n]
  (map make-triple-sum
       (filter prime-sum? (unique-triples n))))
