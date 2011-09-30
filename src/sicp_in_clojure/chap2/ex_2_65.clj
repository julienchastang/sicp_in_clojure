(ns sicp-in-clojure.chap2.ex-2-65
  (:use [sicp-in-clojure.chap2.ex-2-62]
        [sicp-in-clojure.chap2.ex-2-63]
        [sicp-in-clojure.chap2.ex-2-64]))

(def x (reduce #(adjoin-set %2 %1) '() (my-rnds)))

(def y (reduce #(adjoin-set %2 %1) '() (my-rnds)))

(defn union-tree-set [s1 s2]
  (list->tree (union-set (tree->list-2 s1)
                         (tree->list-2 s2))))

;; I believe the running time is O(n)
(union-tree-set x y)

(defn intersection-set [set1 set2]
  (cond (or (empty? set1) (empty? set2)) '()
        (< (first set1) (first set2))
        (intersection-set (next set1) set2)
        (= (first set1) (first set2))
        (cons (first set1) (intersection-set (next set1) (next set2)))
	:else (intersection-set set1 (next set2))))

(intersection-set [1 3 5] [2 4 5 12 13])

(defn intersection-tree-set [s1 s2]
  (list->tree (intersection-set (tree->list-2 s1)
                         (tree->list-2 s2))))


(intersection-tree-set x y)


