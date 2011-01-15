(ns Chap1-3
  [:use clojure.contrib.import-static
   [clojure.contrib.test-is :only [deftest is run-tests]]]) 

(defn close-enough [x y] (< (abs (- x y)) 1.0e-5))

(defn abs[x] (Math/abs x))

(defn iterativeImprove [goodEnough improve]
  (fn [y]
    (if (goodEnough y)
      y
      (recur (improve y)))))

(defn my-sqrt [x]
  (let [nuf (fn [[y y-old]] (<= (abs (- y y-old)) (/ y 10000.0)))
	imp (fn [[y _ ]] [ (/ (+ y (/ x y)) 2) y ])
	]
    (comp first (iterativeImprove nuf imp))))

(deftest test-iterativeImprove
  (is (close-enough ((my-sqrt 144) [1.0 10.0]) 12.0)))
