(ns sicp-in-clojure.chap1.ex-1-23
  (:use clojure.contrib.math))

;From Stu Halloway's book.
(defmacro bench [expr]
  `(let [start# (System/nanoTime)
	 result# ~expr]
     {:result result#
      :elapsed (- (System/nanoTime) start#)}))
		 
(defn square[x] (* x x))

(defn divides?[a b]
    (= (mod b a) 0))

(defn my-next[n]
  ( if (= n 2) 3 (+ n 2)))  

(defn find-divisor[n test-divisor]
  (cond (> (square test-divisor) n) n
	(divides? test-divisor n) test-divisor
	:else (recur n (my-next test-divisor))))

(defn smallest-divisor[n]
    (find-divisor n 2))

(defn prime?[n]
  (conj {:num n}
	(bench (= n (smallest-divisor n)))))

(defn prime-seq[n]
  (map #(list (:num %) (:elapsed %))
       (filter #(true? (:result %))
	       (map #(prime? (+ % n)) (iterate inc 2)))))

(defn search-for-primes[n]
  (let [x (if (odd? n) n (inc n))]
    (map #(nth (prime-seq x) %) [0 1 2])))

;first number is the prime number, second is elapsed time in nanoseconds
;to determine the prime.

(search-for-primes 1000)
;((1009 4000) (1013 4000) (1019 3000))

(search-for-primes 10000)
;((10007 7000) (10009 7000) (10037 7000))

(search-for-primes 100000)
;((100003 24000) (100019 23000) (100043 23000))

(search-for-primes 1000000)
;((1000003 69000) (1000033 61000) (1000037 63000))

; This version of search_for_primes is indeed faster
; compared to ex_1_22.clj. Because the problem is
; growing proportional to √n, then the my-next
; function should provide a speedup up of √(n/2).