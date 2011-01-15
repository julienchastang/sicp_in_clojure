(ns sicp-in-clojure.chap1.ex-1-39
  (:use clojure.contrib.repl-utils))


(defn tan-seq[i] (+ 1 (* 2 i)))

(map #(tan-seq %) (range 0 20))

(defn tan-iter [n d k]
  (loop [sum 0 i k]
    (if (zero? i) (/ n (- (d i) sum))
	(recur (/ (* n n)
		  (- (d i) sum))
	       (dec i)))))

(defn tan-cf[x] (tan-iter x tan-seq 8))

(tan-cf (/ Math/PI 4))
