(ns sicp-in-clojure.chap2.ex-2-28
  (:use clojure.contrib.repl-utils))

(defn fringe[x]
  (cond (not (list? x)) (list x)
	(empty? x) x
	:else (concat (fringe (first x))
		      (fringe (rest x)))))

(fringe '(1 2 (1 (3 2 1) 2) (7 8 9)))
