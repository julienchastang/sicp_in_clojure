(ns sicp-in-clojure.chap2.ex-2-69
  (:use [sicp-in-clojure.chap2.ex-2-67]))


(defn successive-merge [s]
  (if (empty? (rest s))
    (first s)
    (make-code-tree (first s) (successive-merge (rest s)))))

(defn generate-huffman-tree [pairs]
  (successive-merge (make-leaf-set pairs)))

(def a '((A 5) (B 3) (C 1)))

(generate-huffman-tree a)
