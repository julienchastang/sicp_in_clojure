(ns sicp-in-clojure.chap2.ex-2-64
  (:use [sicp-in-clojure.chap2.ex-2-62]
        [sicp-in-clojure.chap2.ex-2-63]))

(defn partial-tree [elts n]
  (if (= n 0)
    (cons '() elts)
    (let [left-size (quot (- n 1) 2)]
      (let [left-result (partial-tree elts left-size)]
        (let [left-tree (first left-result)
              non-left-elts (rest left-result)
              right-size (- n (+ left-size 1))]
          (let [this-entry (first non-left-elts)
                right-result (partial-tree (rest non-left-elts)
                                           right-size)]
            (let [right-tree (first right-result)
                  remaining-elts (next right-result)]
              (cons (make-tree this-entry left-tree right-tree)
                    remaining-elts))))))))

(defn list->tree [elements]
  (first (partial-tree elements (count elements))))

(list->tree (range 1 12 2))


;; (5 (1 () (3 () ())) (9 (7 () ()) (11 () ())))

;; (a)

;; (b) Since the list is already ordered, hopefully it simply takes time O(n)


;; numbers --> reduce --> tree->list2 --> union  