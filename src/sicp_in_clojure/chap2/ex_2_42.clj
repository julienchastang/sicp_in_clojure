(ns sicp-in-clojure.chap2.ex-2-42
  (:use clojure.contrib.repl-utils)
  (:require [sicp-in-clojure.chap2.ex-2-33 :as ex33]))

(def empty-board (list))

(defn accumulate [op initial sequence]
  (if (empty? sequence) initial
      (op (first sequence)
	  (accumulate op initial (rest sequence)))))

(defn flatmap [proc seq]
  (accumulate ex33/append nil (map proc seq)))

(defn adjoin-position [r k coll]
  (cons (list r k) coll))

(defn safe? [k positions]
  (let [a (first positions)
	r (rest positions)]
    (not (some true?
	       (map
		(fn [p] (or (= (first p)
			       (first a))
			    (= (last p)
			       (last a))
			    (= 1 (Math/abs (* 1.0 (/ (- (first p) (first a))
						     (- (last p) (last a))))))))
		r)))))

(defn queens [board-size]
  (letfn [(queens-cols [k]
		       (if (= k 0)
			 (list empty-board)
			 (filter
			  (fn [positions] (safe? k positions))
			  (flatmap (fn [rest-of-queens]
				     (map (fn [new-row]
					    (adjoin-position new-row k rest-of-queens))
					  (range 1 (+ board-size 1))))
				   (queens-cols (- k 1))))))]
    (queens-cols board-size)))


