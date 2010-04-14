(ns sicp-in-clojure.chap1.ex-1-33
  (:use clojure.contrib.repl-utils))

(defn filtered-accumulate-iter [combiner null-value term a next b my-filter]
  (loop[cnt a sum null-value]
    (if (> cnt b) sum
	(recur (next cnt)
	       (if (my-filter cnt b)
		 (apply combiner [sum (term cnt)]) sum)))))

(defn my-even?[a _]
  (even? a))

(defn even-factorial[a b]
  (filtered-accumulate-iter * 1 identity a inc b my-even?))

(even-factorial 1 8)

(defn prime?[a _]
  (if (< a 4) true
      (loop[cnt 2]
	(cond (= (mod a cnt) 0) false
	      (> ((fn[x] (* x x)) cnt) a) true
	      :else (recur (inc cnt))))))

(defn sum-of-squares-prime[a b]
  (filtered-accumulate-iter + 0 (fn[x] (* x x)) a inc b prime?))

(sum-of-squares-prime 1 5)

(defn my-gcd[a b]
  (if (= b 0)
    a
    (my-gcd b (mod a b))))

(defn product-of-relative-primes[a b]
  (filtered-accumulate-iter * 1 identity a inc b (fn[x y] (= 1 (my-gcd x y) ))))

(product-of-relative-primes 1 10)
