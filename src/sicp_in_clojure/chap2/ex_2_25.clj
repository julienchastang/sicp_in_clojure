(ns sicp-in-clojure.chap2.ex-2-25
  (:use clojure.contrib.repl-utils))

(def a '(1 3 (5 7) 9))
(first (rest (first (rest (rest a)))))

(def b '((7)))
(first (first b))

(def c '(1 (2 (3 (4 (5 (6 7)))))))
(first (rest (first (rest (first (rest (first (rest (first (rest (first (rest c))))))))))))



