(ns sicp-in-clojure.chap2.ex-2-38
  (:use clojure.contrib.repl-utils))

(defn fold-right [op initial sequence]
  (if (empty? sequence) initial
      (op (first sequence)
	  (fold-right op initial (rest sequence)))))

(defn fold-left [op initial sequence]
  (letfn [(iter [result rst]
		(if (empty? rst)
		  result
		  (iter (op result (first rst))
			(rest rst))))]
    (iter initial sequence)))

(fold-right / 1 (vector 1 2 3))

(fold-left / 1 (vector 1 2 3))

(fold-right vector nil (vector 1 2 3))

(fold-left vector nil (vector 1 2 3))

(fold-right + 1 (vector 1 2 3))

(fold-left + 1 (vector 1 2 3))

;; In order to have fold-left and fold-right
;; to return the same answer, the operator
;; has to be commutative. 
