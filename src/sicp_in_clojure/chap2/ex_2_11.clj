(ns sicp-in-clojure.chap1.ex-2-11
  (:use clojure.contrib.math))

;Figuring out 9 cases
(defn print-binary-helper[n c]
  (if (= n 0) c
      (recur (Math/floor (/ n 2))
	     (cons (int (mod n 2)) c))))

(defn zero-pad[n c]
  (if (= n 0) c
      (recur (dec n) (cons 0 c))))

(defn print-binary[n]
  (let [b (print-binary-helper n '())]
    (println (zero-pad (- 4 (count b)) b))))

(def a (map #(print-binary %) (range 0 15)))


(defn lower-bound[x] (apply min x))

(defn upper-bound[x] (apply max x))

(defn make-interval [a b] (list a b))

(defn mul-interval [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
	p2 (* (lower-bound x) (upper-bound y))
	p3 (* (upper-bound x) (lower-bound y))
	p4 (* (upper-bound x) (upper-bound y))]
    (list (min p1 p2 p3 p4)
	  (max p1 p2 p3 p4))))

; (0 0 0 0) Case 1
; (1 1 1 1)
; (* lbx lby) (* ubx uby)
(mul-interval (make-interval -4 -3) (make-interval -2 -1))

; (0 0 0 1) Case 2
; (0 0 1 0)
; (* lbx uby) (* lbx lby)
(mul-interval (make-interval -4 -3) (make-interval -1 2))

; (0 1 0 0) Case 3
; (1 0 0 0) (* ubx uby) (* lbx uby)
(mul-interval (make-interval -4 3) (make-interval -1 -2))

; (0 0 1 1) Case 4 (* lbx uby) (* ubx lby)
(mul-interval (make-interval -4 -3) (make-interval 1 2))

; (0 1 0 1) Case 5
; (0 1 1 0)
; (1 0 0 1) 
; (1 0 1 0)
(mul-interval (make-interval -4 3) (make-interval -1 2))


; (0 1 1 1) Case 6
; (1 0 1 1)


; (1 1 0 0) Case 8

; (1 1 0 1) Case 9
; (1 1 1 0)

(defn lower-bound[x] (apply min x))

(defn upper-bound[x] (apply max x))

(defn make-interval [a b] (list a b))

(defn mul-interval [x y]
  (let [lbx (lower-bound x)
	ubx (upper-bound x)
	lby (lower-bound y)
	uby (upper-bound y)
	bits (list (> lbx 0) (> ubx 0) (> lby 0) (> uby 0))] bits))

(mul-interval (make-interval -4 3) (make-interval -1 2))

