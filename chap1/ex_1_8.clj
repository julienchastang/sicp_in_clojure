(ns sicp-in-clojure.chap1.ex-1-8)

(defn cube-iter [n]
  (let [improve 
        (fn[guess] (/ (+ (/ n (* guess guess)) (* 2 guess)) 3))
        good-enough?
        (fn[guess oldguess]
          (let [g (/ guess oldguess)]
            do (println "-->" guess " " oldguess)
            ( and (< g 1.01) (> g 0.99))))]
    (loop [guess 1.0 oldguess 0.5]
      (if (good-enough? guess oldguess)
	guess
	(recur (improve guess) guess)))))

(cube-iter 27)
