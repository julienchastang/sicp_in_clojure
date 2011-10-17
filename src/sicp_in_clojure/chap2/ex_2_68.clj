(ns sicp-in-clojure.chap2.ex-2-68
  (:use [sicp-in-clojure.chap2.ex-2-67])
  (:use [sicp-in-clojure.chap2.ex-2-33 :only [append]]))

(defn encode-symbol [symbol tree]
  (cond (leaf? tree) '()
        (= (first (symbols tree)) symbol)
        (cons 0 (encode-symbol symbol (left-branch tree)))
        :else (cons 1 (encode-symbol symbol (right-branch tree)))))

(defn encode [message tree]
  (if (empty? message)
    '()
    (append (encode-symbol (first message) tree)
            (encode (rest message) tree))))

(encode '(A D A B B C A) sample-tree)

;; Output is the same as the sample message from the last exercise.
