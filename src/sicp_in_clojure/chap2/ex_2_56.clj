(ns sicp-in-clojure.chap1.ex-2-56)

(defn third [c]
  (nth c 2))

(defn eq? [a b]
  (if (and  (seq? a) (seq? b))
    (and (eq? (first a) (first b))
	 (eq? (next a) (next b)))
    (= a b)))

(defn variable? [x]
  (symbol? x))

(defn pair? [c]
  (and (seq? c) (> (count c) 1)))

(defn same-variable? [v1 v2]
  (and (variable? v1) (variable? v2) (eq? v1 v2)))

(defn make-sum [a1 a2]
  (list '+ a1 a2))

(defn make-product [a1 a2]
  (list '* a1 a2))

(defn sum? [x]
  (and (pair? x) (eq? (first x) '+)))

(defn addend [s] (second s))

(defn augend [s] (third s))

(defn product? [x]
  (and (pair? x) (eq? (first x) '*)))

(defn multiplier [p] (second p))

(defn multiplicand [p] (third p))

(defn exponentiation? [x]
  (and (pair? x) (eq? (first x) '**)))

(defn base [p] (second p))

(defn exponent [p] (third p))

(defn make-exponentiation [b e]
  (list '** b e))

(defn deriv [exp var]
  (cond (number? exp) 0
	(variable? exp)
	(if (same-variable? exp var) 1 0)
	(sum? exp)
	(make-sum (deriv (addend exp) var)
		  (deriv (augend exp) var))
	(product? exp)
	(make-sum
	 (make-product (multiplier exp)
		       (deriv (multiplicand exp) var))
	 (make-product (deriv (multiplier exp) var)
		       (multiplicand exp)))
	(exponentiation? exp)
	(make-product
	 (exponent exp)
	 (make-exponentiation (base exp) (- (exponent exp) 1)))
	:else
	(println "unknown expression type -- DERIV" exp)))

(deriv '(* (* x y) (+ x 3)) 'x)
;;   (+ (* (* x y) (+ 1 0)) (* (+ (* x 0) (* 1 y)) (+ x 3)))

(deriv '(** x 3) 'x)
(* 3 (** x 2))

