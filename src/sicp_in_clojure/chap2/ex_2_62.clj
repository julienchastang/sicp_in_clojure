(ns sicp-in-clojure.chap1.ex-2-62
  (:use midje.sweet))



(defn union-set [set1 set2]
  (cond (and (empty? set1) (empty? set2)) '()
	(empty? set2) set1
	(empty? set1) set2
        (< (first set1) (first set2))
        (cons (first set1) (union-set (next set1) set2))
	:else (cons (first set2) (union-set set1 (next set2)))))

(fact (union-set '(1 3) '(2 4)) =>
      '(1 2 3 4))

(fact (union-set nil '(2 4)) =>
      '(2 4))

(fact (union-set '(2 4) nil) =>
      '(2 4))

(fact (union-set '(2 4) '()) =>
      '(2 4))

(fact (union-set nil nil) =>
      '())
