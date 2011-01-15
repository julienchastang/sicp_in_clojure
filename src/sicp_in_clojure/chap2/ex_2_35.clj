(ns sicp-in-clojure.chap1.ex-2-35
  (:use clojure.contrib.repl-utils))


(defn fringe[x]
    (cond (not (list? x)) (list x)
	  (empty? x) x
	  :else (concat (fringe (first x))
			      (fringe (rest x)))))

(defn accumulate [op initial sequence]
  (if (empty? sequence) initial
      (op (first sequence)
	  (accumulate op initial (rest sequence)))))

(defn my-map [p sequence]
  (accumulate (fn[x y] (cons (p x) y)) nil sequence))

(defn count-leaves[x]
  (cond (not (list? x)) 1
	(empty? x) 0
	:else (+ (count-leaves (first x))
		 (count-leaves (rest x)))))

(defn count-leaves[t]
    (accumulate + 0 (my-map (fn[x] 1) (fringe t))))

(count-leaves '(1 2 3 4 5 (6 (7 (8)) 9)))
