(ns sicp-in-clojure.chap1.ex-1-9)

(defn my-plus[a b]
  (if (= a 0) b (inc (my-plus (dec a) b))))

(my-plus 4 5)
(if (= 4 0) 5 (inc (my-plus (dec 4) 5)))
(if (= 4 0) 5 (inc (my-plus 3 5))) ;etc.
;recursive

(defn my-plus[a b]
  (if (= a 0) b (my-plus (dec a) (inc b))))

(my-plus 4 5)
(if (= 4 0) 5 (my-plus (dec 4) (inc 5)))
(if (= 4 0) 5 (my-plus 3 6)) ;etc.
;tail-recursive therefore iterative
