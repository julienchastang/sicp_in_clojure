(ns sicp-in-clojure.chap2.ex-2-57)

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

(defn make-sum [& a]
  (apply list '+ a))

(defn make-product [& a]
  (apply list '* a))

(defn sum? [x]
  (and (pair? x) (eq? (first x) '+)))

(defn addend [s] (second s))

(defn augend [s] (if (> (count s) 3)
		   (cons '+ (nnext s))
		   (third s)))

(defn product? [x]
  (and (pair? x) (eq? (first x) '*)))

(defn multiplier [p] (second p))

(defn multiplicand [p] (if (> (count p) 3)
			 (cons '* (nnext p))
			 (third p))) 

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

(deriv '(* x y (+ x 3)) 'x)

;; (+ (* x (+ (* y (+ 1 0)) (* 0 (+ x 3)))) (* 1 (* y (+ x 3))))

