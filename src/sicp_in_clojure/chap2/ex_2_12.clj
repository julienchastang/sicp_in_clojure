(ns sicp-in-clojure.chap1.ex-2-12
  (:use clojure.contrib.math))

(defn lower-bound[x] (apply min x))

(defn upper-bound[x] (apply max x))

(defn make-interval [a b] (list a b))

(defn make-center-width[c w]
    (make-interval (- c w) (+ c w)))

(defn make-center-percent[c p]
  (let [w (* c (/ p 100))]
    (make-center-width c w)))

(defn center[i]
    (/ (+ (lower-bound i) (upper-bound i)) 2))

(defn width[i]
    (/ (- (upper-bound i) (lower-bound i)) 2))

(defn percent[i]
  (let [c (center i)
	w (width i)]
    (* (/ w c) 100)))

(make-center-percent 6.8 10) ; 6.12 7.48

(percent (make-center-percent 6.8 10)) ; 10
