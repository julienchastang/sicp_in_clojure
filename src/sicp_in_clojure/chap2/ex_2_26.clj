(ns sicp-in-clojure.chap2.ex-2-26
  (:use clojure.contrib.repl-utils))

(def x (list 1 2 3))

(def y (list 4 5 6))

(concat x y)
; (1 2 3 4 5 6)

(cons x y)
; ((1 2 3) 4 5 6)

(list x y)
; ((1 2 3) (4 5 6))
