(ns sicp-in-clojure.chap1.ex-1-16
  (:use clojure.contrib.repl-utils))

(defn square[x] (* x x))

(defn fast-expt-iter[b counter product]
  (loop[bee (square b) cnt (/ counter 2) prod product]
    (if (= cnt 0) prod
      (recur bee (dec cnt) (* bee prod)))))

(defn fast-expt[b counter]
  (if (odd? counter) (* b (fast-expt-iter b (dec counter) 1))
    (fast-expt-iter b counter 1)))

(fast-expt 2 10)