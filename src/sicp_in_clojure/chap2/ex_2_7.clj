(ns sicp-in-clojure.chap1.ex-2-7
  (:use clojure.contrib.math))


(defn lower-bound[x] (min x))

(defn upper-bound[x] (max x))

(defn make-interval [a b] (list a b)) 
