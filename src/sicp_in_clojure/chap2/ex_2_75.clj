(ns sicp-in-clojure.chap2.ex-2-75
  (:use [clojure.repl]))

(defn sqrt[x]
  (java.lang.Math/sqrt x))

(defn square [x]
  (java.lang.Math/pow x  2))

(defn sin [x]
  (java.lang.Math/sin x))

(defn cos [x]
  (java.lang.Math/cos x))

(defn atan [x y]
  (java.lang.Math/atan2 x y))

(defn make-from-real-image [x y]
  (let [dispatch
        (fn [op]
          (cond
           (= op 'real-part) x
           (= op 'imag-part) y
           (= op 'magnitude) (sqrt (+ (square x) (square y)))
           (= op 'angle) (atan y x)
           :else  (throw ( Exception. (str "Unknown op -- MAKE-FROM-REAL-IMAG " op)))))]
    dispatch))

(defn make-from-mag-ang [r a]
  (let [dispatch
        (fn [op]
          (cond
           (= op 'real-part) (* r (cos a))
           (= op 'imag-part) (* r (sin a))
           (= op 'magnitude) r
           (= op 'angle) a
    :else  (throw ( Exception. (str "Unknown op -- MAKE-FROM-MAG-ANG " op)))))]
    dispatch))

(defn apply-generic [op arg] (arg op))

(defn real-part [z] (apply-generic 'real-part z))

(defn imag-part [z] (apply-generic 'imag-part z))

(defn magnitude [z] (apply-generic 'magnitude z))

(defn angle [z] (apply-generic 'angle z))
