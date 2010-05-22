(ns sicp-in-clojure.chap1.ex-2-30
  (:use clojure.contrib.repl-utils))

(defn square[x] (* x x))


(defn square-tree[x]
  (cond (not (list? x)) (square x)
	(empty? x) x
	:else (cons (square-tree (first x))
		    (square-tree (rest x)))))


(square-tree '(1 2 3 4 5 (6 7 8 9)))

(square-tree
 (list 1
       (list 2 (list 3 4) 5)
       (list 6 7)))
