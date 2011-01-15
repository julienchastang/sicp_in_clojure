(ns sicp-in-clojure.chap1.ex-1-10
  (:use clojure.contrib.repl-utils))

(defn ack[x y]
  (cond (= y 0) 0
    (= x 0) (* 2 y)
    (= y 1) 2
    :else (ack (- x 1)
            (ack x (- y 1)))))

(ack 1 10) ;1024 2^10
(ack 2 4) ;65536 2^16
(ack 3 3) ;65536 2^16

(defn log2[x] (/ (Math/log x) (Math/log 2)))

(map #(ack 2 %) (range 0 5))
(map #(log2 (ack 2 %)) (range 0 5))

(defn two-to-the-two[n]
  (loop [cnt n result 0]
    (if (< cnt 0) result
	(recur (dec cnt) (Math/pow 2 result))))) 

(map #(two-to-the-two %) (range 0 5))

(defn f [n] (ack 0 n)) ; 2*n
(defn g [n] (ack 1 n)) ; 2^n
(defn h [n] (ack 2 n)) ; 2^2^2...   n times, n = 0 ==> 0
(defn k [n] (* 5 n n)) ; 5*(n^2)

(map #(ack 3 %) (range 0 4))

;The Ackermann function grows incredibly fast.
;http://en.wikipedia.org/wiki/Ackermann_function
