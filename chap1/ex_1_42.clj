(ns sicp-in-clojure.chap1.ex-1-42
  (:use clojure.contrib.repl-utils))

(defn compose[f1 f2]
  (fn[x] (apply f1 [(apply f2 [x])])))

(defn square[x] (* x x))

((compose square inc) 6) ;49
