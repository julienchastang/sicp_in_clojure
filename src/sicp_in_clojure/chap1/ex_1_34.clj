(ns sicp-in-clojure.chap1.ex-1-34
  (:use clojure.contrib.repl-utils))

(defn f[g]
    (apply g [2]))

(defn square[x] (* x x))

(f square)

(f (fn[z] (* z (+ z 1))))

(f f)

;applicative order evaluation
(defn f[g] (apply g [2]))

(f f)

(apply f [2])

; In clojure, calling (f f) eventually results in calling

; (apply f [2]) which leads to
; java.lang.Integer cannot be cast to clojure.lang.IFn
