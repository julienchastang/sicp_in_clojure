(ns sicp-in-clojure.chap1.ex-1-37
  (:use clojure.contrib.repl-utils))

;recursive solution.
(defn cont-frac [n d k]
  (if (zero? k) (/ n d)
      (/ n (+ d (cont-frac n d (dec k))))))

;(map #(/ 1.0 (cont-frac 1 1 %)) (range 0 20))

(def golden-number (/ 1.0 (cont-frac 1 1 13)))

; k = 13 to get an approximation that is accurate to 4 decimal places

golden-number

;iterative solution
(defn cont-frac-iter [n d k]
  (loop [sum 0 count k]
    (if (< count 0) sum
	(recur (/ n (+ d sum)) (dec count))))) 

(def golden-number (/ 1.0 (cont-frac-iter 1 1 13))

;lambda iterative solution
(defn cont-frac-iter [n d k]
  (loop [sum 0 count k]
    (if (neg? count) sum
	(recur (/ (n count)
		  (+ (d count) sum))
	       (dec count))))) 


(def golden-number (/ 1.0 (cont-frac-iter (fn[i] 1) (fn[i] 1) 13)))
