(ns sicp-in-clojure.chap1.ex-1-40
  (:use clojure.contrib.repl-utils))

(defn average[coll]
  (let [cnt (count coll)]
    (reduce + (map #(/ % cnt) coll))))

(defn square[x] (* x x))

(defn average-damp[f]
  (fn[x] (average [x (f x)])))

((average-damp square) 10)

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

(defn sqrt [x]
  (fixed-point (average-damp (fn[y] (/ x y))) 1.0 0.0001))

(sqrt 4)

(defn cube-root [x]
  (fixed-point (average-damp (fn[y] (/ x (square y)))) 1.0 0.0001))

(cube-root 27)
; --------

(defn deriv[g]
  (fn [x] (let [dx 0.0000001]
	    (/ (- (g (+ x dx)) (g x)) dx))))

(defn cube[x] (* x x x))

((deriv cube) 5)

(defn newton-transform[g]
  (fn[x]
    (- x (/ (g x) ((deriv g) x)))))

(defn newtons-method[g guess]
  (fixed-point (newton-transform g) guess 0.0000001))


(defn sqrt[x]
  (newtons-method (fn[y] (- (square y) x))
		  1.0))

(sqrt 4)

(defn cubic[a b c] (fn[x] (+ (* x x x) (* a x x) (* b x) c)))

; Finding three solutions. Notice the rounding.
(set (map #(/
	    (Math/round (* 100
			   (newtons-method (cubic -1 -5 3) %)))
	    100.)
	  (range -10 10)))

