(ns sicp-in-clojure.chap1.ex-1-35
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

(defn golden-number[x] (fixed-point (fn[x] (+ 1 (/ 1 x))) 1.0 0.00001))

(golden-number 1); 1.6180257510729614 
