(ns sicp-in-clojure.chap1.ex-2-54)



(defn eq? [a b]
  (if (and  (seq? a) (seq? b))
    (and (eq? (first a) (first b))
	 (eq? (next a) (next b)))
    (= a b)))


;; Papa 845 803 5510