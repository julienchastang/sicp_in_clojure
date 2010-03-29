(ns sicp-in-clojure.chap1.ex-1-3)

(defn square[x] (* x x))

(defn sum-of-square[x y] (+ (square x) (square y)))

(defn square-biggest-two [a b c]
  (let [x (sum-of-square a b)
        y (sum-of-square a c)
        z (sum-of-square b c)]
    (cond (and (> x y) (> x z)) x
      (and (> y x) (> y z)) y
      :else z
      )))

(square-biggest-two 1 2 3)