(ns sicp-in-clojure.chap1.ex-1-31
  (:use clojure.contrib.math)
  (:use clojure.contrib.repl-utils))

(defn product-iter[term a next b init-val]
  (loop[cnt a sum init-val]
    (if (> cnt b) sum
	(recur (next cnt) (* sum (term cnt))))))

;factorial
(defn factorial[a b]
  (let[my-a (if (= a 0) 1 a)]
     (product-iter identity my-a inc b 1)))

(factorial 0 5)

;pi calc
(defn term[x]
  (let[a (* x 2)]
    (/ (* (+ 2 a) (+ 4 a)) (* (+ 3 a) (+ 3 a)))))

(defn pi[a b]
  (* 4.0 (product-iter term a inc b 1)))

(pi 0 1000)


; part b

(defn product-recurse [term a next b]
  (if (> a b)
    1
    (* (term a)
       (product-recurse term (next a) next b))))

(defn factorial-2[a b]
  (let[my-a (if (= a 0) 1 a)]
    (product-recurse identity my-a inc b)))

(factorial-2 0 5)
