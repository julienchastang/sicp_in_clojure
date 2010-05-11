(ns sicp-in-clojure.chap1.ex-2-14
  (:use clojure.contrib.math))

(defn lower-bound[x] (apply min x))

(defn upper-bound[x] (apply max x))

(defn make-interval [a b] (list a b))

(defn make-center-width[c w]
    (make-interval (- c w) (+ c w)))

(defn make-center-percent[c p]
  (let [w (* c (/ p 100))]
    (make-center-width c w)))

(defn center[i]
    (/ (+ (lower-bound i) (upper-bound i)) 2))

(defn width[i]
    (/ (- (upper-bound i) (lower-bound i)) 2))

(defn percent[i]
  (let [c (center i)
	w (width i)]
    (* (/ w c) 100)))

(defn add-interval [x y]
  (make-interval (+ (lower-bound x) (lower-bound y))
  		 (+ (upper-bound x) (upper-bound y))))

(defn sub-interval [x y]
  (make-interval (- (upper-bound x) (lower-bound y))
  		 (- (lower-bound x) (upper-bound y))))

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

(defn par1[r1 r2]
  (div-interval (mul-interval r1 r2)
		(add-interval r1 r2)))

(defn par2[r1 r2]
  (let [one (make-interval 1 1)]
    (div-interval one
		  (add-interval (div-interval one r1)
				(div-interval one r2)))))

(def r1 (make-center-percent 5 10.0))

(def r2 (make-center-percent 6 10.0))

(def one (make-interval 1 1))

(par1 r1 r2) ; (2.0082644628099175 3.6666666666666665)

(par2 r1 r2) ; (2.4545454545454546 2.9999999999999996)

; Lem is right

; Experimentation shows that smaller percentages lead to closer answers between par1 and par2.

; par1 and par2 are algebraically equivalent. But we are doing interval math here where
; sucessive operations introduce additional uncetainty. One way to look at it is that
; par2 uses more operations therefore introduces additional uncertainty.

; If we have a small uncertainty percentage, then we get close to a point rather than an
; interval so par1 and par2 yield similar results as if we were operating on points rather
; than intervals.