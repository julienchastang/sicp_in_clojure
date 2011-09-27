(ns sicp-in-clojure.chap1.ex-2-63)

(defn entry [tree]
  (first tree))

(defn left-branch [tree]
  (fnext tree))

(defn right-branch [tree]
  (first (nnext tree)))

(defn make-tree [entry left right]
  (list entry left right))

(defn element-of-set? [x set]
  (cond (empty? set) false
        (= x (entry set)) true
        (< x (entry set)) (element-of-set? x (left-branch set))
        (> x (entry set)) (element-of-set? x (right-branch set))))


(defn adjoin-set [x set]
  (cond
   (empty? set) (make-tree x '() '())
   (= x (entry set)) set
   (< x (entry set)) (make-tree (entry set)
                                (adjoin-set x (left-branch set))
                                (right-branch set))
   (> x (entry set)) (make-tree (entry set)
                                (left-branch set)
                                (adjoin-set x (right-branch set)))))

(defn tree->list-1 [tree]
  (if (empty? tree) '()
      (concat (tree->list-1 (left-branch tree))
              (cons (entry tree)
                    (tree->list-1 (right-branch tree))))))

(defn tree->list-2 [tree]
  (letfn [(copy-to-list [tree result-list]
            (if (empty? tree)
              result-list
              (copy-to-list (left-branch tree)
                            (cons (entry tree)
                                  (copy-to-list (right-branch tree)
                                                result-list)))))]
    (copy-to-list tree '())))


(def a (reduce #(adjoin-set %2 %1) '() (range 10)))

(def my-rnds (fn [] (take 10 (repeatedly #(rand-int 1000)))))

(repeat 20
        (let [r (reduce #(adjoin-set %2 %1) '() (my-rnds))]
          (= (tree->list-1 r )
             (tree->list-2 r ))))

;; (a) As far as I can tell, yes, the two tree->list functions do return the same.

;; (b) Yes. Here is a nice discussion.

