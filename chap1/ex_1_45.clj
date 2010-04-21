(ns sicp-in-clojure.chap1.ex-1-45
  (:use clojure.contrib.repl-utils))

(def tolerance 0.00001)

(defn abs[x] (Math/abs x))

(defn cos[x] (Math/cos x))

(defn sin[x] (Math/sin x))

(defn average[coll]
    (let [cnt (count coll)]
          (reduce + (map #(/ % cnt) coll))))

(defn fixed-point [f first-guess]
  (letfn [(close-enough?[v1 v2] (< (abs (- v1 v2)) tolerance))
	  (my-try[guess]
		 (loop [g guess]
		   (let [next (f g)] (if (close-enough? g next)
				       next
				       (recur next)))))]
    (my-try first-guess)))

(fixed-point cos 1.0)

(fixed-point (fn[y] (+ (sin y) (cos y)))
	     1.0)

(defn sqrt[x]
    (fixed-point (fn [y] (average [y (/ x y)]))
		                1.0))
(sqrt 4)

(defn cube[x]
    (fixed-point (fn [y] (average [y (/ x (* y y))]))
		                1.0))

(cube 27)

(defn nth-root[x n]
  (let [avg-damp (fn[y]
		   (average [y (/ x (Math/pow y (dec n)))]))
	iter-avg-damp (fn[y]
			(nth (iterate avg-damp y) 16))]
    (fixed-point iter-avg-damp 1.0)))

(nth-root 100000 10)

; 4th root requires 2 iterations
; 6th root requires 4 iterations
; 8th root requires 8 iterations
; 10th root requires 16 iterations

; It seems to growing by 2^n?