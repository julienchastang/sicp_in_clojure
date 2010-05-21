(ns sicp-in-clojure.chap1.ex-2-29
  (:use clojure.contrib.repl-utils))

(defn make-mobile[left right]
    (list left right))

(defn make-branch[length structure]
  (make-mobile length structure))

(defn left-branch[mob] (first mob))

(defn right-branch[mob] (last mob))

(defn branch-length[branch] (first branch))

(defn branch-structure[branch] (last branch))

(defn leaf?[mob]
  (not (list? (branch-structure mob))))

(defn total-weight[mob]
  (if (leaf? mob)
    (branch-structure mob)
    (+ (total-weight (left-branch mob))
       (total-weight (right-branch mob)))))

(defn sub-balanced?[mob]
  (=  (* (branch-length (left-branch mob)) (branch-structure (left-branch mob)))
      (* (branch-length (right-branch mob)) (branch-structure (right-branch mob)))))

(defn balanced?[mob]
  (if (and (leaf? (left-branch mob))
	   (leaf? (right-branch mob)))
    (sub-balanced? mob)
      (and (balanced? (left-branch mob))
	   (balanced? (right-branch mob)))))

;make a mobile

(def a (make-branch 3 4))

(def b (make-branch 4 3))

(total-weight (make-mobile a b))

(def c (make-branch 1 7))

(def d (make-branch 3 9))

(def e (make-mobile a b))

(def f (make-mobile c d))

(total-weight (make-mobile e f))

; balanced?

(balanced? (make-mobile a b))

(balanced? (make-mobile e f))
