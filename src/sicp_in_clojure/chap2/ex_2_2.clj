(ns sicp-in-clojure.chap2.ex-2-2
  (:use clojure.contrib.math))

(defn average[coll]
  (let [cnt (count coll)]
    (reduce + (map #(/ % cnt) coll))))

(defn make-point[x y]
  (list x y))

(defn x-point[p]
  (first p))

(defn y-point[p]
  (last p))

(defn make-segment[& p]
  (apply list p))

(defn start-segment[s]
  (first s))

(defn end-segment[s]
  (last s))

(defn midpoint-segment[s]
  (make-point (average (map #(x-point %) s))
	      (average (map #(y-point %) s))))

(def a (make-segment (make-point 1 1) (make-point 2 2) (make-point 3 3)))

(midpoint-segment a)
