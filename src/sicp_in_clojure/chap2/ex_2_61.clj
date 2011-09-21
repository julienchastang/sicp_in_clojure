(ns sicp-in-clojure.chap1.ex-2-61
  (:use midje.sweet))

(defn adjoin-set [x set]
  (cond
   (or (nil? set) (empty? set)) (list x)
   (= x  (first set)) set
   (< x  (first set)) (cons x set)
   :else (cons (first set) (adjoin-set x (next set)))))

(fact
 (adjoin-set 1 '(1 3 4 5 6 7)) =>
 '(1 3 4 5 6 7))

(fact
 (adjoin-set 2 '(1 3 4 5 6 7)) =>
 '(1 2 3 4 5 6 7))

(fact
 (adjoin-set 0 '(1 3 4 5 6 7)) =>
 '(0 1 3 4 5 6 7))

(fact
 (adjoin-set 42 '(1 3 4 5 6 7)) =>
 '(1 3 4 5 6 7 42))

(fact
 (adjoin-set 42 '()) =>
 '(42))

(fact
 (adjoin-set 42 nil) =>
 '(42))
