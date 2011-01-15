(ns sicp-in-clojure.chap1.ex-1-36
  (:use clojure.contrib.repl-utils))

(defmacro take-final
  "Returns the last item of a sequence that satisfies the predicate."
  [pred coll]
  (list 'last (list 'take-while pred coll)))

(macroexpand-1 '(take-final (fn[x] (> 5 x)) (iterate inc 1)))

(defn fixed-point-helper[f]
  (fn[v] (let [x (first v)
	       a ( f x)
	       b ( f a)]
	   [b (Math/abs (- a b))])))

(defn fixed-point[f first-guess thresshold]
  (let [f-closure (fixed-point-helper f)]
    (first (take-final #(> (last %) thresshold)
		       (iterate f-closure [first-guess 1])))))

(defn ex136[x]
  (fixed-point (fn[x] (/ (Math/log 1000) (Math/log x))) 10.0 0.00001))

(def x (ex136 1000))
; 4.555540933824255 

(Math/pow x x)

;-----------------

(defn fixed-point[f first-guess thresshold]
  (let [f-closure (fixed-point-helper f)]
    (take-while #(> (last %) thresshold)
		(iterate f-closure [first-guess 1]))))

(defn ex136[x]
  (fixed-point (fn[x] (/ (Math/log 1000) (Math/log x))) 10.0 0.00001))

(count (ex136 1000))
; It takes 17 iterations to arrive at a solution.

;-----------------

;with average damping, I think
(defn fixed-point-helper[f]
  (fn[v] (let [x (first v)
	       a (apply f [x])
	       b (apply f [a])
	       c (/ (+ a b) 2)]
	   [c (Math/abs (- a c))])))

(defn fixed-point[f first-guess thresshold]
  (let [f-closure (fixed-point-helper f)]
    (take-while #(> (last %) thresshold)
		(iterate f-closure [first-guess 1]))))

(defn ex136[x]
  (fixed-point (fn[x] (/ (Math/log 1000) (Math/log x))) 10.0 0.00001))

(count (ex136 1000))

;With average damping it gets to a solution in 6 steps
