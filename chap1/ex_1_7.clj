(ns sicp-in-clojure.chap1.ex-1-7)

(defn sqrt-iter [n]
  (let [average-pair
        (fn[x y]
          (/ (+ x y) 2))
        improve
        (fn[guess]
          (average-pair guess (/ n guess)))
        good-enough?
        (fn[guess]
          (< (Math/abs (- (* guess guess) n)) 0.0001))]
    (loop [guess 1.0]
      (if (good-enough? guess)
	guess
	(recur (improve guess))))))

(sqrt-iter 4)

(sqrt-iter 4000000000000000000000000000000000000)
;It actually performs OK for very large numbers.
;It seems to return the same answer as Math/sqrt.
;Convergence to the answer may be slow.

(sqrt-iter 0.00000004)
;The predetermined tolerance does not work for small numbers
;because small numbers are below the tolerance.

(defn sqrt-iter2 [n]
  (let [average-pair
        (fn[x y]
          (/ (+ x y) 2))
        improve
        (fn[guess]
          (average-pair guess (/ n guess)))
        good-enough?
        (fn[guess oldguess]
          (let [g (/ guess oldguess)]
            do (println "-->" guess " " oldguess)
            ( and (< g 1.01) (> g 0.99))))]
    (loop [guess 1.0 oldguess 0.5]
      (if (good-enough? guess oldguess)
	guess
	(recur (improve guess) guess)))))

(sqrt-iter2 4)

(sqrt-iter2 4000000000000000000000000000000000000)

(sqrt-iter2 0.00000004)
;Clearly a better option for smaller number