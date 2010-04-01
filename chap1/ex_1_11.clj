(ns sicp-in-clojure.chap1.ex-1-11
  (:use clojure.contrib.repl-utils))

;recursive soluion
(defn ex111[n]
  (if (< n 3)
    n
    (+ (ex111 (- n 1)) 
       (* (ex111 (- n 2)) 2)
       (* (ex111 (- n 3)) 3))))

(ex111 5)

;Don't know how to do iterative solution
