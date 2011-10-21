(ns sicp-in-clojure.chap2.ex-2-69
  (:use [sicp-in-clojure.chap2.ex-2-67])
  (:use [sicp-in-clojure.chap2.ex-2-68]))


(defn successive-merge [s]
  (if (empty? (rest s))
    (first s)
    (make-code-tree (first s) (successive-merge (rest s)))))

(defn generate-huffman-tree [pairs]
  (successive-merge (reverse (make-leaf-set pairs))))

(def a '((A 5) (B 3) (C 1)))

(generate-huffman-tree a)

(def pairs '((A 8) (B 3) (C 1) (D 1) (E 1) (F 1) (G 1) (H 1)))

(generate-huffman-tree pairs)

(encode '(A) (generate-huffman-tree pairs))

