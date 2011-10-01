(ns sicp-in-clojure.chap2.ex-2-21
  (:use clojure.contrib.repl-utils))

(defn square[x] (* x x))

(defn square-list[items]
  (if (empty? items)
    nil
    (cons (square (first items))
	  (square-list (rest items)))))

(square-list (list 1 2 3 4))

(defn square-list[items]
  (map #(square %) items))

(square-list (list 1 2 3 4))
