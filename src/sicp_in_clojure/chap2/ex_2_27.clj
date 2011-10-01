(ns sicp-in-clojure.chap2.ex-2-27
  (:use clojure.contrib.repl-utils))

(defn count-leaves[x]
  (cond (not (list? x)) 1
	(empty? x) 0
	:else (+ (count-leaves (first x))
		 (count-leaves (rest x)))))

(count-leaves '(1 2 3 4 5 (6 7 8 9)))

(defn deep-reverse[x]
  (if (or (not (list? x)) (empty? x)) x
      (concat (deep-reverse (rest x))
	    (list (deep-reverse (first x))))))

(deep-reverse '(1 2 (1 (3 2 1) 2) (7 8 9)))
 



