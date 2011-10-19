(ns sicp-in-clojure.chap2.ex-2-67
  (:use [sicp-in-clojure.chap2.ex-2-33 :only [append]]))

(defn make-leaf [symbol weight]
  (list 'leaf symbol weight))

(defn leaf? [object]
  (= (first object) 'leaf))

(defn symbol-leaf [x]
  (first (rest x)))

(defn weight-leaf [x]
  (first (rest (rest x))))

(declare symbols weight)

(defn make-code-tree [left right]
  (list left
        right
        (append (symbols left) (symbols right))
        (+ (weight left) (weight right))))

(defn left-branch [tree]
  (first tree))

(defn right-branch [tree]
  (first (rest tree)))

(defn symbols [tree]
  (if (leaf? tree)
    (list (symbol-leaf tree))
    (first (rest (rest tree)))))

(defn weight [tree]
  (if (leaf? tree)
    (weight-leaf tree)
    (first (rest (rest (rest tree))))))


(declare choose-branch)

(defn decode [bits tree]
  (letfn [(decode-1 [bits current-branch]
            (if (empty? bits)
              '()
              (let [next-branch
                    (choose-branch (first bits) current-branch)]
                (if (leaf? next-branch)
                  (cons (symbol-leaf next-branch)
                        (decode-1 (rest bits) tree))
                  (decode-1 (rest bits) next-branch)))))]
    (decode-1 bits tree)))

(defn choose-branch [bit branch]
  (cond (= bit 0) (left-branch branch)
        (= bit 1) (right-branch branch)
        :else (throw (Exception. "bad bit --- CHOOSE-BRANCH"))))

(defn adjoin-set [x set]
  (cond (empty? set) (list x)
        (< (weight x) (weight (first set))) (cons x set)
        :else (cons (first set)
                    (adjoin-set x (rest set)))))

(defn make-leaf-set [pairs]
  (if (empty? pairs)
    '()
    (let [pair (first pairs)]
      (adjoin-set (make-leaf (first pair)
                             (first (rest pair)))
                  (make-leaf-set (rest pairs))))))

(def sample-tree (make-code-tree (make-leaf 'A 4)
                                 (make-code-tree
                                  (make-leaf 'B 2)
                                  (make-code-tree (make-leaf 'D 1)
                                                  (make-leaf 'C 1)))))

(def sample-message '(0 1 1 0 0 1 0 1 0 1 1 1 0))

(decode sample-message sample-tree)
