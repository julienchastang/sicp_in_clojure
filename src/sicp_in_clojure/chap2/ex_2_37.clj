(ns sicp-in-clojure.chap2.ex-2-37
  (:use clojure.contrib.repl-utils))

(defn accumulate [op initial sequence]
  (if (empty? sequence) initial
      (op (first sequence)
	  (accumulate op initial (rest sequence)))))

(defn accumulate-n [op initial seqs]
  (if (empty? (first seqs)) nil
      (cons (accumulate op initial (map first seqs))
	    (accumulate-n op initial (map rest seqs)))))

(defn dot-product [v w]
  (accumulate + 0 (map * v w)))

(defn matrix-*-vector [m v]
  (map #(dot-product v %) m))

(defn transpose[mat]
  (accumulate-n cons [] mat))

(defn matrix-*-matrix [m n]
  (let [cols (transpose n)]
    (map #(matrix-*-vector cols %) m)))
  

(def a [[1 2 3] [4 5 6] [7 8 9]])

(def b [1 2 3])
