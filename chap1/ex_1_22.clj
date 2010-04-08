(ns sicp-in-clojure.chap1.ex-1-22
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

(defn find-divisor[n test-divisor]
  (cond (> (square test-divisor) n) n
	(divides? test-divisor n) test-divisor
	:else (recur n (+ test-divisor 1))))

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
;((1009 5000) (1013 4000) (1019 4000))

(search-for-primes 10000)
;((10007 11000) (10009 11000) (10037 11000))

(search-for-primes 100000)
;((100003 41000) (100019 35000) (100043 34000))

(search-for-primes 1000000)
;((1000003 107000) (1000033 101000) (1000037 101000))

;These data show  the order of growth is roughly Θ(√n)
;Increasing by a factor of 10 leads to √10 (3.16) growth
;in time approximately.