(ns sicp-in-clojure.chap2.ex-2-39
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

(defn revers1 [sequence]
  (fold-right (fn [x y] (conj y x)) [] sequence))

(defn revers2 [sequence]
  (fold-left (fn [x y] (cons y x)) [] sequence))
  

