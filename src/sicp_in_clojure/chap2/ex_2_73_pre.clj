(ns sicp-in-clojure.chap2.ex-2-73-pre
  (:refer-clojure :exclude [get]))

;; Basic math

(defn sqrt[x]
  (java.lang.Math/sqrt x))

(defn square [x]
  (java.lang.Math/pow x  2))

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

;; Packages

(defn install-rectangular-package []
  (let [real-part (fn [z] (first z))
        imag-part (fn [z] (first (rest z)))
        make-from-real-imag (fn [x y] (cons x (list y)))
        make-from-mag-ang (fn [r a] (cons (* r (cos a)) (list (* r (sin a)))))
        magnitude (fn [z] (sqrt (+ (square (real-part z))
                                  (square (imag-part z)))))
        angle (fn [z] (atan (imag-part z)
                           (real-part z)))
        tag (fn [x] (attach-tag 'rectangular x))]
    (put 'real-part '(rectangular) real-part)
    (put 'imag-part '(rectangular) imag-part)
    (put 'magnitude '(rectangular) magnitude)
    (put 'angle '(rectangular) angle)
    (put 'make-from-real-imag 'rectangular
         (fn [x y] (tag (make-from-real-imag x y))))
    (put 'make-from-mag-ang 'rectangular
         (fn [r a] (tag (make-from-mag-ang r a))))))

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

(install-rectangular-package)

(install-polar-package)

(defn apply-generic [op & args]
  (let [type-tags (map type-tag args)]
    (let [proc (get op type-tags)]
      (if proc
        (apply proc (map contents args))
        (throw (Exception.
                (str "No method for these types -- APPLY-GENERIC " op type-tags)))))))

(defn real-part [z] (apply-generic 'real-part z))

(defn imag-part [z] (apply-generic 'imag-part z))

(defn magnitude [z] (apply-generic 'magnitude z))

(defn angle [z] (apply-generic 'angle z))

(defn make-from-real-imag [x y]
  ((get 'make-from-real-imag 'rectangular) x y))

(defn make-from-mag-ang [r a]
  ((get 'make-from-real-imag 'polar) r a))

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

