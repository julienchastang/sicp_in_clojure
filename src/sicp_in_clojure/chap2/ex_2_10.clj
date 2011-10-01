 (ns sicp-in-clojure.chap2.ex-2-10
  (:use clojure.contrib.math))

(defn lower-bound[x] (apply min x))

(defn upper-bound[x] (apply max x))

(defn make-interval [a b] (list a b))

(defn mul-interval [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
	p2 (* (lower-bound x) (upper-bound y))
	p3 (* (upper-bound x) (lower-bound y))
	p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4)
		   (max p1 p2 p3 p4))))

(defn div-interval [x y]
  (if (= (lower-bound y) (upper-bound y))
    "Division by zero interval"
    (mul-interval x
		  (make-interval (/ 1.0 (upper-bound y))
				 (/ 1.0 (lower-bound y))))))

(div-interval (make-interval 2 3) (make-interval 5 5))
