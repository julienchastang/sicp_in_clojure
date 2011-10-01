(ns sicp-in-clojure.chap2.ex-2-31
  (:use clojure.contrib.repl-utils))

(defn square[x] (* x x))

(def tree (list 1 (list 2 (list 3 4) 5) (list 6 7)))

(defn tree-map[f x]
  (cond (not (list? x)) (f x)
	(empty? x) x
	:else (cons (tree-map f (first x))
		    (tree-map f (rest x)))))

(defn square-tree[tree]
  (tree-map square tree))

(square-tree tree)
