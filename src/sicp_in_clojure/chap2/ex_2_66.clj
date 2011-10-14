(ns sicp-in-clojure.chap2.ex-2-66
  (:use [sicp-in-clojure.chap2.ex-2-63]
        [clojure.contrib.repl-utils]))

(defn lookup [key set]
  (cond (empty? set) false
        (= key (first (entry set))) (entry set)
        (< key (first (entry set))) (lookup key (left-branch set))
        (> key (first (entry set))) (lookup key (right-branch set))))

(def b '((1 'a) () ((2 'b) () ((3 'c) () ()))))

(lookup 3 b)

