(ns sicp-in-clojure.chap2.ex-2-73
  (:refer-clojure :exclude [get]))

(defn pair? [c]
  (and (seq? c) (> (count c) 1)))

;; Type operations

(defn attach-tag [type-tag contents]
  (cons type-tag contents))

(defn type-tag [datum]
  ( if (pair? datum)
    (first datum)
    (throw (Exception. (str "Bad tagged datum -- TYPE-TAG " datum)))))

(defn contents [datum]
  (if (pair? datum)
    (rest datum)
    (throw (Exception. (str "Bad tagged datum -- CONTENTS " datum)))))

;; Dynamic dispatch map

(def op-map (atom {}))

(defn put [op type item]
  (swap! op-map assoc (list op type) item))

(defn get [op type]
  (@op-map (list op type)))

;; Derivation stuff

(defn variable? [x]
  (symbol? x))

(defn eq? [a b]
  (if (and  (seq? a) (seq? b))
    (and (eq? (first a) (first b))
	 (eq? (next a) (next b)))
    (= a b)))

(defn same-variable? [v1 v2]
  (and (variable? v1) (variable? v2) (eq? v1 v2)))

(defn make-sum [a1 a2]
  (list '+ a1 a2))

(defn addend [s] (first s))

(defn augend [s] (first (rest s)))

(defn make-product [a1 a2]
  (list '* a1 a2))

(defn multiplier [p] (first p))

(defn multiplicand [p] (first (rest p)))

(defn base [p] (first p))

(defn exponent [p] (first (rest p)))

(defn make-exponentiation [b e]
  (list '** b e))

(declare deriv)

(defn install-sum-package []
  (let [drv (fn [exp var]
              (make-sum (deriv (addend exp) var)
                        (deriv (augend exp) var)))]
    (put 'deriv '+
         (fn [exp var] (drv exp var)))))

(defn install-product-package []
  (let [drv (fn [exp var]
              (make-sum
               (make-product (multiplier exp)
                             (deriv (multiplicand exp) var))
               (make-product (deriv (multiplier exp) var)
                             (multiplicand exp))))]
    (put 'deriv '*
         (fn [exp var] (drv exp var)))))

(defn install-exponent-package []
  (let [drv (fn [exp var]
              (make-product
               (exponent exp)
               (make-exponentiation (base exp) (- (exponent exp) 1))))]
    (put 'deriv '**
         (fn [exp var] (drv exp var)))))


;; Dynamic, runtime installation. Cool. Can even change them on the fly if you want.

(install-sum-package)

(install-product-package)

(install-exponent-package)

(defn operator [exp] (first exp))

(defn operands [exp] (rest exp))

(defn deriv [exp var]
  (cond (number? exp) 0
        (variable? exp)
        (if (same-variable? exp var) 1 0)
        :else ((get 'deriv (symbol (operator exp))) (operands exp)
               var)))

(deriv '(* (* x y) (+ x 3)) 'x)

;; Cool. Same answer as in 2.56
;; (+ (* (* x y) (+ 1 0)) (* (+ (* x 0) (* 1 y)) (+ x 3)));; 


(deriv '(** x 3) 'x)
;; (* 3 (** x 2))

;; a. We are doing dynamic dispatch keyed on the arithmetic operator. number?, variable?, and same-variable? are not dealing with arithmetic operators so we cannot dispatch to select them.

;; b. See above

;; c. See above

;; d. As long as we remain consistent in our indexing scheme, everything will work.