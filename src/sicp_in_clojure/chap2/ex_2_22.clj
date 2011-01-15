(ns sicp-in-clojure.chap1.ex-2-22
  (:use clojure.contrib.repl-utils))

; clojure.core/cons
; ([x seq])
;  Returns a new seq where x is the first element and seq is
;    the rest.


(defn square[x] (* x x))

(defn square-list [items]
  (letfn [(iter [things answer]
		(println answer)
		(if (empty? things)
		  answer
		  (iter (rest things) 
			(cons (square (first things))
			      answer))))]
    (iter items nil)))

(square-list (list 1 2 3 4))

; (16 9 4 1)
; cons pushes arguments to the front of the list.

(defn square-list [items]
  (letfn [(iter [things answer]
		(println answer)
		(if (empty? things)
		  answer
		  (iter (rest things) 
			(cons answer
			      (square (first things))))))]
    (iter items nil)))

; (square-list (list 1 2 3 4))

(def a 1)

; This does not work b/c in clojure the 1st argument to cons is
; an element, not a list.


