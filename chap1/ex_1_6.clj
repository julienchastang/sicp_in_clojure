(ns sicp-in-clojure.chap1.ex-1-6)

(defn new-if[predicate then-clause else-clause]
  (cond predicate then-clause
    :else else-clause))

(new-if (= 2 3) 0 5) ;5

(new-if (= 1 1) 0 5) ;0

(defn square[x] (* x x))

;sqrt-iter with regular if
(defn sqrt-iter [n]
  (let [average-pair (fn[x y]  (/ (+ x y) 2))
        improve (fn[guess] (average-pair guess (/ n guess)))
        good-enough? (fn[guess]
		       (< (Math/abs (- (square guess) n)) 0.0001))]
    (loop [guess 1.0]
      (if (good-enough? guess)
	guess
	(recur (improve guess))))))

(sqrt-iter 10) 

;sqrt-iter with regular new-if
(defn sqrt-iter [n]
  (let [average-pair (fn[x y]  (/ (+ x y) 2))
        improve (fn[guess] (average-pair guess (/ n guess)))
        good-enough? (fn[guess]
		       (< (Math/abs (- (square guess) n)) 0.0001))]
    (loop [guess 1.0]
      (new-if (good-enough? guess)
	      guess
	      (recur (improve guess))))))
;new-if is a function not a form. This change will disallow tail call recursion
;because new-if is now the final invocation in the loop body leading to compilation
;errors.