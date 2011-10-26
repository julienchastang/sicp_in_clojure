(ns sicp-in-clojure.chap2.ex-2-71
  (:use [sicp-in-clojure.chap2.ex-2-68 :only [encode]])
  (:use [sicp-in-clojure.chap2.ex-2-69 :only [generate-huffman-tree]])
  (:use [clojure.contrib.math :only [expt]]))


(defn pair-gen [n]  
  (reverse
   (for [x (range n)]
     (list
      (-> x (+ 97) char str symbol)
      (expt 2 x)))))

(defn encfl [n]
  (let [p (pair-gen n)
        fs (ffirst p)
        ls (first (last p))
        tree (generate-huffman-tree p)]
    (list (encode (list fs) tree) (encode (list ls) tree))))

;; Encoding for the most frequent symbol will always be one bit

;; Encdoding for the least frequent symbol will grow proportional to n (n - 1 to be exact).