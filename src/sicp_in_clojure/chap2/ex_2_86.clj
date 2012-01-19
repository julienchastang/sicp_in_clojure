(ns sicp-in-clojure.chap2.ex-2-85
  (:refer-clojure :exclude [get drop]))

;; Very useful debugging macro

(defmacro ? [x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

;; Basic math

(defn gcd[a b]
  (if (zero? b)
    a
    (recur b (mod a b))))

(defn sqrt[x]
  (java.lang.Math/sqrt x))

(defn square [x]
  (java.lang.Math/pow x 2))

(declare mul)

(defn square [x]
  (mul x x))

(defn atan [x y]
  (java.lang.Math/atan2 x y))

(defn sin [x]
  (java.lang.Math/sin x))

(defn cos [x]
  (java.lang.Math/cos x))

(defn pair? [c]
  (and (seq? c) (> (count c) 1)))

;; Type operations

(defn attach-tag [type-tag contents]
  (cons type-tag contents))

(defn type-tag [datum]
  (cond
   (number? (? datum)) 'scheme-number
   (pair? datum) (first datum)
   :else (throw (Exception. (str "Bad tagged datum -- TYPE-TAG " datum)))))

(defn contents [datum]
  (cond
   (number? datum) datum
   (pair? datum) (rest datum)
   :else (throw (Exception. (str "Bad tagged datum -- CONTENTS " datum)))))

;; Dynamic dispatch map

(def op-map (atom {}))

(defn put [op type item]
  (swap! op-map assoc (list op type) item))

(defn get [op type]
  (@op-map (list op type)))


;; Coercion

(def coerc-map (atom {}))

(defn put-coercion  [type1 type2 f]
  (swap! coerc-map assoc (list type1 type2) f))

(defn get-coercion [type1 type2]
  (if (= type1 type2)
    identity
    (@coerc-map (list type1 type2))))

;; The magical dynamic dispatch

(declare succ-raise)

(declare drop)

(defn apply-generic-helper [op & args]
  (let [type-tags (map type-tag args)
        proc (get op type-tags)]
    (if proc
      (apply proc (map contents args))
      (let [[a1 a2] (succ-raise (first args) (fnext args))]
        (apply-generic-helper op a1 a2)))))

(defn apply-generic [op & args]
  (if (> (count args) 1)
    (let [f (fn [arg1 arg2]
              (let [args (list arg1 arg2)]
                (apply apply-generic-helper op args)))]
      (reduce f args))
    (apply apply-generic-helper op args)))

(defn apply-generic-num [op & args]
  (drop (apply apply-generic op args)))

;; Packages

;; Note below that make-from-real-image and make-from-mag-ang have to be "re-tagged" so that there is
;; no ambiguity about the coordinate system of the values.

(declare add)

(defn install-rectangular-package []
  (let [real-part (fn [z] (first z))
        imag-part (fn [z] (first (rest z)))
        make-from-real-imag (fn [x y] (cons x (list y)))
        make-from-mag-ang (fn [r a] (cons (* r (cos a)) (list (* r (sin a)))))
        magnitude (fn [z] (sqrt (add (square (real-part z))
                                  (square (imag-part z)))))
        angle (fn [z] (atan (mul 1.0 (imag-part z))
                           (mul 1.0 (real-part z))))
        tag (fn [x] (attach-tag 'rectangular x))]
    (put 'real-part '(rectangular) real-part)
    (put 'imag-part '(rectangular) imag-part)
    (put 'magnitude '(rectangular) magnitude)
    (put 'angle '(rectangular) angle)
    (put 'make-from-real-imag 'rectangular
         (fn [x y] (tag (make-from-real-imag x y))))
    (put 'make-from-mag-ang 'rectangular
         (fn [r a] (tag (make-from-mag-ang r a))))))

(install-rectangular-package)

(defn real-part [z] (apply-generic 'real-part z))

(defn imag-part [z] (apply-generic 'imag-part z))

(defn magnitude [z] (apply-generic 'magnitude z))

(defn angle [z] (apply-generic 'angle z))

(defn make-from-real-imag [x y]
  ((get 'make-from-real-imag 'rectangular) x y))


;;;; Polar

(defn install-polar-package []
  (let [magnitude (fn [z] (first z))
        angle (fn [z] (first (rest z)))
        make-from-mag-ang (fn [r a] (cons r (list a)))
        real-part (fn [z] (* (magnitude z) (cos (angle z))))
        imag-part (fn [z] (* (magnitude z) (sin (angle z))))
        make-from-real-imag (fn [x y]
                                    (cons (sqrt (+ (square x) (square y))) (list (atan y x))))
        tag (fn [x] (attach-tag 'polar x))]
    (put 'real-part '(polar) real-part)
    (put 'imag-part '(polar) imag-part)
    (put 'magnitude '(polar) magnitude)
    (put 'angle '(polar) angle)
    (put 'make-from-real-imag 'polar
         (fn [x y] (tag (make-from-real-imag x y))))
    (put 'make-from-mag-ang 'polar
         (fn [r a] (tag (make-from-mag-ang r a))))))

(install-polar-package)

(defn make-from-mag-ang [r a]
  ((get 'make-from-real-imag 'polar) r a))

;; Scheme number package

(declare make-rational)

(defn install-scheme-number-package []
  (put 'add '(scheme-number scheme-number)
       (fn [x y] (+ x y)))
  (put 'sub '(scheme-number scheme-number)
       (fn [x y] (- x y)))
  (put 'mul '(scheme-number scheme-number)
       (fn [x y] (* x y)))
  (put 'div '(scheme-number scheme-number)
       (fn [x y] (/ x y)))
  (put 'exp '(scheme-number scheme-number)
       (fn [x y] (java.lang.Math/pow x y)))
  (put 'equ? '(scheme-number scheme-number)
       (fn [x y] (= x y)))
  (put '=zero? '(scheme-number)
       (fn [x] (= x 0)))
  (put 'sine '(scheme-number)
       (fn [x] (sin x)))
  (put 'cosine '(scheme-number)
       (fn [x] (cos x)))
  (put '=zero? '(scheme-number)
       (fn [x] (= x 0)))
  (put 'project '(scheme-number)
       (fn [x] (make-rational (int x) 1))))

(install-scheme-number-package)

(defn add [x y] (apply-generic-num 'add x y))

(defn sub [x y] (apply-generic-num 'sub x y))

(defn mul [x y] (apply-generic-num 'mul x y))

(defn div [x y] (apply-generic-num 'div x y))

(defn exp [x y] (apply-generic-num 'exp x y))

(defn equ? [x y] (apply-generic 'equ? x y))

(defn =zero? [x] (apply-generic '=zero? x))

(defn sine [x] (apply-generic 'sine x))

(defn cosine [x] (apply-generic 'cosine x))

(defn project [x]  (apply-generic 'project x))

;; Rational number package

(defn install-rational-package []
  (let [numer (fn [x]  (first x))
        denom (fn [x] (fnext x))
        make-rat (fn [n d]
                   (let [g (gcd n d)]
                     (if (zero? g)
                       '(0 0)
                       (cons (/ n g) (list (/ d g))))))
        add-rat (fn [x y] (make-rat (+ (* (numer x) (denom y))
                                      (* (numer y) (denom x))) (* (denom x) (denom y))))
        sub-rat (fn [x y] (make-rat (- (* (numer x) (denom y))
                                      (* (numer y) (denom x))) (* (denom x) (denom y))))
        mul-rat (fn [x y] (make-rat (* (numer x) (numer y))
                                   (* (denom x) (denom y))))
        div-rat (fn [x y]
                  (make-rat (* (numer x) (denom y)) (* (denom x) (numer y))))
        equ-rat? (fn [x y]
                   (and (equ? (numer x) (numer y)) (equ? (denom x) (denom y))))
        =zero-rat? (fn [x]
                     (equ-rat? x (make-rat 0 0)))
        project (fn [x] (numer x))
        tag (fn [x] (attach-tag 'rational x))]
    (put 'add '(rational rational)
         (fn [ x y] (tag (add-rat x y))))
    (put 'sub '(rational rational)
         (fn [x y] (tag (sub-rat x y))))
    (put 'mul '(rational rational)
         (fn [x y] (tag (mul-rat x y))))
    (put 'div '(rational rational)
         (fn [ x y] (tag (div-rat x y))))
    (put 'equ? '(rational rational)
         (fn [ x y] (equ-rat? x y)))
    (put 'sine '(rational)
         (fn [x] (sin (/ (numer x) (denom x)))))
    (put 'cosine '(rational)
         (fn [x] (cos (/ (numer x) (denom x)))))
    (put '=zero? '(rational)
         (fn [x] (=zero-rat? x)))
    (put 'project '(rational)
         (fn [x] (project x)))
    (put 'make 'rational
         (fn [n d] (tag (make-rat n d))))))

(install-rational-package)

(defn make-rational [n d]
  ((get 'make 'rational) n d))

(defn install-complex-package []
  (let [make-from-real-imag (fn [x y] ((get 'make-from-real-imag 'rectangular) x y))
        make-from-mag-ang (fn [r a] ((get 'make-from-mag-ang 'polar) r a))
        add-complex (fn [z1 z2] (make-from-real-imag (add (real-part z1) (real-part z2))
                                                     (add (imag-part z1) (imag-part z2))))
        sub-complex (fn [z1 z2]
                      (make-from-real-imag (sub (real-part z1)  (real-part z2))
                                           (sub (imag-part z1)  (imag-part z2))))
        mul-complex (fn [z1 z2] (make-from-mag-ang (mul (magnitude z1) (magnitude z2))
                                                  (add (angle z1) (angle z2))))
        div-complex (fn [z1 z2]
                      (make-from-mag-ang (div (magnitude z1) (magnitude z2)) (sub (angle z1) (angle z2))))
        equ-complex? (fn [z1 z2]
                       (and (equ? (magnitude z1) (magnitude z2)) (equ? (angle z1) (angle z2))))
        =zero-complex? (fn [z]
                         (equ-complex? z (attach-tag 'complex (make-from-real-imag 0 0))))
        project (fn [z] (real-part z))
        tag (fn [z] (attach-tag 'complex z))]
    (put 'add '(complex complex)
         (fn [z1 z2] (tag (add-complex z1 z2))))
    (put 'sub '(complex complex)
         (fn [z1 z2] (tag (sub-complex z1 z2))))
    (put 'mul '(complex complex)
         (fn [z1 z2] (tag (mul-complex z1 z2))))
    (put 'div '(complex complex)
         (fn [z1 z2] (tag (div-complex z1 z2))))
    (put 'equ?  '(complex complex)
         (fn [z1 z2] (equ-complex? z1 z2)))
    (put '=zero?  '(complex)
         (fn [z] (=zero-complex? z)))
    (put 'project '(complex)
         (fn [z] (project z)))
    (put 'make-from-real-imag 'complex
         (fn [x y] (tag (make-from-real-imag x y))))
    (put 'make-from-mag-ang 'complex
         (fn [r a] (tag (make-from-mag-ang r a))))
    (put 'real-part '(complex) real-part)
    (put 'imag-part '(complex) imag-part)
    (put 'magnitude '(complex) magnitude)
    (put 'angle '(complex) angle)))

(install-complex-package)

(defn add-complex [z1 z2]
  (make-from-real-imag (+ (real-part z1) (real-part z2))
                       (+ (imag-part z1) (imag-part z2))))
(defn sub-complex [z1 z2]
  (make-from-real-imag (- (real-part z1) (real-part z2))
                       (- (imag-part z1) (imag-part z2))))
(defn mul-complex [z1 z2]
  (make-from-mag-ang (* (magnitude z1) (magnitude z2))
                     (+ (angle z1) (angle z2))))

(defn div-complex [z1 z2]
  (make-from-mag-ang (/ (magnitude z1) (magnitude z2))
                     (- (angle z1) (angle z2))))

(defn make-complex-from-real-imag [x y] ((get 'make-from-real-imag 'complex) x y))

(defn make-complex-from-mag-ang [r a] ((get 'make-from-mag-ang 'complex) r a))

;; Coercing types

(defn scheme-number->complex [n]
  (make-complex-from-real-imag (contents n) 0))

(put-coercion 'scheme-number 'complex scheme-number->complex)

;; Building tower

(def tower  ['rational 'scheme-number 'complex])

(defn raise [n]
  (let [f {'rational (fn [n]
                       (let [[d n] (contents n)]
                         (* 1.0 (/ d n))))
           'scheme-number (fn [n]
                            (make-complex-from-real-imag n 0))}]
    ((f (type-tag n)) n)))

(defn succ-raise [n1 n2]
  (let [idx #(.indexOf tower (type-tag %))
        a (idx n1)
        b (idx n2)
        f (fn [x num]
            (let [n (atom x)
                  i (idx num)]
              (while (not= (idx @n) i)
                (swap! n raise))
              @n))]
    (if (< a b)
      (list (f n1 n2) n2)
      (list n1 (f n2 n1)))))

(defn drop [num]
  (if (and  (not= (type-tag num) 'scheme-number)
            (equ? num (raise (project num))))
    (drop (project num))
    num))
