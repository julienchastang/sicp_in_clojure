(ns sicp-in-clojure.chap1.ex-1-3)

(defn square[x] (* x x))

(defn sum-of-squares[x y] (+ (square x) (square y)))

(defn square-biggest-two [a b c]
    (cond (> a b c) (sum-of-squares a b)
	  (> b c a) (sum-of-squares b c)
	  (> c a b) (sum-of-squares c a)
	  (> c b a) (sum-of-squares c b)
	  (> b a c) (sum-of-squares b a)
	  (> a c b) (sum-of-squares a c)))

(square-biggest-two 1 2 3)  ;13
(square-biggest-two 1 2 -3 );5