(ns sicp-in-clojure.chap2.ex-2-53
  (:use clojure.test)
  (:use midje.sweet))

(fact
 (list 'a 'b 'c) =>
 '(a b c))

(fact
 (list (list 'george)) =>
 '((george)))

(fact
 (rest '((x1 x2) (y1 y2)))
 '(y1 y2))

(defn memq [item x]
  (cond (empty? x) false
	(= item (first x)) x
	:else (memq item (rest x))))

(fact
 (memq 'red '((red shoes) (blue socks))) =>
 false)

(fact
 (memq 'red '(red shoes blue socks))
 '(red shoes blue socks))


(defn my-equal? [a b]
  (if (or (empty? a) (empty? b)) false
      (and (= (first a) (first b))
	   (my-equal? (rest a) (rest b)))))

(fact
 (my-equal? '(this is  a list)
	    '(this (is a) list)) =>
 false) 
