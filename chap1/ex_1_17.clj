(ns sicp-in-clojure.chap1.ex-1-17
  (:use clojure.contrib.math))

(defn mult[a b]
  (if (= b 0) 0
      (+ a (mult a (- b 1)))))

(mult 6 5)

(defn fast-mult[a b]
  (letfn [(double [n] (+ n n))
	  (halve [n] (floor (/ n 2)))]
    (loop [mya a myb b accum 0]
;      (println "a" mya "b" myb "accum" accum)
      (if (= myb 1) (+ accum mya)
	  (recur (double mya) (halve myb)
		 (if (odd? myb) (+ accum mya) accum))))))

(fast-mult 6 6)
