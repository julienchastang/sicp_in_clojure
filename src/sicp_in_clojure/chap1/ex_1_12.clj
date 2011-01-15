(ns sicp-in-clojure.chap1.ex-1-12
  (:use clojure.contrib.repl-utils))

(defn pascal-row[x]
  (vec (concat [1]
         (map #(reduce + %)
           (partition 2 1 x))
         [1])))

(take 5 (iterate pascal-row [1]))
