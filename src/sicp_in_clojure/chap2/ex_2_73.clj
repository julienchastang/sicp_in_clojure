(ns sicp-in-clojure.chap2.ex-2-73)

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

(defn attach-tag [type-tag contents]
  (cons type-tag contents))

(defn type-tag [datum]
  ( if (pair? datum)
    (first datum)
    (throw (Exception. "Bad tagged datum -- TYPE-TAG" datum))))

(defn contents [datum]
  (if (pair? datum)
    (first (rest datum))
    (throw (Exception. "Bad tagged datum -- CONTENTS" datum))))

(defn rectangular? [z]
  (= (type-tag z) 'rectangular))

(defn polar? [z]
  (= (type-tag z) 'polar))

;; Ben version
(defn real-part-rectangular [z]
  (first z))

(defn imag-part-rectangular [z]
  (first (rest z)))

(defn magnitude-rectangular [z]
  (sqrt (+ (square (real-part-rectangular z))
           (square (imag-part-rectangular z)))))

(defn angle-rectangular [z]
  (atan (imag-part-rectangular z)
        (real-part-rectangular z)))

(defn make-from-real-imag-rectangular [x y]
  (attach-tag 'rectangular
              (cons x (list y))))

(defn make-from-mag-ang-rectangular [r a]
  (attach-tag 'rectangular
              (cons (* r (cos a)) (list (* r (sin a))))))

;; Alyssa Version

(defn magnitude-polar [z]
  (first z))

(defn angle-polar [z]
  (first (rest z)))

(defn real-part-polar [z]
  (* (magnitude-polar z) (cos (angle-polar z))))

(defn imag-part-polar [z]
  (* (magnitude-polar z) (sin (angle-polar z))))

(defn make-from-real-imag-polar [x y]
  (attach-tag 'polar
              (cons (sqrt (+ (square x) (square y))) (list (atan y x)))))

(defn make-from-mag-ang-polar [r a]
  (attach-tag 'polar
              (cons r (list a))))


;; Generic version

(defn real-part [z]
  (cond (rectangular? z)
        (real-part-rectangular (contents z))
        (polar? z)
        (real-part-polar (contents z))
        :else (throw (Exception. "Unknown type -- REAL-PART" z))))
(defn imag-part [z]
  (cond (rectangular? z)
        (imag-part-rectangular (contents z))
        (polar? z)
        (imag-part-polar (contents z))
        :else (throw (Exception. "Unknown type -- IMAG-PART" z))))
(defn magnitude [z]
  (cond (rectangular? z)
        (magnitude-rectangular (contents z))
        (polar? z)
        (magnitude-polar (contents z))
        :else (throw (Exception. "Unknown type -- MAGNITUDE" z))))
(defn angle [z]
  (cond (rectangular? z)
        (angle-rectangular (contents z))
        (polar? z)
        (angle-polar (contents z))
        :else (throw (Exception. "Unknown type -- ANGLE" z))))

(defn make-from-real-imag [x y]
  (make-from-real-imag-rectangular x y))

(defn make-from-mag-ang [r a]
    (make-from-mag-ang-polar r a))

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

