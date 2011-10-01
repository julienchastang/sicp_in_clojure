(ns sicp-in-clojure.chap2.ex-2-8
  (:use clojure.contrib.math))

(defn lower-bound[x] (min x))

(defn upper-bound[x] (max x))

(defn make-interval [a b] (list a b)) 

(defn add-interval [x y]
  (make-interval (+ (lower-bound x) (lower-bound y))
  		 (+ (upper-bound x) (upper-bound y))))

(defn sub-interval [x y]
  (make-interval (- (upper-bound x) (lower-bound y))
  		 (- (lower-bound x) (upper-bound y))))

