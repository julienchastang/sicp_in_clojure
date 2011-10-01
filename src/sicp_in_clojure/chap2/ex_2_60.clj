(ns sicp-in-clojure.chap2.ex-2-60)

(defn element-of-set? [x set]
  (cond (nil? set) false
	(= x (first set)) true
	:else (element-of-set? x (next set))))

(defn adjoin-set [x set]
  (cons x set))

(defn intersection-set [set1 set2]
  (cond (or (nil? set1) (nil? set2)) '()
	(element-of-set? (first set1) set2)
	(cons (first set1)
	      (intersection-set (next set1) set2)) 
	:else (intersection-set (next set1) set2)))

(defn union-set [set1 set2]
  (cond (and (nil? set1) (nil? set2)) nil
	(nil? set2) set1
	(nil? set1) set2
	:else (union-set (next set1)
			 (adjoin-set (first set1) set2))))


(def a (reduce #(adjoin-set %2 %1) '() (concat (range 3 7) (range 5))))

(def b (reduce #(adjoin-set %2 %1) '() (concat (range 5 10) (range 7))))


(intersection-set a b)

(union-set a b)

;; adjoin-set is now constant time since it no longer depends on element-of-set?

;; intersection-set stays O(n^2)

;; union-set is time o(n)
