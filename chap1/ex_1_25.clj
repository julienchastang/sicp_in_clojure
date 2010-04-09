(ns sicp-in-clojure.chap1.ex-1-25
  (:use clojure.contrib.math))

;From Stu Halloway's book.
(defmacro bench [expr]
  `(let [start# (System/nanoTime)
	 result# ~expr]
     {:result result#
      :elapsed (- (System/nanoTime) start#)}))
		 
(defn square[x] (* x x))

(defn fast-expt-iter[b counter product]
  (loop[bee (square b) cnt (/ counter 2) prod product]
    (if (= cnt 0) prod
      (recur bee (dec cnt) (* bee prod)))))

(defn fast-expt[b counter]
  (if (odd? counter) (* b (fast-expt-iter b (dec counter) 1))
    (fast-expt-iter b counter 1)))

(defn expmod [base exp m]
    (mod (fast-expt base exp) m))

(defn fermat-test[n]
  (defn try-it[a]
    (= (expmod a n n) a))
  (try-it (+ 1 (Math/round (rand (- n 1))))))

(defn fast-prime?[n times]
    (cond (= times 0) true
	  (fermat-test n) (recur n (- times 1))
	   :else false))

(defn prime?[n]
  (conj {:num n}
	(bench (fast-prime? n 3))))

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
; ((1009 1364000) (1013 1356000) (1019 995000))

; Alyssa P. Hacker's methods seems to work, but it is much slower.
