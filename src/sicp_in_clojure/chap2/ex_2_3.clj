(ns sicp-in-clojure.chap2.ex-2-3
  (:use clojure.contrib.math))

(defn make-point[x y]
  (list x y))

(defn make-rectangle[x y w h]
  (list (make-point x y) w h))

(defn perimeter[r]
  ( + (* (second r) 2) (* (last r) 2)))

(defn area[r]
  (* (second r) (last r)))

(def a (make-rectangle  2 3 4 5))

(perimeter a)

(area a)

; Here is another representation.

(defn make-rectangle[x y w h]
  (list (make-point x y) w h
	( + (* w 2) (* h 2))
	(* w h)))

(defn perimeter[r]
     (nth r 3))

(defn area[r]
  (nth r 4))

(def a (make-rectangle  2 3 4 5))

(perimeter a)

(area a)

