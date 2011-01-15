(ns sicp-in-clojure.chap1.ex-1-44
  (:use clojure.contrib.repl-utils))

(defn average[coll]
  (let [cnt (count coll)]
    (reduce + (map #(/ % cnt) coll))))

(defn smooth[f] (let[dx 0.0001]
		  (fn[x] (average [(f (- x dx))
				   (f x)
				   (f (+ x dx))]
				  ))))

(defn square[x] (* x x))

((smooth square) 2)

(defn n-fold-smoothed[f n]  (nth (iterate smooth f) n))

((n-fold-smoothed square 5) 2)






