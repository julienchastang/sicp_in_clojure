(ns sicp-in-clojure.chap1.ex-2-33
  (:use clojure.contrib.repl-utils))


(defn square[x] (* x x))

(defn accumulate [op initial sequence]
  (if (empty? sequence) initial
    (op (first sequence)
	(accumulate op initial (rest sequence)))))

(accumulate * 1 (list 1 2 3 4 5))

(defn my-map [p sequence]
  (accumulate (fn[x y] (cons (p x) y)) nil sequence))

(my-map square '(1 2 3))

(defn append[seq1 seq2]
    (accumulate cons seq2 seq1))

(append '(1 2 3) '(6 7 8))

(let [x (ref 0)]
  (defn incr1 []
    (dosync (ref-set x (inc @x)))))

(defn length[sequence]
  (accumulate (fn[x y] (incr1)) nil sequence))

(length '(1 2 3 3 4 5 6 7))
