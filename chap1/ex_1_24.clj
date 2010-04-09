(ns sicp-in-clojure.chap1.ex-1-24
  (:use clojure.contrib.math))

;From Stu Halloway's book.
(defmacro bench [expr]
  `(let [start# (System/nanoTime)
	 result# ~expr]
     {:result result#
      :elapsed (- (System/nanoTime) start#)}))
		 
(defn square[x] (* x x))

(defn expmod[base exp m]
  (cond (= exp 0) 1
	(even? exp) (mod (square (expmod base (/ exp 2) m))  m)
	:else (mod (* base (expmod base (- exp 1) m))  m)))

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
;((1009 115000) (1013 115000) (1019 115000))

(search-for-primes 10000)
;((10007 119000) (10009 120000) (10037 117000))

(search-for-primes 100000)
;((100003 174000) (100019 122000) (100043 124000))

(search-for-primes 1000000)
;((1000003 172000) (1000033 124000) (1000037 122000))

; So far, it takes longer than the 2 previous exercises,
; but note that the time is growing very slowly (log(n) ??)
; so eventually, this method will win.

