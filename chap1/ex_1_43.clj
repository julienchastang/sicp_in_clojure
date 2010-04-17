(ns sicp-in-clojure.chap1.ex-1-43
  (:use clojure.contrib.repl-utils))

(defn square[x] (* x x))

(defn compose[f1 f2]
  (fn[x] (apply f1 [(apply f2 [x])])))

(defn repeated[f n]
  (cond (= n 0) f
	(< n 0) f
	:else (recur (compose f f) (- n 2))))

((repeated square 2) 5)

;clojure version

(take 5 (iterate square 2))



