(ns sicp-in-clojure.chap2.ex-2-69
  (:use [sicp-in-clojure.chap2.ex-2-67]))

(defn successive-merge [s]
  (letfn [(f [[s w]] (make-leaf s w))]
    (if (= (count s) 1)
      (f (last s))
      (make-code-tree (f (first s)) (successive-merge (rest s))))))

(defn successive-merge [s]
  (if (= (count s) 1)
    (last s)
    (make-code-tree (first s) (successive-merge (rest s)))))

(defn generate-huffman-tree [pairs]
  (successive-merge (make-leaf-set pairs)))

(def a '((A 5) (B 3)))

(generate-huffman-tree a)
