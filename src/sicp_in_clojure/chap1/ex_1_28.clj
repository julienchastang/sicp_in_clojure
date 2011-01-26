(ns sicp-in-clojure.chap1.ex-1-28
  (:use clojure.contrib.math)
  (:use clojure.contrib.repl-utils))

(defn square[x] (* x x))

(defn expmod[base exp m]
  (cond (= exp 0) 1
	(even? exp) (mod (square (expmod base (/ exp 2) m))  m)
	:else (mod (* base (expmod base (- exp 1) m))  m)))

(defn prime?[n]
  (every? true? (map #(= (expmod % (- n 1) n) (mod % n))
			(range 0 n))))

;11 12 are positive and negative controls
(map #(prime? %) [11 12 561 1105 1729 2465 2821 6601])
; (true false true true true true true true)

; Indeed Fermat's test incorrectly predicts Carmichael numbers
; as primes.